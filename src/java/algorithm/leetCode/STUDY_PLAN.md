# LeetCode Study Plan — two categorisations + schedule

## Inventory
- **254 problems**: 54 Easy / 189 Medium / 11 Hard. Rename `E_226.temp.java` to `E_226.java`.
- 59 `.temp.java` files; 58 duplicate a final solution. Archive or delete them.
- `A_FrequentUse.java`: syntax and structure reference; skim before each phase. `A_Test.java`: scratch.
- Prefixes: `E_`, `M_`, `H_`. Most files hold several approaches, up to the ✅ optimum.

## How the parts fit
- **Part 1** orders problems by best approach. Follow it.
- **Part 2** groups the same problems by topic. Use it for drills and cross-technique contrast.
- Each problem appears once per part. `⇄` marks a secondary pattern.
- **Family** clusters drills. **Related** gives the strongest links, without underscores (`M131` = `M_131.java`); it may cross groups and need not be symmetric. Find IDs in the Appendix.

---

# Part 1 — Categorisation by best approach (19 groups)

## 1. Arrays & Hashing (13)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Pair lookup | `E_1` | Two Sum | hashmap, one pass | ↔ E167 (sorted version), M15 (kSum) |
| Frequency | `E_242` | Valid Anagram | 26-count array | ↔ E383, E387, M49 |
| Frequency | `E_383` | Ransom Note | same counting as 242 | ↔ E242, E387 |
| Frequency | `E_387` | First Unique Character | count + scan | ↔ E242, E383 |
| Frequency | `M_49` | Group Anagrams | count-key → hashmap of lists | ↔ E242, M438 (anagram signature in a window) |
| Set ops | `E_349` | Intersection of Two Arrays I | set, dedup | ↔ E350 |
| Set ops | `E_350` | Intersection of Two Arrays II | map counting, min count | ↔ E349 |
| Counting | `E_169` | Majority Element | Boyer–Moore vote, O(1) space | ↔ E136 (special-element tricks) |
| Counting | `E_997` | Find the Town Judge | in/out degree arrays | ↔ M207 (in-degree counting) |
| Counting | `M_442` | Find All Duplicates | in-place negation marking | ↔ M287 (dup family), E26 |
| Counting | `M_1007` | Min Domino Rotations | frequency per face + greedy min | ↔ E997 (count elimination) |
| GCD / math | `E_914` | X of a Kind in a Deck | GCD of all counts | — |
| Bitmask | `M_318` | Max Product of Word Lengths | 26-bit mask per word | — |

## 2. Two Pointers (24)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| In-place cleanup | `E_26` | Remove Duplicates from Sorted Array | slow/fast write pointer | ↔ E27, E283, M82 (sorted-list run deletion) |
| In-place cleanup | `E_27` | Remove Element | same template | ↔ E26, E283 |
| In-place cleanup | `E_88` | Merge Sorted Array | merge from the back | ↔ E977, M148 (list merge) |
| In-place cleanup | `E_283` | Move Zeroes | swap on non-zero | ↔ E27, E26 |
| In-place cleanup | `E_977` | Squares of Sorted Array | two ends → middle | ↔ E88, E167 |
| Sorted pair sums | `E_167` | Two Sum II | shrink on sum | ↔ E1 (hash version), M15, E633 |
| Sorted pair sums | `E_633` | Sum of Square Numbers | a,b pointers on range | ↔ E167, E69 (sqrt bound) |
| kSum family | `M_15` | 3Sum | sort + fix one + pair scan | ↔ M16, M18, E167, M611 |
| kSum family | `M_16` | 3Sum Closest | track closest diff | ↔ M15, M18 |
| kSum family | `M_18` | 4Sum | two fixed + pair scan, skip dups | ↔ M15, M16 |
| kSum family | `M_611` | Valid Triangle Number | sort + count pairs | ↔ M15 |
| Container & terrain | `M_11` | Container With Most Water | move the shorter wall | ↔ H42, M84 (stack view) |
| Container & terrain | `H_42` | Trapping Rain Water | best: two ends, O(1) space ⇄stack | ↔ M11, M84 (stack) |
| Partition / rearrange | `M_75` | Sort Colors | Dutch flag, 3 pointers | ↔ M324, M215 (partition) |
| Partition / rearrange | `M_324` | Wiggle Sort II | partition + virtual indexing | ↔ M75, M215 |
| Partition / rearrange | `E_189` | Rotate Array | triple reversal | ↔ M31 (in-place rearrange) |
| Partition / rearrange | `M_31` | Next Permutation | pivot, swap, reverse suffix | ↔ M556 (digit version), M46 |
| Partition / rearrange | `M_556` | Next Greater III | next permutation on digits | ↔ M31 (same template), M402 (digit-pruning contrast) |
| Palindrome center | `M_5` | Longest Palindromic Substring | expand-around-center, O(1) space | ↔ M647, M516 (DP version), E234 (list) |
| Palindrome center | `M_647` | Palindromic Substrings | same, count all | ↔ M5, M516 |
| Other scans | `M_240` | Search a 2D Matrix II | staircase from top-right, O(m+n) | ↔ M74 (bin-search version) |
| Other scans | `E_581` | Shortest Unsorted Subarray | scan ends inward | ↔ E665 (fix-up) |
| Other scans | `M_287` | Find the Duplicate Number | Floyd's cycle | ↔ M142 (same Floyd math), M442 |
| Other scans | `E_925` | Long Pressed Name | compare char runs | — |

## 3. Sliding Window (8)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Distinct-char windows | `M_3` | Longest Substring w/o Repeating | last-index map | ↔ M159, H340 (k generalization), H76 |
| Distinct-char windows | `M_159` | ≤2 Distinct Chars | count map, shrink | ↔ M3, H340 |
| Distinct-char windows | `H_340` | ≤K Distinct Chars | generalizes 159 | ↔ M159, M3, H76 |
| Anagram windows | `M_438` | Find All Anagrams | fixed window compare | ↔ M567, E242 (count base), M49 (signature grouping) |
| Anagram windows | `M_567` | Permutation in String | same, two strings | ↔ M438, E242 |
| Sum windows | `M_209` | Min Size Subarray Sum | shrink on sum ≥ target | ↔ M560 (prefix alternative), H76 |
| State windows | `M_978` | Turbulent Subarray | alternating-sign run | ↔ M376 (same condition) |
| Coverage | `H_76` | Minimum Window Substring | need/have counters | ↔ H340, M209, M567 |

## 4. Binary Search (13)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Classic | `E_704` | Binary Search | base template | ↔ E35, M34 |
| Classic | `E_35` | Search Insert Position | lower bound | ↔ E704, M34 |
| Classic | `E_69` | Sqrt(x) | integer sqrt | ↔ E367, E633 (pointer alternative) |
| Classic | `E_367` | Valid Perfect Square | same as 69 | ↔ E69, E633 |
| Rotated array | `M_33` | Rotated Sorted Search I | half-sorted check | ↔ M81, M153 |
| Rotated array | `M_81` | Rotated Sorted Search II | + duplicate skip | ↔ M33, M153 |
| Rotated array | `M_153` | Rotated Minimum | compare to right end | ↔ M33, M81 |
| Peak | `M_162` | Find Peak Element | climb the slope | — |
| Range bounds | `M_34` | First & Last Position | two bound calls | ↔ E35, E704 |
| 2D | `M_74` | Search a 2D Matrix | treat as flattened 1D | ↔ M240 (staircase version) |
| On the answer | `M_875` | Koko Eating Bananas | min feasible speed | ↔ M1011 (identical template) |
| On the answer | `M_1011` | Ship Packages | min feasible capacity | ↔ M875 |
| On the answer | `M_378` | Kth in Sorted Matrix | count ≤ mid ⇄heap | ↔ M373 (heap alternative) |

## 5. Prefix sums & products (4)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Immutable range query | `E_303` | Range Sum Query | 1D prefix | ↔ M304 |
| Immutable range query | `M_304` | Range Sum Query 2D | 2D prefix | ↔ E303 |
| Subarray target | `M_560` | Subarray Sum = K | prefix + hashmap | ↔ M209 (window alternative), M437 (prefix counts on tree paths) |
| Prefix products | `M_238` | Product Except Self | prefix/suffix products | ↔ M560 (sums), M152 (products) |

## 6. Linked List (13)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Basic ops | `E_83` | Remove Duplicates Sorted List | skip equals | ↔ M82 (harder version), E203 |
| Basic ops | `E_203` | Remove Elements | dummy head | ↔ E83, M19 |
| Basic ops | `E_160` | Intersection of Two Lists | two-pointer switch | ↔ M19 (pointer alignment around end/length) |
| Basic ops | `M_2` | Add Two Numbers | carry walk | ↔ E67 (add binary), M43 |
| Basic ops | `M_19` | Remove Nth From End | two-pointer gap | ↔ E160 (pointer alignment around end/length) |
| Basic ops | `M_24` | Swap Pairs | rewire in pairs | ↔ M19, M328 |
| Basic ops | `M_82` | Remove Duplicates II | skip whole runs | ↔ E83, E26 (array version) |
| Fast & slow | `E_141` | Cycle Detect | meet check | ↔ M142 (follow-up), M287 (Floyd math) |
| Fast & slow | `M_142` | Cycle Entry | Floyd entry math | ↔ E141, M287 |
| Reorder check | `E_234` | Palindrome List | reverse half | ↔ M5 (palindrome family) |
| Rebuild | `M_138` | Copy with Random | interleave + split | — |
| Rebuild | `M_148` | Sort List | merge sort | ↔ E88 (merge core) |
| Rebuild | `M_328` | Odd Even List | two runs rejoin | ↔ M24 |

