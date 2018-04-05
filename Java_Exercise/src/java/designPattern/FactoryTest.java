package designPattern;

/*
 * 	Factory Pattern
 *  it is used to generate instances that is sub type of or implementation of a specific type
 *  Pick specific class at runtime
 */

public class FactoryTest {
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		Shape shape;
		
		shape =shapeFactory.getShape("Circle");
		System.out.println(shape);
		shape= shapeFactory.getShape("Rectangle");
		System.out.println(shape);
	}
}

interface Shape {
	void draw();
}

class Rectangle implements Shape {
   public void draw() {
      System.out.println("This is a Rectangle.");
   }
}

class Circle implements Shape {
   public void draw() {
      System.out.println("This is a Circle");
   }
}

class ShapeFactory {
   public Shape getShape(String shapeType){
      if(shapeType == null){
         return null;
      }		

      if(shapeType.equalsIgnoreCase("CIRCLE")){
         return new Circle();
      } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
         return new Rectangle();
      }
      
      // if we need to add more shapes, we have to modify this ShapeFactory 
      // This is against open for extension, but closed for modification.
      // That is why Factory Method comes to here 
      
      return null;
   }
}