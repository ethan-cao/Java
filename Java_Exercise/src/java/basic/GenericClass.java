package basic;

import java.util.ArrayList;
import java.util.List;

public class GenericClass {

	public static void main(String[] args) {
		List<Bike> bikeList = new ArrayList<>();
		bikeList.add(new Bike());
		bikeList.add(new Bike());
		
		RentalGeneric<Bike, String> bikeRental = new RentalGeneric<>(2, bikeList);
		
		Bike bikeToRent = bikeRental.getRental();  
		bikeRental.returnRental(bikeToRent);
	}
}

class RentalGeneric<T, T1 extends String>{
	private List<T> rentalPool;
	private int maxNum;
	
	RentalGeneric(int maxNum, List<T> rentalPool){
		this.maxNum = maxNum;
		this.rentalPool = rentalPool;

		//  type parameter can not instantiate directly
//		new T(); compiler complains
	}

	//cannot use wildcards <? extends Type>  or <? super Type>
	T getRental(){
		return rentalPool.get(0);
	}
	
	void returnRental(T returnedThing){
		rentalPool.add(returnedThing);
	}
}

class Bike{}