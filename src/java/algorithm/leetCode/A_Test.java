class A_Test {

    public static void main(String[] args) {
        System.out.println("Debugging...");

        int x = numDecodings("06");

        System.out.println("results: " + x);
    }

    public static int numDecodings(String s) {
        final int L = s.length();

        // counts[i]: when string size is i, how many ways to decode
        int[] counts = new int[L];

        int singleDigit = getSingleDigit(s, 0);
        counts[0] = singleDigit == 0 ? 0 : 1;

        if (L == 1)  {
            return counts[0];
        }

        singleDigit = getSingleDigit(s, 1);
        if (singleDigit != 0) {
            counts[1] = counts[1 - 1];
        }

        int doubleDigits = getDoubleDigit(s, 1);
        if (doubleDigits >= 10 && doubleDigits <= 26) {
            counts[1]+= 1;
        }

        for (int i = 2; i < L; ++i) {
            singleDigit = getSingleDigit(s, i);
            doubleDigits = getDoubleDigit(s, i);

            if (singleDigit != 0) {
                counts[i] = counts[i - 1];
            }

            if (doubleDigits >= 10 && doubleDigits <= 26) {
                counts[i] += counts[i - 2];
            }
        }

        return counts[L-1];
    }

    static int getSingleDigit(String s, int i) {
        return s.charAt(i) - '0';
    }

    static int getDoubleDigit(String s, int i) {
        return (s.charAt(i - 1) - '0') * 10  +  s.charAt(i) - '0';
    }
}