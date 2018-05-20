package thread16;

public class FirstThread  extends Thread{

    protected int i;

    public static void main(String[] args)
    {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                // 创建并启动线程执行流
                new FirstThread().start();
                new FirstThread().start();
            }
        }
    }


    @Override
    public void run() {
        for (; i < 100; i++) {
            // Thread的getName返回线程的名字
            System.out.println(this.getName() + " " + i);
        }
    }
}
