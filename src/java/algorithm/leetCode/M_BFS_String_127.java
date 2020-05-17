package algorithm.leetCode;

/*
Given two words (beginWord and endWord), and a dictionary's word list,
find the length of shortest transformation sequence from beginWord to endWord, such that:
Only one letter can be changed at a time.
Each transformed word must exist in the word list.

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

### Example
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

*/

import java.util.*;

public class M_BFS_String_127 {

    public static void main(String... args) {
        System.out.println(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));
    }

    // BFS
    // Time O(), Space: O()
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // check if the endWord actually exists in the wordList
        Set<String> wordSet = new HashSet<String>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        int length = 1;
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> used = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);


        // bidirectional search is to run two simultaneous searches, from different ends
        // when two searches meet the same word, it is path

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {

            // start from end which has less
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> nextStart = new HashSet<>();

            for (String word : beginSet) {
                char[] characters = word.toCharArray();

                for (int i = 0; i < characters.length; i++) {
                    for (char c = 'a'; c <= 'z'; ++c) {
                        char character = characters[i];

                        characters[i] = c;
                        String newWord = String.valueOf(characters);

                        // meet
                        if (endSet.contains(newWord)) {
                            return length + 1;
                        }

                        if (wordSet.contains(newWord) && !used.contains(newWord)) {
                            nextStart.add(newWord);
                            used.add(newWord);
                        }

                        characters[i] = character;
                    }
                }
            }

            beginSet = nextStart;
            length++;
        }

        return 0;
    }

}