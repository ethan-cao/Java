package basic;

public class MethodTest {

    // accepts array or an arbitrary number of values
    static void m1(int... o){
        System.out.println(o);
    }

    // Only accepts array
    static void m2(int[] o){
        System.out.println(o);
    }

    public static void main(String[] args){
        int[] i = {1};

        m1(1);
        m1(i);

//        m2(1); // compilation fail
        m2(i);
    }

    public Thread m3(){
        return null;   // regardless the return type, return null is always ok
    }
}