## 7. Stack & Monotonic Stack (17)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Matching | `E_20` | Valid Parentheses | push open, pop match | ↔ M678 (greedy variant), M22 (generation), M856, M394 |
| Matching | `M_856` | Score of Parentheses | depth/count | ↔ E20, M394 |
| Expression eval | `M_150` | Reverse Polish Notation | operand stack | ↔ M227, H224, H772 |
| Expression eval | `M_227` | Basic Calculator II | last-op sign trick | ↔ M150, H224, H772 |
| Expression eval | `M_394` | Decode String | repeat frames | ↔ E20 (paren nesting) |
| Expression eval | `H_224` | Basic Calculator I | + nested parens | ↔ M227, H772, M150 |
| Expression eval | `H_772` | Basic Calculator III | + precedence | ↔ H224, M227 |
| Next greater | `M_496` | Next Greater I | classic monotonic | ↔ M503 (circular), M739 |
| Next greater | `M_503` | Next Greater II | + circular wrap | ↔ M496, M739 (array next-greater variants) |
| Next greater | `M_739` | Daily Temperatures | distance variant | ↔ M496, M901 |
| Previous greater / span | `M_901` | Online Stock Span | previous greater + accumulated span | ↔ M739 (direction contrast), M1019 (order/direction contrast) |
| Next greater | `M_1019` | Next Greater in Linked List | list variant | ↔ M901, M496 |
| Next greater | `M_84` | Largest Rectangle Histogram | area variant | ↔ H85 (direct use), H42, M907, M1130 (stack solution) |
| Next greater | `M_907` | Sum of Subarray Minimums | contribution variant | ↔ M84 |
| Next greater | `M_456` | 132 Pattern | hold candidate 3 | ↔ M496 |
| Next greater | `M_402` | Remove K Digits | greedy pop tall | ↔ M556 (digit ops) |
| Next greater | `H_85` | Maximal Rectangle | run 84 per row ⇄DP | ↔ M84, M221 (DP version) |

## 8. Heap & Top-K selection (6)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| kth order | `M_215` | Kth Largest in Array | quickselect O(n) avg | ↔ M347, M378 (bin-search alt) |
| Top frequent | `M_347` | Top K Frequent Elements | bucket O(n) or heap | ↔ M692, M451, M215 |
| Top frequent | `M_692` | Top K Frequent Words | heap with tie-break | ↔ M347 |
| Top frequent | `M_451` | Sort Characters by Frequency | bucket | ↔ M347, M692 |
| Merge pairs | `M_373` | K Pairs Smallest Sums | heap over indices | ↔ M378, M264 (merge sources) |
| Scheduling | `M_253` | Meeting Rooms II | min-heap of end times ⇄sorted starts/ends sweep | ↔ M56 (interval sorting base), M435 (compatible-set scheduling contrast) |

## 9. Binary Tree (27)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Compare & simple ops | `E_100` | Same Tree | parallel DFS | ↔ E101 |
| Compare & simple ops | `E_101` | Symmetric Tree | mirror DFS | ↔ E100, E226 |
| Compare & simple ops | `E_226` | Invert Binary Tree | swap children (temp file only) | ↔ E101, E100 |
| Compare & simple ops | `E_112` | Path Sum | DFS with remainder | ↔ M437 (count version), E257 |
| Compare & simple ops | `E_257` | Binary Tree Paths | DFS + path backtrack | ↔ E112, M437 |
| DFS traversals | `M_94` | Inorder | stack iterative + recursive | ↔ M144, M145, M173, M230 |
| DFS traversals | `M_144` | Preorder | stack | ↔ M94, M145, M114 (preorder rewiring) |
| DFS traversals | `M_145` | Postorder | reverse-preorder or stack | ↔ M94, M144 |
| Level BFS | `M_102` | Level Order Traversal | queue by level | ↔ M103, M199 |
| Level BFS | `M_103` | Zigzag Order | + direction flip | ↔ M102 |
| Level BFS | `M_199` | Right Side View | last of level | ↔ M102, M513, M515 |
| Level BFS | `M_513` | Bottom Left Value | first of last level | ↔ M199, M515 |
| Level BFS | `M_515` | Row Maximums | max per level | ↔ M199, M513 |
| Level BFS | `M_429` | N-ary Level Order | queue on children | ↔ M102 |
| Next-right | `M_116` | Populating Next Right I | perfect tree | ↔ M117 |
| Next-right | `M_117` | Populating Next Right II | + gap handling | ↔ M116 |
| Construction | `M_105` | Build Pre+Inorder | divide on root index | ↔ M889, M1008, M654 (root-split range construction) |
| Construction | `M_889` | Build Pre+Postorder | divide on child | ↔ M105, M1008 |
| Construction | `M_654` | Maximum Binary Tree | recursive max split | ↔ M105 (root-split range construction) |
| Construction | `M_427` | Quad Tree | recursive quadrants | — |
| Construction | `M_109` | Sorted List → BST | slow/mid split ⇄list | ↔ M148 (source list), M1008 (BST construction) |
| Flatten | `M_114` | Flatten to Linked List | pre-order sequence + right-spine rewire | ↔ M144 (preorder vs in-place rewiring) |
| LCA & paths | `M_236` | Lowest Common Ancestor | post-order returns | — |
| LCA & paths | `M_437` | Path Sum III | prefix sum on tree | ↔ E112 (base), M560 (same prefix trick) |
| Tree → graph BFS | `M_863` | Distance K | parent map + BFS | ↔ M102 (layered BFS; parent edges make the tree a graph) |
| Tree DP | `M_337` | House Robber III | rob/skip per node | ↔ M198 (line version), M740 |
| Tree DP | `M_1339` | Split Product | total × subtree | — |

## 10. Binary Search Tree (7)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Validate / ops | `M_98` | Validate BST | bounds or inorder | ↔ M230 |
| Validate / ops | `M_230` | Kth Smallest in BST | inorder counter | ↔ M173, M98, E538 |
| Validate / ops | `M_173` | BST Iterator | controlled inorder stack | ↔ M230 |
| Validate / ops | `M_701` | Insert into BST | walk to leaf | ↔ M98 (bounds) |
| Validate / ops | `E_538` | Greater Tree | reverse inorder acc | ↔ M230 |
| Construction | `M_1008` | Build from Preorder | bounds | ↔ M105, M109 |
| Construction | `M_95` | Unique BSTs II | enumerate all roots | ↔ M96 (count version) |

## 11. Trie (4)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Basics | `M_208` | Implement Trie | children + flag | ↔ M211, M648 |
| Basics | `M_211` | Add & Search Words | + wildcard DFS | ↔ M208, M648 |
| Applications | `M_648` | Replace Words | prefix replace | ↔ M208, M211 |
| Applications | `M_421` | Max XOR of Two Numbers | binary trie | ↔ M208 |

## 12. Graph — BFS / DFS (12)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Islands / flood fill | `M_200` | Number of Islands | DFS sink | ↔ M695 (area variant), M130, M934, M79 (visited restoration contrast) |
| Islands / flood fill | `M_695` | Max Area of Island | DFS returns area | ↔ M200 |
| Islands / flood fill | `M_130` | Surrounded Regions | border DFS first | ↔ M200, M417 (reverse flow) |
| Islands / flood fill | `M_417` | Pacific Atlantic | ocean DFS inward | ↔ M130 |
| Islands / flood fill | `M_934` | Shortest Bridge | DFS + multi-source BFS | ↔ M200, M542 (one island supplies all BFS seeds) |
| Multi-source BFS | `M_542` | 01 Matrix | all zeros as seeds | ↔ M1162 (identical template), M1091 (multi-source vs shortest path) |
| Multi-source BFS | `M_1162` | Far from Land | same seed pattern | ↔ M542 |
| Single-source grid BFS | `M_1091` | Binary Matrix Path | shortest path to target | ↔ M542 (distance-transform contrast), M752 (generated-state shortest path) |
| Implicit BFS | `M_127` | Word Ladder | word neighbors ⇄strings | ↔ M752 |
| Implicit BFS | `M_752` | Open the Lock | dial states | ↔ M127, M1306, M1091 (explicit grid vs generated dial states) |
| Implicit BFS | `M_1306` | Jump Game III | index graph | ↔ M752, M55 (greedy version) |
| Misc DFS | `M_529` | Minesweeper | reveal DFS | ↔ M200 |

## 13. Graph — topo, shortest, connectivity (9)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Topological order | `M_207` | Course Schedule | cycle check | ↔ M210, E997 (in-degree base) |
| Topological order | `M_210` | Course Schedule II | emit order | ↔ M207 |
| Tree centers / leaf peel | `M_310` | Min Height Trees | undirected leaf peeling | ↔ M207, M210 (degree-queue vs DAG topological order) |
| Bipartite | `M_785` | Is Graph Bipartite | 2-color BFS/DFS | — |
| Weighted shortest | `M_743` | Network Delay Time | Dijkstra | ↔ M787, M1129 |
| Weighted shortest | `M_787` | Cheapest Flights | bounded-hop Bellman-Ford / DP | ↔ M743, M1129 (weighted-hop DP vs unweighted state BFS) |
| State-constrained BFS | `M_1129` | Alternating Colors | unweighted BFS on (node, incoming color) | ↔ M743, M787 (unweighted state-space contrast) |
| Euler path | `M_332` | Reconstruct Itinerary | Hierholzer DFS | — |
| Union-find | `M_1319` | Make Network Connected | count components | ↔ M200 (component counting) |

## 14. Backtracking (15)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Subsets | `M_78` | Subsets | include/exclude | ↔ M90 (dup variant), M77 |
| Subsets | `M_90` | Subsets II | + sort, skip dup level | ↔ M78, M47 (same dup logic), M40 (target + dup skip) |
| Combinations | `M_77` | Combinations | index start | ↔ M78, M39, M216 |
| Combinations | `M_39` | Combination Sum | enumerate unordered combinations; reuse allowed | ↔ M40 (no-reuse variant), M216, M377 (ordered counting), M518 (unordered counting) |
| Combinations | `M_40` | Combination Sum II | no reuse, dup skip | ↔ M39, M47, M90 (same duplicate-skip logic) |
| Combinations | `M_216` | Combination Sum III | size k, 1..9 | ↔ M77, M39 |
| Permutations | `M_46` | Permutations | used[] | ↔ M47 (dup version), M31 (iterative next) |
| Permutations | `M_47` | Permutations II | + dup skip | ↔ M46, M90 |
| Word / grid search | `M_79` | Word Search | mark & restore | ↔ H37 (grid constraints), M200 (permanent visited vs restore) |
| Partition | `M_131` | Palindrome Partitioning | cut positions ⇄DP | ↔ H132 (DP version), M139 (partition family) |
| Constraint build | `M_17` | Phone Letter Combinations | digit map | ↔ M22 |
| Constraint build | `M_22` | Generate Parentheses | open/close budget | ↔ M17, E20, M678 (validation counterparts) |
| Constraint build | `H_37` | Sudoku Solver | constraint prune | ↔ M79 |
| Pruned bucketing | `M_473` | Matchsticks to Square | 4-side buckets | ↔ M698 (identical template) |
| Pruned bucketing | `M_698` | K Equal Sum Subsets | k buckets + memo | ↔ M473, M416 (k=2 special case; not general-k DP) |

