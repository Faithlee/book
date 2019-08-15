package thread.base;

/**
 * Thread实现多线程
 */
public class ThreadTest extends Thread{

    private Integer i = 0;

    public ThreadTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        /**
         * 直接使用this表示当前线程，getName()即可获取当前线程名称
         *
         * Thread创建的多线程没有共享变量i，循环的变量不连续
         */
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

        System.out.println("我是主线程");
    }
}
