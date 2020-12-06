package designPattern;

/**
 * Proxy Pattern
 *
 * It wraps an object as the same Type but control the creation and access of the wrapped object
 * It is used to lazy-instantiate an object,  control access to the object.
 */

class ProxyPattern {

    public static void main(String... args) {
        EngineerProxy engineer = new EngineerProxy("Ethan");
        engineer.work();
        engineer.work();
    }

}

class EngineerProxy extends Engineer {
    private Engineer engineer;
    private String workObject = "";

    public EngineerProxy(String name) {
        // object creation happens inside proxy
        // however creation happens outside decorator in Engineer(name)
        this.engineer = new Engineer(name);
    }

    public void work() {
        // proxy controls how it behaves
        if (this.workObject.isEmpty()) {
            this.workObject = "Website";
            System.out.printf("%s is coding %s\n", this.engineer.name, this.workObject);
        } else {
            System.out.printf("%s has started coding %s\n", this.engineer.name, this.workObject);
        }
    }
}

