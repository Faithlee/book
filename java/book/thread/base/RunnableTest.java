package thread.base;

/**
 * Runnable实现多线程，run()的方法体作为Thread的线程执行体；
 *
 * Runnable的实例作为Thread的target对象；
 */
public class RunnableTest implements Runnable{

    private int i;

    @Override
    public void run() {
        /**
         * 获取当前线程名称：Thread.currentThread().getName()
         *
         * 线程共享RunnableTest类实例的变量i，线程1和2输出是连续的
         *
         * todo 注意：由于可能没有加锁导致输出会有重复？
         */
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            if (i == 20) {
                RunnableTest runnableTest = new RunnableTest();
                new Thread(runnableTest, "线程1").start();
                new Thread(runnableTest, "线程2").start();

            }
        }
    }
}
