package designPattern;

public class BuilderTest {
    public static void main(String[] args) {
        Car.Builder builder = new Car.Builder(4, "BZ");


//    	System.out.println(car);
    }
}

/*  Builder Pattern is used to create object that is made from a bunch of other objects
 *  For creating an elements of a complex aggregate.
 *  When you want to the creation of different to be independent of the main object
 *  Hide the creation of each part, only the builder only how to build everything
 */

class Car {
    public static class Builder {
        // required parameters
        private final int wheels;
        private final String brand;

        // optional parameters
        private String color;

        public Builder(int wheels, String brand) {
            this.wheels = wheels;
            this.brand = brand;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    private final int wheels;
    private final String brand;
    private final String color;

    private Car(Builder carBuilder) {
        this.wheels = carBuilder.wheels;
        this.brand = carBuilder.brand;
        this.color = carBuilder.color;
    }

    public String toString() {
        return "car : " + this.wheels + " " + this.color;
    }
}

//interface CarBuilder {
//    public void setWheels();
//
//    public void setColor();
//
//    public Car getCar();
//}
//
//// this specific builder knows the specification: 4 wheels and red
//class RedCarBuilder implements CarBuilder {
//    private Car car;
//
//    public RedCarBuilder() {
//        this.car = new Car();
//    }
//
//    public void setWheels() {
//        this.car.setWheels(4);
//    }
//
//    public void setColor() {
//        this.car.setColor("red");
//    }
//
//    public Car getCar() {
//        return this.car;
//    }
//}
//
//class CarBuildDirector {
//    private CarBuilder builder;
//
//    public CarBuildDirector(CarBuilder builder) {
//        this.builder = builder;
//    }
//
//    public Car makeCar() {
//        this.builder.setWheels();
//        this.builder.setColor();
//
//        return this.builder.getCar();
//    }
//}
