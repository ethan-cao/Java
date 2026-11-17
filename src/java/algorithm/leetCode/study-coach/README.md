# LeetCode Study Coach

A local, static study coach for the adaptive plan in `../STUDY_PLAN.md`.

The application selects what to study, guides the timed cold-attempt routine, records results, schedules future reviews, and tracks progress. It does **not** open Java files, inspect solutions, run tests, or submit to LeetCode.

## Quick start

No build step, server, account, or dependency installation is required.

1. Open `index.html` in a modern browser.
2. On first use, either:
   - click **Start a new log**, or
   - load an existing `REVIEW_LOG.md`.
3. Follow the questions on the **Today** page.
4. Record each result.
5. Click **Save / export REVIEW_LOG.md** before closing.

If the browser supports the File System Access API, the tool can write `REVIEW_LOG.md` directly. Otherwise it downloads the updated file. The header always shows **Saved** or **Unsaved changes**.

## Daily workflow

### Monday–Friday: 30 minutes

Two Easy/Medium questions. Each question uses the same 15-minute cycle:

| Stage | Time | Rule |
|---|---:|---|
| Think | 3 min | Read only the question, constraints, and signature; identify the pattern and invariant |
| Try | 5 min | Attempt independently without hints |
| Code | 5 min | Continue or finish independently, then stop |
| Finish/check | 2 min | Log success, or mark the result and check the core answer |

Minutes are filled automatically when you press Record result. The clock starts at **Start attempt**, or when the question appears if you never press it, and stops at the cycle budget. Press Start when you begin so the minutes match the attempt.

### Saturday: 45 minutes

One of:

- Three Easy/Medium questions, including a scheduled topical drill when due
- One Hard assessment, using the unchanged 45-minute format

### Sunday: 45 minutes

Three category-unlabelled Easy/Medium questions:

1. Due 🔶/❌ work
2. Other due reviews
3. Random previously attempted questions

Every second week, one slot may contain a genuinely unfamiliar sibling from a completed family.

A scheduled Hard assessment replaces all three Sunday Easy/Medium slots.

## REVIEW_LOG.md

The Markdown file is the authoritative history store. It contains:

- Schema version, cycle, current roadmap block, and snooze metadata
- One append-only row per attempt
- Optional genuinely unfamiliar questions

Example:

```markdown
---
schema_version: 1
cycle: 1
current_roadmap_block: 1
snoozed_until:
---

# Review Log

| Timestamp | Problem | Roadmap block | Attempt type | Mark | Minutes | Failure type | Next review | Notes |
|---|---|---:|---|---|---:|---|---|---|
| 2026-11-09T18:30 | M_49 | 1 | Labelled | 🔶 | 15 | invariant | 2026-11-11 | Uncertain about the grouping key |

# Unfamiliar questions

| ID | Title | Difficulty | Family | Added |
|---|---|---|---|---|
| LC_239 | Sliding Window Maximum | Medium | Sliding Window | 2026-11-18 |
```

See `REVIEW_LOG.example.md` for a complete valid example.

## Attempt types

| Type | Category visible? | Counts toward Fluent ✅? | Counts toward category-hidden mastery? | Usually used for |
|---|---|---|---|---|da
| **Labelled** | Yes | Yes | No | Weeky, Saturday, and block-review questions |
| **Unlabelled** | No | Yes | Yes | Sunday mixed review and unfamiliar questions |

Assistance is recorded by the mark, not by a type. Reading `# Analysis` during an attempt caps it at 🔶, and reading or copying a substantive solution is ❌. Study done after a failure is not logged; the next logged attempt is a cold retry.

Existing logs are converted when loaded: `Focused` becomes `Labelled`, `Mixed` and `Unfamiliar` become `Unlabelled`, and `Guided` becomes `Labelled` with its ✅ capped at 🔶. Converted notes start with "Former Guided attempt." Exporting writes the new format. The separate unfamiliar-question list remains available for scheduling transfer practice.

The same guide is available in the app beneath the result form and in Catalog & Settings.

## Automatic states

The tool derives every status from attempt history:

- **Not attempted**
- **Needs attention**
- **Review scheduled**
- **Review due**
- **Established**

There are no Core/Reference labels and no manually assigned priorities.

Review intervals:

- ✅ **Fluent**: 7 and 30 days
- 🔶 **Hinted / shaky**: 2, 7, and 21 days
- ❌ **Not yet fluent**: 1, 3, 7, 14, and 30 days

Assisted attempts never count as ✅; see Attempt types above.

## Catalog generation

`data/catalog.js` is generated from `../STUDY_PLAN.md`:

```bash
python3 scripts/generate_catalog.py
```

The generator validates:

- 254 unique problems
- Part 1 and Part 2 group mappings
- 22 roadmap blocks and 96 focused-session budgets
- Eight topical drills
- All compact Related references

The generated catalog contains metadata only—no solution notes, analysis text, or Java code.

## Tests

Run the automated tests with Node's built-in test runner:

```bash
npm test
```

The suite covers:

- Catalog integrity and roadmap budgets
- Markdown parse/serialize round trips
- Invalid log rejection
- Review-date intervals
- Automatic states and Established rules
- Weekday/weekend question counts
- Hard-question restrictions
- Due-review prioritization
- Unfamiliar Sunday transfers
- Manual overrides
- Drill scheduling

## Data safety

- Always export after recording results if direct file saving is unavailable.
- Do not replace a valid `REVIEW_LOG.md` with an invalid file; the parser rejects invalid imports.
- Keep backups if the file is stored in Git.
- The browser may cache interface state, but `REVIEW_LOG.md` remains the source of truth.
