package iostream15;

import java.io.*;

/**
 * 输入输出流体系
 */
public class StreamSystem3 {

    public static void main(String[] args) throws Exception
    {
        StreamSystem3 stream = new StreamSystem3();

        stream.packStreamTest();

        // todo 输入输出流体系提供近40个类，按输入字节/字符、输出字节/字符划分
        // 通常字节流比字符流功能强大，字节流可以处理所有的二进制文件，
        // 文本内容考虑使用字符流，二进制内容使用字节流

        // 1. 以数组作为物理节点的节点流
        stream.arrayNodeStreamTest();
    }

    /**
     * 处理流
     */
    public void packStreamTest() throws IOException
    {
        // todo 程序通过处理流执行输入/输出，使用处理流包装节点流(节点流直接与底层IO设备交互)
        // todo System.out返回的类型就是PrintStream

        try (
                FileOutputStream outputStream = new FileOutputStream("PrintStream.txt");
                // todo 注意：处理流的构造器参数是已存在的流，而节点流参数是物理IO节点
                PrintStream stream = new PrintStream(outputStream);
        ) {
            stream.println("普通测试字符串");
            stream.println(new StreamSystem3());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 以数组为物理节点的节点流
     */
    public void arrayNodeStreamTest() throws IOException
    {
        // todo 1. 字节数组的节点流，2. 字符数组的节点流，3. 字符串的节点流

        String src = "从明天起，做一个幸福的人\n"
                + "喂马，劈柴，周游世界\n"
                + "从明天起，关心粮食和蔬菜\n"
                + "我有一所房子，面朝大海，春暖花开\n"
                + "从明天起，和每一个亲人通信\n"
                + "告诉他们我的幸福\n";

        char[] buffer = new char[32];
        int hasRead = 0;

        try (
                StringReader reader = new StringReader(src)
        ) {
            while ((hasRead = reader.read(buffer)) > 0) {
                System.out.println(new String(buffer, 0, hasRead));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // todo 创建StringWriter输出流(实际以StringBuffer作为输出节点)
        try (
                StringWriter writer = new StringWriter()
        ) {
            writer.write("有一个美丽的新世界，\n");
            writer.write("她在远方等我，\n");
            writer.write("那里有天真的孩子，\n");
            writer.write("还有姑娘的酒窝\n");
            System.out.println(writer.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
