//checked
package basic;

public class ReferenceCast {
    public static void main(String[] args) {
        Truth t1 = new Truth1();
//        (Truth2) t1.tellTruth();   // Cast return type from tellTruth() to Truth2
        ((Truth2) t1).tellTruth();   // Cast t1 to Truth2, then invoke tellTruth()

    }

    static void upCastingTest(){
        Truth1 t1 = new Truth1();
        System.out.println( (Truth) t1);
    }

    static void downCastingTest(){
        Truth t1 = new Truth1();
        Truth t2 = new Truth2();

        // Downcasting : depends
        // if the actual object can pass instanceof, then runtime ok, otherwise, runtime error

        Truth1 a1 = (Truth1) t1; // correct, as t1 is actually Truth1 type, downcasting is possible
        Truth1 a2 = (Truth1) t2; // runtime error, as t2 is actually Truth2 type, downcast is not possible

        Object o = getSomeObject();
        String s = (String) o;

        Object o1 = new Object();
        String s1 = (String) o1; // runtime error, because o1 instanceof String --> false

        Object o2 = "a String";
        String s2 = (String) o2; // runtime of, because o2 instanceof String --> true
    }

    private static Object getSomeObject() {
//      return new ReferenceCast();
        return null;
    }
}


interface Truth {
    void tellTruth();
}

class Truth1 implements Truth {
    @Override
    public void tellTruth() {
    }
}

class Truth2 implements Truth {
    @Override
    public void tellTruth() {
    }
}