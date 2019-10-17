package designPattern;

class VistorTest{
    public static void main(String[] args) {

    	Tobacoo t1 = new Tobacoo(100);
    	Liquor  l1 = new Liquor(100);
    	
    	TaxVisitor tv = new TaxVisitor();
    	System.out.println("Tax for t1 is : " + tv.visit(t1) );
    	System.out.println("Tax for l1 is : " + tv.visit(l1) );
    }
}

/////////////////////////////////////////////

//The visitor pattern is used when you have to perform
//the same action on many objects of different types
interface Visitor {
	public double visit(Tobacoo tobacooItem);
	
	public double visit(Liquor liquorItem);
}

//Allows the Visitor to pass the object so the right operations occur on the right type of object.
interface Visitable {
	public double accept(Visitor visitor);
}

/////////////////////////////////////////////

class TaxVisitor implements Visitor{

	// Implement the actual calculation for Tobacoo
	@Override
	public double visit(Tobacoo tobacooItem){
		return tobacooItem.getPrice() * 0.2;
	}
	
	// Implement the actual calculation for Liquor 
	@Override
	public double visit(Liquor liquorItem){
		return liquorItem.getPrice() * 0.3;
	}
}

class Tobacoo implements Visitable{
	private double price;
	
	Tobacoo(double price){
		this.price = price;
	}
	
	@Override
	public double accept(Visitor visitor){
		return visitor.visit(this);
	}
	
	public double getPrice(){
		return this.price;
	}
}

class Liquor implements Visitable{
	private double price;
	
	Liquor(double price){
		this.price = price;
	}
	
	@Override 
	public double accept(Visitor visitor){
		return visitor.visit(this);
	}
	
	public double getPrice(){
		return this.price;
	}
}