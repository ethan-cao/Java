package algorithm.leetCode;

/*
Given a positive integer, return its corresponding column title
as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
    ...

### Example
1 --> "A"
28 --> "AB"
701 -->  "ZY"

### Condition

### Essential problem
Convert decimal number to base 26 number

1    -> A     1  * 26^0
26   -> Z     26 * 26^0

27   -> AA    1 * 26^1 + 1 * 26^0
52   -> AZ    1 * 26^1 + 26 * 26^0
53   -> BA    2 * 26^1 + 1 * 26^0
78   -> BZ    2 * 26^1 + 26 * 26^0
702  -> ZZ    26 * 26^1 + 26 * 26^0h

703  -> AAA     1 * 26^2 + 1 * 26^1 + 1 * 26^0
18278  -> ZZZ   26 * 26^2 + 26 * 26^1 + 26 * 26^0

18279  -> AAAA   1 * 26^3 + 1 * 26^2 + 1 * 26^1 + 1 * 26^0
475254 -> ZZZZ   26 * 26^3 + 26 * 26^2 + 26 * 26^1 + 26 * 26^0

475255 -> AAAAA  1 * 26^4 + 1 * 26^3 + 1 * 26^2 + 1 * 26^1 + 1 * 26^0

### Corner case

*/

public class E_Math_168 {

    public static void main(String... args) {
        System.out.println(convertToTitle(1));  // A
        System.out.println(convertToTitle(2));  // B
        System.out.println(convertToTitle(26));  // Z
        System.out.println(convertToTitle(28));   // AB
        System.out.println(convertToTitle(701));   // ZY
        System.out.println(convertToTitle(731));    // ABC
    }

    public static String convertToTitle(int n) {
        StringBuilder title = new StringBuilder();

        while (n > 0) {
            // Instead of 1 -> A, 26 -> Z, we can assume 0 -> A, 25 -> Z
            n--;

            title.insert(0, (char) (n % 26 + 'A'));

            n /= 26;
        }

        return title.toString();
    }
}