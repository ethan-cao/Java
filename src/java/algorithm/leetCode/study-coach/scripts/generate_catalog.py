#!/usr/bin/env python3
"""Generate study-coach/data/catalog.js from STUDY_PLAN.md.

The generated catalog contains metadata only. It never includes solution notes,
analysis text, or Java code.
"""

from __future__ import annotations

import argparse
import json
import re
from pathlib import Path

FILE_ID_RE = re.compile(r"^[EMH]_\d+$")
COMPACT_ID_RE = re.compile(r"\b([EMH]\d+)\b")
GROUP_HEADING_RE = re.compile(r"^## (\d+)\. (.*?) \((\d+)\)$", re.MULTILINE)
P2_GROUP_HEADING_RE = re.compile(r"^### (\d+)\. (.*?) \((\d+)\)$", re.MULTILINE)
SUBSECTION_RE = re.compile(r"^\*\*(.+?)\*\*$")
ROADMAP_RE = re.compile(r"^\| (\d+) \| (.*?) \| (\d+) \|$")
DRILL_RE = re.compile(r"^\| (\d+) \| (.*?) \((\d+)\) \| (.*?) \|$")


def clean_cell(value: str) -> str:
    value = value.strip()
    value = value.replace(r"\|", "|")
    value = re.sub(r"`", "", value)
    value = re.sub(r"\*\*(.+?)\*\*", r"\1", value)
    return value.strip()


def split_table_row(line: str) -> list[str]:
    line = line.strip()
    if line.startswith("|"):
        line = line[1:]
    if line.endswith("|"):
        line = line[:-1]
    return [clean_cell(cell) for cell in re.split(r"(?<!\\)\|", line)]


def find_file_index(cells: list[str]) -> int | None:
    for index, cell in enumerate(cells):
        if FILE_ID_RE.fullmatch(cell):
            return index
    return None


def parse_problem_rows(lines: list[str], part2: bool = False) -> list[dict]:
    rows: list[dict] = []
    current_group: int | None = None
    current_subsection = ""

    for raw_line in lines:
        line = raw_line.rstrip()

        heading_match = P2_GROUP_HEADING_RE.match(line) if part2 else GROUP_HEADING_RE.match(line)
        if heading_match:
            current_group = int(heading_match.group(1))
            current_subsection = ""
            continue

        subsection_match = SUBSECTION_RE.match(line)
        if subsection_match and current_group is not None:
            current_subsection = subsection_match.group(1).strip()
            continue

        if not line.startswith("|") or current_group is None:
            continue

        cells = split_table_row(line)
        if len(cells) < 4 or set(cells[0]) <= {"-", " ", ":"}:
            continue

        file_index = find_file_index(cells)
        if file_index is None:
            continue

        title_index = file_index + 1
        if title_index >= len(cells):
            continue

        family = cells[0] if file_index != 0 else current_subsection
        title = cells[title_index]
        related_cell = cells[-1]
        related_ids = COMPACT_ID_RE.findall(related_cell)
        row = {
            "id": cells[file_index],
            "title": title,
            "family": family,
            "subsection": current_subsection,
            "related_ids": related_ids,
            "group": current_group,
        }

        if part2:
            technique_index = title_index + 1
            row["technique"] = cells[technique_index] if technique_index < len(cells) else ""

        rows.append(row)

    return rows


def parse_appendix(lines: list[str]) -> dict[str, dict]:
    appendix: dict[str, dict] = {}
    in_appendix = False

    for raw_line in lines:
        line = raw_line.rstrip()
        if line.startswith("## Appendix"):
            in_appendix = True
            continue
        if not in_appendix or not line.startswith("|"):
            continue

        cells = split_table_row(line)
        if len(cells) != 3 or not FILE_ID_RE.fullmatch(cells[0]):
            continue

        appendix[cells[0]] = {
            "p1_group": int(cells[1]),
            "p2_group": int(cells[2]),
        }

    return appendix


