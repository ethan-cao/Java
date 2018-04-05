//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


public class BenfordsLaw {
    public BenfordsLaw() {
    }

    public static int questionableDigit(int[] transactions, int threshold) {
        if (threshold >= 2 && threshold <= 10) {
            for(int i = 1; i <= 9; ++i) {
                int actualAppear = actualCount(i, transactions);
                double expectedAppear = Math.log10((double)(1 + 1 / i));
                if ((double)actualAppear > expectedAppear * (double)threshold || (double)actualAppear < expectedAppear * (double)(1 / threshold)) {
                    return i;
                }
            }
        }

        return -1;
    }

    private static int actualCount(int i, int[] transactions) {
        int appear = 0;

        for(int j = 0; j < transactions.length; ++j) {
            if (Character.getNumericValue(Integer.toString(transactions[j]).charAt(0)) == i) {
                ++appear;
            }
        }

        return appear;
    }

    public static void main(String[] args) {
        int[] transactions = new int[]{5236, 7290, 200, 1907, 3336, 9182, 17, 4209, 8746, 7932, 6375, 909, 2189, 3977, 2389, 2500, 1239, 3448, 6380, 4812};
        int threshold = 2;
        int result = questionableDigit(transactions, threshold);
        System.out.println(result);
    }
}
