package iostream15;

import java.io.*;

/**
 * 文件流输入输出
 */
public class FileStream2 {

    public static void main(String[] args) throws Exception
    {
        // todo 1. 按照程序所在的内存角度：输入流/输出流
        // todo 2. 按照操作数据单元划分：字节流/字符流
        // todo 3. 按照流的角色：节点流(特定设备的读写流)/处理流(对已存在的流进行封装或连接)
        // todo 4. 需要深入理解<流概念模型>：把设备里的有序数据抽象为水滴流模型


        FileStream2 stream = new FileStream2();
        // 字节流输入
        stream.streamReadTest();
        // 字符流输入
        stream.readerReadTest();

        // 字节流输出
        stream.outputStreamTest();
        // 字符输出流
        stream.writerTest();
    }

    /**
     * 输入流
     */
    public void streamReadTest() throws IOException
    {
        // todo InputStream/Reader是所有输入流的抽象基类
        // todo InputStream用于处理字节流，Reader处理字符流
        // FileInputStream/FileReader；

        // todo 绝对路径
        String absolutePath = "/data/application/hello/src/main/java/iostream15/FileStream2.java";
        FileInputStream stream = new FileInputStream(absolutePath);
        // todo 相对路径
        //String relativePath = "./src/main/java/iostream15/FileStream2.java";
        //FileInputStream stream = new FileInputStream(relativePath);

        // 从输入流读取字节保存到buf数组中(todo 推荐使用文本字符流操作，否则会出现截断)
        byte[] buf = new byte[1024];
        // 实际读取的字节数
        int hasRead = 0;

        while ((hasRead = stream.read(buf))  > 0) {
            System.out.println(new String(buf, 0, hasRead));
        }

        // 注意要关闭程序打开的资源（垃圾回收机制无法回收不属于内存里资源）
        stream.close();
    }

    /**
     * 文本流操作
     */
    public void readerReadTest() throws IOException
    {
        System.out.println("-------------分割线--------------");

        String absolutePath = "/data/application/hello/src/main/java/iostream15/FileStream2.java";

        try (
                FileReader reader = new FileReader(absolutePath)
        ) {
            char[] buf = new char[32];
            int hasRead = 0;
            while ((hasRead = reader.read(buf)) > 0) {
                System.out.println(new String(buf, 0, hasRead));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 字节输出流
     */
    public void outputStreamTest() throws IOException
    {
        System.out.println("-------------我是分割线--------------");

        // 输出流FileOutputStream

      String filename = "/data/application/hello/src/main/java/iostream15/FileStream2.java";
        try (
                FileInputStream input = new FileInputStream(filename);
                // 输出流写入到新文件中
                FileOutputStream output = new FileOutputStream("newFile.txt")
        ) {
            byte[] buf = new byte[32];
            int hasRead = 0;

            while ((hasRead = input.read(buf)) > 0) {
                // todo 输出格式会有问题
                // output.write(buf);
                // todo 推荐使用下面方法
                output.write(buf, 0, hasRead);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("查看文件: newFile.txt");
    }

    /**
     * 字符输出流
     */
    public void writerTest() throws IOException
    {
        try (
                FileWriter writer = new FileWriter("poem.txt")
        ) {
            writer.write("    锦瑟 - 李商隐\n");
            writer.write("锦瑟无端五十弦，一弦一柱思华年。\n");
            writer.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃。\n");
            writer.write("沧海月明珠有泪，蓝田日暖玉生烟。\n");
            writer.write("此情可待成追忆，只是当时已惘然。\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
