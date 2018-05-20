package thread16;

import java.lang.Thread;

/**
 * 线程的创建与启动
 */
public class Thread1 {

    public static void main(String[] args)
    {
        for (int i = 0; i < 100; i++) {
            // 查看当前线程：即主线程
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                // 创建并启动线程
                new FirstThreads().start();
                new FirstThreads().start();
            }
        }
    }
}


class FirstThreads extends Thread
{
    private int i;

    // todo run方法体即为线程执行
    public void run() {
        for (; i < 100; i++) {
            // Thread.getName: 返回当前线程的名称
            System.out.println(this.getName() + " " + i);
        }
    }
}
