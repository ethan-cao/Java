package designPattern;

/**
 * Abstract Factory Pattern
 *
 * A factory of factories
 * A factory that groups the individual but related/dependent factories together without specifying their concrete classes.
 */

public class AbstractFactoryTest {
    public static void main(String[] args) {

    }
}


abstract class Computer {
}

class Mac extends Computer {
}

class ChromeBook extends Computer {
}



abstract class Phone {
}

class Iphone extends Phone {
}

class Android extends Phone {
}



interface AbstractFactory {
    Computer createComputer();

    Phone createPhone();
}

// all Apple stuff are grouped
class AppleFactory implements AbstractFactory {
    @Override
    public Computer createComputer() {
        return new Mac();
    }

    @Override
    public Phone createPhone() {
        return new Iphone();
    }
}

// all Google stuff are grouped
class GoogleFactory implements AbstractFactory {
    @Override
    public Computer createComputer() {
        return new ChromeBook();
    }

    @Override
    public Phone createPhone() {
        return new Android();
    }
}
