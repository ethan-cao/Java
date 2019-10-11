package designPattern;

/**
 * Builder Pattern
 *
 * Separate complex object creation in steps
 *
 */

class BuilderPattern {
    public static void main(String[] args) {
        Car.Builder carBuilder = new Car.Builder("BMW");
        Car car = carBuilder.setColor("white").build();
        System.out.println(car);
    }
}

class Car {

    public static class Builder {
        private final String brand;  // mandatory
        private String color;        // optional

        public Builder(String brand) {
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

    private final String brand;
    private final String color;

    private Car(Builder carBuilder) {
        this.brand = carBuilder.brand;
        this.color = carBuilder.color;
    }

    public String toString() {
        return "car : " + this.brand + " " + this.color;
    }

}

