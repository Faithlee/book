package file;

import java.io.*;
import java.util.Scanner;

public class FileIO {

    public static void main(String[] args) throws IOException {

        // 1.读取文件
//        inputStream();

        // 2.读取文件内容并输出
//        inputStream2();

        // 3.输出到文件
        // inputStream2OutputStream();

        // 4. Reader读取字符流
        // testFileReader();

        // 5.FileWriter输出到文件，
        // testFileWriter();

        // 6.PrintStream处理流输出
        // testPrintStream();

        // 7.以字符串作为节点的字符流
        // testStringStream();

        // 8.转换流测试
        // testByteTransferChar();

        // 9.重定向输出/输入
        // testRedirectOutput();

        // 10.重定向输入流
        testRedirectInput();
    }

    /**
     * 字节流输入
     * @throws IOException
     */
    public static void inputStream() throws IOException {
        File file = new File("book\\file\\FileIO.java");

        FileInputStream inputStream = new FileInputStream(file);
        // 每次读取1个字节，类似一滴一滴取水
        int buffer;
        while ((buffer = inputStream.read()) != -1) {
            // 转为16进制
            System.out.println(Integer.toHexString(buffer));

            // 去掉 &0xFF;
        }

        inputStream.close();
    }

    /**
     * 读取文件内容并输出
     */
    public static void inputStream2() {
        File file = new File("book\\file\\FileIO.java");
        try(
                FileInputStream inputStream = new FileInputStream(file)
        ) {
            // 类似竹筒取水
            byte[] buffer = new byte[1 * 1024];
            int read;
            while ((read = inputStream.read(buffer)) > 0) {
                String str = new String(buffer, 0, read);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取输入流内容然后输出
     *
     * #实现文件内容的拷贝
     */
    public static void inputStream2OutputStream() {
        File file = new File("book\\file\\FileIO.java");

        // 如果目录不存在，则需要先创建，输出文件不存在则不需要
        File output = new File("tmp\\inputStream2OutputStream.txt");

        try (
                FileInputStream inputStream = new FileInputStream(file);
                FileOutputStream outputStream = new FileOutputStream(output)
        ) {
            // 读取到的字节数
            int hasRead;
            byte[] buffer = new byte[1024];
            while ((hasRead=inputStream.read(buffer, 0, buffer.length)) > 0) {
                // todo 读取到buffer中的字节内容输出文件
                outputStream.write(buffer, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /******************* Reader/Writer操作字符流 **************************/

    /**
     * 测试字符流读写
     */
    public static void testFileReader() {
        // 测试字符流读写测试字符流读写测试字符流读写测试字符流读写测试字符流读写测试字符流读写测试字符流读写测试字符流读写测试字符流读写
        File file = new File("book\\file\\FileIO.java");
        try (
                FileReader reader = new FileReader(file);
                ) {
            char[] chars = new char[32];
            int hasRead;
            while ((hasRead = reader.read(chars)) > 0) {
                System.out.println(new java.lang.String(chars, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testFileWriter() {
        File poem = new File("tmp\\write.txt");

        /**
         * 写文件时可以设置是否追加的模式
         */
        try (
                FileWriter writer = new FileWriter(poem)
                ) {
            writer.write("这是一首诗;");
            writer.write("这是一首诗;");
            writer.write("这是一首诗;");
            writer.write("这是一首诗;");
            // 换行符？
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("over!");
    }


    /***********************  使用处理流   *****************************/

    /**
     * 使用处理流，如PrintStream将内容输出到指定文件
     *
     * 1. 输入/输出更简单
     * 2. 使用处理流执行效率更高
     */
    public static void testPrintStream() {
        File file = new File("tmp\\print_stream.txt");

        /**
         * System.out的实现就是PrintStream
         * 参考：重定向输出
         */
        try (
              FileOutputStream outputStream = new FileOutputStream(file);
              PrintStream stream = new PrintStream(outputStream);
               ) {
            stream.println("普通输出测试");
            // 输出对象
            stream.println(new FileIO());
        } catch (IOException e) {
           e.printStackTrace();
        }

        System.out.println("over");
    }

    /**
     * 字符流：以字符串作为节点读写内容
     *
     * 字节流以字节数组为节点，字符流以字符数组为节点
     */
    public static void testStringStream() {
        String str = "以字符串作为节点读写内容\n" +
                "以字符串作为节点读写内容\n" +
                "以字符串作为节点读写内容\n" +
                "以字符串作为节点读写内容\n" +
                "以字符串作为节点读写内容\n";
        try (
                StringReader reader = new StringReader(str)
                ) {
            int hasRead;
            char[] chars = new char[62];
            while ((hasRead = reader.read(chars)) > -1) {
                System.out.println(new String(chars, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                StringWriter writer = new StringWriter()
                ) {
            writer.write("实际上是以StringBuffer作为输出节点!");
            writer.write("实际上是以StringBuffer作为输出节点!");
            writer.write("实际上是以StringBuffer作为输出节点!");
            writer.write("实际上是以StringBuffer作为输出节点!");
            /**
             * write()与append()的区别?
             * 实际append也是调用write方法追加到StringBuilder中了
             */
            System.out.println(writer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节转换字符流操作
     *
     * 获取键盘标准输入字节流转为字符流
     * 与使用Scanner从Sytem.in相似，都可以实现从命令行读取内容
     */
    public static void testByteTransferChar() {
        /**
         * 使用BufferedReader包装节点流，方便读取整行内容
         */
        try (
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader buffer = new BufferedReader(reader)
                ) {
            String line;

            while ((line = buffer.readLine()) != null) {
                if (line.equals("quit")) {
                    System.exit(1);
                }

                System.out.println("输入内容:" + line);
            }
        } catch (IOException e) {

        }
    }

    /**
     * 测试推回输入流
     */
    public static void testPushbackfStream() {
        // todo
    }

    /**
     * 重定向标准输入/输出
     */
    public static void testRedirectOutput() {
        /**
         * 将System.out.println重定向的内容输出到文件中
         */

        File file = new File("tmp\\redirect.txt");
        try (
            FileOutputStream outputStream = new FileOutputStream(file);
            PrintStream stream = new PrintStream(outputStream)
                ) {
            System.setOut(stream);

            // 重定向
            System.out.println("使用System.setOut重定向输出到文件中");
            System.out.println(new FileIO());

            stream.print("这是打印的内容");
            stream.append("这是追加的内容!");
            stream.println("这是打印换行的内容!");
            stream.append("这又是追加的内容!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }

    /**
     * 设置标准输入
     */
    public static void testRedirectInput() {

        File file = new File("tmp\\redirect.txt");
        try (
                FileInputStream inputStream = new FileInputStream(file);
                ) {
            // #重定向输入流
            System.setIn(inputStream);

            // 设置标准输入源
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * java虚拟机读写其它进程数据
     */
    public static void testRuntimeExec() {
        // todo
    }
}
