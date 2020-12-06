package designPattern;

/**
 * Decorator Pattern
 *
 *
 * It wraps an object as the same type and make it behave differently at runtime
 * without changing it type to avoid affecting the behavior of other objects from the same class
 */

class DecoratorPattern {

    public static void main(String... args) {
        Engineer engineer = new Engineer("Ethan");
        engineer.work();

        Engineer javaEngineer = new JavaEngineer(engineer);
        javaEngineer.work();
    }

}

class JavaEngineer extends Engineer {
    private Engineer engineer;

    public JavaEngineer(Engineer engineer) {
        super(engineer.name);
        this.engineer = engineer;
    }

    public void work() {
        System.out.println(this.name + " is coding Java ...");
    }
}
