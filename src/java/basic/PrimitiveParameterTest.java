package basic;

public class PrimitiveParameterTest {
    public static void display(long ivar) {
        System.out.println(ivar);
    }

    public static void display(Integer ivar) {
        System.out.println(ivar * ivar);
    }

    public static void display(Long ivar) {
        System.out.println(ivar * ivar * ivar);
    }

    public static void main(String[] args) {
        int var = 2;
        display(var);
        // look for byte-> short-> int-> long,
        // if can not find it, find Integer
    }
}