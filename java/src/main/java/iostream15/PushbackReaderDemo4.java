package iostream15;

import java.io.*;

/**
 * 推回输入流
 */
public class PushbackReaderDemo4 {

    public static void main(String[] args)
    {
        // 输入输出流体系中特殊的节点流

        PushbackReaderDemo4 pushback = new PushbackReaderDemo4();

        pushback.pushbackInputStreamTest();
    }

    /**
     * 推回输入流测试：查找字符串，并把打印目标字符串之前内容
     */
    public void pushbackInputStreamTest()
    {
        System.out.println("-------------我是分割线--------------");

        // PushbackInputStream/PushbackReader
        // 将字节/字符内容（可以是数组）推回到缓冲区中，允许重复读取刚刚的内容
        // todo read()读取内容机制：优先读取推回缓冲区的内容，如果buffer(水桶)没有装满，则会继续从原输入流中读取
        // todo 实现：创建缓冲区大小，调用unread()推回缓冲区

        try (
                FileReader reader = new FileReader("/data/application/hello/src/main/java/iostream15/PushbackReaderDemo4.java");
                // 缓冲区大小默认为1，此处设置64
                PushbackReader pr = new PushbackReader(reader, 64)
        ) {
            // todo 推回缓冲区的内容不能超缓冲区的容量，否则会抛出异常
            char[] buf = new char[32];
            String lastContent = "";
            int  hasRead = 0;
            while ((hasRead = pr.read(buf)) > 0) {
                String content = new String(buf, 0, hasRead);
                int targetIndex = 0;
                if ((targetIndex = (lastContent + content).indexOf("new PushbackReader")) > 0) {
                    // todo 推回到缓冲区：转换为字符数组
                    // todo 不能超越缓冲区的大小64
                    pr.unread((lastContent + content).toCharArray());

                    // 重新定义一个长度为targetIndex的char数组储存之前内容
                    if (targetIndex > 32) {
                        buf = new char[targetIndex];
                    }

                    // 读取指定字符串之前的内容
                    pr.read(buf, 0, targetIndex);
                    System.out.println("打印的推回缓冲中的内容:");
                    System.out.println(new String(buf, 0, targetIndex));
                    System.exit(0);
                } else {
                    System.out.println(lastContent);
                    // 上次读取到的内容
                    lastContent = content;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
