package designPattern;

/**
 * Factory Pattern
 *
 * It generates an instance for client without exposing any instantiation logic to the client
 */

public class FactoryTest {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape1 = shapeFactory.getShape("Circle");
        Shape shape2 = shapeFactory.getShape("Rectangle");
    }
}

interface Shape {
}

class Rectangle implements Shape {
}

class Circle implements Shape {
}

class ShapeFactory {

    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }

        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }

        // if we need to add more shapes, we have to modify this ShapeFactory
        // This is against open for extension, but closed for modification.
        // That is where Factory Method comes to play

        return null;
    }

    // Alternatively
    public Shape getCircleShape() {
        return new Circle();
    }

    public Shape getRectangleShape() {
        return new Rectangle();
    }
}