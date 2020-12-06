package designPattern;

/**
 * Fasade Pattern
 *
 * It wraps an object as another Type to provide a simplified interface to a larger body of code
 */

class FacadePattern {
    public static void main(String... args) {
        Computer mac = new Mac();
        ComputerFacade macFacade = new ComputerFacade(mac);

        macFacade.scanVirus();
    }
}

class ComputerFacade {
    private Computer computer;

    public ComputerFacade(Computer computer) {
        this.computer = computer;
    }

    public void scanVirus() {
        this.computer.turnOn();
        this.computer.runVirusScan();
        this.computer.turnOff();
    }
}