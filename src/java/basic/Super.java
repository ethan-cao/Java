package basic;
 
class Super {
	public String v = "Father";
	public String x = "Father's x";

	public Super() {
		System.out.println("Father无参构造方法被调用!");
	}

	public Super(String v) {
		System.out.println("Father带参构造方法被调用!");
		this.v = "Father类的带参数构中的v";
	}

	public void outinfo() {
		System.out.println("Father的outinfo方法被调用");
	}

	public static void main(String[] args) {
		new Son().test();
//		new Son("new").test();

	}
}

class Son extends Super {
	public String v = "Son";

	public Son() {
//		super("asd");
//		super(); //  超类的构造方法总是被隐式调用的, 且只能放到第一行.
		System.out.println("Son无参数构造方法被调用!");
//		 super(); // Wrong, 调用的构造函数必须放到最前面.
	}

	public Son(String str) {
//		super(str);
//		super();
		System.out.println("Son带参数构造方法被调用!");
	}

	// 覆盖了超类成员方法outinfo()
	public void outinfo() {
		System.out.println("Son的outinfo()方法被调用");
	}

	public void test() {
		String v = "hi!"; // variable shadowing, local variable v 覆盖了成员变量v和超类变量v

		System.out.println("------1-----");
		System.out.println(v); // 输出局部变量v
		System.out.println(this.v); // 输出(子类)成员变量v
		System.out.println(super.v); // 输出超类成员变量v

		System.out.println("------2-----");
		System.out.println(x); // 输出超类成员变量v,子类继承而来
		System.out.println(super.x); // 输出超类成员变量v

		System.out.println("------3-----");
		outinfo(); // 调用子类的outinfo()方法
		this.outinfo(); // 调用子类的outinfo()方法
		super.outinfo(); // 调用父类的outinfo()方法
	}

}