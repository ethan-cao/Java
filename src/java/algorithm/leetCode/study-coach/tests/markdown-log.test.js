"use strict";

const test = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs");
const path = require("node:path");
const markdownLog = require("../js/markdown-log.js");
const catalog = require("../data/catalog.js");

const legacyRows = [
  "| 2026-11-09T10:00 | M_49 | 1 | Focused | ✅ | 12 | none | 2026-11-16 |  |",
  "| 2026-11-10T10:00 | M_49 | 1 | Mixed | 🔶 | 11 | invariant | 2026-11-12 | Hinted |",
  "| 2026-11-11T10:00 | M_49 | 1 | Unfamiliar | ✅ | 14 | none | 2026-11-18 |  |",
  "| 2026-11-12T10:00 | M_49 | 1 | Guided | ✅ | 9 | none | 2026-11-13 | Rebuilt the grouping key |",
  "| 2026-11-13T10:00 | M_49 | 1 | Guided | ❌ | 9 | invariant | 2026-11-14 |  |",
];

function readExample() {
  return fs.readFileSync(path.join(__dirname, "..", "REVIEW_LOG.example.md"), "utf8");
}

function legacyLog(rows) {
  return [
    "---",
    "schema_version: 1",
    "cycle: 1",
    "current_roadmap_block: 1",
    "snoozed_until:",
    "---",
    "",
    "# Review Log",
    "",
    "| Timestamp | Problem | Roadmap block | Attempt type | Mark | Minutes | Failure type | Next review | Notes |",
    "|---|---|---:|---|---|---:|---|---|---|",
    ...rows,
    "",
    "# Unfamiliar questions",
    "",
    "| ID | Title | Difficulty | Family | Added |",
    "|---|---|---|---|---|",
    "",
  ].join("\n");
}

test("the attempt types are exactly Labelled and Unlabelled", () => {
  assert.deepEqual(markdownLog.ATTEMPT_TYPES, ["Labelled", "Unlabelled"]);
});

test("example REVIEW_LOG.md parses without errors", () => {
  const parsed = markdownLog.parseReviewLog(readExample(), catalog);
  assert.equal(parsed.ok, true);
  assert.deepEqual(parsed.errors, []);
  assert.equal(parsed.attempts.length, 4);
  assert.equal(parsed.unfamiliar.length, 1);
  assert.equal(parsed.metadata.currentRoadmapBlock, 1);
});

test("Markdown serialization round-trips attempts and unfamiliar questions", () => {
  const parsed = markdownLog.parseReviewLog(readExample(), catalog);
  const serialized = markdownLog.serializeReviewLog(parsed);
  const reparsed = markdownLog.parseReviewLog(serialized, catalog);

  assert.equal(reparsed.ok, true, reparsed.errors.join("\n"));
  assert.deepEqual(reparsed.attempts, parsed.attempts);
  assert.deepEqual(reparsed.unfamiliar, parsed.unfamiliar);
  assert.equal(reparsed.metadata.currentRoadmapBlock, parsed.metadata.currentRoadmapBlock);
});

test("unknown problem IDs are rejected", () => {
  const parsed = markdownLog.parseReviewLog(readExample(), catalog);
  parsed.attempts.push({
    timestamp: "2026-11-19T10:00",
    problemId: "M_9999",
    roadmapBlock: 1,
    attemptType: "Labelled",
    mark: "✅",
    minutes: 10,
    failureType: "none",
    nextReview: "2026-11-26",
    notes: "",
  });

  const serialized = markdownLog.serializeReviewLog(parsed);
  const rejected = markdownLog.parseReviewLog(serialized, catalog);
  assert.equal(rejected.ok, false);
  assert.ok(rejected.errors.some((error) => error.includes("M_9999")));
});

test("pipe characters in notes survive serialization", () => {
  const state = markdownLog.createEmptyReviewLog();
  state.attempts.push({
    timestamp: "2026-11-09T10:00",
    problemId: "M_49",
    roadmapBlock: 1,
    attemptType: "Labelled",
    mark: "✅",
    minutes: 12,
    failureType: "none",
    nextReview: "2026-11-16",
    notes: "count key | hashmap",
  });

  const serialized = markdownLog.serializeReviewLog(state);
  const parsed = markdownLog.parseReviewLog(serialized, catalog);
  assert.equal(parsed.ok, true, parsed.errors.join("\n"));
  assert.equal(parsed.attempts[0].notes, "count key | hashmap");
});

test("invalid schema versions are rejected", () => {
  const state = markdownLog.createEmptyReviewLog();
  state.metadata.schemaVersion = 999;
  const serialized = markdownLog.serializeReviewLog(state);
  const parsed = markdownLog.parseReviewLog(serialized, catalog);
  assert.equal(parsed.ok, false);
  assert.ok(parsed.errors.some((error) => error.includes("schema_version")));
});

test("custom unfamiliar IDs can appear in attempts", () => {
  const state = markdownLog.createEmptyReviewLog();
  state.unfamiliar.push({
    id: "LC_239",
    title: "Sliding Window Maximum",
    difficulty: "Medium",
    family: "Sliding Window",
    added: "2026-11-09",
  });
  state.attempts.push({
    timestamp: "2026-11-09T10:00",
    problemId: "LC_239",
    roadmapBlock: 3,
    attemptType: "Unlabelled",
    mark: "✅",
    minutes: 14,
    failureType: "none",
    nextReview: "2026-11-16",
    notes: "",
  });

  const parsed = markdownLog.parseReviewLog(markdownLog.serializeReviewLog(state), catalog);
  assert.equal(parsed.ok, true, parsed.errors.join("\n"));
  assert.equal(parsed.attempts[0].problemId, "LC_239");
});

test("legacy attempt types map onto the two current types", () => {
  const parsed = markdownLog.parseReviewLog(legacyLog(legacyRows), catalog);

  assert.equal(parsed.ok, true, parsed.errors.join("\n"));
  assert.deepEqual(
    parsed.attempts.map((attempt) => attempt.attemptType),
    ["Labelled", "Unlabelled", "Unlabelled", "Labelled", "Labelled"]
  );
  assert.deepEqual(parsed.attempts.map((attempt) => attempt.mark), ["✅", "🔶", "✅", "🔶", "❌"]);
  assert.equal(parsed.attempts[3].notes, "Former Guided attempt. Rebuilt the grouping key");
  assert.equal(parsed.attempts[4].notes, "Former Guided attempt.");
});

test("exported logs use only the current attempt types", () => {
  const parsed = markdownLog.parseReviewLog(legacyLog(legacyRows), catalog);
  const serialized = markdownLog.serializeReviewLog(parsed);

  assert.equal(serialized.includes("| Focused |"), false);
  assert.equal(serialized.includes("| Guided |"), false);
  assert.equal(markdownLog.parseReviewLog(serialized, catalog).ok, true);
});

test("attempt types outside the current and legacy sets are rejected", () => {
  const parsed = markdownLog.parseReviewLog(
    legacyLog(["| 2026-11-09T10:00 | M_49 | 1 | Repair | ✅ | 12 | none | 2026-11-16 |  |"]),
    catalog
  );

  assert.equal(parsed.ok, false);
  assert.ok(parsed.errors.some((error) => error.includes("invalid attempt type 'Repair'")));
});
