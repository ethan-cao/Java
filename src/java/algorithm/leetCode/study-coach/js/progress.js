/**
 * Automatic review states, next-review scheduling, and progress statistics.
 * All status values are derived from attempt history; none are manually assigned.
 */
(function (global) {
  "use strict";

  const SUCCESS_INTERVALS = [7, 30];
  const HINTED_INTERVALS = [2, 7, 21];
  const FAILED_INTERVALS = [1, 3, 7, 14, 30];

  function parseDate(value) {
    const date = new Date(`${value}T00:00:00`);
    return Number.isNaN(date.getTime()) ? null : date;
  }

  function dateToString(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  }

  function addDays(value, days) {
    const date = parseDate(value);
    if (!date) throw new Error(`Invalid date: ${value}`);
    date.setDate(date.getDate() + days);
    return dateToString(date);
  }

  function daysBetween(left, right) {
    const leftDate = parseDate(left);
    const rightDate = parseDate(right);
    if (!leftDate || !rightDate) throw new Error("Invalid date supplied to daysBetween");
    return Math.round((rightDate - leftDate) / 86400000);
  }

  function attemptDate(attempt) {
    return attempt.timestamp.slice(0, 10);
  }

  function isIndependentAttempt(attempt) {
    return attempt.attemptType === "Labelled" || attempt.attemptType === "Unlabelled";
  }

  function isCategoryUnlabelled(attempt) {
    return attempt.attemptType === "Unlabelled";
  }

  function sortAttempts(attempts) {
    return [...attempts].sort((left, right) => left.timestamp.localeCompare(right.timestamp));
  }

  function attemptsForProblem(attempts, problemId) {
    return sortAttempts(attempts.filter((attempt) => attempt.problemId === problemId));
  }

  function calculateNextReview(priorAttempts, result) {
    const { mark, timestamp } = result;
    const attemptDay = attemptDate({ timestamp });
    const prior = sortAttempts(
      priorAttempts.filter((attempt) => attempt.timestamp < timestamp)
    );

    if (mark === "✅") {
      const previousIndependent = [...prior]
        .reverse()
        .find((attempt) => isIndependentAttempt(attempt));
      const previousSuccess = previousIndependent && previousIndependent.mark === "✅";
      return addDays(attemptDay, previousSuccess ? SUCCESS_INTERVALS[1] : SUCCESS_INTERVALS[0]);
    }

    if (mark === "🔶") {
      let count = 0;
      for (let index = prior.length - 1; index >= 0; index -= 1) {
        const attempt = prior[index];
        if (!isIndependentAttempt(attempt) || attempt.mark !== "🔶") break;
        count += 1;
      }
      const interval = HINTED_INTERVALS[Math.min(count, HINTED_INTERVALS.length - 1)];
      return addDays(attemptDay, interval);
    }

    if (mark === "❌") {
      let count = 0;
      for (let index = prior.length - 1; index >= 0; index -= 1) {
        const attempt = prior[index];
        if (!isIndependentAttempt(attempt) || attempt.mark !== "❌") break;
        count += 1;
      }
      const interval = FAILED_INTERVALS[Math.min(count, FAILED_INTERVALS.length - 1)];
      return addDays(attemptDay, interval);
    }

    throw new Error(`Unsupported review result: ${mark}`);
  }

  function establishedInfo(problemId, attempts) {
    const history = attemptsForProblem(attempts, problemId);
    if (history.length === 0) {
      return { established: false };
    }

    const latest = history[history.length - 1];
    if (!isIndependentAttempt(latest) || latest.mark !== "✅") {
      return { established: false };
    }

    const latestDay = attemptDate(latest);
    let searchStart = 0;
    for (let index = history.length - 2; index >= 0; index -= 1) {
      const attempt = history[index];
      if (!isIndependentAttempt(attempt) || attempt.mark !== "✅") {
        searchStart = index + 1;
        break;
      }
    }

    const previousSuccess = history
      .slice(searchStart, history.length - 1)
      .reverse()
      .find((attempt) => isIndependentAttempt(attempt) && attempt.mark === "✅" && daysBetween(attemptDate(attempt), latestDay) >= 14);

    if (!previousSuccess) {
      return { established: false };
    }

    const categoryUnlabelled = isCategoryUnlabelled(latest) || isCategoryUnlabelled(previousSuccess);
    if (!categoryUnlabelled) {
      return { established: false };
    }

    return {
      established: true,
      firstSuccess: previousSuccess,
      secondSuccess: latest,
      categoryUnlabelled,
    };
  }

  function getStatus(problemId, attempts, today, snoozedUntil = {}) {
    const history = attemptsForProblem(attempts, problemId);
    if (history.length === 0) {
      return {
        state: "Not attempted",
        due: false,
        nextReview: null,
        latest: null,
        established: false,
        snoozedUntil: snoozedUntil[problemId] || null,
      };
    }

    const latest = history[history.length - 1];
    const established = establishedInfo(problemId, attempts).established;
    const snooze = snoozedUntil[problemId] || null;
    const nextReview = latest.nextReview;

    if (established) {
      return {
        state: "Established",
        due: false,
        nextReview,
        latest,
        established,
        snoozedUntil: snooze,
      };
    }

    if (latest.mark !== "✅") {
      return {
        state: "Needs attention",
        due: Boolean(nextReview && nextReview <= today && (!snooze || snooze <= today)),
        nextReview,
        latest,
        established: false,
        snoozedUntil: snooze,
      };
    }

    const reviewDateDue = Boolean(nextReview && nextReview <= today);
    const selectableDue = reviewDateDue && (!snooze || snooze <= today);
    return {
      state: reviewDateDue ? "Review due" : "Review scheduled",
      due: selectableDue,
      nextReview,
      latest,
      established: false,
      snoozedUntil: snooze,
    };
  }

  function deriveStatuses(problems, attempts, today, snoozedUntil = {}) {
    const statuses = new Map();
    for (const problem of problems) {
      statuses.set(problem.id, getStatus(problem.id, attempts, today, snoozedUntil));
    }
    return statuses;
  }

  function focusedSessionKey(attempt) {
    return `${attempt.timestamp.slice(0, 10)}|${attempt.roadmapBlock}`;
  }

  function countFocusedSessions(attempts, block = null) {
    const keys = new Set();
    for (const attempt of attempts) {
      if (attempt.attemptType !== "Labelled") continue;
      if (block !== null && attempt.roadmapBlock !== block) continue;
      keys.add(focusedSessionKey(attempt));
    }
    return keys.size;
  }

  function percent(numerator, denominator) {
    return denominator === 0 ? null : Math.round((numerator / denominator) * 100);
  }

  function buildStatistics({ catalog, attempts, metadata, today, statuses = null }) {
    const problems = catalog.problems;
    const derivedStatuses = statuses ?? deriveStatuses(problems, attempts, today, metadata.snoozedUntil || {});
    const currentBlock = metadata.currentRoadmapBlock;
    const currentBlockDefinition = catalog.roadmapBlocks.find((block) => block.id === currentBlock);

    const statusCounts = {
      "Not attempted": 0,
      "Needs attention": 0,
      "Review scheduled": 0,
      "Review due": 0,
      Established: 0,
    };
    for (const status of derivedStatuses.values()) {
      statusCounts[status.state] += 1;
    }

    const coldAttempts = attempts.filter((attempt) => isIndependentAttempt(attempt));
    const independentSuccesses = coldAttempts.filter((attempt) => attempt.mark === "✅");
    const categoryAttempts = coldAttempts.filter((attempt) => isCategoryUnlabelled(attempt));
    const categorySuccesses = categoryAttempts.filter((attempt) => attempt.mark === "✅");

    const failureCounts = {};
    for (const attempt of attempts) {
      if (attempt.mark === "✅" || attempt.failureType === "none") continue;
      failureCounts[attempt.failureType] = (failureCounts[attempt.failureType] || 0) + 1;
    }

    const currentBlockSessions = countFocusedSessions(attempts, currentBlock);
    const totalFocusedSessions = countFocusedSessions(attempts);
    const remainingSessions = Math.max(
      0,
      catalog.roadmapBlocks
        .filter((block) => block.id >= currentBlock)
        .reduce((total, block) => total + block.sessionBudget, 0) - currentBlockSessions
    );

    const recentSessionDays = new Set();
    const cutoff = addDays(today, -14);
    for (const attempt of attempts) {
      if (attempt.attemptType !== "Labelled") continue;
      const day = attempt.timestamp.slice(0, 10);
      if (day >= cutoff && day <= today) recentSessionDays.add(day);
    }
    const sessionsPerWeek = recentSessionDays.size / 2;

    return {
      currentBlock,
      currentBlockFocus: currentBlockDefinition ? currentBlockDefinition.focus : "",
      currentBlockBudget: currentBlockDefinition ? currentBlockDefinition.sessionBudget : 0,
      currentBlockSessions,
      blockBudgetReached: currentBlockSessions >= (currentBlockDefinition?.sessionBudget ?? 0),
      totalFocusedSessions,
      remainingSessions,
      estimatedWeeks: Math.ceil(remainingSessions / 6),
      recentSessionsPerWeek: sessionsPerWeek,
      adjustedEstimatedWeeks: sessionsPerWeek > 0 ? Math.ceil(remainingSessions / sessionsPerWeek) : null,
      uniqueAttempted: new Set(attempts.map((attempt) => attempt.problemId)).size,
      statusCounts,
      dueCount: [...derivedStatuses.values()].filter((status) => status.due).length,
      coldAttemptCount: coldAttempts.length,
      independentSuccessCount: independentSuccesses.length,
      independentSuccessRate: percent(independentSuccesses.length, coldAttempts.length),
      categoryAttemptCount: categoryAttempts.length,
      categorySuccessCount: categorySuccesses.length,
      categorySuccessRate: percent(categorySuccesses.length, categoryAttempts.length),
      failureCounts,
      totalMinutes: attempts.reduce((total, attempt) => total + attempt.minutes, 0),
      attemptCount: attempts.length,
    };
  }

  const api = {
    addDays,
    attemptDate,
    buildStatistics,
    calculateNextReview,
    daysBetween,
    deriveStatuses,
    establishedInfo,
    getStatus,
    isCategoryUnlabelled,
    isIndependentAttempt,
    parseDate,
    sortAttempts,
  };

  global.StudyCoachProgress = api;
  if (typeof module !== "undefined" && module.exports) {
    module.exports = api;
  }
})(typeof window !== "undefined" ? window : globalThis);
