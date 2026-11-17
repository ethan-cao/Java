"use strict";

const test = require("node:test");
const assert = require("node:assert/strict");
require("../js/progress.js");
const scheduler = require("../js/scheduler.js");
const catalog = require("../data/catalog.js");

function metadata(currentRoadmapBlock = 1, snoozedUntil = {}) {
  return {
    cycle: 1,
    currentRoadmapBlock,
    snoozedUntil,
  };
}

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
    notes: "",
  };
}

function plan(options) {
  return scheduler.buildDailyPlan({
    dateString: options.dateString,
    catalog,
    attempts: options.attempts ?? [],
    metadata: options.metadata ?? metadata(options.currentRoadmapBlock ?? 1),
    unfamiliar: options.unfamiliar ?? [],
    manualChoice: options.manualChoice ?? null,
  });
}

test("a weekday schedules two Easy/Medium questions with the category visible", () => {
  const dailyPlan = plan({ dateString: "2026-11-09" });

  assert.equal(dailyPlan.dayType, "Weekday focused");
  assert.equal(dailyPlan.slotCount, 2);
  assert.equal(dailyPlan.questions.length, 2);
  assert.ok(dailyPlan.questions.every((question) => !question.isHard));
  assert.ok(dailyPlan.questions.every((question) => question.attemptType === "Labelled"));
  assert.equal(dailyPlan.questions[0].problemId, "E_1");
  assert.equal(dailyPlan.questions[1].problemId, "E_242");
});

test("due failed work is selected before new roadmap work on weekdays", () => {
  const attempts = [
    attempt("E_1", "2026-11-08T10:00", "❌", { nextReview: "2026-11-09" }),
  ];
  const dailyPlan = plan({ dateString: "2026-11-09", attempts });

  assert.equal(dailyPlan.questions[0].problemId, "E_1");
  assert.ok(dailyPlan.questions[0].reason.toLowerCase().includes("due"));
  assert.equal(dailyPlan.questions[1].problemId, "E_242");
});

test("a non-Hard Saturday schedules three Easy/Medium questions", () => {
  const dailyPlan = plan({ dateString: "2026-11-14" });

  assert.equal(dailyPlan.dayType, "Saturday focused");
  assert.equal(dailyPlan.slotCount, 3);
  assert.equal(dailyPlan.questions.length, 3);
  assert.ok(dailyPlan.questions.every((question) => !question.isHard));
});

test("a new Hard question replaces all Saturday Easy/Medium slots", () => {
  const dailyPlan = plan({
    dateString: "2026-11-14",
    currentRoadmapBlock: 2,
  });

  assert.equal(dailyPlan.dayType, "Saturday Hard assessment");
  assert.equal(dailyPlan.slotCount, 1);
  assert.equal(dailyPlan.questions.length, 1);
  assert.equal(dailyPlan.questions[0].problemId, "H_42");
  assert.equal(dailyPlan.questions[0].isHard, true);
});

test("a due Hard review uses the full Saturday session", () => {
  const attempts = [
    attempt("H_42", "2026-11-07T10:00", "❌", { roadmapBlock: 2, nextReview: "2026-11-14" }),
  ];
  const dailyPlan = plan({
    dateString: "2026-11-14",
    attempts,
    currentRoadmapBlock: 2,
  });

  assert.equal(dailyPlan.dayType, "Saturday Hard assessment");
  assert.equal(dailyPlan.slotCount, 1);
  assert.equal(dailyPlan.questions[0].problemId, "H_42");
});

test("Sunday schedules three category-hidden Unlabelled questions", () => {
  const dailyPlan = plan({ dateString: "2026-11-15" });

  assert.equal(dailyPlan.dayType, "Sunday mixed review");
  assert.equal(dailyPlan.slotCount, 3);
  assert.equal(dailyPlan.questions.length, 3);
  assert.ok(dailyPlan.questions.every((question) => question.attemptType === "Unlabelled"));
  assert.ok(dailyPlan.questions.every((question) => question.categoryHidden));
  assert.ok(dailyPlan.questions.every((question) => !question.isHard));
});

