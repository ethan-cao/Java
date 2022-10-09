package basic;

public class Lambda {
    public static void main(String[] args){
        // lambda statement : param -> { return expression; }
        // lambda expression : param -> expression
    }

    static void anonymousClassAlternative(){
        // anonymous class
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Run");
            }
        };

        // use lambda expressions to instantiate functional interface
        Runnable r2 = () -> System.out.println("Run");
    }
}