package iostream15;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @see ProcessDemo6#writeToProcess()
 * Process对象测试子进程输入
 */
public class ReadStandard {

    public static void main(String[] args)
    {
        try (
                Scanner scanner = new Scanner(System.in);
                PrintStream stream = new PrintStream(new FileOutputStream("standard_out.txt"));
        ) {
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                // System.out返回的类型为PrintStream
                stream.println("键盘输入的内容：" + scanner.next());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
