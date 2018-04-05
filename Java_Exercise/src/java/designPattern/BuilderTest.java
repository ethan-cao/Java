package designPattern;

public class BuilderTest{
    public static void main(String[] args){
    	CarBuilder builder = new RedCarBuilder();
    	CarBuildDirector director = new CarBuildDirector(builder);
    	Car car = director.makeCar();
    
    	System.out.println(car);
    }
}

/*  Builder Pattern is used to create object that is made from a bunch of other objects
 *  For creating an elements of a complex aggregate. 
 *  When you want to the creation of different to be independent of the main object
 *  Hide the creation of each part, only the builder only how to build everything
 */

class Car {
    private int wheels;
    private String color;

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public String toString(){
    	return "car : " + this.wheels + " " + this.color;
    }
}

interface CarBuilder {
    public void setWheels();

    public void setColor();

    public Car getCar();
}

// this specific builder knows the specification: 4 wheels and red 
class RedCarBuilder implements CarBuilder {
    private Car car;

    public RedCarBuilder() {
        this.car = new Car();
    }

    public void setWheels() {
        this.car.setWheels(4);
    }

    public void setColor() {
        this.car.setColor("red");
    }

    public Car getCar() {
        return this.car;
    }
}

class CarBuildDirector {
    private CarBuilder builder;

    public CarBuildDirector(CarBuilder builder) {
        this.builder = builder;
    }

    public Car makeCar() {
        this.builder.setWheels();
        this.builder.setColor();
        
        return this.builder.getCar();
    }
}
