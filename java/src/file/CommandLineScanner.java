package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class CommandLineScanner {
    /**
     * 从命令行读取标准输入内容然后输出或重定向指定文件
     */
    public static void main(String[] args) {
        File file = new File("tmp\\scanner_output.txt");
        /**
         * 1. 改进输出到文件
         * 2. 增加异常处理
         */
        try (
                Scanner scanner = new Scanner(System.in);
                FileOutputStream outputStream = new FileOutputStream(file);
                PrintStream stream = new PrintStream(outputStream)
        ) {

            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                //System.out.println(scanner.next());
                // 输出到文件中
                stream.println(scanner.next());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }
}
