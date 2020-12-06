package designPattern;

/**
 * Abstract Factory Pattern
 *
 * Enhanced Factory Method Pattern, useful when necessary to group related objects creation
 * A factory of factories
 */

abstract class Computer {
    public void turnOn() {
        System.out.println("Turning on ...");
    }

    public void runVirusScan() {
        System.out.println("Running virus scan ...");
    }

    public void turnOff() {
        System.out.println("Turning off ...");
    }
}

class Mac extends Computer {
}

class ChromeBook extends Computer {
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
        return new IPhone();
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