## 15. Greedy & Intervals (14)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Sort + greedy | `E_455` | Assign Cookies | two-pointer match | — |
| Sort + greedy | `M_406` | Queue Reconstruction | tall first, insert by k | — |
| Sort + greedy | `M_376` | Wiggle Subsequence | count direction flips | ↔ M300 (subsequence DP/greedy contrast), M978 (contiguous-run version) |
| Local fix | `E_665` | Non-decreasing Array | one fix pass | ↔ E581 (sortedness) |
| Local fix | `E_860` | Lemonade Change | bill counters | — |
| Jumps | `M_55` | Jump Game | farthest reach | ↔ M1306 (BFS version) |
| Jumps | `M_621` | Task Scheduler | O(n) slot formula ⇄heap | — |
| Circular scan | `M_134` | Gas Station | restart on deficit | ↔ M55 (feasibility greedy) |
| Intervals | `M_56` | Merge Intervals | sort + sweep | ↔ M986, M435, M452 |
| Intervals | `M_986` | Interval Intersections | two-list walk | ↔ M56 |
| Intervals | `M_435` | Non-overlapping Intervals | keep min end | ↔ M452, M646, M56 |
| Intervals | `M_452` | Arrows & Balloons | earliest-end point stabbing | ↔ M435, M646 (same earliest-end scan) |
| Intervals | `M_646` | Pair Chain | chain by end | ↔ M435, M452 (same earliest-end scan), M300, H354 (LIS-style alternatives) |
| Parenthesis bounds | `M_678` | Valid Parenthesis String | lo/hi range | ↔ E20, M22 |

## 16. Dynamic Programming (45)
**Core 1D (15)**
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Count paths | `E_70` | Climbing Stairs | fib roll | ↔ E746, M377 (variable steps), M91 |
| Count paths | `E_746` | Min Cost Climbing Stairs | 2-state roll | ↔ E70 |
| Generate table | `E_118` | Pascal's Triangle | row build | ↔ M62 (same Pascal/binomial recurrence) |
| No-adjacent pick | `M_198` | House Robber | rob/skip | ↔ M740 (bucket restate), M337 (tree version) |
| No-adjacent pick | `M_740` | Delete & Earn | buckets + robber | ↔ M198, M337 |
| Kadane | `M_53` | Maximum Subarray | local/global max | ↔ M152, E121 (stock view) |
| Kadane | `M_152` | Max Product Subarray | track min too | ↔ M53 |
| Decode counts | `M_91` | Decode Ways | legal 1/2-digit final segment | ↔ E70 (step counting), M139 (prefix-segmentation DP) |
| Catalan count | `M_96` | Unique BSTs | sum over roots | ↔ M95 (generate version), M1130 (root/interval decomposition) |
| Break integer | `M_343` | Integer Break | max product split | ↔ M279 (additive integer decomposition) |
| Smallest generator | `M_264` | Ugly Number II | 3-pointer DP | ↔ M373 (merge sources) |
| Run counting | `M_413` | Arithmetic Slices | extend/reset run; accumulate count | ↔ M978 (extend/reset adjacent-comparison run) |
| Interval DP | `M_1130` | Min-Cost Tree from Leaves | interval DP; O(n) stack in file | ↔ M84 (monotonic-stack contrast), M96 (root/interval decomposition) |
| Schedule DP | `M_983` | Min Cost For Tickets | prefix lookback over 1/7/30-day coverage | ↔ M139 (prefix lookback: min cost vs reachability) |
| Count ways | `M_1155` | Dice Rolls Target Sum | ordered sequences + exact dice count | ↔ M377 (unbounded ordered-sequence recurrence) |

**Stock state machines (6)** — one template, rising difficulty
| File | Problem | Notes | Related |
|---|---|---|---|
| `E_121` | Stock I | one transaction | ↔ M122, M53 (Kadane view) |
| `M_122` | Stock II | unlimited | ↔ E121, M714 |
| `M_714` | Stock + Fee | minus fee on sell | ↔ M122, M309 |
| `M_309` | Stock + Cooldown | 3 states | ↔ M122, M714 |
| `H_123` | Stock III | ≤2 transactions | ↔ H188 (generalization), E121 |
| `H_188` | Stock IV | ≤k transactions | ↔ H123 |

**Knapsack (7)**
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| 0/1 subset | `M_416` | Equal Subset Partition | target = sum/2 | ↔ M494 (± transform), M474 |
| 0/1 subset | `M_474` | Ones & Zeroes | 2D weight | ↔ M416, M494 |
| 0/1 subset | `M_494` | Target Sum | ± transform | ↔ M416, M474 (0/1 capacity/count contrasts) |
| Unbounded | `M_322` | Coin Change | min coins | ↔ M518 (count version), M279 |
| Unbounded | `M_518` | Coin Change II | count combos | ↔ M322, M377, M39 (unordered enumeration vs counting) |
| Unbounded | `M_279` | Perfect Squares | min squares | ↔ M322, M343 (additive decomposition, different objective) |
| Count orders | `M_377` | Combination Sum IV | counts permutations | ↔ M518 (order irrelevant), E70, M39 (enumerate combinations), M1155 (adds exact dice-count dimension) |

**LIS family (3)**
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| LIS | `M_300` | Longest Increasing Subsequence | DP; patience O(n log n) | ↔ M673, H354, M646 (pair-chain DP vs earliest-end greedy) |
| LIS | `M_673` | Number of LIS | + count array | ↔ M300 |
| LIS | `H_354` | Russian Doll Envelopes | sort + LIS | ↔ M300, M646 (greedy counterpart) |

**Grid / state (8)**
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Path counts | `M_62` | Unique Paths | rolling row | ↔ M63 (obstacles), M64, E118 (Pascal/binomial recurrence) |
| Path counts | `M_63` | Unique Paths II | + obstacles | ↔ M62 |
| Path cost | `M_64` | Min Path Sum | rolling row | ↔ M62, M120, M741 |
| Path cost | `M_120` | Triangle | bottom-up roll | ↔ M64 |
| Region DP | `M_221` | Maximal Square | heights + neighbors | ↔ H85 (rectangle version) |
| State memo | `M_576` | Out of Boundary Paths | memo on moves | ↔ M688 (identical template) |
| State memo | `M_688` | Knight Probability | memo on steps | ↔ M576 |
| Double walk | `M_741` | Cherry Pickup | two walkers | ↔ M64 |

**String / LCS (6)**
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| LCS base | `M_1143` | Longest Common Subsequence | base template | ↔ M583, M718, M516 |
| LCS variant | `M_583` | Delete Operation | LCS-derived | ↔ M1143 |
| Contiguous common | `M_718` | Repeated Subarray | reset to 0 on mismatch | ↔ M1143 (contiguous subarray vs noncontiguous subsequence) |
| Interval DP | `M_516` | Longest Palindromic Subsequence | interval DP | ↔ M5 (substring version), M1143 (LCS with reverse) |
| Reachability | `M_139` | Word Break | dict reachability | ↔ M131 (partition enumeration), M91 (prefix-segmentation DP), M983 (reachability vs min-cost lookback) |
| Min cuts | `H_132` | Palindrome Partitioning II | cut DP ⇄backtracking | ↔ M131 |

## 17. Matrix — in-place manipulation (6)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Traverse | `M_54` | Spiral Matrix I | boundary walk | ↔ M59 |
| Traverse | `M_59` | Spiral Matrix II | fill while walking | ↔ M54 |
| Rotate | `M_48` | Rotate Image | transpose + flip | — |
| Zero & simulate | `M_73` | Set Matrix Zeroes | first row/col markers | ↔ M289 (in-place tricks) |
| Zero & simulate | `M_289` | Game of Life | in-place 2-bit state | ↔ M73 |
| Precompute | `M_361` | Bomb Enemy | wall-delimited row/col counts | — |

## 18. Math & Bit Manipulation (12)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Bit tricks | `E_136` | Single Number | XOR | — |
| Bit tricks | `E_231` | Power of Two | n & (n-1) | ↔ E338 (single-set-bit special case of popcount) |
| Bit tricks | `E_190` | Reverse Bits | shift accumulate | ↔ E338 (bit counting) |
| Bit tricks | `E_338` | Counting Bits | DP on bits | ↔ E190, E231 (single-set-bit special case) |
| Bit tricks | `E_405` | To Hexadecimal | hex nibbles | ↔ E168 (base conversion) |
| Base conversion | `E_67` | Add Binary | carry string add | ↔ M43, M2 (list version) |
| Base conversion | `E_168` | Column Title | base-26 | ↔ E405 |
| Base conversion | `M_43` | Multiply Strings | school multiplication | ↔ E67, M2 |
| Number theory | `E_204` | Count Primes | sieve | — |
| Number theory | `E_754` | Reach a Number | step math | — |
| Number theory | `M_50` | Pow(x, n) | binary exponentiation | ↔ M29 |
| Number theory | `M_29` | Divide Two Integers | shift subtract | ↔ M50 |

## 19. Design (5)
| Family | File | Problem | Notes | Related |
|---|---|---|---|---|
| Stack / queue wrappers | `E_155` | Min Stack | min-pair stack | ↔ E232 |
| Stack / queue wrappers | `E_232` | Queue via Stacks | two stacks | ↔ E155 |
| O(1) maps | `M_146` | LRU Cache | hashmap + DLL | ↔ M380 |
| O(1) maps | `M_380` | Insert Delete GetRandom | array + index map | ↔ M146 |
| Ordered access | `M_981` | Time-based KV Store | binary search inside | ↔ M34 (bounds search) |

