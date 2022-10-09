package basic;

public class StaticMethodVariable {
	public static int cao1 = 2;
	
	public static void main(String[] args){
		StaticMethodVariable s = new StaticMethodVariable();
		s.m2();
		StaticMethodVariable.m1();
	}
	
	static void m1(){
		TestStaticMethodVariable.v1++;
		System.out.println(TestStaticMethodVariable.v1);
	}
	
	void m2(){
		TestStaticMethodVariable.v1++;
	}
}

class TestStaticMethodVariable{
	static int v1 = 3;
}