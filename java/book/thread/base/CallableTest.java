package thread.base;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Callable实现多线程
 */
public class CallableTest {


    public static void main(String[] args) {
        /**
         *
         * Callable的call方法作为线程执行体，但其本身没有实现run方法;
         * 返回值使用Future接口来代表，Future接口实现了Runnable接口;
         * 可以用Future接口的实现类FutureTask作为Thread的target对象
         */
        FutureTask<Integer> task = new FutureTask<>(()->{
            int i = 0;
            for (; i < 40; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            return i;
        });

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if (i == 20) {
                new Thread(task).start();
            }
//            if (task.isDone()) {
//                System.out.println("子线程开始返回值");
//            } else {
//                System.out.println(Thread.currentThread().getName() + ":" + i);
//
//            }
        }
        if (task.isDone()) {
            System.out.println("子线程开始返回值");
        }

        try {
            System.out.println("子线程返回值: " + task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
