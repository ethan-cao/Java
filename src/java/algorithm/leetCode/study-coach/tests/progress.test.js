"use strict";

const test = require("node:test");
const assert = require("node:assert/strict");
const progress = require("../js/progress.js");
const catalog = require("../data/catalog.js");

function attempt(problemId, timestamp, mark, options = {}) {
  return {
    timestamp,
    problemId,
    roadmapBlock: options.roadmapBlock ?? 1,
    attemptType: options.attemptType ?? "Labelled",
    mark,
    minutes: options.minutes ?? 12,
    failureType: options.failureType ?? (mark === "✅" ? "none" : "invariant"),
    nextReview: options.nextReview ?? "2026-12-01",
    notes: options.notes ?? "",
  };
}

test("✅ review intervals advance from seven to thirty days", () => {
  const first = progress.calculateNextReview([], {
    timestamp: "2026-11-01T10:00",
    mark: "✅",
    attemptType: "Labelled",
  });
  assert.equal(first, "2026-11-08");

  const second = progress.calculateNextReview(
    [attempt("M_49", "2026-11-01T10:00", "✅", { nextReview: "2026-11-08" })],
    { timestamp: "2026-11-08T10:00", mark: "✅", attemptType: "Labelled" }
  );
  assert.equal(second, "2026-12-08");
});

test("🔶 intervals follow two, seven, and twenty-one days", () => {
  const first = progress.calculateNextReview([], {
    timestamp: "2026-11-01T10:00",
    mark: "🔶",
    attemptType: "Labelled",
  });
  assert.equal(first, "2026-11-03");

  const second = progress.calculateNextReview(
    [attempt("M_49", "2026-11-01T10:00", "🔶", { nextReview: "2026-11-03" })],
    { timestamp: "2026-11-03T10:00", mark: "🔶", attemptType: "Labelled" }
  );
  assert.equal(second, "2026-11-10");

  const third = progress.calculateNextReview(
    [
      attempt("M_49", "2026-11-01T10:00", "🔶", { nextReview: "2026-11-03" }),
      attempt("M_49", "2026-11-03T10:00", "🔶", { nextReview: "2026-11-10" }),
    ],
    { timestamp: "2026-11-10T10:00", mark: "🔶", attemptType: "Labelled" }
  );
  assert.equal(third, "2026-12-01");
});

test("a first ❌ is retested the next day", () => {
  const first = progress.calculateNextReview([], {
    timestamp: "2026-11-01T10:00",
    mark: "❌",
    attemptType: "Labelled",
  });
  assert.equal(first, "2026-11-02");
});

test("automatic states reflect latest attempt history", () => {
  assert.equal(progress.getStatus("M_49", [], "2026-11-09").state, "Not attempted");

  const scheduled = progress.getStatus(
    "M_49",
    [attempt("M_49", "2026-11-01T10:00", "✅", { nextReview: "2026-11-20" })],
    "2026-11-09"
  );
  assert.equal(scheduled.state, "Review scheduled");
  assert.equal(scheduled.due, false);

  const due = progress.getStatus(
    "M_49",
    [attempt("M_49", "2026-11-01T10:00", "✅", { nextReview: "2026-11-09" })],
    "2026-11-09"
  );
  assert.equal(due.state, "Review due");
  assert.equal(due.due, true);

  const failed = progress.getStatus(
    "M_49",
    [attempt("M_49", "2026-11-01T10:00", "❌", { nextReview: "2026-11-09" })],
    "2026-11-09"
  );
  assert.equal(failed.state, "Needs attention");
  assert.equal(failed.due, true);
});

test("Established requires two separated fluent successes, one of them Unlabelled", () => {
  const labelledOnly = [
    attempt("M_49", "2026-10-01T10:00", "✅", { nextReview: "2026-10-08" }),
    attempt("M_49", "2026-10-22T10:00", "✅", { nextReview: "2026-11-21" }),
  ];
  assert.equal(progress.establishedInfo("M_49", labelledOnly).established, false);

  const withUnlabelled = [
    attempt("M_49", "2026-10-01T10:00", "✅", { nextReview: "2026-10-08" }),
    attempt("M_49", "2026-10-22T10:00", "✅", { attemptType: "Unlabelled", nextReview: "2026-11-21" }),
  ];
  assert.equal(progress.establishedInfo("M_49", withUnlabelled).established, true);
  assert.equal(progress.getStatus("M_49", withUnlabelled, "2026-10-23").state, "Established");
});

test("a later failure removes Established status", () => {
  const history = [
    attempt("M_49", "2026-10-01T10:00", "✅", { nextReview: "2026-10-08" }),
    attempt("M_49", "2026-10-22T10:00", "✅", { attemptType: "Unlabelled", nextReview: "2026-11-21" }),
    attempt("M_49", "2026-10-29T10:00", "❌", { nextReview: "2026-10-30" }),
  ];
  assert.equal(progress.getStatus("M_49", history, "2026-10-30").state, "Needs attention");

  const afterFailure = [
    ...history,
    attempt("M_49", "2026-11-05T10:00", "✅", { attemptType: "Unlabelled", nextReview: "2026-12-05" }),
  ];
  assert.equal(progress.establishedInfo("M_49", afterFailure).established, false);
});

test("snoozed reviews are not selected as due before their snooze date", () => {
  const history = [attempt("M_49", "2026-11-01T10:00", "✅", { nextReview: "2026-11-09" })];
  const status = progress.getStatus("M_49", history, "2026-11-09", { M_49: "2026-11-10" });
  assert.equal(status.state, "Review due");
  assert.equal(status.due, false);
});

test("statistics track sessions without counting each question as a session", () => {
  const attempts = [
    attempt("E_1", "2026-11-09T10:00", "✅", { nextReview: "2026-11-16" }),
    attempt("E_242", "2026-11-09T10:20", "✅", { nextReview: "2026-11-16" }),
    attempt("M_49", "2026-11-10T10:00", "✅", { nextReview: "2026-11-17" }),
  ];

  const stats = progress.buildStatistics({
    catalog,
    attempts,
    metadata: { currentRoadmapBlock: 1, snoozedUntil: {} },
    today: "2026-11-11",
  });

  assert.equal(stats.currentBlockSessions, 2);
  assert.equal(stats.totalFocusedSessions, 2);
  assert.equal(stats.uniqueAttempted, 3);
  assert.equal(stats.independentSuccessCount, 3);
});

test("only Labelled attempts count as focused sessions", () => {
  const attempts = [
    attempt("E_1", "2026-11-09T10:00", "✅", { nextReview: "2026-11-16" }),
    attempt("M_49", "2026-11-15T10:00", "✅", { attemptType: "Unlabelled", nextReview: "2026-11-22" }),
  ];

  const stats = progress.buildStatistics({
    catalog,
    attempts,
    metadata: { currentRoadmapBlock: 1, snoozedUntil: {} },
    today: "2026-11-16",
  });

  assert.equal(stats.totalFocusedSessions, 1);
  assert.equal(stats.categoryAttemptCount, 1);
});
