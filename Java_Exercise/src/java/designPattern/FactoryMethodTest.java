package designPattern;

/**
 * Factory Method Pattern
 *
 * It defines an interface for creating objects, and delegate the instantiation logic to child classes.
 *
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        // The logic of choosing the desired factory is in client side

        Factory factory1 = new CircleFactory();
        Shape shape1 = factory1.getShape();

        Factory factory2 = new RectangleFactory();
        Shape shape2 = factory2.getShape();
    }
}

interface Factory {
    Shape getShape();
}

class CircleFactory implements Factory {
    public Circle getShape() {
        return new Circle();
    }
}

class RectangleFactory implements Factory {
    public Rectangle getShape() {
        return new Rectangle();
    }
}

// if we wanna create different instance, just need to add new factory
// No need to modify existing factory, this complies with open for extension and closed for modification