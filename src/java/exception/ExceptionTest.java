package exception;

import java.io.EOFException;
import java.io.IOException;

public class ExceptionTest {

    public static void main(String[] args) {
        testExceptionExecutionOrder();


		try{
            doStuff();
		}
		catch(Exception e){
			System.out.println("in main");
		}finally{
			System.out.println("in finally");
		}

		System.out.println("after");
    }


    static void test(){
        System.out.println("before ");

        try {
            int i = 1 / 0;
            System.out.println("try ");
        } catch (Exception e) {
            System.out.println("catch ");
            throw e;   // re-throw the exception
        } finally {
            System.out.println("finally ");
        }

        System.out.println("after ");
        //  since e is re-thrown,  e is propagated to caller in the call stack, that is why “after” is no printed
        // if e is not re-thrown, “after is printed”
    }

    static void testExceptionExecutionOrder(){
        System.out.print("before ");

        try {
            int i = 1 / 0;
            System.out.println("try ");
        } catch (Exception e) {
            System.out.println("catch ");
//            throw e;
        } finally {
            System.out.println("finally ");
        }

        System.out.println("after "); // 如果沒有throw e, after也会被打出来
    }

    // runtime/unchecked exception, ArithmeticException is thrown here, does not need catch or declare
    static void testUncheckedException() {
        int x = 5 / 0; // Can't divide by zero!
    }

    // checked exception, must catch or declare
    static void testCheckedException() throws ClassNotFoundException {
        try { // catch and declare at the same time is also ok
            Class loadedClass = Class.forName("className");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void doStuff() {
        try {
            testUncheckedException();
        } catch (Exception e) {
            System.out.println("in doStuff");
            e.printStackTrace(System.out);  // Print the call stack
        }

//		testCheckedException();
    }
}

interface Base {
    void doIt() throws IOException;
}

class Child implements Base {
    @Override
    public void doIt() throws EOFException {
        // Override method can only more specific exception
    }

    public void test() throws Exception {
        // the actual thrown exception can be subclass
        throw new EOFException();
    }
}
