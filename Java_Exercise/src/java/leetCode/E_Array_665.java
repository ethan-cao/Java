package leetCode;

/*
Given an array with n integers, your task is to check
if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for
every i (1 <= i < n).

### Input
int[]

### Output
boolean

### Example
[4,2,3] -->  True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

[4,2,1] -->  False
Explanation: You can't get a non-decreasing array by modify at most one element.

### Conditio: n
The n belongs to [1, 10,000].

### Essential problem

### Corner case

*/
public class E_Array_665 {
    public static void main(String... args) {
        int[] input = {4, 2, 3};

        System.out.println(checkPossibility(input));
    }

    public static boolean checkPossibility(int[] nums) {

        return false;
    }
}
