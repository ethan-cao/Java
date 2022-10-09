package algorithm.leetCode;

/*
Your friend is typing his name into a keyboard.  Sometimes, when typing a character c,
the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.
Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

name.length <= 1000 & typed.length <= 1000
The characters of name and typed are lowercase letters.

### Example
name = "alex", typed = "aaleex" --->  true
Explanation: 'a' and 'e' in 'alex' were long pressed.

name = "saeed", typed = "ssaaedd" --> false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.

name = "leelee", typed = "lleeelee" -->  true

name = "laiden", typed = "laiden" --> true
Explanation: It's not necessary to long press any character.

### Condition

### Essential problem

### Corner case

*/

public class E_2Pointer_String_925 {
    public static void main(String... args) {
        String name = "alex";
        String typed = "aaleex";  // true

        name = "saeed";
        typed = "ssaaedd";  //false

        name = "leelee";
        typed = "lleeelee"; // true

        name = "laiden";
        typed = "laiden"; // true

        name = "aab";
        typed = "aaab"; // true

        System.out.println(isLongPressedName1(name, typed));
    }

    public static boolean isLongPressedName1(String name, String typed) {
        int i = 0;

        for (int j = 0; j < typed.length() && i < name.length(); ++j) {
            if (name.charAt(i) == typed.charAt(j)) {
                ++i;
            } else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }
        }

        return i == name.length();
    }
}
