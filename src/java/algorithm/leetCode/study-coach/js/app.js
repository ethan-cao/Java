/**
 * Study Coach UI controller.
 *
 * The app selects and tracks work. It does not open Java files, inspect
 * solutions, run tests, or submit to LeetCode.
 */
(function () {
  "use strict";

  const catalog = window.STUDY_CATALOG;
  const markdownLog = window.StudyCoachMarkdownLog;
  const progress = window.StudyCoachProgress;
  const scheduler = window.StudyCoachScheduler;

  const EASY_MEDIUM_STAGES = [
    {
      label: "Think",
      minutes: 3,
      instruction: "Read only the clean question, constraints, and signature. Identify the pattern trigger and invariant.",
    },
    {
      label: "Try",
      minutes: 5,
      instruction: "Attempt independently. Sketch the solution and begin implementing without hints.",
    },
    {
      label: "Code",
      minutes: 5,
      instruction: "Continue or finish independently. Stop when this stage ends.",
    },
    {
      label: "Finish / check",
      minutes: 2,
      instruction: "If successful, log the result. If not, mark result, check the core answer, and log the next review.",
    },
  ];

  const HARD_STAGES = [
    {
      label: "Select and plan",
      minutes: 4,
      instruction: "Read only the clean question and plan the approach, invariant, and complexity.",
    },
    {
      label: "Cold implementation",
      minutes: 30,
      instruction: "Implement independently. This Hard attempt has a 30-minute ceiling.",
    },
    {
      label: "Test and explain",
      minutes: 4,
      instruction: "Check key boundaries and state the actual time and space complexity.",
    },
    {
      label: "Compare analysis",
      minutes: 5,
      instruction: "Only now compare with # Analysis and identify the exact gaps.",
    },
    {
      label: "Log and schedule",
      minutes: 2,
      instruction: "Record the result and next review date.",
    },
  ];

  const state = {
    log: null,
    saved: false,
    view: "today",
    currentDate: "",
    dailyPlan: null,
    currentQuestionIndex: 0,
    manualChoice: null,
    activeQuestionId: null,
    questionShownKey: null,
    questionShownAt: null,
    timer: {
      running: false,
      stageIndex: 0,
      stageStartedAt: null,
      attemptStartedAt: null,
      intervalId: null,
    },
    resultTimestamp: null,
    editingAttemptIndex: null,
    catalogSearch: "",
    logSearch: "",
    logFilter: "all",
  };

  const elements = {};

  function cacheElements() {
    const ids = [
      "saved-status",
      "date-status",
      "block-status",
      "today-summary",
      "today-plan",
      "attempt-panel",
      "attempt-problem",
      "attempt-reason",
      "attempt-type-badge",
      "timer-panel",
      "timer-stage",
      "timer-remaining",
      "timer-instruction",
      "start-attempt-button",
      "next-stage-button",
      "open-result-button",
      "snooze-button",
      "result-form",
      "result-problem",
      "result-timestamp",
      "result-attempt-type",
      "result-mark",
      "result-minutes",
      "result-failure-type",
      "result-next-review",
      "result-notes",
      "today-complete",
      "complete-export-button",
      "advance-block-button",
      "log-search",
      "log-filter",
      "log-table-body",
      "edit-panel",
      "edit-attempt-form",
      "edit-problem",
      "edit-timestamp",
      "edit-attempt-type",
      "edit-mark",
      "edit-minutes",
      "edit-failure-type",
      "edit-next-review",
      "edit-notes",
      "cancel-edit-button",
      "progress-cards",
      "state-count-table",
      "failure-count-table",
      "roadmap-progress",
      "catalog-search",
      "catalog-table-body",
      "load-log-button",
      "export-log-button",
      "new-log-button",
      "log-file-input",
      "file-errors",
      "unfamiliar-form",
      "unfamiliar-id",
      "unfamiliar-title",
      "unfamiliar-difficulty",
      "unfamiliar-family",
      "manual-choice-form",
      "manual-problem-select",
      "manual-attempt-type",
    ];

    for (const id of ids) {
      elements[id] = document.getElementById(id);
    }
  }

  function escapeHtml(value) {
    return String(value ?? "")
      .replace(/&/g, "&amp;")
      .replace(/</g, "&lt;")
      .replace(/>/g, "&gt;")
      .replace(/"/g, "&quot;")
      .replace(/'/g, "&#39;");
  }

  function localDateString(date = new Date()) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  }

  function localTimestampString(date = new Date()) {
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    return `${localDateString(date)}T${hours}:${minutes}`;
  }

  function markUnsaved() {
    state.saved = false;
    renderHeader();
  }

  function markSaved() {
    state.saved = true;
    renderHeader();
  }

  function combinedCatalog() {
    return scheduler.buildCombinedCatalog(catalog, state.log.unfamiliar);
  }

  function currentQuestion() {
    if (!state.dailyPlan) return null;
    return state.dailyPlan.questions[state.currentQuestionIndex] || null;
  }

  function rebuildDailyPlan(resetIndex = true, excludeIds = []) {
    const previous = state.dailyPlan;
    if (resetIndex) {
      state.currentQuestionIndex = 0;
    }

    const plan = scheduler.buildDailyPlan({
      dateString: state.currentDate,
      catalog,
      attempts: state.log.attempts,
      metadata: state.log.metadata,
      unfamiliar: state.log.unfamiliar,
      manualChoice: state.manualChoice,
    });

    state.dailyPlan = resetIndex || !previous ? plan : refillOpenSlots(previous, plan, excludeIds);
    state.manualChoice = null;

    if (state.currentQuestionIndex >= state.dailyPlan.questions.length) {
      state.currentQuestionIndex = state.dailyPlan.questions.length;
    }
  }

  function refillOpenSlots(previous, plan, excludeIds) {
    const completed = previous.questions.slice(0, state.currentQuestionIndex);
    const blocked = new Set(
      [...completed.map((question) => question.problemId), ...excludeIds].filter(Boolean)
    );
    const openSlots = previous.questions.length - completed.length;
    const refill = plan.questions
      .filter((question) => question.problemId && !blocked.has(question.problemId))
      .slice(0, openSlots);
    while (refill.length < openSlots) {
      refill.push({
        problemId: null,
        reason: "No other eligible Easy/Medium question is available. Add an unfamiliar question or choose manually.",
      });
    }
    return { ...plan, questions: [...completed, ...refill] };
  }

  function resetTimer() {
    if (state.timer.intervalId) {
      clearInterval(state.timer.intervalId);
    }
    state.timer = {
      running: false,
      stageIndex: 0,
      stageStartedAt: null,
      attemptStartedAt: null,
      intervalId: null,
    };
  }

  function stagesForQuestion(question) {
    return question && question.isHard ? HARD_STAGES : EASY_MEDIUM_STAGES;
  }

  function startAttempt() {
    const question = currentQuestion();
    if (!question || !question.problemId) return;

    state.activeQuestionId = question.problemId;
    resetTimer();
    state.timer.running = true;
    state.timer.stageIndex = 0;
    state.timer.stageStartedAt = Date.now();
    state.timer.attemptStartedAt = Date.now();
    state.timer.intervalId = setInterval(tickTimer, 250);
    elements["result-form"].classList.add("hidden");
    renderToday();
  }

  function goToStage(index) {
    const question = currentQuestion();
    if (!question) return;
    const stages = stagesForQuestion(question);
    if (index < 0 || index >= stages.length) return;

    state.timer.stageIndex = index;
    state.timer.stageStartedAt = Date.now();
    renderToday();
  }

  function nextStage() {
    const question = currentQuestion();
    if (!question) return;
    const stages = stagesForQuestion(question);
    const nextIndex = state.timer.stageIndex + 1;
    if (nextIndex >= stages.length) {
      stopTimerAndShowResult();
    } else {
      goToStage(nextIndex);
    }
  }

  function stopTimerAndShowResult() {
    if (state.timer.intervalId) {
      clearInterval(state.timer.intervalId);
      state.timer.intervalId = null;
    }
    state.timer.running = false;
    openResultForm(true);
    renderToday();
  }

  function tickTimer() {
    const question = currentQuestion();
    if (!question || !state.timer.running) return;

    const stages = stagesForQuestion(question);
    const stage = stages[state.timer.stageIndex];
    const elapsed = Date.now() - state.timer.stageStartedAt;
    const remaining = stage.minutes * 60000 - elapsed;

    if (remaining <= 0) {
      if (state.timer.stageIndex === stages.length - 1) {
        stopTimerAndShowResult();
      } else {
        goToStage(state.timer.stageIndex + 1);
      }
      return;
    }

    const totalSeconds = Math.ceil(remaining / 1000);
    const minutes = String(Math.floor(totalSeconds / 60)).padStart(2, "0");
    const seconds = String(totalSeconds % 60).padStart(2, "0");
    elements["timer-remaining"].textContent = `${minutes}:${seconds}`;
  }

  function elapsedAttemptMinutes(question) {
    const cap = question?.isHard ? 45 : 15;
    const startedAt =
      state.activeQuestionId === question?.problemId ? state.timer.attemptStartedAt : null;
    const timedFrom = startedAt ?? state.questionShownAt ?? Date.now();
    const elapsedMinutes = Math.round((Date.now() - timedFrom) / 60000);
    return Math.min(Math.max(elapsedMinutes, 1), cap);
  }

  function calculateNextReviewForForm() {
    const timestamp = elements["result-timestamp"].value;
    const mark = elements["result-mark"].value;
    const attemptType = elements["result-attempt-type"].value;
    if (!timestamp || !mark) return "";

    try {
      return progress.calculateNextReview(state.log.attempts, { timestamp, mark, attemptType });
    } catch (error) {
      return "";
    }
  }

  function updateResultNextReview() {
    const nextReview = calculateNextReviewForForm();
    if (nextReview) {
      elements["result-next-review"].value = nextReview;
    }
  }

  function openResultForm() {
    const question = currentQuestion();
    if (!question || !question.problemId) return;

    const alreadyOpen =
      !elements["result-form"].classList.contains("hidden") &&
      elements["result-problem"].value === question.problemId;
    if (alreadyOpen) return;

    resetTimerStageWithoutClearingAttempt();
    state.resultTimestamp = localTimestampString();
    elements["result-problem"].value = question.problemId;
    elements["result-timestamp"].value = state.resultTimestamp;
    elements["result-attempt-type"].value = question.attemptType;
    elements["result-mark"].value = "✅";
    elements["result-minutes"].value = elapsedAttemptMinutes(question);
    elements["result-failure-type"].value = "none";
    elements["result-notes"].value = "";
    updateResultNextReview();
    elements["result-form"].classList.remove("hidden");
  }

  function resetTimerStageWithoutClearingAttempt() {
    if (state.timer.intervalId) {
      clearInterval(state.timer.intervalId);
      state.timer.intervalId = null;
    }
    state.timer.running = false;
  }

  function recordResult(event) {
    event.preventDefault();
    const question = currentQuestion();
    if (!question || !question.problemId) return;

    const attemptType = elements["result-attempt-type"].value;
    const mark = elements["result-mark"].value;
    const minutes = Number.parseInt(elements["result-minutes"].value, 10);
    const failureType = elements["result-failure-type"].value;
    const nextReview = elements["result-next-review"].value;
    const notes = elements["result-notes"].value.trim();
    const timestamp = elements["result-timestamp"].value || localTimestampString();

    if (!Number.isInteger(minutes) || minutes < 1 || minutes > 60) {
      alert("Minutes must be between 1 and 60.");
      return;
    }
    if (!nextReview) {
      alert("A next review date is required.");
      return;
    }

    const attempt = {
      timestamp,
      problemId: question.problemId,
      roadmapBlock: state.log.metadata.currentRoadmapBlock,
      attemptType,
      mark,
      minutes,
      failureType,
      nextReview,
      notes,
    };

    state.log.attempts.push(attempt);
    state.log.attempts = progress.sortAttempts(state.log.attempts);
    markUnsaved();

    state.activeQuestionId = null;
    state.resultTimestamp = null;
    resetTimer();
    elements["result-form"].classList.add("hidden");

    state.currentQuestionIndex += 1;
    rebuildDailyPlan(false);
    renderAll();
  }

  function snoozeCurrentQuestion() {
    const question = currentQuestion();
    if (!question || !question.problemId) return;

    state.log.metadata.snoozedUntil[question.problemId] = progress.addDays(state.currentDate, 1);
    markUnsaved();
    state.activeQuestionId = null;
    resetTimer();
    elements["result-form"].classList.add("hidden");
    rebuildDailyPlan(false, [question.problemId]);
    renderAll();
  }

  function statusForProblem(problemId) {
    const combined = combinedCatalog();
    const statuses = progress.deriveStatuses(
      combined.problems,
      state.log.attempts,
      state.currentDate,
      state.log.metadata.snoozedUntil
    );
    return statuses.get(problemId) || {
      state: "Not attempted",
      due: false,
      nextReview: null,
      latest: null,
      established: false,
    };
  }

  function renderHeader() {
    const savedElement = elements["saved-status"];
    savedElement.textContent = state.saved ? "Saved" : "Unsaved changes";
    savedElement.className = `status-pill ${state.saved ? "success" : "warning"}`;

    const parsedDate = new Date(`${state.currentDate}T00:00:00`);
    const weekday = parsedDate.toLocaleDateString(undefined, { weekday: "long" });
    elements["date-status"].textContent = `${weekday} · ${state.currentDate}`;
    elements["block-status"].textContent = `Cycle ${state.log.metadata.cycle} · Block ${state.log.metadata.currentRoadmapBlock}`;
  }

  function trackQuestionPresentation() {
    const question = currentQuestion();
    const key = question ? `${state.currentQuestionIndex}|${question.problemId}` : null;
    if (key !== state.questionShownKey) {
      state.questionShownKey = key;
      state.questionShownAt = Date.now();
    }
  }

  function renderToday() {
    renderHeader();
    trackQuestionPresentation();
    const plan = state.dailyPlan;
    const block = catalog.roadmapBlocks.find((item) => item.id === state.log.metadata.currentRoadmapBlock);
    const summaryParts = [
      plan?.dayType ?? "No plan",
      `${plan?.slotCount ?? 0} question slots`,
      block ? `Block ${block.id}: ${block.focus}` : "",
    ];
    elements["today-summary"].textContent = summaryParts.filter(Boolean).join(" · ");

    const list = elements["today-plan"];
    list.innerHTML = "";

    if (!plan) return;

    plan.warnings.forEach((warning) => {
      const warningCard = document.createElement("div");
      warningCard.className = "question-card";
      warningCard.innerHTML = `<div class="question-main"><p class="question-title">⚠️ ${escapeHtml(warning)}</p></div>`;
      list.appendChild(warningCard);
    });

    plan.questions.forEach((question, index) => {
      const card = document.createElement("article");
      card.className = "question-card";
      if (index === state.currentQuestionIndex) card.classList.add("active");
      if (index < state.currentQuestionIndex) card.classList.add("completed");

      if (!question.problemId) {
        card.innerHTML = `
          <div class="question-main">
            <p class="question-title">No automatic suggestion</p>
            <p class="question-meta">${escapeHtml(question.reason)}</p>
          </div>
        `;
        list.appendChild(card);
        return;
      }

      const status = statusForProblem(question.problemId);
      const meta = [
        question.difficulty,
        `Type: ${question.attemptType}`,
        question.categoryHidden ? "Category hidden" : `Block ${question.roadmapBlock}`,
        status.state,
      ];
      if (question.drill) meta.push(`Drill: ${question.drill}`);
      if (status.nextReview) meta.push(`Next review: ${status.nextReview}`);

      const main = document.createElement("div");
      main.className = "question-main";
      main.innerHTML = `
        <p class="question-title">${escapeHtml(question.problemId)} · ${escapeHtml(question.title)}</p>
        <p class="question-meta">${escapeHtml(question.reason)}</p>
        <p class="question-meta">${escapeHtml(meta.join(" · "))}</p>
      `;

      const actions = document.createElement("div");
      actions.className = "question-actions";
      if (index === state.currentQuestionIndex) {
        const startButton = document.createElement("button");
        startButton.type = "button";
        startButton.className = "primary-button";
        startButton.textContent = state.activeQuestionId === question.problemId ? "Attempt in progress" : "Start attempt";
        startButton.disabled = state.activeQuestionId === question.problemId;
        startButton.addEventListener("click", startAttempt);
        actions.appendChild(startButton);
      } else if (index > state.currentQuestionIndex) {
        const badge = document.createElement("span");
        badge.className = "status-pill";
        badge.textContent = `Question ${index + 1}`;
        actions.appendChild(badge);
      } else {
        const badge = document.createElement("span");
        badge.className = "status-pill success";
        badge.textContent = "Recorded";
        actions.appendChild(badge);
      }

      card.appendChild(main);
      card.appendChild(actions);
      list.appendChild(card);
    });

    const question = currentQuestion();
    const attemptPanel = elements["attempt-panel"];
    const completePanel = elements["today-complete"];

    if (!question || !question.problemId) {
      attemptPanel.classList.add("hidden");
      completePanel.classList.add("hidden");
      return;
    }

    attemptPanel.classList.remove("hidden");
    completePanel.classList.add("hidden");
    elements["attempt-problem"].textContent = `${question.problemId} · ${question.title}`;
    elements["attempt-reason"].textContent = question.reason;
    elements["attempt-type-badge"].textContent = question.attemptType;
    elements["snooze-button"].classList.toggle("hidden", !statusForProblem(question.problemId).due);
    elements["start-attempt-button"].disabled = Boolean(state.activeQuestionId);
    elements["next-stage-button"].disabled = !state.timer.running;

    if (!state.timer.running && !state.activeQuestionId) {
      elements["timer-stage"].textContent = "Not started";
      elements["timer-remaining"].textContent = question.isHard ? "45:00" : "15:00";
      const stages = stagesForQuestion(question);
      elements["timer-instruction"].textContent = stages[0].instruction;
    } else if (state.timer.running) {
      const stages = stagesForQuestion(question);
      const stage = stages[state.timer.stageIndex];
      elements["timer-stage"].textContent = `Stage ${state.timer.stageIndex + 1}/${stages.length}: ${stage.label}`;
      const elapsed = Date.now() - state.timer.stageStartedAt;
      const remaining = Math.max(0, stage.minutes * 60000 - elapsed);
      const totalSeconds = Math.ceil(remaining / 1000);
      const minutes = String(Math.floor(totalSeconds / 60)).padStart(2, "0");
      const seconds = String(totalSeconds % 60).padStart(2, "0");
      elements["timer-remaining"].textContent = `${minutes}:${seconds}`;
      elements["timer-instruction"].textContent = stage.instruction;
    }
  }

  function renderTodayCompleteState() {
    const plan = state.dailyPlan;
    const completePanel = elements["today-complete"];
    if (plan && state.currentQuestionIndex >= plan.questions.length) {
      completePanel.classList.remove("hidden");
      elements["attempt-panel"].classList.add("hidden");
    }
  }

  function latestAttemptForEachProblem() {
    const map = new Map();
    for (const attempt of state.log.attempts) {
      map.set(attempt.problemId, attempt);
    }
    return map;
  }

  function renderLog() {
    const body = elements["log-table-body"];
    body.innerHTML = "";
    const search = state.logSearch.trim().toLowerCase();
    const latest = latestAttemptForEachProblem();
    const rows = state.log.attempts
      .map((attempt, index) => ({ attempt, index }))
      .filter(({ attempt }) => {
        if (state.logFilter === "problem") return latest.get(attempt.problemId) === attempt;
        if (state.logFilter === "needs-attention") {
          return latest.get(attempt.problemId) === attempt && statusForProblem(attempt.problemId).state === "Needs attention";
        }
        if (state.logFilter === "established") {
          return latest.get(attempt.problemId) === attempt && statusForProblem(attempt.problemId).state === "Established";
        }
        return true;
      })
      .filter(({ attempt }) => {
        if (!search) return true;
        const values = [attempt.problemId, attempt.attemptType, attempt.mark, attempt.failureType, attempt.notes];
        return values.join(" ").toLowerCase().includes(search);
      });

    if (rows.length === 0) {
      const row = document.createElement("tr");
      row.innerHTML = `<td colspan="10" class="muted">No attempts match the current filters.</td>`;
      body.appendChild(row);
      return;
    }

    for (const { attempt, index } of rows) {
      const tr = document.createElement("tr");
      const actions = document.createElement("div");
      actions.className = "actions-cell";
      const editButton = document.createElement("button");
      editButton.type = "button";
      editButton.className = "secondary-button";
      editButton.textContent = "Edit";
      editButton.addEventListener("click", () => openEditAttempt(index));

      const deleteButton = document.createElement("button");
      deleteButton.type = "button";
      deleteButton.className = "danger-button";
      deleteButton.textContent = "Delete";
      deleteButton.addEventListener("click", () => deleteAttempt(index));

      actions.appendChild(editButton);
      actions.appendChild(deleteButton);

      tr.innerHTML = `
        <td>${escapeHtml(attempt.timestamp)}</td>
        <td>${escapeHtml(attempt.problemId)}</td>
        <td>${attempt.roadmapBlock}</td>
        <td>${escapeHtml(attempt.attemptType)}</td>
        <td>${escapeHtml(attempt.mark)}</td>
        <td>${attempt.minutes}</td>
        <td>${escapeHtml(attempt.failureType)}</td>
        <td>${escapeHtml(attempt.nextReview)}</td>
        <td>${escapeHtml(attempt.notes)}</td>
      `;
      const actionCell = document.createElement("td");
      actionCell.appendChild(actions);
      tr.appendChild(actionCell);
      body.appendChild(tr);
    }
  }

  function openEditAttempt(index) {
    const attempt = state.log.attempts[index];
    if (!attempt) return;

    state.editingAttemptIndex = index;
    elements["edit-problem"].value = attempt.problemId;
    elements["edit-timestamp"].value = attempt.timestamp;
    elements["edit-attempt-type"].value = attempt.attemptType;
    elements["edit-mark"].value = attempt.mark;
    elements["edit-minutes"].value = attempt.minutes;
    elements["edit-failure-type"].value = attempt.failureType;
    elements["edit-next-review"].value = attempt.nextReview;
    elements["edit-notes"].value = attempt.notes;
    elements["edit-panel"].classList.remove("hidden");
    elements["edit-panel"].scrollIntoView({ behavior: "smooth", block: "nearest" });
  }

  function saveEditedAttempt(event) {
    event.preventDefault();
    if (state.editingAttemptIndex === null) return;

    const index = state.editingAttemptIndex;
    const original = state.log.attempts[index];
    const attemptType = elements["edit-attempt-type"].value;
    const mark = elements["edit-mark"].value;
    const minutes = Number.parseInt(elements["edit-minutes"].value, 10);
    const failureType = elements["edit-failure-type"].value;
    const notes = elements["edit-notes"].value.trim();
    const manualNextReview = elements["edit-next-review"].value;

    if (!Number.isInteger(minutes) || minutes < 1 || minutes > 60) {
      alert("Minutes must be between 1 and 60.");
      return;
    }

    const priorAttempts = state.log.attempts.filter((_, attemptIndex) => attemptIndex !== index);
    const calculatedNextReview = progress.calculateNextReview(priorAttempts, {
      timestamp: original.timestamp,
      mark,
      attemptType,
    });

    state.log.attempts[index] = {
      ...original,
      attemptType,
      mark,
      minutes,
      failureType,
      nextReview: manualNextReview || calculatedNextReview,
      notes,
    };
    state.log.attempts = progress.sortAttempts(state.log.attempts);
    state.editingAttemptIndex = null;
    elements["edit-panel"].classList.add("hidden");
    markUnsaved();
    rebuildDailyPlan(false);
    renderAll();
  }

  function deleteAttempt(index) {
    const attempt = state.log.attempts[index];
    if (!attempt) return;
    const confirmed = confirm(`Delete the ${attempt.problemId} attempt from ${attempt.timestamp}?`);
    if (!confirmed) return;

    state.log.attempts.splice(index, 1);
    if (state.editingAttemptIndex === index) {
      state.editingAttemptIndex = null;
      elements["edit-panel"].classList.add("hidden");
    } else if (state.editingAttemptIndex !== null && state.editingAttemptIndex > index) {
      state.editingAttemptIndex -= 1;
    }
    markUnsaved();
    rebuildDailyPlan(false);
    renderAll();
  }

  function renderProgress() {
    const combined = combinedCatalog();
    const statuses = progress.deriveStatuses(
      combined.problems,
      state.log.attempts,
      state.currentDate,
      state.log.metadata.snoozedUntil
    );
    const stats = progress.buildStatistics({
      catalog: combined,
      attempts: state.log.attempts,
      metadata: state.log.metadata,
      today: state.currentDate,
      statuses,
    });

    const cards = [
      { label: "Current block", value: `${stats.currentBlockSessions}/${stats.currentBlockBudget}` },
      { label: "Block focus", value: stats.currentBlockFocus },
      { label: "Focused sessions", value: `${stats.totalFocusedSessions}/96` },
      { label: "Remaining sessions", value: stats.remainingSessions },
      { label: "Estimated weeks", value: stats.adjustedEstimatedWeeks ?? stats.estimatedWeeks },
      { label: "Questions attempted", value: stats.uniqueAttempted },
      { label: "Due now", value: stats.dueCount },
      { label: "Needs attention", value: stats.statusCounts["Needs attention"] },
      { label: "Established", value: stats.statusCounts.Established },
      { label: "Fluent success", value: stats.independentSuccessRate === null ? "—" : `${stats.independentSuccessRate}%` },
      { label: "Category-unlabelled success", value: stats.categorySuccessRate === null ? "—" : `${stats.categorySuccessRate}%` },
      { label: "Study minutes", value: stats.totalMinutes },
    ];

    const cardContainer = elements["progress-cards"];
    cardContainer.innerHTML = "";
    for (const card of cards) {
      const cardElement = document.createElement("article");
      cardElement.className = "stat-card";
      cardElement.innerHTML = `<strong>${escapeHtml(card.value)}</strong><span>${escapeHtml(card.label)}</span>`;
      cardContainer.appendChild(cardElement);
    }

    const stateBody = elements["state-count-table"];
    stateBody.innerHTML = "";
    for (const [stateName, count] of Object.entries(stats.statusCounts)) {
      const row = document.createElement("tr");
      row.innerHTML = `<td>${escapeHtml(stateName)}</td><td>${count}</td>`;
      stateBody.appendChild(row);
    }

    const failureBody = elements["failure-count-table"];
    failureBody.innerHTML = "";
    const failureEntries = Object.entries(stats.failureCounts).sort((left, right) => right[1] - left[1]);
    if (failureEntries.length === 0) {
      failureBody.innerHTML = `<tr><td colspan="2" class="muted">No failure types recorded.</td></tr>`;
    } else {
      for (const [failureType, count] of failureEntries) {
        const row = document.createElement("tr");
        row.innerHTML = `<td>${escapeHtml(failureType)}</td><td>${count}</td>`;
        failureBody.appendChild(row);
      }
    }

    const roadmap = elements["roadmap-progress"];
    roadmap.innerHTML = "";
    for (const block of catalog.roadmapBlocks) {
      const used = scheduler.countBlockSessions(state.log.attempts, block.id);
      const percent = Math.min(100, Math.round((used / block.sessionBudget) * 100));
      const current = block.id === stats.currentBlock;
      const row = document.createElement("div");
      row.className = "roadmap-row";
      row.innerHTML = `
        <strong>${block.id}</strong>
        <div class="progress-track" title="${escapeHtml(block.focus)}">
          <div class="progress-fill" style="width:${percent}%"></div>
        </div>
        <span>${used}/${block.sessionBudget} ${current ? "· current" : ""}</span>
      `;
      roadmap.appendChild(row);
    }
  }

  function renderCatalog() {
    const combined = combinedCatalog();
    const search = state.catalogSearch.trim().toLowerCase();
    const body = elements["catalog-table-body"];
    body.innerHTML = "";

    const manualSelect = elements["manual-problem-select"];
    const selectedValue = manualSelect.value;
    manualSelect.innerHTML = "";
    for (const problem of combined.problems) {
      const option = document.createElement("option");
      option.value = problem.id;
      option.textContent = `${problem.id} — ${problem.title}`;
      manualSelect.appendChild(option);
    }
    if (selectedValue) manualSelect.value = selectedValue;

    const filtered = combined.problems.filter((problem) => {
      if (!search) return true;
      const values = [
        problem.id,
        problem.title,
        problem.difficulty,
        problem.p1GroupName,
        problem.p2GroupName,
        String(problem.roadmapBlock),
      ];
      return values.join(" ").toLowerCase().includes(search);
    });

    if (filtered.length === 0) {
      body.innerHTML = `<tr><td colspan="7" class="muted">No catalog rows match the search.</td></tr>`;
      return;
    }

    for (const problem of filtered) {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${escapeHtml(problem.id)}</td>
        <td>${escapeHtml(problem.title)}</td>
        <td>${escapeHtml(problem.difficulty)}</td>
        <td>${problem.roadmapBlock || "—"}</td>
        <td>${escapeHtml(problem.p1GroupName)} (${problem.p1Group})</td>
        <td>${escapeHtml(problem.p2GroupName)} (${problem.p2Group})</td>
        <td>${escapeHtml(problem.relatedIds.map((id) => `${id[0]}_${id.slice(1)}`).join(", ") || "—")}</td>
      `;
      body.appendChild(row);
    }
  }

  function renderAll() {
    renderToday();
    renderTodayCompleteState();
    renderLog();
    renderProgress();
    renderCatalog();
  }

  function showView(view) {
    state.view = view;
    document.querySelectorAll(".app-view").forEach((element) => element.classList.remove("active"));
    document.getElementById(`view-${view}`).classList.add("active");
    document.querySelectorAll(".nav-button").forEach((button) => {
      button.classList.toggle("active", button.dataset.view === view);
    });
  }

  function showFileErrors(errors) {
    const container = elements["file-errors"];
    if (!errors || errors.length === 0) {
      container.classList.add("hidden");
      container.innerHTML = "";
      return;
    }

    container.classList.remove("hidden");
    container.innerHTML = "";
    const title = document.createElement("strong");
    title.textContent = "The REVIEW_LOG.md was not loaded:";
    container.appendChild(title);
    const list = document.createElement("ul");
    for (const error of errors) {
      const item = document.createElement("li");
      item.textContent = error;
      list.appendChild(item);
    }
    container.appendChild(list);
  }

  async function loadReviewLogFile(file) {
    const text = await file.text();
    const parsed = markdownLog.parseReviewLog(text, catalog);
    if (!parsed.ok) {
      showFileErrors(parsed.errors);
      return;
    }

    state.log = parsed;
    state.currentDate = localDateString();
    state.currentQuestionIndex = 0;
    state.manualChoice = null;
    state.activeQuestionId = null;
    state.editingAttemptIndex = null;
    resetTimer();
    elements["result-form"].classList.add("hidden");
    elements["edit-panel"].classList.add("hidden");
    showFileErrors([]);
    markSaved();
    rebuildDailyPlan(true);
    showView("today");
    renderAll();
  }

  function downloadReviewLog(text) {
    const blob = new Blob([text], { type: "text/markdown;charset=utf-8" });
    const url = URL.createObjectURL(blob);
    const anchor = document.createElement("a");
    anchor.href = url;
    anchor.download = "REVIEW_LOG.md";
    document.body.appendChild(anchor);
    anchor.click();
    anchor.remove();
    URL.revokeObjectURL(url);
  }

  async function saveReviewLog() {
    const text = markdownLog.serializeReviewLog(state.log);

    if (window.showSaveFilePicker) {
      try {
        const handle = await window.showSaveFilePicker({
          suggestedName: "REVIEW_LOG.md",
          types: [
            {
              description: "Markdown review log",
              accept: { "text/markdown": [".md"] },
            },
          ],
        });
        const writable = await handle.createWritable();
        await writable.write(text);
        await writable.close();
        markSaved();
        showFileErrors([]);
        return;
      } catch (error) {
        if (error && error.name === "AbortError") return;
        // Fall back to a normal download if direct saving is unavailable.
      }
    }

    downloadReviewLog(text);
    markSaved();
    showFileErrors([]);
  }

  function startNewReviewLog() {
    const confirmed = confirm(
      "Start a new REVIEW_LOG.md? Export the current file first if you want to keep its history."
    );
    if (!confirmed) return;

    state.log = markdownLog.createEmptyReviewLog({
      currentRoadmapBlock: 1,
      cycle: 1,
    });
    state.currentDate = localDateString();
    state.currentQuestionIndex = 0;
    state.manualChoice = null;
    state.activeQuestionId = null;
    state.editingAttemptIndex = null;
    resetTimer();
    elements["result-form"].classList.add("hidden");
    elements["edit-panel"].classList.add("hidden");
    showFileErrors([]);
    markUnsaved();
    rebuildDailyPlan(true);
    showView("today");
    renderAll();
  }

  function advanceBlock() {
    const currentBlock = state.log.metadata.currentRoadmapBlock;
    if (currentBlock >= 22) {
      alert("Block 22 is the final consolidation block.");
      return;
    }

    const confirmed = confirm(`Advance from block ${currentBlock} to block ${currentBlock + 1}?`);
    if (!confirmed) return;

    state.log.metadata.currentRoadmapBlock += 1;
    markUnsaved();
    state.activeQuestionId = null;
    resetTimer();
    elements["result-form"].classList.add("hidden");
    rebuildDailyPlan(true);
    renderAll();
  }

  function addUnfamiliarQuestion(event) {
    event.preventDefault();
    const id = elements["unfamiliar-id"].value.trim();
    const title = elements["unfamiliar-title"].value.trim();
    const difficulty = elements["unfamiliar-difficulty"].value;
    const family = elements["unfamiliar-family"].value.trim();

    if (!/^[A-Za-z][A-Za-z0-9_-]*$/.test(id)) {
      alert("Use an ID such as LC_239. It must start with a letter.");
      return;
    }
    if (!title || !family) {
      alert("Title and completed family are required.");
      return;
    }

    const combined = combinedCatalog();
    if (combined.problems.some((problem) => problem.id === id)) {
      alert("That ID already exists in the catalog or unfamiliar list.");
      return;
    }

    state.log.unfamiliar.push({
      id,
      title,
      difficulty,
      family,
      added: localDateString(),
    });
    state.log.unfamiliar.sort((left, right) => left.added.localeCompare(right.added) || left.id.localeCompare(right.id));
    markUnsaved();
    elements["unfamiliar-form"].reset();
    elements["unfamiliar-difficulty"].value = "Medium";
    rebuildDailyPlan(false);
    renderAll();
  }

  function useManualChoice(event) {
    event.preventDefault();
    const problemId = elements["manual-problem-select"].value;
    const attemptType = elements["manual-attempt-type"].value;
    if (!problemId) return;

    if (state.dailyPlan && state.currentQuestionIndex >= state.dailyPlan.questions.length) {
      alert("Today’s slots are already complete. Manual choice does not add an extra question.");
      return;
    }

    const completedToday = (state.dailyPlan?.questions ?? [])
      .slice(0, state.currentQuestionIndex)
      .some((question) => question.problemId === problemId);
    if (completedToday) {
      alert("That question is already recorded for today.");
      return;
    }

    state.manualChoice = { problemId, attemptType };
    state.activeQuestionId = null;
    resetTimer();
    elements["result-form"].classList.add("hidden");
    rebuildDailyPlan(false);
    showView("today");
    renderAll();
  }

  function bindEvents() {
    document.querySelectorAll(".nav-button").forEach((button) => {
      button.addEventListener("click", () => showView(button.dataset.view));
    });

    elements["start-attempt-button"].addEventListener("click", startAttempt);
    elements["next-stage-button"].addEventListener("click", nextStage);
    elements["open-result-button"].addEventListener("click", () => openResultForm());
    elements["snooze-button"].addEventListener("click", snoozeCurrentQuestion);
    elements["result-form"].addEventListener("submit", recordResult);
    elements["result-mark"].addEventListener("change", updateResultNextReview);
    elements["result-attempt-type"].addEventListener("change", updateResultNextReview);
    elements["complete-export-button"].addEventListener("click", saveReviewLog);
    elements["advance-block-button"].addEventListener("click", advanceBlock);

    elements["log-search"].addEventListener("input", (event) => {
      state.logSearch = event.target.value;
      renderLog();
    });
    elements["log-filter"].addEventListener("change", (event) => {
      state.logFilter = event.target.value;
      renderLog();
    });
    elements["edit-attempt-form"].addEventListener("submit", saveEditedAttempt);
    elements["cancel-edit-button"].addEventListener("click", () => {
      state.editingAttemptIndex = null;
      elements["edit-panel"].classList.add("hidden");
    });

    elements["catalog-search"].addEventListener("input", (event) => {
      state.catalogSearch = event.target.value;
      renderCatalog();
    });
    elements["load-log-button"].addEventListener("click", () => elements["log-file-input"].click());
    elements["log-file-input"].addEventListener("change", async (event) => {
      const file = event.target.files && event.target.files[0];
      if (file) {
        await loadReviewLogFile(file);
      }
      event.target.value = "";
    });
    elements["export-log-button"].addEventListener("click", saveReviewLog);
    elements["new-log-button"].addEventListener("click", startNewReviewLog);
    elements["unfamiliar-form"].addEventListener("submit", addUnfamiliarQuestion);
    elements["manual-choice-form"].addEventListener("submit", useManualChoice);

    window.addEventListener("beforeunload", (event) => {
      if (!state.saved) {
        event.preventDefault();
        event.returnValue = "";
      }
    });
  }

  function initialize() {
    cacheElements();
    state.log = markdownLog.createEmptyReviewLog({ cycle: 1, currentRoadmapBlock: 1 });
    state.currentDate = localDateString();
    state.saved = false;
    rebuildDailyPlan(true);
    bindEvents();
    renderAll();
    showView("today");
  }

  document.addEventListener("DOMContentLoaded", initialize);
})();