def parse_roadmap(lines: list[str]) -> list[dict]:
    in_roadmap = False
    blocks: list[dict] = []

    for raw_line in lines:
        line = raw_line.rstrip()
        if line.startswith("## 5. Focused-session roadmap"):
            in_roadmap = True
            continue
        if in_roadmap and line.startswith("### Hard problems inside the roadmap"):
            break
        if not in_roadmap:
            continue

        match = ROADMAP_RE.match(line)
        if not match:
            continue

        blocks.append(
            {
                "id": int(match.group(1)),
                "focus": match.group(2).strip(),
                "sessionBudget": int(match.group(3)),
            }
        )

    return blocks


def parse_drills(lines: list[str]) -> list[dict]:
    in_drills = False
    drills: list[dict] = []

    for raw_line in lines:
        line = raw_line.rstrip()
        if line.startswith("## 6. Topical drills"):
            in_drills = True
            continue
        if in_drills and line.startswith("## 7."):
            break
        if not in_drills or not line.startswith("|"):
            continue

        match = DRILL_RE.match(line)
        if not match:
            continue

        drills.append(
            {
                "afterBlock": int(match.group(1)),
                "name": match.group(2).strip(),
                "part2Group": int(match.group(3)),
                "emphasis": match.group(4).strip(),
            }
        )

    return drills


def roadmap_block_for(row: dict, group_to_block: dict[int, int]) -> int:
    group = row["group"]

    if group == 7:
        family = row["family"]
        if family in {"Matching", "Expression eval"}:
            return 7
        return 8

    if group == 16:
        subsection = row["subsection"]
        if subsection.startswith("Core") or subsection.startswith("Stock"):
            return 17
        if subsection.startswith("Knapsack"):
            return 18
        if subsection.startswith("Grid"):
            return 19
        if subsection.startswith("LIS") or subsection.startswith("String"):
            return 20

    return group_to_block[group]


def build_group_to_block(blocks: list[dict]) -> dict[int, int]:
    explicit: dict[int, list[int]] = {
        1: [1, 5],
        2: [2],
        3: [3],
        4: [4],
        5: [19],
        6: [6],
        9: [15],
        10: [8],
        11: [17],
        12: [9],
        13: [10, 11],
        14: [14],
        15: [12],
        16: [13],
        21: [18],
    }

    group_to_block: dict[int, int] = {}
    for block_id, groups in explicit.items():
        for group in groups:
            group_to_block[group] = block_id
    return group_to_block


