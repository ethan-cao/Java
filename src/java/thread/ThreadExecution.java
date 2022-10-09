package thread;

public class ThreadExecution {
    public static void main(String[] args) {

        Thread t1 = new Thread() {
            public void run() {
                System.out.println("t1");
            }
        };

        Thread t2 = new Thread(){
            public void run() {
                System.out.println("t2");
            }
        };

        t1.start();  // t1 might get execute before main finish
        t2.start();  // t2 might get execute before main finish

        System.out.println("main");
    }
}
