package dateStringI18N;

public class StringFormatTest {

    public static void main(String[] args) {
        int i1 = -123;
        int i2 = 12345;
        System.out.printf("%1$(7d \n", i1);
        System.out.printf("%0,7d \n", i2);
        System.out.format("%+-7d \n", i2);
        System.out.printf("%2$b + %1$5d \n", i1, false);
        System.out.println(System.lineSeparator());
        System.out.printf("%2$d  +  %1$d ", 123, 456);
        System.out.println(System.lineSeparator());

        System.out.printf("ocp : %2$d   %s  %3$b", "1", 2, 0);

        System.out.println(System.lineSeparator());

        System.out.println(String.format("%1$10s : %2$8s %3$5s%%", "A", 1, 1.2));

    }

}
