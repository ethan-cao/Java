package algorithm.search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {2, 3, 5, 6, 8, 10, 14};
        int index = BinarySearch.binarySearch(array, 10);

        System.out.println("target is at : " + index);
    }

    // searching a finite sorted array
    static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            /*
                int mid = (left + right) / 2;
                divided by 2 is a bug... if left + right is larger than maximal value of int, result overflows to negative
                https://research.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html
             */
            int middle = left + (right - left) / 2;

            if (array[middle] == target) {
                return middle;
            }

            if (array[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }
}