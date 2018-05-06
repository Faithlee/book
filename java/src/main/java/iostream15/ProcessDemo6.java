package iostream15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * 虚拟机读写其它进程的数据
 */
public class ProcessDemo6 {

    public static void main(String[] args) throws Exception
    {
        // Runtime对象exec()可以运行其它程序，返回Process对象，
        // Process对象代表由Java程序(当前程序)启动的子进程

        // todo 程序与子进程之前通信
        // 1. InputStream getErrorStream()
        // 2. InputStream getInputStream()
        // 3. OutputStream getOutputStream()

        // todo 关于输入/输出流的区分
        // 子进程读取程序的数据，站在程序角度来看仍然是[输出流]，如同将文件节点替换为子进程节点了

        // 1. InputStream.getErrorStream()：
        ProcessDemo6 process = new ProcessDemo6();
        process.readFromProcessTest();

        // 2. OutputStream.getOutputStream
        process.writeToProcess();
    }

    /**
     * 读取子进程数据作为输入
     */
    public void readFromProcessTest() throws IOException
    {
        // todo 返回子进程（当前程序的还是当前命令的？）
        Process process = Runtime.getRuntime().exec("javac");

        try (
                // 错误流对于本程序是输入流，对于子进程是输出流
                InputStreamReader reader = new InputStreamReader(process.getErrorStream());
                // 缓冲流
                BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
            String buffer = null;
            while ((buffer = bufferedReader.readLine()) != null) {
                System.out.println(buffer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 将本程序的输出作为子进程的输入
     *
     * @see ReadStandard#main(java.lang.String[])
     * @throws IOException
     */
    public void writeToProcess() throws  IOException
    {
        // java虚拟机中运行另一个程序
        Process process = Runtime.getRuntime().exec("java iostream15.ReadStandard");

        try (
            // todo 将本程序的输出流，作为子进程的输入流
            // process的输出流对本程序是输出流，对process进程是输入流
            PrintStream stream = new PrintStream(process.getOutputStream());
            // todo * 执行结果无效？无法输入到子进程中???
        ) {
            stream.println("普通字符串");
            stream.println(new ProcessDemo6());
        }
    }
}



