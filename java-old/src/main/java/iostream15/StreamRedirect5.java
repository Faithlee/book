package iostream15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * 标准重定向流
 */
public class StreamRedirect5 {

    public static void main(String[] args)
    {
        // todo 标准输入/输出：System.in/System.out，默认表示键盘、显示器
        // todo 重定向输入/输出方式：
        // 1. System.setIn操作输入流InputStream
        // 2. System.setOut、System.setErr方法操作PrintStream处理流

        StreamRedirect5 redirect = new StreamRedirect5();

        // Scanner从文件中接收数据
        redirect.setInTest();

        // todo 放在输入重定向前会影响测试结果，不会有输出
        // 输出到文件
        redirect.setOutTest();
    }

    /**
     * 将标准输出重定向输出到文件
     */
    public void setOutTest()
    {
        try (
                FileOutputStream output = new FileOutputStream("std_output.txt");
                PrintStream stream = new PrintStream(output);
        ) {
            // todo 将标准输出重定向到文件
            System.setOut(stream);

            // todo 输出不会显示到显示器，重定向到文件
            System.out.println("我被重定向了！！！");

            System.out.println(new StreamRedirect5());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 重定向输入
     */
    public void setInTest()
    {

        try (
                FileInputStream stream = new FileInputStream("/data/application/hello/src/main/java/iostream15/StreamRedirect5.java");
        ) {
            // todo 重定向输入流
            System.setIn(stream);

            // 指定从标准输入扫描
            Scanner scanner = new Scanner(System.in);
            // 使用回车作为分隔符，不会出现断行
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
                System.out.println("模拟键盘输入的内容：" + scanner.next());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