test("a genuinely new unfamiliar sibling can occupy a Sunday slot", () => {
  const unfamiliar = [
    {
      id: "LC_239",
      title: "Sliding Window Maximum",
      difficulty: "Medium",
      family: "Sliding Window",
      added: "2026-11-01",
    },
  ];
  const attempts = [
    attempt("E_1", "2026-10-25T10:00", "✅", { nextReview: "2026-11-20" }),
  ];
  const dailyPlan = plan({
    dateString: "2026-11-15",
    attempts,
    unfamiliar,
    currentRoadmapBlock: 3,
  });

  const unfamiliarQuestion = dailyPlan.questions.find((question) => question.problemId === "LC_239");
  assert.ok(unfamiliarQuestion, "Expected the unfamiliar question in an Unlabelled slot");
  assert.equal(unfamiliarQuestion.attemptType, "Unlabelled");
  assert.equal(unfamiliarQuestion.categoryHidden, true);
});

test("an Unlabelled unfamiliar attempt still enforces the biweekly transfer interval", () => {
  const unfamiliar = [
    {
      id: "LC_239",
      title: "Sliding Window Maximum",
      difficulty: "Medium",
      family: "Sliding Window",
      added: "2026-11-01",
    },
    {
      id: "LC_295",
      title: "Find Median from Data Stream",
      difficulty: "Medium",
      family: "Heap",
      added: "2026-11-01",
    },
  ];
  const attempts = [
    attempt("LC_239", "2026-11-15T10:00", "✅", {
      roadmapBlock: 3,
      attemptType: "Unlabelled",
      nextReview: "2026-12-06",
    }),
  ];

  const tooSoon = plan({
    dateString: "2026-11-22",
    attempts,
    unfamiliar,
    currentRoadmapBlock: 3,
  });
  assert.equal(tooSoon.questions.some((question) => question.problemId === "LC_295"), false);

  const dueForTransfer = plan({
    dateString: "2026-11-29",
    attempts,
    unfamiliar,
    currentRoadmapBlock: 3,
  });
  assert.equal(dueForTransfer.questions.some((question) => question.problemId === "LC_295"), true);
});

test("manual Hard choice is ignored on weekdays", () => {
  const dailyPlan = plan({
    dateString: "2026-11-09",
    manualChoice: { problemId: "H_42", attemptType: "Labelled" },
  });

  assert.equal(dailyPlan.slotCount, 2);
  assert.ok(dailyPlan.questions.every((question) => !question.isHard));
  assert.ok(dailyPlan.warnings.some((warning) => warning.includes("Hard questions are not scheduled Monday–Friday")));
});

test("manual Hard choice replaces a weekend session", () => {
  const dailyPlan = plan({
    dateString: "2026-11-14",
    currentRoadmapBlock: 2,
    manualChoice: { problemId: "H_42", attemptType: "Labelled" },
  });

  assert.equal(dailyPlan.slotCount, 1);
  assert.equal(dailyPlan.questions[0].problemId, "H_42");
});

test("topical drills wait until the block budget is reached", () => {
  assert.equal(scheduler.drillForBlock(catalog, 8, []), null);

  const attempts = [
    attempt("M_496", "2026-11-02T10:00", "✅", { roadmapBlock: 8, nextReview: "2026-11-09" }),
    attempt("M_503", "2026-11-03T10:00", "✅", { roadmapBlock: 8, nextReview: "2026-11-10" }),
    attempt("M_739", "2026-11-04T10:00", "✅", { roadmapBlock: 8, nextReview: "2026-11-11" }),
    attempt("M_901", "2026-11-05T10:00", "✅", { roadmapBlock: 8, nextReview: "2026-11-12" }),
  ];

  const drill = scheduler.drillForBlock(catalog, 8, attempts);
  assert.equal(drill.afterBlock, 8);
  assert.equal(drill.part2Group, 22);
});
