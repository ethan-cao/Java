package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericMethod {

	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<>();
	    animals.add(new Dog());
	    animals.add(new Husky());
		
	    addMethod(animals);
	    addMethod1(animals);
	    addMethod2(animals);
		addMethod3(animals);

		List<String> l = new ArrayList<>();
		l.add("A");
		l.add("B");
		l.add("C");
		addMethod4(l);
		addMethod5(l);
		System.out.println(l);
	}

	public static <T> T genericMethod1(T t) {
		return t;
	}

	public static <T1 extends String, T2 extends Integer, T3> T1 genericMethod2(T1 t1, T2 t2) {
		return t1;
	}

	static void addMethod(List<Animal> animals) {
		animals.add(new Cat());
	}
	
	static void addMethod1(List<? extends Animal> animals){
//		compiler error
//		animals.add(new Dog());
		/*
		 if the argument is List<Husky>, and we try to add Dog instance, then it is illegal
		 this could happen at runtime, since there is no type check at runtime
		 */
	}

	static void addMethod2(List<? super Animal> animals){
		animals.add(new Dog());
		/*
		 * if the argument can only be Animal or Object
		 * then it is safe to add Dog instance 
		 */
	}

	static void addMethod3(List<?> animals){  // the same as List<? extends Object>
//		compiler error
//		animals.add(new Dog());
	}

	static void addMethod4(List<String> list){
		list.add("D");
//		list.add(new Animal());  // compilation fail
	}

	static void addMethod5(List list){
	    // we can add anything
		list.add(new Animal());
	}
}

class Animal {}

class Dog extends Animal {}
class Husky extends Dog{}

class Cat extends Animal {}
