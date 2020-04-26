package basic;

public class Number {

    public static void main(String... args) {

        System.out.println(Integer.compare(Integer.MAX_VALUE, Integer.MIN_VALUE)); // 1
        System.out.println(Integer.compareUnsigned(Integer.MAX_VALUE, Integer.MIN_VALUE)); // -1

        System.out.println(Integer.MIN_VALUE / Integer.MAX_VALUE); //   -1
        System.out.println(Integer.divideUnsigned(Integer.MIN_VALUE, Integer.MAX_VALUE)); // 1

//        Integer.parseInt("2147483648");  // runtime exception, Integer.MAX_VALUE + 1
        Integer.parseUnsignedInt("2147483648"); // works

        // scientific notation: 3.30 x 10ˆ23
        double d  = 3.30e23;

        // 10^9 + 7
        int i = (int) 1e9 + 7;

    }

}
