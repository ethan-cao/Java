"use strict";

const test = require("node:test");
const assert = require("node:assert/strict");
const catalog = require("../data/catalog.js");

test("catalog contains the complete problem inventory", () => {
  assert.equal(catalog.schemaVersion, 1);
  assert.equal(catalog.problemCount, 254);
  assert.equal(catalog.problems.length, 254);
  assert.equal(new Set(catalog.problems.map((problem) => problem.id)).size, 254);
});

test("catalog difficulty counts match the study plan", () => {
  const counts = { Easy: 0, Medium: 0, Hard: 0 };
  for (const problem of catalog.problems) {
    counts[problem.difficulty] += 1;
  }
  assert.equal(counts.Easy, 54);
  assert.equal(counts.Medium, 189);
  assert.equal(counts.Hard, 11);
});

test("roadmap blocks and budgets match the adaptive plan", () => {
  assert.equal(catalog.roadmapBlocks.length, 22);
  assert.equal(catalog.roadmapBudgetTotal, 96);
  assert.equal(
    catalog.roadmapBlocks.reduce((total, block) => total + block.sessionBudget, 0),
    96
  );
  assert.equal(catalog.roadmapBlocks[21].id, 22);
});

test("all related IDs resolve to catalog problems", () => {
  const ids = new Set(catalog.problems.map((problem) => problem.id));
  for (const problem of catalog.problems) {
    for (const compactId of problem.relatedIds) {
      const normalized = `${compactId[0]}_${compactId.slice(1)}`;
      assert.ok(ids.has(normalized), `${problem.id} references unknown ${normalized}`);
    }
  }
});

test("catalog metadata does not expose solution analysis", () => {
  const forbiddenKeys = new Set([
    "notes",
    "analysis",
    "solution",
    "variants",
    "java",
    "code",
  ]);

  for (const problem of catalog.problems) {
    for (const key of Object.keys(problem)) {
      assert.equal(forbiddenKeys.has(key.toLowerCase()), false, `Unexpected key: ${key}`);
    }
  }
});

test("all eight topical drills are present", () => {
  assert.equal(catalog.drills.length, 8);
  const afterBlocks = catalog.drills.map((drill) => drill.afterBlock);
  assert.deepEqual(afterBlocks, [8, 9, 10, 11, 15, 17, 19, 20]);
});
