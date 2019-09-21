package exception;

public class NewException {
    // for case1 this is correct
	public static void main(String[] args) throws MissingInfoException, AgeOutOfRangeException{

    // for case2 this is correct
//	public static void main(String[] args) throws Exception {

//    public static void main(String[] args) {
        /**
         * try、catch、finally三个代码块中变量的作用域分别独立而不能相互访问，也不能从外面访问任何一个块里面的资源。
         * 如果要在三个块中都可以访问，则需要将变量定义到这些块的外面。
         *
         * we can also use try-with-resource syntax
         */
        Candidate c = null;
        Candidate c1 = null;

//        try {
            c = new Candidate("J", 201);
            c1 = new Candidate("W", 32);
//		} catch (MissingInfoException e1) {
//			e1.printStackTrace();  // works for case1
//		} catch (AgeOutOfRangeException e2) {
//			e2.printStackTrace();  // works for case1 
//        } catch (Exception e3) {
//            System.out.println(e3.getClass().getName());
//            e3.printStackTrace();  // works for case2
//        }

        System.out.println(c);
        System.out.println(c1);
    }
}

class MissingInfoException extends Exception {}

class AgeOutOfRangeException extends Exception {}

class Candidate {
    String name;
    int age;

    // case1: declare multiple exception
	Candidate(String name, int age) throws MissingInfoException, AgeOutOfRangeException{

    // case2: declare superclass exception
//    Candidate(String name, int age) throws Exception {

        if (name == null) {
            throw new MissingInfoException();
        } else if (age <= 10 || age > 150) {
            throw new AgeOutOfRangeException();
        } else {
            this.name = name;
            this.age = age;
        }
    }

    @Override
    public String toString() {
        return name + " age " + age;
    }
}