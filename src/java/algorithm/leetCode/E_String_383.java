package algorithm.leetCode;


/*
Given an arbitrary ransom note string
and another string containing letters from all the magazines,
write a function that will return true
if the ransom note can be constructed from the magazines ;
otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.
You may assume that both strings contain only lowercase letters.

### Input
String a, String b

### Output
boolean

### Example
canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

### Condition

### Essential problem

### Corner case

*/
public class E_String_383 {
    public static void main(String... args) {
        String a = "a";
        String b = "b"; // f

        a = "aa";
        b = "ab"; //f

        a = "aa";
        b = "aab"; //t

        a = "";
        b = "asd";  // t

        System.out.println(canConstruct(a, b));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        for (int i = 0; i < ransomNote.length(); ++i) {
            int index = magazine.indexOf(ransomNote.charAt(i));
            if (index >= 0) {
                magazine = magazine.replaceFirst(ransomNote.charAt(i) + "", "");
            } else {
                return false;
            }
        }

        return true;
    }

    public static boolean canConstruct1(String ransomNote, String magazine) {
        int[] alphabet = new int[26];

        for (int i = 0; i < magazine.length(); ++i) {
            ++alphabet[magazine.charAt(i) - 'a'];
        }

        for (int i = 0; i < ransomNote.length(); ++i) {
            if (--alphabet[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
