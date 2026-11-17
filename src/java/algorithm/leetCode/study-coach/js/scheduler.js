/**
 * Daily question selection engine.
 *
 * Selection uses only roadmap position and attempt history. There are no
 * Core/Reference labels or manually assigned priorities.
 */
(function (global) {
  "use strict";

  const progress = global.StudyCoachProgress;

  function dayOfWeek(dateString) {
    const date = progress.parseDate(dateString);
    if (!date) throw new Error(`Invalid date: ${dateString}`);
    return date.getDay();
  }

  function buildCombinedCatalog(catalog, unfamiliar = []) {
    const customProblems = unfamiliar.map((item) => ({
      id: item.id,
      number: 0,
      title: item.title,
      difficulty: item.difficulty,
      isHard: false,
      p1Group: 0,
      p1GroupName: "Unfamiliar",
      p1Family: item.family,
      p2Group: 0,
      p2GroupName: "Unfamiliar",
      p2Family: item.family,
      relatedIds: [],
      roadmapBlock: 0,
      roadmapOrder: 0,
      custom: true,
    }));

    return {
      ...catalog,
      problems: [...catalog.problems, ...customProblems],
    };
  }

  function statusFor(statuses, problemId) {
    return statuses.get(problemId) || {
      state: "Not attempted",
      due: false,
      nextReview: null,
      latest: null,
      established: false,
      snoozedUntil: null,
    };
  }

  function problemMap(problems) {
    return new Map(problems.map((problem) => [problem.id, problem]));
  }

  function dueCandidates(problems, statuses) {
    return problems
      .filter((problem) => statusFor(statuses, problem.id).due)
      .sort((left, right) => {
        const leftStatus = statusFor(statuses, left.id);
        const rightStatus = statusFor(statuses, right.id);
        const leftPriority = leftStatus.state === "Needs attention" ? 0 : 1;
        const rightPriority = rightStatus.state === "Needs attention" ? 0 : 1;
        if (leftPriority !== rightPriority) return leftPriority - rightPriority;
        const nextReview = (leftStatus.nextReview || "9999-12-31").localeCompare(rightStatus.nextReview || "9999-12-31");
        if (nextReview !== 0) return nextReview;
        return left.id.localeCompare(right.id);
      });
  }

  function nextRoadmapCandidate(problems, statuses, currentBlock, selectedIds) {
    const candidates = problems
      .filter((problem) => !problem.isHard)
      .filter((problem) => problem.roadmapBlock >= currentBlock)
      .filter((problem) => statusFor(statuses, problem.id).state === "Not attempted")
      .filter((problem) => !selectedIds.has(problem.id))
      .sort((left, right) => {
        if (left.roadmapBlock !== right.roadmapBlock) return left.roadmapBlock - right.roadmapBlock;
        return left.roadmapOrder - right.roadmapOrder;
      });
    return candidates[0] || null;
  }

  function randomPreviouslyAttempted(problems, attempts, statuses, dateString, selectedIds) {
    const attemptedIds = new Set(attempts.map((attempt) => attempt.problemId));
    const candidates = problems
      .filter((problem) => !problem.isHard)
      .filter((problem) => attemptedIds.has(problem.id))
      .filter((problem) => !selectedIds.has(problem.id))
      .filter((problem) => !statusFor(statuses, problem.id).due);

    if (candidates.length === 0) return null;

    const hash = (value) => {
      let hashValue = 2166136261;
      for (let index = 0; index < value.length; index += 1) {
        hashValue ^= value.charCodeAt(index);
        hashValue = Math.imul(hashValue, 16777619);
      }
      return hashValue >>> 0;
    };

    return [...candidates].sort((left, right) => {
      const leftHash = hash(`${dateString}:${left.id}`);
      const rightHash = hash(`${dateString}:${right.id}`);
      if (leftHash !== rightHash) return leftHash - rightHash;
      return left.id.localeCompare(right.id);
    })[0];
  }

  function unfamiliarCandidate(combinedCatalog, attempts, today, selectedIds) {
    const customIds = new Set(combinedCatalog.problems.filter((problem) => problem.custom).map((problem) => problem.id));
    if (customIds.size === 0) return null;

    const unfamiliarAttempts = attempts.filter(
      (attempt) => customIds.has(attempt.problemId) && attempt.attemptType === "Unlabelled"
    );
    const lastUnfamiliar = unfamiliarAttempts
      .slice()
      .sort((left, right) => left.timestamp.localeCompare(right.timestamp))
      .pop();

    if (lastUnfamiliar) {
      const daysSince = progress.daysBetween(progress.attemptDate(lastUnfamiliar), today);
      if (daysSince < 14) return null;
    } else if (attempts.length > 0) {
      const firstAttemptDay = attempts
        .map((attempt) => progress.attemptDate(attempt))
        .sort()[0];
      if (progress.daysBetween(firstAttemptDay, today) < 14) return null;
    } else {
      return null;
    }

    return (
      combinedCatalog.problems
        .filter((problem) => customIds.has(problem.id))
        .filter((problem) => !attempts.some((attempt) => attempt.problemId === problem.id))
        .filter((problem) => !selectedIds.has(problem.id))
        .sort((left, right) => left.id.localeCompare(right.id))[0] || null
    );
  }

  function countBlockSessions(attempts, block) {
    const keys = new Set();
    for (const attempt of attempts) {
      if (attempt.attemptType !== "Labelled") continue;
      if (attempt.roadmapBlock !== block) continue;
      keys.add(`${attempt.timestamp.slice(0, 10)}|${attempt.roadmapBlock}`);
    }
    return keys.size;
  }

  function drillForBlock(catalog, currentBlock, attempts) {
    const blockDefinition = catalog.roadmapBlocks.find((block) => block.id === currentBlock);
    if (!blockDefinition) return null;
    if (currentBlock < 22 && countBlockSessions(attempts, currentBlock) < blockDefinition.sessionBudget) {
      return null;
    }

    return catalog.drills.find((drill) => drill.afterBlock === currentBlock) || null;
  }

  function drillCandidates(combinedCatalog, drill, statuses, selectedIds, attempts) {
    const candidates = combinedCatalog.problems
      .filter((problem) => problem.p2Group === drill.part2Group)
      .filter((problem) => !problem.isHard)
      .filter((problem) => !selectedIds.has(problem.id));

    const notAttempted = candidates.filter((problem) => statusFor(statuses, problem.id).state === "Not attempted");
    if (notAttempted.length < 3) return [];

    const due = candidates.filter((problem) => statusFor(statuses, problem.id).due);
    const remaining = notAttempted.filter((problem) => !due.some((dueProblem) => dueProblem.id === problem.id));
    return [...due, ...remaining].slice(0, 3);
  }

  function firstDueHard(problems, statuses, selectedIds) {
    return (
      dueCandidates(problems, statuses)
        .filter((problem) => problem.isHard)
        .filter((problem) => !selectedIds.has(problem.id))[0] || null
    );
  }

  function firstNewHard(combinedCatalog, statuses, currentBlock, selectedIds) {
    return (
      combinedCatalog.problems
        .filter((problem) => problem.isHard)
        .filter((problem) => problem.roadmapBlock === currentBlock)
        .filter((problem) => statusFor(statuses, problem.id).state === "Not attempted")
        .filter((problem) => !selectedIds.has(problem.id))
        .sort((left, right) => left.roadmapOrder - right.roadmapOrder)[0] || null
    );
  }

  function makeQuestion(problem, attemptType, reason, options = {}) {
    return {
      problemId: problem.id,
      title: problem.title,
      difficulty: problem.difficulty,
      isHard: problem.isHard,
      attemptType,
      reason,
      categoryHidden: attemptType === "Unlabelled",
      roadmapBlock: problem.roadmapBlock || options.currentBlock || 0,
      custom: Boolean(problem.custom),
      drill: options.drill || null,
    };
  }

  function buildDailyPlan(options) {
    const {
      dateString,
      catalog,
      attempts,
      metadata,
      unfamiliar = [],
      manualChoice = null,
    } = options;

    if (!progress) throw new Error("StudyCoachProgress must load before StudyCoachScheduler.");
    if (!dateString || !catalog || !metadata) throw new Error("dateString, catalog, and metadata are required.");

    const combinedCatalog = buildCombinedCatalog(catalog, unfamiliar);
    const problems = combinedCatalog.problems;
    const statuses = progress.deriveStatuses(problems, attempts, dateString, metadata.snoozedUntil || {});
    const currentBlock = metadata.currentRoadmapBlock;
    const weekday = dayOfWeek(dateString);
    const selectedIds = new Set();
    const warnings = [];

    let manualProblem = null;
    if (manualChoice && manualChoice.problemId) {
      manualProblem = problems.find((problem) => problem.id === manualChoice.problemId) || null;
      if (!manualProblem) {
        warnings.push(`Manual question '${manualChoice.problemId}' was not found.`);
      } else if (manualProblem.isHard && weekday >= 1 && weekday <= 5) {
        warnings.push("Hard questions are not scheduled Monday–Friday. The manual Hard choice was ignored.");
        manualProblem = null;
      }
    }

    const basePlan = {
      date: dateString,
      currentBlock,
      questions: [],
      warnings,
    };

    // Manual Hard assessment replaces an entire weekend session.
    if (manualProblem && manualProblem.isHard) {
      selectedIds.add(manualProblem.id);
      basePlan.dayType = weekday === 0 || weekday === 6 ? "Weekend Hard assessment" : "Weekday focused";
      basePlan.slotCount = 1;
      basePlan.questions.push(
        makeQuestion(
          manualProblem,
          manualChoice.attemptType || "Labelled",
          "Manually selected Hard assessment",
          { currentBlock }
        )
      );
      return basePlan;
    }

    if (weekday >= 1 && weekday <= 5) {
      basePlan.dayType = "Weekday focused";
      basePlan.slotCount = 2;

      if (manualProblem) {
        selectedIds.add(manualProblem.id);
        basePlan.questions.push(
          makeQuestion(manualProblem, manualChoice.attemptType || "Labelled", "Manually selected question", {
            currentBlock,
          })
        );
      }

      while (basePlan.questions.length < basePlan.slotCount) {
        const due = dueCandidates(problems, statuses).find((problem) => !problem.isHard && !selectedIds.has(problem.id));
        if (due) {
          selectedIds.add(due.id);
          basePlan.questions.push(
            makeQuestion(
              due,
              manualChoice?.problemId === due.id ? manualChoice.attemptType || "Labelled" : "Labelled",
              `Due ${statusFor(statuses, due.id).state.toLowerCase()} review`,
              { currentBlock }
            )
          );
          continue;
        }

        const next = nextRoadmapCandidate(problems, statuses, currentBlock, selectedIds);
        if (next) {
          selectedIds.add(next.id);
          basePlan.questions.push(
            makeQuestion(next, "Labelled", `Next not-attempted question in roadmap block ${next.roadmapBlock}`, {
              currentBlock,
            })
          );
          continue;
        }

        basePlan.questions.push({
          problemId: null,
          reason: "No eligible Easy/Medium question. Advance the roadmap, add an unfamiliar question, or choose manually.",
        });
        break;
      }

      return basePlan;
    }

    if (weekday === 6) {
      const dueHard = firstDueHard(problems, statuses, selectedIds);
      if (dueHard) {
        basePlan.dayType = "Saturday Hard assessment";
        basePlan.slotCount = 1;
        selectedIds.add(dueHard.id);
        basePlan.questions.push(
          makeQuestion(dueHard, "Labelled", "Due Hard review uses the full 45-minute Hard format", { currentBlock })
        );
        return basePlan;
      }

      if (manualProblem) {
        selectedIds.add(manualProblem.id);
      }

      const drill = drillForBlock(catalog, currentBlock, attempts);
      const plannedDrill = drill && drillCandidates(combinedCatalog, drill, statuses, selectedIds, attempts);

      if (plannedDrill && plannedDrill.length === 3) {
        basePlan.dayType = "Saturday topical drill";
        basePlan.slotCount = 3;
        if (manualProblem) {
          basePlan.questions.push(
            makeQuestion(manualProblem, manualChoice.attemptType || "Labelled", "Manually selected question", {
              currentBlock,
            })
          );
        }

        for (const problem of plannedDrill) {
          if (basePlan.questions.length >= 3) break;
          selectedIds.add(problem.id);
          const status = statusFor(statuses, problem.id);
          basePlan.questions.push(
            makeQuestion(
              problem,
              "Labelled",
              status.due ? `Drill question with a due ${status.state.toLowerCase()} review` : `Drill question: ${drill.name}`,
              { currentBlock, drill: drill.name }
            )
          );
        }

        while (basePlan.questions.length < 3) {
          const due = dueCandidates(problems, statuses).find(
            (problem) => !problem.isHard && !selectedIds.has(problem.id)
          );
          if (!due) break;
          selectedIds.add(due.id);
          basePlan.questions.push(
            makeQuestion(due, "Labelled", `Due ${statusFor(statuses, due.id).state.toLowerCase()} review`, {
              currentBlock,
            })
          );
        }
        return basePlan;
      }

      const newHard = firstNewHard(combinedCatalog, statuses, currentBlock, selectedIds);
      if (newHard && !manualProblem) {
        basePlan.dayType = "Saturday Hard assessment";
        basePlan.slotCount = 1;
        selectedIds.add(newHard.id);
        basePlan.questions.push(
          makeQuestion(newHard, "Labelled", `New Hard question in roadmap block ${currentBlock}`, { currentBlock })
        );
        return basePlan;
      }

      basePlan.dayType = "Saturday focused";
      basePlan.slotCount = 3;
      if (manualProblem) {
        basePlan.questions.push(
          makeQuestion(manualProblem, manualChoice.attemptType || "Labelled", "Manually selected question", {
            currentBlock,
          })
        );
      }

      while (basePlan.questions.length < basePlan.slotCount) {
        const due = dueCandidates(problems, statuses).find((problem) => !problem.isHard && !selectedIds.has(problem.id));
        if (due) {
          selectedIds.add(due.id);
          const status = statusFor(statuses, due.id);
          basePlan.questions.push(
            makeQuestion(due, "Labelled", `Due ${status.state.toLowerCase()} review`, { currentBlock })
          );
          continue;
        }

        const next = nextRoadmapCandidate(problems, statuses, currentBlock, selectedIds);
        if (next) {
          selectedIds.add(next.id);
          basePlan.questions.push(
            makeQuestion(next, "Labelled", `Next not-attempted question in roadmap block ${next.roadmapBlock}`, {
              currentBlock,
            })
          );
          continue;
        }

        basePlan.questions.push({
          problemId: null,
          reason: "No eligible Easy/Medium question. Advance the roadmap, add an unfamiliar question, or choose manually.",
        });
        break;
      }

      return basePlan;
    }

    // Sunday: three category-unlabelled Easy/Medium cycles, unless a Hard
    // assessment was manually scheduled.
    basePlan.dayType = "Sunday mixed review";
    basePlan.slotCount = 3;

    if (manualProblem) {
      selectedIds.add(manualProblem.id);
      basePlan.questions.push(
        makeQuestion(
          manualProblem,
          manualChoice.attemptType || "Unlabelled",
          "Manually selected category-unlabelled question",
          { currentBlock }
        )
      );
    }

    while (basePlan.questions.length < basePlan.slotCount) {
      const due = dueCandidates(problems, statuses).find((problem) => !problem.isHard && !selectedIds.has(problem.id));
      if (due) {
        selectedIds.add(due.id);
        const status = statusFor(statuses, due.id);
        basePlan.questions.push(
          makeQuestion(due, "Unlabelled", `Due ${status.state.toLowerCase()} review with its category hidden`, {
            currentBlock,
          })
        );
        continue;
      }

      const unfamiliarChoice = unfamiliarCandidate(combinedCatalog, attempts, dateString, selectedIds);
      if (unfamiliarChoice) {
        selectedIds.add(unfamiliarChoice.id);
        basePlan.questions.push(
          makeQuestion(unfamiliarChoice, "Unlabelled", "Biweekly unfamiliar sibling from a completed family", {
            currentBlock,
          })
        );
        continue;
      }

      const random = randomPreviouslyAttempted(problems, attempts, statuses, dateString, selectedIds);
      if (random) {
        selectedIds.add(random.id);
        basePlan.questions.push(
          makeQuestion(random, "Unlabelled", "Random previously attempted question with its category hidden", {
            currentBlock,
          })
        );
        continue;
      }

      const next = nextRoadmapCandidate(problems, statuses, currentBlock, selectedIds);
      if (next) {
        selectedIds.add(next.id);
        basePlan.questions.push(
          makeQuestion(next, "Unlabelled", "No prior question is available yet; new roadmap question with category hidden", {
            currentBlock,
          })
        );
        continue;
      }

      basePlan.questions.push({
        problemId: null,
        reason: "No eligible Easy/Medium question. Add an unfamiliar question or choose manually.",
      });
      break;
    }

    return basePlan;
  }

  const api = {
    buildCombinedCatalog,
    buildDailyPlan,
    countBlockSessions,
    drillForBlock,
  };

  global.StudyCoachScheduler = api;
  if (typeof module !== "undefined" && module.exports) {
    module.exports = api;
  }
})(typeof window !== "undefined" ? window : globalThis);