### Judgment calls — where approaches compete, and the bucket I chose
| Problem | Competing approaches | Chosen category | Why |
|---|---|---|---|
| `H_42` Rain Water | stack / two pointers / DP | Two Pointers | O(n) time **and** O(1) space — strictly best |
| `M_240` Search 2D II | binary search / staircase | Two Pointers | O(m+n) beats O(m log n) |
| `M_5`, `M_647` | DP / expand-center | Two Pointers | same O(n²) time, O(1) space, simpler |
| `M_287` Duplicate | binary search / Floyd | Two Pointers | O(n) time, O(1) space, no mutation |
| `M_378` Kth Matrix | heap / value-range search | Binary Search | O(n log(range)) < heap worst case |
| `M_621` Task Scheduler | heap / slot formula | Greedy | O(n) formula beats heap simulation |
| `M_264` Ugly II | heap / 3-pointer DP | DP | O(n) vs O(n log n) |
| `M_96` Unique BSTs | DP / BST construction | DP | Catalan recurrence is the solution |
| `M_95` Unique BSTs II | DP / backtracking | BST | it *constructs* trees; recursion family |
| `M_421` Max XOR | trie / brute pairs | Trie | binary trie is the only practical optimal |
| `M_238` Prod Except Self | prefix products vs division | Prefix | prefix/suffix product arrays |
| `M_1130` Min-Cost Tree | interval DP / monotonic stack | DP | interval DP teachable; stack O(n) noted |
| `M_300` LIS | DP / patience sort | DP | DP is the core; patience = O(n log n) upgrade |
| `M_347`, `M_692`, `M_451`, `M_215` | heap / bucket / quickselect | Heap & Top-K | one coherent drill; best variant noted per row |
| `E_925` Long Pressed | hashmap / two pointers | Two Pointers | char-run comparison is two-pointer |
| `M_31` Next Permutation | greedy / array ops | Two Pointers | pivot + suffix reverse |
| `M_556` Next Greater III | next-greater title / next permutation | Two Pointers | same pivot + swap + suffix reversal as `M_31`; monotonic stack is not the organizing solution |
| `M_406` Queue Reconstruct | sorting + insertion | Greedy | sort-then-place greedy |

---

# Part 2 — Categorisation by question topic (24 groups)

The same problems by question topic; technique tags point to Part 1.

### 1. Palindromes (5)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Substring | `M_5` | Longest Palindromic Substring | expand-around-center | ↔ M647, M516, E234 (list version) |
| Substring | `M_647` | Palindromic Substrings | expand-around-center | ↔ M5, M516 |
| Subsequence | `M_516` | Longest Palindromic Subsequence | interval DP | ↔ M5 (substring), M1143 (LCS with reverse) |
| Partition | `M_131` | Palindrome Partitioning | backtracking | ↔ H132 (DP version), M139 (partition family) |
| Partition | `H_132` | Palindrome Partitioning II | min-cut DP | ↔ M131 |

(234 palindrome linked list → group 11.)

### 2. Stock price series (6)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| One transaction | `E_121` | Stock I | state machine | ↔ M122, M53 (Kadane view) |
| Unlimited | `M_122` | Stock II | state machine | ↔ E121, M714 |
| Unlimited + fee | `M_714` | Stock with Fee | state machine | ↔ M122, M309 |
| Unlimited + cooldown | `M_309` | Stock with Cooldown | 3 states | ↔ M122, M714 |
| ≤2 transactions | `H_123` | Stock III | 2-transaction states | ↔ H188, E121 |
| ≤k transactions | `H_188` | Stock IV | k-transaction states | ↔ H123 |

(901 Stock Span → group 22; 134 Gas Station → group 21.)

### 3. Intervals & scheduling (7)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Merge | `M_56` | Merge Intervals | sort + sweep | ↔ M986, M435 |
| Intersect | `M_986` | Interval List Intersections | two-list walk | ↔ M56 |
| Overlap removal | `M_435` | Non-overlapping Intervals | keep min end | ↔ M452, M646, M253 |
| Point stabbing | `M_452` | Arrows to Burst Balloons | earliest-end greedy | ↔ M435, M646 (same earliest-end scan) |
| Chain | `M_646` | Max Length of Pair Chain | chain by end | ↔ M435, M452 (same earliest-end scan), M300, H354 (LIS-style alternatives) |
| Meeting load | `M_253` | Meeting Rooms II | min-heap ⇄sorted starts/ends sweep | ↔ M56 (interval sorting base), M435 (compatible-set scheduling contrast) |
| Scheduling with idle | `M_621` | Task Scheduler | slot formula | — |

### 4. Parentheses & expressions (9)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Matching | `E_20` | Valid Parentheses | stack | ↔ M678, M22 |
| Matching | `M_678` | Valid Parenthesis String | greedy bounds | ↔ E20, M22 |
| Matching | `M_856` | Score of Parentheses | stack | ↔ E20 |
| Generation | `M_22` | Generate Parentheses | backtracking | ↔ E20, M678 (validation counterparts), M17 |
| Encoding | `M_394` | Decode String | stack | ↔ E20 |
| Evaluation | `M_150` | Reverse Polish Notation | stack | ↔ M227, H224, H772 |
| Evaluation | `M_227` | Basic Calculator II | stack | ↔ M150, H224, H772 |
| Evaluation | `H_224` | Basic Calculator I | stack | ↔ M227, H772 |
| Evaluation | `H_772` | Basic Calculator III | stack | ↔ H224, M227, M150 |

### 5. Subarrays & bar-terrain (11)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Kadane | `M_53` | Maximum Subarray | DP | ↔ M152 |
| Kadane | `M_152` | Maximum Product Subarray | DP | ↔ M53 |
| Sum target | `M_209` | Min Size Subarray Sum | sliding window | ↔ M560 (prefix contrast), H76 |
| Sum target | `M_560` | Subarray Sum Equals K | prefix + hashmap | ↔ M209, M437 (prefix counts on tree paths) |
| Products | `M_238` | Product Except Self | prefix/suffix | ↔ M560 |
| Runs | `M_413` | Arithmetic Slices | run-state DP | ↔ M978 (extend/reset adjacent-comparison run) |
| Runs | `M_978` | Longest Turbulent Subarray | two-pointer | ↔ M376, M413 (longest run vs accumulated run count) |
| Min contribution | `M_907` | Sum of Subarray Minimums | monotonic stack | ↔ M84 |
| Terrain | `M_11` | Container With Most Water | two pointers | ↔ H42 |
| Terrain | `M_84` | Largest Rectangle Histogram | monotonic stack | ↔ H42, M907, H85 |
| Terrain | `H_42` | Trapping Rain Water | two pointers | ↔ M11, M84 |

### 6. Subsequences & string comparison (7)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Increasing | `M_300` | Longest Increasing Subsequence | DP / patience | ↔ M673, H354, M646 (pair-chain DP vs earliest-end greedy) |
| Increasing | `M_673` | Number of Longest LIS | DP + counts | ↔ M300 |
| Increasing (2D) | `H_354` | Russian Doll Envelopes | sort + LIS | ↔ M300, M646 (greedy contrast) |
| Alternating | `M_376` | Wiggle Subsequence | greedy flips | ↔ M978 |
| LCS | `M_1143` | Longest Common Subsequence | DP | ↔ M583, M718, M516 |
| LCS variant | `M_583` | Delete Operation for Two Strings | DP | ↔ M1143 |
| Contiguous common | `M_718` | Max Length of Repeated Subarray | DP; reset on mismatch | ↔ M1143 (contiguous subarray vs noncontiguous subsequence) |

### 7. Anagrams & frequency (11)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Compare | `E_242` | Valid Anagram | count array | ↔ E383, E387, M49 |
| Compare | `E_383` | Ransom Note | count array | ↔ E242 |
| Compare | `E_387` | First Unique Character | count array | ↔ E242 |
| Compare | `M_49` | Group Anagrams | count-key hashmap | ↔ E242, M438 (anagram signature in a window) |
| Set ops | `E_349` | Intersection of Two Arrays I | set | ↔ E350 |
| Set ops | `E_350` | Intersection of Two Arrays II | map counting | ↔ E349 |
| Window match | `M_438` | Find All Anagrams in a String | sliding window | ↔ M567, E242, M49 (signature grouping) |
| Window match | `M_567` | Permutation in String | sliding window | ↔ M438 |
| Marking | `M_442` | Find All Duplicates | in-place negation | ↔ M287 (dup family), E26 |
| Frequency sort | `M_451` | Sort Characters by Frequency | bucket | ↔ M347 (top-k version), M692 |
| Group limit | `E_914` | X of a Kind in a Deck | GCD | — |

### 8. Strings & word puzzles (9)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Distinct substring | `M_3` | Longest Substring w/o Repeating | sliding window | ↔ M159, H340 |
| Distinct substring | `M_159` | ≤2 Distinct Chars | sliding window | ↔ M3, H340 |
| Distinct substring | `H_340` | ≤K Distinct Chars | sliding window | ↔ M159 |
| Coverage | `H_76` | Minimum Window Substring | sliding window | ↔ H340, M209 |
| Typing compare | `E_925` | Long Pressed Name | two pointers | — |
| Transform chain | `M_127` | Word Ladder | BFS | ↔ M752 |
| Dictionary reach | `M_139` | Word Break | DP | ↔ M131 (partition enumeration), M91 (prefix segmentation), M983 (reachability vs min-cost lookback) |
| Word pair | `M_318` | Max Product of Word Lengths | bitmask | — |
| Prefix replace | `M_648` | Replace Words | trie | ↔ M208 |

### 9. Grid & board (28)
**Matrix manipulation (6)**
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Rotate | `M_48` | Rotate Image | transpose + flip | — |
| Traverse | `M_54` | Spiral Matrix I | boundary walk | ↔ M59 |
| Traverse | `M_59` | Spiral Matrix II | fill + walk | ↔ M54 |
| Marking | `M_73` | Set Matrix Zeroes | marker rows | ↔ M289 |
| Simulate | `M_289` | Game of Life | in-place bits | ↔ M73 |
| Precompute | `M_361` | Bomb Enemy | wall-delimited row/col counts | — |

**Search in matrix (2)**
| File | Problem | Technique | Related |
|---|---|---|---|
| `M_74` | Search a 2D Matrix | binary search | ↔ M240 |
| `M_240` | Search a 2D Matrix II | staircase walk | ↔ M74 |

