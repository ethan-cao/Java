package serialization;

import java.io.*;
import java.util.Date;

public class SerializableTest {

    public static void main(String[] args) throws Exception {
        test1();

        // test2();
    }

    static void test1() throws Exception {
        Manager mgr = new Manager();
        mgr.companyName = "ABC Inc";
        mgr.name = "Sam";
        mgr.mgrId = "M001";
        mgr.level = "senior";
        System.out.println(mgr.toString());

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/ethan/mgr.ser"));
        oos.writeObject(mgr);
        oos.close();

        mgr = null;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/ethan/mgr.ser"));
        mgr = (Manager) ois.readObject();
        ois.close();
        System.out.println(mgr.toString());
    }

    static void test2() throws Exception {
        Student st = new Student();
        st.roomNo = 1;
        st.name = "Ethan";
        st.dob = new Date();
        st.address = "NL";
        st.regNo = "N";
        st.host.roomNo = 2;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/ethan/stu.ser"));
        oos.writeObject(st);
        oos.close();

        st = null;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/ethan/stu.ser"));
        st = (Student) ois.readObject();
        ois.close();
        System.out.println(st.toString());
    }
}

abstract class Employee {
    // Neither of the 2 instance property get serialized as Employee does not
    // implement Serializable
    String name;
    transient String companyName;
    static String city; // Static variables are not serialized.
}

class Manager extends Employee implements Serializable {
    String mgrId; // serialized
    transient String level; // this one does not get serialized as it is transient

    @Override
    public String toString() {
        return mgrId + " " + level + " " + name + " " + companyName;
    }
}

class Hostelite implements Serializable {
    int roomNo;
}

class Person extends Hostelite implements Serializable {
    String name;
    Date dob;
    transient String address;
}

class Student extends Person implements Serializable {
    String regNo;
    Hostelite host = new Hostelite();
}