def generate(plan_text: str) -> dict:
    part1_text = plan_text.split("# Part 1", 1)[1].split("# Part 2", 1)[0]
    part2_text = plan_text.split("# Part 2", 1)[1].split("# Part 3", 1)[0]
    part3_text = plan_text.split("# Part 3", 1)[1].split("## Appendix", 1)[0]

    p1_rows = parse_problem_rows(part1_text.splitlines(), part2=False)
    p2_rows = parse_problem_rows(part2_text.splitlines(), part2=True)
    appendix = parse_appendix(plan_text.splitlines())
    roadmap = parse_roadmap(part3_text.splitlines())
    drills = parse_drills(part3_text.splitlines())

    if len(appendix) != 254:
        raise ValueError(f"Expected 254 appendix problems, found {len(appendix)}")
    if len(p1_rows) != 254:
        raise ValueError(f"Expected 254 Part 1 rows, found {len(p1_rows)}")
    if len(p2_rows) != 254:
        raise ValueError(f"Expected 254 Part 2 rows, found {len(p2_rows)}")
    if len(roadmap) != 22:
        raise ValueError(f"Expected 22 roadmap blocks, found {len(roadmap)}")
    if len(drills) != 8:
        raise ValueError(f"Expected 8 drills, found {len(drills)}")

    p1_group_names: dict[int, str] = {}
    for match in GROUP_HEADING_RE.finditer(part1_text):
        p1_group_names[int(match.group(1))] = match.group(2).strip()

    p2_group_names: dict[int, str] = {}
    for match in P2_GROUP_HEADING_RE.finditer(part2_text):
        p2_group_names[int(match.group(1))] = match.group(2).strip()

    group_to_block = build_group_to_block(roadmap)
    block_order: dict[int, int] = {block["id"]: 0 for block in roadmap}

    p1_by_id = {row["id"]: row for row in p1_rows}
    p2_by_id = {row["id"]: row for row in p2_rows}

    problems: list[dict] = []
    for row in p1_rows:
        problem_id = row["id"]
        p2_row = p2_by_id[problem_id]
        appendix_entry = appendix[problem_id]
        if appendix_entry["p1_group"] != row["group"]:
            raise ValueError(f"P1 group mismatch for {problem_id}")
        if appendix_entry["p2_group"] != p2_row["group"]:
            raise ValueError(f"P2 group mismatch for {problem_id}")

        block = roadmap_block_for(row, group_to_block)
        block_order[block] += 1

        difficulty_letter = problem_id[0]
        problems.append(
            {
                "id": problem_id,
                "number": int(problem_id.split("_", 1)[1]),
                "title": row["title"],
                "difficulty": "Easy" if difficulty_letter == "E" else "Medium" if difficulty_letter == "M" else "Hard",
                "isHard": difficulty_letter == "H",
                "p1Group": row["group"],
                "p1GroupName": p1_group_names[row["group"]],
                "p1Family": row["family"],
                "p2Group": p2_row["group"],
                "p2GroupName": p2_group_names[p2_row["group"]],
                "p2Family": p2_row["family"],
                "relatedIds": row["related_ids"],
                "roadmapBlock": block,
                "roadmapOrder": block_order[block],
            }
        )

    expected_budget_total = 96
    actual_budget_total = sum(block["sessionBudget"] for block in roadmap)
    if actual_budget_total != expected_budget_total:
        raise ValueError(f"Roadmap budgets total {actual_budget_total}, expected {expected_budget_total}")

    expected_ids = set(appendix)
    actual_ids = {problem["id"] for problem in problems}
    if actual_ids != expected_ids:
        raise ValueError(f"Catalog ID mismatch: {sorted(expected_ids ^ actual_ids)}")

    for problem in problems:
        for related_id in problem["relatedIds"]:
            normalized = f"{related_id[0]}_{related_id[1:]}"
            if normalized not in expected_ids:
                raise ValueError(f"{problem['id']} references unknown problem {normalized}")

    catalog = {
        "schemaVersion": 1,
        "source": "STUDY_PLAN.md",
        "problemCount": len(problems),
        "roadmapBudgetTotal": actual_budget_total,
        "roadmapBlocks": roadmap,
        "drills": drills,
        "problems": problems,
    }
    return catalog


def write_catalog(catalog: dict, output: Path) -> None:
    body = json.dumps(catalog, ensure_ascii=False, indent=2)
    contents = f"""/** Generated by scripts/generate_catalog.py. Do not edit manually. */
const STUDY_CATALOG = {body};

if (typeof module !== "undefined" && module.exports) {{
  module.exports = STUDY_CATALOG;
}}
if (typeof window !== "undefined") {{
  window.STUDY_CATALOG = STUDY_CATALOG;
}}
"""
    output.parent.mkdir(parents=True, exist_ok=True)
    output.write_text(contents, encoding="utf-8")


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--plan", type=Path, default=Path(__file__).resolve().parents[2] / "STUDY_PLAN.md")
    parser.add_argument("--output", type=Path, default=Path(__file__).resolve().parents[1] / "data" / "catalog.js")
    args = parser.parse_args()

    catalog = generate(args.plan.read_text(encoding="utf-8"))
    write_catalog(catalog, args.output)
    print(f"Generated {catalog['problemCount']} problems -> {args.output}")


if __name__ == "__main__":
    main()
