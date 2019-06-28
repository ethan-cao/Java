package basic;


public class ObjectEqualityTest {

    public static void main(String[] args){
        Integer i1 = 1000;
        Integer i2 = 1000;
        Integer i3 = 10;
        Integer i4 = 10;

        if( i1 != i2 )           System.out.println("different objects");
        if( i1.equals(i2) )  System.out.println("meaningfully equal");
        if( i3 == i4 )         System.out.println("same object");
        if( i3.equals(i4) )  System.out.println("meaningfully equal");

        /**
         * i3 == i4 is because: for saving memory,
         * instances of the following wrapper class are equal to each other
         *
         * Boolean
         * Byte
         * Character : from \u0000 to \u007f (7f is 127 in decimal)
         * Short and Integer : from –128 to 127
         */

    }

}