package thread.base;

/**
 * Runnable以lambda表达式形式实现多线程
 */
public class RunnableLambda {

    /**
     * todo 函数式的实现方式下要求静态成员变量？
     */
    private static int i;

    public static void main(String[] args) {

        // 申明Runnable接口实现
        Runnable thread = () -> {
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        };

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);

            if (i == 20) {
                new Thread(thread, "线程1").start();
                new Thread(thread, "线程2").start();
            }
        }
    }
}
