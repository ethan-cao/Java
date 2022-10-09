package basic;

public class Utilities {
    public static void main(String[] args) {
        float f1 = 1.2f;

        // check primitive variable type
        System.out.println(((Object) (f1)).getClass().getName());


        // print current call stack
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            System.out.println(ste);
        }

        // check if string is empty
        String s = "";
        boolean isEmpty;
        if (s == null) {
            isEmpty = true;
        } else {
            isEmpty = s.length() == 0;
        }
        System.out.println(isEmpty);


    }

    public void printCallStack() {
        try {
        } catch (Exception e) {
            e.printStackTrace(System.out);  // Print the call stack
        }
    }

}


