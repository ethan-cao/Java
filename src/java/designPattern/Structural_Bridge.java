package designPattern;

/**
 * Bridge Pattern
 *
 * It wraps an object as another Type with composition
 *
 * Implementation details are pushed from a hierarchy to another object with a separate hierarchy.
 * It decouples an abstraction from its implementation so that the two can vary independently
 */

class BridgePattern {

    public static void main(String... args) {
        Vehicle vehicle = new Vehicle();
        vehicle.moveOnWater();
        vehicle.moveOnLand();
    }

}

class Vehicle {
    private RunOnWater runOnWaterModule = new RunOnWater();
    private RunOnLand runOnLandModule = new RunOnLand();

    public void moveOnWater() {
        runOnWaterModule.move();
    }

    public void moveOnLand() {
        runOnLandModule.move();
    }
}

interface Movable {
    void move();
}

class RunOnWater implements Movable {
    public void move() {
        System.out.println("Move on Water");
    }
}

class RunOnLand implements Movable {
    public void move() {
        System.out.println("Move on Land");
    }
}
