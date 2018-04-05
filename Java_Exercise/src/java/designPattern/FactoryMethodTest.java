package designPattern;

public class FactoryMethodTest {

	public static void main(String[] args) {
		/*
		 * Pick the desired factory
		 * The logic of choosing the desired is in client side
		 */

		// Factory factory = new CircleFactory();
		Factory factory = new RectangleFactory();
		
		Shape shape = factory.getShape();
		
		System.out.println(shape);
	}
}

// Shape interface, Rectangle, Circle classes are defined in SimpleFactoryTest.java

interface Factory {
	public Shape getShape();
}

class CircleFactory implements Factory{
	public Circle getShape(){
		return new Circle();
	}
}

class RectangleFactory implements Factory{
	public Rectangle getShape(){
		return new Rectangle();
	}
}

// Add new factory and according class, if you need to create different instance
// No need to modify existing factory 