**Flood fill / regions (5)**
| File | Problem | Technique | Related |
|---|---|---|---|
| `M_200` | Number of Islands | DFS | ↔ M695, M130, M934, M529, M79 (visited restoration contrast) |
| `M_695` | Max Area of Island | DFS | ↔ M200 |
| `M_130` | Surrounded Regions | border DFS | ↔ M200, M417 |
| `M_417` | Pacific Atlantic Water Flow | dual DFS | ↔ M130 |
| `M_934` | Shortest Bridge | DFS + multi-source BFS | ↔ M200, M542 (one island supplies all BFS seeds) |

**Grid distance & shortest-path BFS (3)**
| File | Problem | Technique | Related |
|---|---|---|---|
| `M_542` | 01 Matrix | multi-source BFS from zeros | ↔ M1162, M1091 (multi-source vs shortest path) |
| `M_1162` | As Far from Land as Possible | multi-source BFS from land | ↔ M542 |
| `M_1091` | Shortest Path in Binary Matrix | single-source BFS | ↔ M542 (distance-transform contrast), M752 (generated-state shortest path) |

**Path DP on grid (7)**
| File | Problem | Technique | Related |
|---|---|---|---|
| `M_62` | Unique Paths | DP | ↔ M63, M64, E118 (Pascal/binomial recurrence) |
| `M_63` | Unique Paths II | DP | ↔ M62 |
| `M_64` | Min Path Sum | DP | ↔ M62, M120, M741 |
| `M_120` | Triangle | DP | ↔ M64 |
| `M_576` | Out of Boundary Paths | memo DP | ↔ M688 |
| `M_688` | Knight Probability | memo DP | ↔ M576 |
| `M_741` | Cherry Pickup | double DP | ↔ M64 |

**Board games (3)**
| File | Problem | Technique | Related |
|---|---|---|---|
| `H_37` | Sudoku Solver | backtracking | ↔ M79 |
| `M_529` | Minesweeper | DFS | ↔ M200 |
| `M_79` | Word Search | backtracking | ↔ H37, M200 (permanent visited vs restore) |

**Region DP (2)**
| File | Problem | Technique | Related |
|---|---|---|---|
| `M_221` | Maximal Square | DP | ↔ H85 |
| `H_85` | Maximal Rectangle | histogram stack | ↔ M221, M84 |

### 10. Binary tree & BST (34)
**Traversals & views (17)**
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Compare | `E_100` | Same Tree | DFS | ↔ E101 |
| Compare | `E_101` | Symmetric Tree | DFS | ↔ E100, E226 |
| Ops | `E_226` | Invert Binary Tree | DFS (temp file only) | ↔ E101 |
| Paths | `E_112` | Path Sum | DFS | ↔ M437, E257 |
| Paths | `E_257` | Binary Tree Paths | DFS | ↔ E112 |
| DFS order | `M_94` | Inorder Traversal | stack | ↔ M144, M145 |
| DFS order | `M_144` | Preorder Traversal | stack | ↔ M94, M145, M114 (preorder rewiring) |
| DFS order | `M_145` | Postorder Traversal | stack | ↔ M94, M144 |
| Level BFS | `M_102` | Level Order | queue | ↔ M103, M199, M429 |
| Level BFS | `M_103` | Zigzag Level Order | queue | ↔ M102 |
| Level BFS | `M_429` | N-ary Level Order | queue | ↔ M102 |
| Views | `M_199` | Right Side View | level last | ↔ M513, M515 |
| Views | `M_513` | Bottom Left Tree Value | level first | ↔ M199, M515 |
| Views | `M_515` | Largest Value Each Row | level max | ↔ M199, M513 |
| Linking | `M_116` | Populating Next Right I | level link | ↔ M117 |
| Linking | `M_117` | Populating Next Right II | level link | ↔ M116 |
| Flatten | `M_114` | Flatten to Linked List | preorder + rewire | ↔ M144 (preorder vs in-place rewiring) |

**Construction (7)**
| File | Problem | Technique | Related |
|---|---|---|---|
| `M_105` | Build from Pre+Inorder | divide | ↔ M889, M1008, M654 (root-split range construction) |
| `M_889` | Build from Pre+Postorder | divide | ↔ M105 |
| `M_654` | Maximum Binary Tree | recursive root/range split | ↔ M105 (root-split range construction) |
| `M_427` | Construct Quad Tree | spatial quadrant recursion | — |
| `M_1008` | BST from Preorder | bounds | ↔ M105, M109 |
| `M_95` | Unique BSTs II | enumerate | ↔ M96 |
| `M_96` | Unique BSTs (count) | Catalan DP | ↔ M95, M1130 (root/interval decomposition) |

**BST ops (5)**
| File | Problem | Technique | Related |
|---|---|---|---|
| `E_538` | Convert BST to Greater Tree | reverse inorder | ↔ M230 |
| `M_98` | Validate BST | bounds | ↔ M230 |
| `M_230` | Kth Smallest in BST | inorder | ↔ M98, M173, E538 |
| `M_701` | Insert into BST | walk | — |
| `M_173` | BST Iterator | stack | ↔ M230 |

**LCA / paths / tree DP (5)**
| File | Problem | Technique | Related |
|---|---|---|---|
| `M_236` | Lowest Common Ancestor | post-order | — |
| `M_437` | Path Sum III | prefix on tree | ↔ E112, M560 (same trick) |
| `M_863` | All Nodes Distance K | parent map + BFS | ↔ M102 (layered BFS; parent edges make the tree a graph) |
| `M_1130` | Min-Cost Tree from Leaf Values | interval DP / stack | ↔ M84 (monotonic-stack contrast), M96 (root/interval decomposition) |
| `M_1339` | Max Product of Splitted Tree | total × subtree | — |

### 11. Linked list (14)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Remove | `E_83` | Remove Duplicates Sorted List | skip | ↔ M82 |
| Remove | `E_203` | Remove Elements | dummy | ↔ E83, M19 |
| Remove | `M_82` | Remove Duplicates II | skip runs | ↔ E83, E26 (array version) |
| Add | `M_2` | Add Two Numbers | carry walk | ↔ E67 (add binary) |
| Pointers | `E_141` | Linked List Cycle | fast/slow | ↔ M142, M287 (Floyd math) |
| Pointers | `M_142` | Linked List Cycle II | Floyd entry | ↔ E141, M287 |
| Pointers | `E_160` | Intersection of Two Lists | switch walk | ↔ M19 (pointer alignment around end/length) |
| Pointers | `M_19` | Remove Nth From End | gap pointers | ↔ E160 (pointer alignment around end/length) |
| Rewire | `M_24` | Swap Pairs | pairwise rewire | ↔ M328 |
| Rewire | `M_328` | Odd Even Linked List | split rejoin | ↔ M24 |
| Check | `E_234` | Palindrome Linked List | reverse half | ↔ M5 (palindrome family) |
| Rebuild | `M_138` | Copy List with Random Pointer | interleave | — |
| Rebuild | `M_148` | Sort List | merge sort | ↔ E88 (merge core) |
| Bridge | `M_109` | Sorted List → BST | mid split | ↔ M148, M1008 (BST build) |

### 12. Graphs & reachability (12)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Degree | `E_997` | Find the Town Judge | counting | ↔ M207 (in-degree) |
| Dependency order | `M_207` | Course Schedule | topo cycle check | ↔ M210, M310 (degree-queue contrast) |
| Dependency order | `M_210` | Course Schedule II | topo emit | ↔ M207 |
| Tree centers / leaf peel | `M_310` | Min Height Trees | undirected leaf peeling | ↔ M207, M210 (degree-queue vs DAG topological order) |
| Tour | `M_332` | Reconstruct Itinerary | Euler path | — |
| Shortest weighted | `M_743` | Network Delay Time | Dijkstra | ↔ M787, M1129 |
| Shortest weighted | `M_787` | Cheapest Flights within K | bounded-hop Bellman-Ford / DP | ↔ M743, M1129 (weighted-hop DP vs unweighted state BFS) |
| State-constrained BFS | `M_1129` | Alternating Colors | unweighted BFS on (node, incoming color) | ↔ M743, M787 (unweighted state-space contrast) |
| Coloring | `M_785` | Is Graph Bipartite | 2-color | — |
| Implicit BFS | `M_752` | Open the Lock | BFS | ↔ M127, M1306, M1091 (explicit grid vs generated dial states) |
| Implicit BFS | `M_1306` | Jump Game III | BFS | ↔ M55 (greedy version), M752 |
| Connectivity | `M_1319` | Make Network Connected | union-find | ↔ M200 (components) |

### 13. Permutations, combinations & subsets (10)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Subsets | `M_78` | Subsets | backtracking | ↔ M90, M77 |
| Subsets | `M_90` | Subsets II | backtracking | ↔ M78, M47, M40 (same duplicate-skip logic) |
| Combinations | `M_77` | Combinations | backtracking | ↔ M78, M39 |
| Combination sums | `M_39` | Combination Sum | enumerate unordered combinations | ↔ M40, M216, M377 (ordered counting), M518 (unordered counting) |
| Combination sums | `M_40` | Combination Sum II | backtracking | ↔ M39, M47, M90 (same duplicate-skip logic) |
| Combination sums | `M_216` | Combination Sum III | backtracking | ↔ M39, M77 |
| Letters mapping | `M_17` | Letter Combinations of a Phone Number | backtracking | ↔ M22 |
| Permutations | `M_46` | Permutations | backtracking | ↔ M47 |
| Permutations | `M_47` | Permutations II | backtracking | ↔ M46, M90 |
| Permutation transform | `M_31` | Next Permutation | pivot + reverse | ↔ M556 (digit version), M46 |

