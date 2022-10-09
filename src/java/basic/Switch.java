package basic;

import basic.EnumTest.Light;

public class Switch {

	public static void main(String [] args){
		 
		Integer i = 1;
		int y = 2;
		int x = 4;  // check the print, when x = 1, 2, 4
		/**
		 A switch expression should be
		 primitive:        byte, short, char,    and int
		 and wrap type:    Byte, Short, Character and Integer
		 It also works with Enum and the String class
		 */
		switch (x) {
		  case 1: System.out.println("2");   // case expression should be constant
//		  case y+1: System.out.println("2");   // cannot use variable
//		  case i: System.out.println("2");   // cannot use variable
		  case 3: System.out.println("3");   // case expression使用枚举类型时，不能在每一个枚举类型值的前面加上枚举类型的类名，否则编译器就会报错 
		  default: System.out.println("default");
		  case 4: System.out.println("4");
		  case 5:  System.out.println("5");
		}

		EnumTest.Light l = EnumTest.Light.GREEN;
		switch (l){
			case GREEN:  System.out.println("green");
			case RED:  System.out.println("green");
			// switch expression使用枚举类型时, 不可以加上Enum Type Name
//			case EnumTest.Light.Blue:  System.out.println("green");
		}

		// case 后紧跟常量表达式，不能是变量。
		// cases will be checked one by one in order, whenever a case is matched, it will start executing until break is encountered or until the end of the switch
		
		// when no case is matched, default block will be executed. It will start executing until break is encountered or until the end of the switch
		// where default is positioned does not affect others. even if it is on the top
		
		
		
		// string valid JDK >= 1.7 
		System.out.println("Test String");
/*		
		String color = "Red";
		switch(color) {
			case "red":
				System.out.println("Found Red");
				break;
			case "Blue": 
				System.out.println("Found Blue");
				break;
			case "White":
				System.out.println("Found White");
				break;
			default:
				System.out.println("not specific color, Found Default");
		}
*/		
		
	}

}
