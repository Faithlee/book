package thread;

public class ThreadTest extends Thread{

    private Integer i = 0;

    public ThreadTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + ": " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            if (i == 20) {
                new ThreadTest("线程1").start();
                new ThreadTest("线程2").start();
            }
        }
    }
}