### 14. Partition & make-a-target (10)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Subset sum | `M_416` | Partition Equal Subset Sum | 0/1 knapsack | ↔ M494 (same transform), M474 |
| Assign ± | `M_494` | Target Sum | 0/1 knapsack | ↔ M416, M474 (0/1 capacity/count contrasts) |
| 2D weight | `M_474` | Ones and Zeroes | 0/1 knapsack | ↔ M416 |
| Equal groups | `M_473` | Matchsticks to Square | pruned DFS | ↔ M698 (identical template) |
| Equal groups | `M_698` | Partition to K Equal Subsets | pruned DFS | ↔ M473, M416 (k=2 special case; not general-k DP) |
| Min coins | `M_322` | Coin Change | unbounded knapsack | ↔ M518, M279 |
| Count combos | `M_518` | Coin Change II | unbounded knapsack | ↔ M322, M377, M39 (unordered enumeration vs counting) |
| Count orders | `M_377` | Combination Sum IV | unbounded knapsack | ↔ M518 (order contrast), E70, M39 (enumerate combinations), M1155 (exact dice-count dimension) |
| Min count | `M_279` | Perfect Squares | unbounded knapsack | ↔ M322, M343 (additive decomposition, different objective) |
| Split product | `M_343` | Integer Break | additive integer decomposition | ↔ M279 (min part count vs max product) |

### 15. Numbers & digit manipulation (16)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Bit tricks | `E_136` | Single Number | XOR | — |
| Bit tricks | `E_231` | Power of Two | bit check | ↔ E338 (single-set-bit special case of popcount) |
| Bit tricks | `E_190` | Reverse Bits | shifts | ↔ E338 |
| Bit tricks | `E_338` | Counting Bits | bit DP | ↔ E190, E231 (single-set-bit special case) |
| Bit tricks | `E_405` | Number to Hexadecimal | nibbles | ↔ E168 |
| Base conversion | `E_67` | Add Binary | string add | ↔ M2, M43 |
| Base conversion | `E_168` | Excel Column Title | base-26 | ↔ E405 |
| Base conversion | `M_43` | Multiply Strings | digit math | ↔ E67, M2 |
| Number theory | `E_204` | Count Primes | sieve | — |
| Number theory | `E_754` | Reach a Number | step math | — |
| Number theory | `M_50` | Pow(x, n) | fast power | ↔ M29 |
| Number theory | `M_29` | Divide Two Integers | shifts | ↔ M50 |
| Sequence generation | `E_118` | Pascal's Triangle | rows | ↔ M62 (same Pascal/binomial recurrence) |
| Sequence generation | `M_264` | Ugly Number II | 3-pointer | ↔ M373 (merge sources) |
| Digits | `E_633` | Sum of Square Numbers | two pointers | ↔ E367 (square verify), E167 |
| Digits | `M_402` | Remove K Digits | monotonic stack | ↔ M556 (digit rearrange), M31 |

### 16. Array search & in-place cleanup (20)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Pair sums | `E_1` | Two Sum | hashmap | ↔ E167 |
| Pair sums | `E_167` | Two Sum II | two pointers | ↔ E1, M15 |
| Pair sums | `E_169` | Majority Element | Boyer–Moore | — |
| Dedup / remove | `E_26` | Remove Duplicates Sorted Array | two pointers | ↔ E27, E283, M82 (sorted-list run deletion) |
| Dedup / remove | `E_27` | Remove Element | two pointers | ↔ E26, E283 |
| Dedup / remove | `E_283` | Move Zeroes | two pointers | ↔ E27, E26 |
| Merge / rotate | `E_88` | Merge Sorted Array | back merge | ↔ E977, M148 |
| Merge / rotate | `E_189` | Rotate Array | reversal | ↔ M31 |
| Merge / rotate | `E_977` | Squares of Sorted Array | end merge | ↔ E88 |
| Fix-up | `E_581` | Shortest Unsorted Subarray | boundary scan | ↔ E665 |
| Fix-up | `E_665` | Non-decreasing Array | one-pass fix | ↔ E581 |
| kSum | `M_15` | 3Sum | two pointers | ↔ M16, M18 |
| kSum | `M_16` | 3Sum Closest | two pointers | ↔ M15, M18 |
| kSum | `M_18` | 4Sum | two pointers | ↔ M15, M16 |
| Sort / partition | `M_75` | Sort Colors | Dutch flag | ↔ M324, M215 |
| Sort / partition | `M_324` | Wiggle Sort II | partition | ↔ M75 |
| Cycle trick | `M_287` | Find the Duplicate Number | Floyd | ↔ M142, M442 |
| Pattern detect | `M_456` | 132 Pattern | stack | ↔ M496 |
| Counting | `M_1007` | Min Domino Rotations | frequency | ↔ E997 |
| Pairwise | `M_611` | Valid Triangle Number | two pointers | ↔ M15 |

### 17. Implement a data structure (7)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Stack/queue | `E_155` | Min Stack | min-pair stack | ↔ E232 |
| Stack/queue | `E_232` | Queue via Stacks | two stacks | ↔ E155 |
| Range queries | `E_303` | Range Sum Query | prefix | ↔ M304 |
| Range queries | `M_304` | Range Sum Query 2D | 2D prefix | ↔ E303 |
| O(1) ops | `M_146` | LRU Cache | hashmap + DLL | ↔ M380 |
| O(1) ops | `M_380` | Insert Delete GetRandom O(1) | array + map | ↔ M146 |
| Versioned | `M_981` | Time-based Key-Value Store | binary search | ↔ M34 (bounds search) |

### 18. Prefix matching (trie) questions (3)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Build & search | `M_208` | Implement Trie | trie | ↔ M211, M648 (in group 8) |
| Build & search | `M_211` | Add & Search Words | trie | ↔ M208 |
| Bit trie | `M_421` | Max XOR of Two Numbers | binary trie | ↔ M208 |

(648 Replace Words → group 8.)

### 19. Search in sorted data / thresholds (11)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Classic | `E_704` | Binary Search | template | ↔ E35, M34 |
| Classic | `E_35` | Search Insert Position | lower bound | ↔ E704, M34 |
| Classic | `E_69` | Sqrt(x) | value search | ↔ E367, E633 (pointer alt) |
| Classic | `E_367` | Valid Perfect Square | value search | ↔ E69, E633 |
| Rotated | `M_33` | Search Rotated I | boundary logic | ↔ M81, M153 |
| Rotated | `M_81` | Search Rotated II | + dups | ↔ M33, M153 |
| Rotated | `M_153` | Min in Rotated | boundary logic | ↔ M33, M81 |
| Boundary | `M_34` | First & Last Position | bounds | ↔ E35, E704 |
| Peak | `M_162` | Find Peak Element | slope | — |
| Threshold | `M_875` | Koko Eating Bananas | min speed | ↔ M1011 |
| Threshold | `M_1011` | Ship Packages | min capacity | ↔ M875 |

### 20. Count the ways (5)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Steps | `E_70` | Climbing Stairs | DP | ↔ E746, M377 (variable steps) |
| Steps | `E_746` | Min Cost Climbing Stairs | DP | ↔ E70 |
| Decode | `M_91` | Decode Ways | prefix-segmentation DP | ↔ E70 (step rules), M139 (legal final segment) |
| Calendar | `M_983` | Min Cost For Tickets | prefix lookback DP | ↔ M139 (reachability vs min-cost lookback) |
| Dice | `M_1155` | Dice Rolls Target Sum | ordered sequences + exact dice count | ↔ M377 (unbounded ordered-sequence recurrence) |

(96 Unique BSTs → group 10; 139 Word Break → group 8.)

### 21. Fair allocation & jumps (5)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Match supply to demand | `E_455` | Assign Cookies | sort + two-pointer match | — |
| Cash-register simulation | `E_860` | Lemonade Change | denomination counters | — |
| Reachability | `M_55` | Jump Game | greedy | ↔ M1306 (BFS version) |
| Circular feasibility | `M_134` | Gas Station | greedy | ↔ M55 |
| Placement | `M_406` | Queue Reconstruction by Height | greedy | — |

(1306 Jump III → group 12.)

### 22. Next-greater queries (6)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Array | `M_496` | Next Greater Element I | monotonic stack | ↔ M503, M739 |
| Circular array | `M_503` | Next Greater Element II | monotonic stack | ↔ M496, M739 (array next-greater variants) |
| Digits | `M_556` | Next Greater Element III | next permutation: pivot, swap, reverse suffix | ↔ M31 (same template), M402 (digit-pruning contrast) |
| Weather | `M_739` | Daily Temperatures | monotonic stack | ↔ M496, M901 |
| Prices | `M_901` | Online Stock Span | previous greater + accumulated span | ↔ M739 (direction contrast), M1019 (order/direction contrast) |
| Linked list | `M_1019` | Next Greater Node in Linked List | monotonic stack | ↔ M901, M496 |

### 23. House-robber selection family (3)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| Line | `M_198` | House Robber | DP | ↔ M740, M337 |
| Tree | `M_337` | House Robber III | tree DP | ↔ M198, M740 |
| Buckets | `M_740` | Delete and Earn | DP | ↔ M198 |

### 24. Top-K & kth-order questions (5)
| Family | File | Problem | Technique | Related |
|---|---|---|---|---|
| kth | `M_215` | Kth Largest in Array | quickselect / heap | ↔ M347, M378 |
| Top frequent | `M_347` | Top K Frequent Elements | bucket / heap | ↔ M692, M451 |
| Top frequent | `M_692` | Top K Frequent Words | heap | ↔ M347 |
| Pair merge | `M_373` | K Pairs Smallest Sums | heap | ↔ M378, M264 (merge DP) |
| Sorted matrix | `M_378` | Kth Smallest in Sorted Matrix | value binary search | ↔ M373 |

---

# Part 3 — Adaptive study plan

The catalogue is a map, not a checklist. Aim for cold pattern selection and reliable implementation.

## 1. Cadence

**7 days/week, 240 minutes.**

| Day | Available time | Session type |
|---|---|---:|
| Monday–Friday | **30 min/day** | **Two 15-minute Easy/Medium cycles**, new work or due reviews |
| Saturday | **45 min** | **Three 15-minute Easy/Medium cycles** (including a planned drill), or the 45-minute Hard format |
| Sunday | **45 min** | **Three 15-minute Easy/Medium mixed/review cycles**, or one scheduled Hard assessment |

- 96 Monday–Saturday focused sessions + 16 Sundays = 112 sessions over 16 weeks, 64 hours. A session is a time block, not a question.
- 16 Easy/Medium cycles/week (10 weekday, 6 weekend) is capacity, not a quota.
- Easy/Medium cycle: 3 think → 5 try → 5 code → 2 finish/check. Stop at 15 minutes.
- Hard: weekend only, 45 minutes, ≤30 cold implementation; replaces all three Easy/Medium slots.
- Week 1 Sunday: setup, diagnosis, catch-up. From week 2, Sunday is category-unlabelled unless a Hard assessment replaces it.
- Recheck after two weeks. Never raise the 15-minute cap. Remaining weeks = `ceil(remaining Monday–Saturday sessions ÷ 6)`.
- Timeout ends the question after finish/check. Never borrow time; cut new work or extend the calendar.
- Drills and transfer replace slots; add no time.

