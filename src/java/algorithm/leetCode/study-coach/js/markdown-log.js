/**
 * REVIEW_LOG.md parser, validator, and deterministic serializer.
 *
 * The Markdown file is the authoritative study-history store. localStorage may
 * cache it for convenience, but this module's state is what gets exported.
 */
(function (global) {
  "use strict";

  const SCHEMA_VERSION = 1;
  const ATTEMPT_TYPES = ["Labelled", "Unlabelled"];

  // Earlier logs used four attempt types. Each maps onto the current two.
  // Guided rows were assisted, so parseAttemptRow caps their marks at 🔶.
  const LEGACY_ATTEMPT_TYPES = new Map([
    ["Focused", "Labelled"],
    ["Mixed", "Unlabelled"],
    ["Unfamiliar", "Unlabelled"],
    ["Guided", "Labelled"],
  ]);

  function normalizeAttemptType(value) {
    return LEGACY_ATTEMPT_TYPES.get(value) ?? value;
  }
  const MARKS = ["✅", "🔶", "❌"];
  const FAILURE_TYPES = [
    "recognition",
    "invariant",
    "implementation",
    "edge case",
    "complexity",
    "none",
  ];

  const ATTEMPT_HEADER = [
    "Timestamp",
    "Problem",
    "Roadmap block",
    "Attempt type",
    "Mark",
    "Minutes",
    "Failure type",
    "Next review",
    "Notes",
  ];

  const UNFAMILAR_HEADER = [
    "ID",
    "Title",
    "Difficulty",
    "Family",
    "Added",
  ];

  const TIMESTAMP_RE = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}$/;
  const DATE_RE = /^\d{4}-\d{2}-\d{2}$/;
  const CUSTOM_ID_RE = /^[A-Za-z][A-Za-z0-9_-]*$/;

  function splitTableRow(line) {
    let value = line.trim();
    if (value.startsWith("|")) value = value.slice(1);
    if (value.endsWith("|")) value = value.slice(0, -1);
    return value
      .split(/(?<!\\)\|/)
      .map((cell) => cell.trim().replace(/\\\|/g, "|"));
  }

  function escapeCell(value) {
    return String(value ?? "")
      .replace(/\s+/g, " ")
      .trim()
      .replace(/\|/g, "\\|");
  }

  function headerCellsMatch(cells, expected) {
    return (
      cells.length === expected.length &&
      expected.every((expectedCell, index) => cells[index] === expectedCell)
    );
  }

  function parseFrontMatter(lines, errors) {
    const metadata = {
      schemaVersion: SCHEMA_VERSION,
      cycle: 1,
      currentRoadmapBlock: 1,
      snoozedUntil: {},
    };

    let index = 0;
    while (index < lines.length && lines[index].trim() === "") index += 1;

    if (index >= lines.length || lines[index].trim() !== "---") {
      errors.push("Missing YAML front matter. The file must start with '---'.");
      return { metadata, nextIndex: index };
    }

    index += 1;
    while (index < lines.length && lines[index].trim() !== "---") {
      const line = lines[index].trim();
      const match = /^([a-z_]+):\s*(.*)$/.exec(line);
      if (match) {
        const key = match[1];
        const value = match[2].trim();
        if (key === "schema_version") {
          const parsed = Number.parseInt(value, 10);
          if (!Number.isInteger(parsed) || parsed !== SCHEMA_VERSION) {
            errors.push(`Unsupported schema_version: ${value}`);
          } else {
            metadata.schemaVersion = parsed;
          }
        } else if (key === "cycle") {
          const parsed = Number.parseInt(value, 10);
          if (!Number.isInteger(parsed) || parsed < 1) {
            errors.push(`Invalid cycle: ${value}`);
          } else {
            metadata.cycle = parsed;
          }
        } else if (key === "current_roadmap_block") {
          const parsed = Number.parseInt(value, 10);
          if (!Number.isInteger(parsed) || parsed < 1 || parsed > 22) {
            errors.push(`Invalid current_roadmap_block: ${value}`);
          } else {
            metadata.currentRoadmapBlock = parsed;
          }
        } else if (key === "snoozed_until") {
          if (value) {
            for (const entry of value.split(",")) {
              const snoozeMatch = /^([^=]+)=(\d{4}-\d{2}-\d{2})$/.exec(entry.trim());
              if (!snoozeMatch) {
                errors.push(`Invalid snoozed_until entry: ${entry}`);
                continue;
              }
              const problemId = snoozeMatch[1].trim();
              if (!DATE_RE.test(snoozeMatch[2])) {
                errors.push(`Invalid snooze date for ${problemId}`);
              } else {
                metadata.snoozedUntil[problemId] = snoozeMatch[2];
              }
            }
          }
        } else {
          errors.push(`Unknown front-matter key: ${key}`);
        }
      }
      index += 1;
    }

    if (index >= lines.length) {
      errors.push("Unterminated YAML front matter.");
    }

    return { metadata, nextIndex: Math.min(index + 1, lines.length) };
  }

  function parseTableRows(lines, startIndex, expectedHeader, errors, tableName) {
    const rows = [];
    let index = startIndex;
    let header = null;

    while (index < lines.length && lines[index].trim() === "") index += 1;
    if (index >= lines.length || !lines[index].trim().startsWith("|")) {
      errors.push(`Missing ${tableName} table.`);
      return { rows, nextIndex: index };
    }

    header = splitTableRow(lines[index]);
    if (!headerCellsMatch(header, expectedHeader)) {
      errors.push(`Invalid ${tableName} table header.`);
      return { rows, nextIndex: index + 1 };
    }

    index += 1;
    if (index >= lines.length || !lines[index].trim().startsWith("|")) {
      errors.push(`Missing ${tableName} separator row.`);
      return { rows, nextIndex: index + 1 };
    }
    index += 1;

    while (index < lines.length) {
      const line = lines[index].trim();
      if (line === "") break;
      if (!line.startsWith("|")) break;

      const cells = splitTableRow(line);
      if (cells.length !== expectedHeader.length) {
        errors.push(`${tableName} row ${index + 1} has ${cells.length} columns; expected ${expectedHeader.length}.`);
      } else {
        rows.push({ cells, lineNumber: index + 1 });
      }
      index += 1;
    }

    return { rows, nextIndex: index };
  }

  function validDate(value) {
    if (!DATE_RE.test(value)) return false;
    const parsed = new Date(`${value}T00:00:00Z`);
    return !Number.isNaN(parsed.getTime()) && parsed.toISOString().slice(0, 10) === value;
  }

  function validTimestamp(value) {
    if (!TIMESTAMP_RE.test(value)) return false;
    const parsed = new Date(`${value}:00Z`);
    return !Number.isNaN(parsed.getTime());
  }

  function parseAttemptRow(row, knownIds, unfamiliarIds, errors) {
    const cells = row.cells;
    const problemId = cells[1];
    const legacyGuided = cells[3] === "Guided";
    const attempt = {
      timestamp: cells[0],
      problemId,
      roadmapBlock: Number.parseInt(cells[2], 10),
      attemptType: normalizeAttemptType(cells[3]),
      mark: legacyGuided && cells[4] === "✅" ? "🔶" : cells[4],
      minutes: Number.parseInt(cells[5], 10),
      failureType: cells[6],
      nextReview: cells[7],
      notes: legacyGuided ? `Former Guided attempt. ${cells[8]}`.trim() : cells[8],
    };

    if (!validTimestamp(attempt.timestamp)) {
      errors.push(`Attempt row ${row.lineNumber}: invalid timestamp '${attempt.timestamp}'.`);
    }
    if (!knownIds.has(problemId) && !unfamiliarIds.has(problemId)) {
      errors.push(`Attempt row ${row.lineNumber}: unknown problem '${problemId}'.`);
    }
    if (!Number.isInteger(attempt.roadmapBlock) || attempt.roadmapBlock < 1 || attempt.roadmapBlock > 22) {
      errors.push(`Attempt row ${row.lineNumber}: invalid roadmap block.`);
    }
    if (!ATTEMPT_TYPES.includes(attempt.attemptType)) {
      errors.push(`Attempt row ${row.lineNumber}: invalid attempt type '${attempt.attemptType}'.`);
    }
    if (!MARKS.includes(attempt.mark)) {
      errors.push(`Attempt row ${row.lineNumber}: invalid mark '${attempt.mark}'.`);
    }
    if (!Number.isInteger(attempt.minutes) || attempt.minutes < 1 || attempt.minutes > 60) {
      errors.push(`Attempt row ${row.lineNumber}: minutes must be 1-60.`);
    }
    if (!FAILURE_TYPES.includes(attempt.failureType)) {
      errors.push(`Attempt row ${row.lineNumber}: invalid failure type '${attempt.failureType}'.`);
    }
    if (!validDate(attempt.nextReview)) {
      errors.push(`Attempt row ${row.lineNumber}: invalid next review '${attempt.nextReview}'.`);
    }

    return attempt;
  }

  function parseUnfamiliarRow(row, knownIds, errors) {
    const cells = row.cells;
    const item = {
      id: cells[0],
      title: cells[1],
      difficulty: cells[2],
      family: cells[3],
      added: cells[4],
    };

    if (!CUSTOM_ID_RE.test(item.id) || knownIds.has(item.id)) {
      errors.push(`Unfamiliar row ${row.lineNumber}: invalid or duplicate ID '${item.id}'.`);
    }
    if (!item.title) {
      errors.push(`Unfamiliar row ${row.lineNumber}: title is required.`);
    }
    if (item.difficulty !== "Easy" && item.difficulty !== "Medium") {
      errors.push(`Unfamiliar row ${row.lineNumber}: difficulty must be Easy or Medium.`);
    }
    if (!item.family) {
      errors.push(`Unfamiliar row ${row.lineNumber}: family is required.`);
    }
    if (!validDate(item.added)) {
      errors.push(`Unfamiliar row ${row.lineNumber}: invalid added date '${item.added}'.`);
    }

    return item;
  }

  function parseReviewLog(text, catalog) {
    const errors = [];
    const lines = String(text ?? "").split(/\r?\n/);
    const front = parseFrontMatter(lines, errors);
    const metadata = front.metadata;

    const knownIds = new Set((catalog?.problems ?? []).map((problem) => problem.id));

    const reviewHeading = lines.findIndex((line) => line.trim() === "# Review Log");
    const unfamiliarHeading = lines.findIndex((line) => line.trim() === "# Unfamiliar questions");
    if (reviewHeading < 0) {
      errors.push("Missing '# Review Log' heading.");
    }
    if (unfamiliarHeading < 0) {
      errors.push("Missing '# Unfamiliar questions' heading.");
    }

    let unfamiliar = [];
    if (unfamiliarHeading >= 0) {
      const parsed = parseTableRows(lines, unfamiliarHeading + 1, UNFAMILAR_HEADER, errors, "Unfamiliar questions");
      unfamiliar = parsed.rows.map((row) => parseUnfamiliarRow(row, knownIds, errors));
    }

    const unfamiliarIds = new Set(unfamiliar.map((item) => item.id));
    let attempts = [];
    if (reviewHeading >= 0) {
      const parsed = parseTableRows(lines, reviewHeading + 1, ATTEMPT_HEADER, errors, "Review Log");
      attempts = parsed.rows.map((row) => parseAttemptRow(row, knownIds, unfamiliarIds, errors));
    }

    attempts.sort((left, right) => left.timestamp.localeCompare(right.timestamp));
    unfamiliar.sort((left, right) => {
      const added = left.added.localeCompare(right.added);
      return added !== 0 ? added : left.id.localeCompare(right.id);
    });

    return {
      ok: errors.length === 0,
      errors,
      metadata,
      attempts,
      unfamiliar,
    };
  }

  function createEmptyReviewLog(options = {}) {
    const today = new Date();
    const todayString = today.toISOString().slice(0, 10);
    return {
      ok: true,
      errors: [],
      metadata: {
        schemaVersion: SCHEMA_VERSION,
        cycle: options.cycle ?? 1,
        currentRoadmapBlock: options.currentRoadmapBlock ?? 1,
        snoozedUntil: {},
      },
      attempts: [],
      unfamiliar: [],
      createdDate: todayString,
    };
  }

  function serializeReviewLog(state) {
    const metadata = state.metadata;
    const snoozes = Object.entries(metadata.snoozedUntil || {})
      .filter(([, value]) => DATE_RE.test(value))
      .sort(([leftId], [rightId]) => leftId.localeCompare(rightId))
      .map(([problemId, date]) => `${problemId}=${date}`)
      .join(",");

    const attempts = [...state.attempts].sort((left, right) =>
      left.timestamp.localeCompare(right.timestamp)
    );

    const unfamiliar = [...state.unfamiliar].sort((left, right) => {
      const added = left.added.localeCompare(right.added);
      return added !== 0 ? added : left.id.localeCompare(right.id);
    });

    const lines = [
      "---",
      `schema_version: ${metadata.schemaVersion}`,
      `cycle: ${metadata.cycle}`,
      `current_roadmap_block: ${metadata.currentRoadmapBlock}`,
      `snoozed_until: ${snoozes}`,
      "---",
      "",
      "# Review Log",
      "",
      `| ${ATTEMPT_HEADER.join(" | ")} |`,
      "|---|---|---:|---|---|---:|---|---|---|",
    ];

    for (const attempt of attempts) {
      lines.push(
        `| ${escapeCell(attempt.timestamp)} | ${escapeCell(attempt.problemId)} | ${attempt.roadmapBlock} | ` +
          `${escapeCell(attempt.attemptType)} | ${escapeCell(attempt.mark)} | ${attempt.minutes} | ` +
          `${escapeCell(attempt.failureType)} | ${escapeCell(attempt.nextReview)} | ${escapeCell(attempt.notes)} |`
      );
    }

    lines.push(
      "",
      "# Unfamiliar questions",
      "",
      `| ${UNFAMILAR_HEADER.join(" | ")} |`,
      "|---|---|---|---|---|"
    );

    for (const item of unfamiliar) {
      lines.push(
        `| ${escapeCell(item.id)} | ${escapeCell(item.title)} | ${escapeCell(item.difficulty)} | ` +
          `${escapeCell(item.family)} | ${escapeCell(item.added)} |`
      );
    }

    lines.push("");
    return lines.join("\n");
  }

  const api = {
    SCHEMA_VERSION,
    ATTEMPT_TYPES,
    MARKS,
    FAILURE_TYPES,
    parseReviewLog,
    createEmptyReviewLog,
    serializeReviewLog,
    normalizeAttemptType,
    escapeCell,
    splitTableRow,
  };

  global.StudyCoachMarkdownLog = api;
  if (typeof module !== "undefined" && module.exports) {
    module.exports = api;
  }
})(typeof window !== "undefined" ? window : globalThis);
