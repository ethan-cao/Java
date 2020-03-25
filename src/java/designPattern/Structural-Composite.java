package designPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern
 *
 * It composes objects in tree structure to enable performing operation uniformly
 *
 * Put all operations that you want to perform "uniformly" in base component,
 * so it can let clients treat individual objects and compositions uniformly.
 */

class CompositePattern {

    public static void main(String... args) {
        Manager employee1 = new Manager("A");
        Employee employee2 = new Engineer("B");
        Employee employee3 = new Engineer("C");
        Employee employee4 = new Designer("D");

        employee1.addSubordinates(employee2);
        employee1.addSubordinates(employee3);
        employee1.addSubordinates(employee4);

        // treat individual objects and compositions uniformly.
        employee1.work();
    }

}


// base component
abstract class Employee {
    public String name;
    public int budget;

    abstract public void work();
}

// component
class Engineer extends Employee {
    public Engineer() { }

    public Engineer(String name) {
        this.name = name;
    }

    public void work() {
        System.out.println(this.name + " is coding...");
    }
}

class Designer extends Employee {
    public Designer(String name) {
        this.name = name;
    }

    public void work() {
        System.out.println(this.name + " is designing...");
    }
}

// composite
// it holds a collection of components, and methods to add/remove elements from the collection
class Manager extends Employee {
    List<Employee> subordinates = new ArrayList<>();

    public Manager(String name) {
        this.name = name;
    }

    // treat individual objects and compositions uniformly
    public void work() {
        System.out.println(this.name + " is managing...");
        subordinates.forEach(subordinate -> subordinate.work());
    }

    public void addSubordinates(Employee employee) {
        this.subordinates.add(employee);
    }

    public void removeSubordinates(int employeeIdx) {
        this.subordinates.remove(employeeIdx);
    }

}