## 2. State and selection

No manual priorities. The roadmap picks new material; history picks repetition.

| Automatic state | Rule from attempt history | Treatment |
|---|---|---|
| **Not attempted** | No recorded attempt in the current cycle | Eligible as new roadmap work; not a debt that must be cleared before advancing |
| **Needs attention** | Latest cold attempt is 🔶/❌ | Schedule from that result; study the analysis after ❌ when needed, then test cold |
| **Review scheduled** | Latest cold result is ✅ and its next review is in the future | No immediate repetition; retain in future mixed selection |
| **Review due** | A scheduled cold review date has arrived | Re-test in the next suitable session |
| **Established** | Two Fluent ✅ results separated by at least 14 days, including at least one Unlabelled ✅ | Long-term mixed review only; any later failure returns it to Needs attention |

**Labelled** = category visible. **Unlabelled** = category hidden, on review or new work. Help is not a type; §4 records it.

Fill each Easy/Medium slot:

1. Due 🔶/❌.
2. Other due ✅ reviews.
3. Next unattempted question in the current block, in family and row order.
4. Otherwise advance; do not manufacture repetitions.

Hard waits for a weekend. Sunday follows §7. Manual choices need no label.

## 3. Cold attempts

> Read only the clean question, constraints, and required signature. Do not open `# Analysis`, variants, notes, or an existing answer before or during the attempt.

Reading `# Analysis` during an attempt caps it at 🔶, even with from-scratch code. Reading or copying a substantive solution is ❌.

Use `A_Test.java` or a clean workspace. Keep history in the log, not in `X_###.practice.java` copies.

### Easy/Medium cycle — ≤15 minutes

| Step | Minutes | Rule |
|---|---:|---|
| **Think** | **3** | Read only the clean question/constraints/signature; identify the pattern trigger and invariant/recurrence |
| **Try** | **5** | Attempt independently; sketch the solution and begin implementing without hints |
| **Code** | **5** | Continue/finish the implementation independently; stop when this block ends |
| **Finish / check** | **2** | If successful, log ✅, or 🔶 if hinted, and the next review. If not successful, stop, mark ❌, check the answer/core approach, and log the next review |
| **Total ceiling** | **15** | Then move to the next scheduled question; never extend the attempt |

Check only the central gap after a failed attempt. Study further afterwards; retry cold at the scheduled review.

### Sessions

- **Mon–Fri (30 min):** two cycles; log each before the next.
- **Sat (45 min):** three Easy/Medium or drill cycles, or the Hard format below.
- A Hard assessment replaces all three Easy/Medium slots. Sunday follows §7.

| Hard step | Minutes |
|---|---:|
| Select clean question and plan | 4 |
| **Cold implementation** | **≤30** |
| Test and explain complexity | 4 |
| Compare `# Analysis` and identify exact gaps | 5 |
| Log and schedule review | 2 |
| **Total** | **45** |

A known category shows implementation skill; only an Unlabelled attempt tests pattern selection.

## 4. Marks and reviews

- **✅ Fluent:** no hint or solution; correct within the time cap; optimal approach; invariant and complexity explained; edge cases checked. A cross-pattern answer also names one alternative and its trade-off.
- **🔶 Hinted/shaky:** any hint, significant overtime, uncertain invariant, or an explanation dependent on the template.
- **❌ Not yet fluent:** unfinished, or read/copied a substantive solution.

A known technique still allows a Fluent ✅, but not the Unlabelled success required for Established.

### Review schedule

Intervals run from the latest attempt; each review sets the next. 🔶/❌ restarts the shorter sequence.

| Latest result | Action now | Next cold reviews |
|---|---|---|
| **❌** | Use the two-minute finish/check to identify the core approach. If more work is needed, study the analysis afterwards; this is not a logged attempt and must not extend today's attempt | **1, 3, 7, 14, 30 days** advancing only with independence |
| **🔶** | Identify the precise missing trigger, invariant, implementation detail, or edge case | **2, 7, 21 days** |
| **✅** | No immediate repetitions; use the delay to test retention | **7 and 30 days**, then long-term mixed review |

A failed Unlabelled attempt enters **Needs attention**. Mark 🔶 if shaky, ❌ if unfinished or assisted; retry cold at the next suitable slot. Do not repeat a strong item just because it is next in a table.

### Review log

Keep one history; no priority or status column.

| Problem | Date | Attempt type | Mark | Minutes | Failure type | Next review |
|---|---|---|---|---:|---|---|
| `M_###` | YYYY-MM-DD | Labelled / Unlabelled | ✅/🔶/❌ | — | recognition / invariant / implementation / edge case / complexity / none | YYYY-MM-DD |

The tool derives state, next review, Fluent count, and Established. The next slot follows §2.

## 5. Focused-session roadmap

Budgets, not promises. Drills consume their block. Due work and failures extend; fluent recall shortens. Intervals bring sweep and greedy before the heap in `M_253`.

| Roadmap block | Focus (Part 1 groups) | Focused sessions |
|---:|---|---:|
| 1 | Arrays & Hashing + Prefix (1 + 5) | 6 |
| 2 | Two Pointers (2) | 8 |
| 3 | Sliding Window (3) | 3 |
| 4 | Binary Search (4) | 5 |
| 5 | Design (19) | 2 |
| 6 | Linked List (6) | 5 |
| 7 | Stack — expressions & matching (part of 7) | 3 |
| 8 | Stack — monotonic family (rest of 7) | 4 |
| 9 | Greedy & Intervals (15) | 5 |
| 10 | Heap & Top-K (8) | 4 |
| 11 | Matrix — in-place manipulation (17) | 2 |
| 12 | Binary Tree (9) | 9 |
| 13 | BST + Trie (10 + 11) | 3 |
| 14 | Backtracking (14) | 5 |
| 15 | Graph — BFS/DFS (12) | 4 |
| 16 | Graph — topo / shortest / connectivity (13) | 3 |
| 17 | DP — core 1D + stocks (part of 16) | 7 |
| 18 | DP — knapsack (part of 16) | 3 |
| 19 | DP — grid & states (part of 16) | 3 |
| 20 | DP — LIS + string/LCS (rest of 16) | 3 |
| 21 | Math & Bit (18) | 3 |
| 22 | Hard-problem and transfer consolidation | 6 |
|  | **Total focused sessions** | **96** |

Leave a block when its budget is used and its distinctions can be explained without the table. Unattempted rows stay available for mixed work. Extend or cut new work if 🔶/❌ dominates or the due queue grows; a later failure reopens Needs attention.

### Hard problems inside the roadmap

`H_37` Sudoku · `H_42` Rain Water · `H_76` Min Window · `H_85` Maximal Rectangle · `H_123`/`H_188` Stocks III/IV · `H_132` Palindrome Partitioning II · `H_224`/`H_772` Calculators I/III · `H_340` K Distinct · `H_354` Russian Dolls

Place each Hard where its main prerequisite is learned: Saturday 45-minute format, or Sunday for a scheduled mixed assessment. Add its alternative after the second prerequisite—`H_85` with stack in block 8, DP after 19. Extend rather than split cold work across weekdays.

Block 22 takes the **six highest-value** sessions from overdue Hard work, failed mixed recall, and unfamiliar transfer, not all 11. Spread Hard across weekends; spend spare sessions on unfamiliar mixed work, and extend if Needs attention grows.

## 6. Topical drills

One budgeted Saturday each: **three Easy/Medium representatives** from the target Part 2 family, due 🔶/❌ first, then easy-to-confuse contrasts. One 15-minute cycle each. A due Hard takes the Saturday; move the drill. No oral or extended comparison stages.

| After roadmap block | Drill (Part 2) | Emphasis |
|---:|---|---|
| 8 | Next-greater queries (22) | array, circular, distance/span, digits, and linked-list variants |
| 9 | Intervals & scheduling (3) | sort + sweep versus greedy end times versus heap load |
| 10 | Top-K (24) | quickselect, bucket, heap, and value binary search trade-offs |
| 11 | Matrix manipulation subsection of Grid & board (9) | boundaries, markers, and in-place state only |
| 15 | Grid regions/search subsections of Grid & board (9) | flood fill, multi-source BFS, and board DFS/backtracking prerequisites |
| 17 | Stocks (2) | one generalized state machine, not six isolated recurrences |
| 19 | Path/region DP subsections of Grid & board (9) | rolling states, move-limited states, and double-walker state |
| 20 | Palindromes (1) | expand-center, backtracking, interval DP, and LCS contrast |

Matrix, search, and DP drills thus follow their prerequisites, without a 28-problem Grid day.

## 7. Sunday mixed practice (45 min)

Three 15-minute cycles:

1. Due 🔶/❌.
2. Other due ✅ reviews.
3. A previously attempted question, Part 1 group hidden.

- Hide group, technique notes, and Related until each result is logged.
- Every second week, one non-emergency slot takes an unfamiliar Easy/Medium sibling of a recently completed family.
- At major transitions—linear → trees → backtracking/graphs → DP → consolidation—include one Unlabelled question from completed material.
- A remembered solution is not transfer evidence: say why the pattern fits and why its closest rival does not.
- A Hard assessment replaces all three cycles.
- Catch-up uses the same slots; otherwise cut future work or extend.

## 8. Cross-pattern problems

`H_42` ⇄stack · `H_85` ⇄DP · `M_240` ⇄binary search · `M_378` ⇄heap · `M_287` ⇄binary search · `M_264` ⇄heap · `M_131`/`H_132` ⇄DP · `M_5`/`M_647` ⇄DP · `M_1130` ⇄stack · `M_347` ⇄bucket sort · `M_215` ⇄quickselect · `M_300` ⇄patience sorting · `M_621` ⇄heap · `M_337` ⇄DP · `M_139` ⇄BFS · `M_127` ⇄strings · `M_109` ⇄linked list · `M_1019` ⇄linked list · `M_901` ⇄stocks · `M_981` ⇄binary search

