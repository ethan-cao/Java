package algorithm.search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {2, 3, 5, 6, 8, 10, 14};
        int index = BinarySearch.binarySearch(array, 10);

        System.out.println("target is at : " + index);
    }

    // searching a finite sorted array.
    static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int targetIndex = -1;

        while (low <= high) {
            /*
                int mid = (low + high) / 2;
                divided by 2 is a bug... if low + high is larger than maximal value of int, result overflows to negative
                https://research.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html
             */
            int mid = low + (high - low) / 2;

//            int mid = (low + high) >>> 1;
            // >>> is signed bit operator, moving toward right to get the int value of x/2

            int midValue = array[mid];

            if (midValue < target) {
                low = mid + 1;
            } else if (midValue > target) {
                high = mid - 1;
            } else {
                targetIndex = mid;
                break;
            }
        }

        return targetIndex;
    }
}