package designPattern;

/**
 * Flyweight Pattern
 *
 * It caches objects for future reuse.
 * Anything that will be cached is flyweight.
 *
 * e.g. java.lang.Integer#valueOf(int)
 */

class FlyweightPattern {

    public static void main(String... args) {
        //  java.lang.Integer#valueOf(int) caches values in [-128 to 127]

        Integer i1_1 = Integer.valueOf(1);
        Integer i1_2 = Integer.valueOf(1);
        System.out.println(i1_1 == i1_2);

        Integer i1000_1 = Integer.valueOf(1000);
        Integer i1000_2 = Integer.valueOf(1000);
        System.out.println(i1000_1 == i1000_2);
    }

}