For each alternative, state the change in invariant, complexity, mutation/space, and implementation risk. Schedule a later Labelled or Unlabelled cycle after the second prerequisite. Reading is separate; Hard alternatives take a weekend slot.

## 9. Maintenance

- Keep `A_FrequentUse.java` current with post-attempt lookups.
- Do not expand while the due queue grows or 🔶/❌ dominates. Later substitutions for Established review: **239**, **72**, **295**, **547 or 684**.
- Outside study time, clean `.temp.java` drafts and rename `E_226.temp.java`.

---

## Appendix — problem index (ID → group)

**P1** and **P2** are group numbers; related problems may sit elsewhere.

| Problem | P1 group | P2 group |
|---|---|---|
| E_1 | 1 | 16 |
| E_20 | 7 | 4 |
| E_26 | 2 | 16 |
| E_27 | 2 | 16 |
| E_35 | 4 | 19 |
| E_67 | 18 | 15 |
| E_69 | 4 | 19 |
| E_70 | 16 | 20 |
| E_83 | 6 | 11 |
| E_88 | 2 | 16 |
| E_100 | 9 | 10 |
| E_101 | 9 | 10 |
| E_112 | 9 | 10 |
| E_118 | 16 | 15 |
| E_121 | 16 | 2 |
| E_136 | 18 | 15 |
| E_141 | 6 | 11 |
| E_155 | 19 | 17 |
| E_160 | 6 | 11 |
| E_167 | 2 | 16 |
| E_168 | 18 | 15 |
| E_169 | 1 | 16 |
| E_189 | 2 | 16 |
| E_190 | 18 | 15 |
| E_203 | 6 | 11 |
| E_204 | 18 | 15 |
| E_226 | 9 | 10 |
| E_231 | 18 | 15 |
| E_232 | 19 | 17 |
| E_234 | 6 | 11 |
| E_242 | 1 | 7 |
| E_257 | 9 | 10 |
| E_283 | 2 | 16 |
| E_303 | 5 | 17 |
| E_338 | 18 | 15 |
| E_349 | 1 | 7 |
| E_350 | 1 | 7 |
| E_367 | 4 | 19 |
| E_383 | 1 | 7 |
| E_387 | 1 | 7 |
| E_405 | 18 | 15 |
| E_455 | 15 | 21 |
| E_538 | 10 | 10 |
| E_581 | 2 | 16 |
| E_633 | 2 | 15 |
| E_665 | 15 | 16 |
| E_704 | 4 | 19 |
| E_746 | 16 | 20 |
| E_754 | 18 | 15 |
| E_860 | 15 | 21 |
| E_914 | 1 | 7 |
| E_925 | 2 | 8 |
| E_977 | 2 | 16 |
| E_997 | 1 | 12 |
| H_37 | 14 | 9 |
| H_42 | 2 | 5 |
| H_76 | 3 | 8 |
| H_85 | 7 | 9 |
| H_123 | 16 | 2 |
| H_132 | 16 | 1 |
| H_188 | 16 | 2 |
| H_224 | 7 | 4 |
| H_340 | 3 | 8 |
| H_354 | 16 | 6 |
| H_772 | 7 | 4 |
| M_2 | 6 | 11 |
| M_3 | 3 | 8 |
| M_5 | 2 | 1 |
| M_11 | 2 | 5 |
| M_15 | 2 | 16 |
| M_16 | 2 | 16 |
| M_17 | 14 | 13 |
| M_18 | 2 | 16 |
| M_19 | 6 | 11 |
| M_22 | 14 | 4 |
| M_24 | 6 | 11 |
| M_29 | 18 | 15 |
| M_31 | 2 | 13 |
| M_33 | 4 | 19 |
| M_34 | 4 | 19 |
| M_39 | 14 | 13 |
| M_40 | 14 | 13 |
| M_43 | 18 | 15 |
| M_46 | 14 | 13 |
| M_47 | 14 | 13 |
| M_48 | 17 | 9 |
| M_49 | 1 | 7 |
| M_50 | 18 | 15 |
| M_53 | 16 | 5 |
| M_54 | 17 | 9 |
| M_55 | 15 | 21 |
| M_56 | 15 | 3 |
| M_59 | 17 | 9 |
| M_62 | 16 | 9 |
| M_63 | 16 | 9 |
| M_64 | 16 | 9 |
| M_73 | 17 | 9 |
| M_74 | 4 | 9 |
| M_75 | 2 | 16 |
| M_77 | 14 | 13 |
| M_78 | 14 | 13 |
| M_79 | 14 | 9 |
| M_81 | 4 | 19 |
| M_82 | 6 | 11 |
| M_84 | 7 | 5 |
| M_90 | 14 | 13 |
| M_91 | 16 | 20 |
| M_94 | 9 | 10 |
| M_95 | 10 | 10 |
| M_96 | 16 | 10 |
| M_98 | 10 | 10 |
| M_102 | 9 | 10 |
| M_103 | 9 | 10 |
| M_105 | 9 | 10 |
| M_109 | 9 | 11 |
| M_114 | 9 | 10 |
| M_116 | 9 | 10 |
| M_117 | 9 | 10 |
| M_120 | 16 | 9 |
| M_122 | 16 | 2 |
| M_127 | 12 | 8 |
| M_130 | 12 | 9 |
| M_131 | 14 | 1 |
| M_134 | 15 | 21 |
| M_138 | 6 | 11 |
| M_139 | 16 | 8 |
| M_142 | 6 | 11 |
| M_144 | 9 | 10 |
| M_145 | 9 | 10 |
| M_146 | 19 | 17 |
| M_148 | 6 | 11 |
| M_150 | 7 | 4 |
| M_152 | 16 | 5 |
| M_153 | 4 | 19 |
| M_159 | 3 | 8 |
| M_162 | 4 | 19 |
| M_173 | 10 | 10 |
| M_198 | 16 | 23 |
| M_199 | 9 | 10 |
| M_200 | 12 | 9 |
| M_207 | 13 | 12 |
| M_208 | 11 | 18 |
| M_209 | 3 | 5 |
| M_210 | 13 | 12 |
| M_211 | 11 | 18 |
| M_215 | 8 | 24 |
| M_216 | 14 | 13 |
| M_221 | 16 | 9 |
| M_227 | 7 | 4 |
| M_230 | 10 | 10 |
| M_236 | 9 | 10 |
| M_238 | 5 | 5 |
| M_240 | 2 | 9 |
| M_253 | 8 | 3 |
| M_264 | 16 | 15 |
| M_279 | 16 | 14 |
| M_287 | 2 | 16 |
| M_289 | 17 | 9 |
| M_300 | 16 | 6 |
| M_304 | 5 | 17 |
| M_309 | 16 | 2 |
| M_310 | 13 | 12 |
| M_318 | 1 | 8 |
| M_322 | 16 | 14 |
| M_324 | 2 | 16 |
| M_328 | 6 | 11 |
| M_332 | 13 | 12 |
| M_337 | 9 | 23 |
| M_343 | 16 | 14 |
| M_347 | 8 | 24 |
| M_361 | 17 | 9 |
| M_373 | 8 | 24 |
| M_376 | 15 | 6 |
| M_377 | 16 | 14 |
| M_378 | 4 | 24 |
| M_380 | 19 | 17 |
| M_394 | 7 | 4 |
| M_402 | 7 | 15 |
| M_406 | 15 | 21 |
| M_413 | 16 | 5 |
| M_416 | 16 | 14 |
| M_417 | 12 | 9 |
| M_421 | 11 | 18 |
| M_427 | 9 | 10 |
| M_429 | 9 | 10 |
| M_435 | 15 | 3 |
| M_437 | 9 | 10 |
| M_438 | 3 | 7 |
| M_442 | 1 | 7 |
| M_451 | 8 | 7 |
| M_452 | 15 | 3 |
| M_456 | 7 | 16 |
| M_473 | 14 | 14 |
| M_474 | 16 | 14 |
| M_494 | 16 | 14 |
| M_496 | 7 | 22 |
| M_503 | 7 | 22 |
| M_513 | 9 | 10 |
| M_515 | 9 | 10 |
| M_516 | 16 | 1 |
| M_518 | 16 | 14 |
| M_529 | 12 | 9 |
| M_542 | 12 | 9 |
| M_556 | 2 | 22 |
| M_560 | 5 | 5 |
| M_567 | 3 | 7 |
| M_576 | 16 | 9 |
| M_583 | 16 | 6 |
| M_611 | 2 | 16 |
| M_621 | 15 | 3 |
| M_646 | 15 | 3 |
| M_647 | 2 | 1 |
| M_648 | 11 | 8 |
| M_654 | 9 | 10 |
| M_673 | 16 | 6 |
| M_678 | 15 | 4 |
| M_688 | 16 | 9 |
| M_692 | 8 | 24 |
| M_695 | 12 | 9 |
| M_698 | 14 | 14 |
| M_701 | 10 | 10 |
| M_714 | 16 | 2 |
| M_718 | 16 | 6 |
| M_739 | 7 | 22 |
| M_740 | 16 | 23 |
| M_741 | 16 | 9 |
| M_743 | 13 | 12 |
| M_752 | 12 | 12 |
| M_785 | 13 | 12 |
| M_787 | 13 | 12 |
| M_856 | 7 | 4 |
| M_863 | 9 | 10 |
| M_875 | 4 | 19 |
| M_889 | 9 | 10 |
| M_901 | 7 | 22 |
| M_907 | 7 | 5 |
| M_934 | 12 | 9 |
| M_978 | 3 | 5 |
| M_981 | 19 | 17 |
| M_983 | 16 | 20 |
| M_986 | 15 | 3 |
| M_1007 | 1 | 16 |
| M_1008 | 10 | 10 |
| M_1011 | 4 | 19 |
| M_1019 | 7 | 22 |
| M_1091 | 12 | 9 |
| M_1129 | 13 | 12 |
| M_1130 | 16 | 10 |
| M_1143 | 16 | 6 |
| M_1155 | 16 | 20 |
| M_1162 | 12 | 9 |
| M_1306 | 12 | 12 |
| M_1319 | 13 | 12 |
| M_1339 | 9 | 10 |
