// checked
package basic;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AbstractClass implements III{
    public static void main(String[] args) {
    }
}

abstract class C1 {
    static int i = 1;
    int j = 2;

    abstract protected A m1() throws IOException;

    public void m() {
    }
}

abstract class C2 extends C1 {
    @Override
    public A m1() throws FileNotFoundException {
//	protected A m1() {
        return null;
    }
}

class C3 extends C1 {
    @Override
    public B m1() {
        return new B("asd"); // can return A's subclass
    }

    public void m1(int i) {
    }
}

interface III{

}
