package leetCode;

import jdk.nashorn.internal.objects.annotations.Function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
In English, we have a concept called root, which can be followed by some other words to form another longer word
let's call this word successor.
For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence.
You need to replace all the successor in the sentence with the root forming it.
If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.


### Example
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

### Condition
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000

### Essential problem

### Corner case

*/
public class M_Trie_648 {
    public static void main(String[] args) {
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("bat");
        dict.add("rat");

        System.out.println(replaceWords(dict, "the cattle was rattled by the battery"));
        //  "the cat was rat by the bat"
    }

    public static String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TreeSet<String> rootCandidates = new TreeSet<>(Comparator.comparingInt(String::length));

            for (String root : dict) {
                if (word.startsWith(root)) {
                    rootCandidates.add(root);
                }
            }

            word = rootCandidates.isEmpty() ? word : rootCandidates.first();
            result += word + " ";
        }

        return result.substring(0, result.length() - 1);
    }

    public static String replaceWords1(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        String result = "";

        for (int i = 0; i < words.length; i++) {

        }
    }
}

