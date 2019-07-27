package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {


    public static void main(String[] args) {
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
