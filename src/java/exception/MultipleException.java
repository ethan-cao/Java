package exception;

import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.ZipError;
import java.util.zip.ZipException;

public class MultipleException {

    public static void main(String[] args) {
        MultipleException me = new MultipleException();

        try {
            me.method1();
        } catch (SQLException | IOException e) {  // order does not matter
            System.out.println(e);
        }
    }

    // it is legal to put super and sub exception together after throws
    void method1() throws SQLException, IOException, ZipException {
        try {
            method2();
        } catch (Exception e) {
//			System.out.println(e);
//			e = new Exception();
//			throws e;
        }
    }

    void method2() throws SQLException, IOException {
        throw new RuntimeException("A");
    }

}
