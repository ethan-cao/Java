package algorithm.recursion;

public class GreatestCommonDivisor {
    public static void main(String... args) {
        int[] a = {12, 8, 32};

        int greatestCommonDivisor = 0;
        for (int number : a) {
            greatestCommonDivisor = getGreatestCommonDivisor(number, greatestCommonDivisor);
        }

        System.out.println(" greatestCommonDivisor : " + greatestCommonDivisor);
    }

    // https://en.wikipedia.org/wiki/Euclidean_algorithm
    public static int getGreatestCommonDivisor(int a, int b) {
        return b > 0 ? getGreatestCommonDivisor(b, a % b) : a;
    }
}
