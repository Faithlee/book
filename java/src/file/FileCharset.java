package file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

public class FileCharset {

    public static void main(String[] args) throws IOException {
        // 1. 查看所有字符集
        // testAvailableCharset();

        // 2. 字符集序列转化测试
        // testCharsetTransfer();

        // 3. 指定字符集读写文件
        // testUTF8File();

        // 4. 以utf8的编码写gbk文件
        testCharsetWriteFile();
    }

    /**
     * 打印全部字符集
     */
    public static void testAvailableCharset() {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet()) {
            System.out.println(alias + "----->" + map.get(alias));
        }
    }

    /**
     * 字符集操作字符编码解码
     */
    public static void testCharsetTransfer() throws IOException {
        // 处理utf8文件以解码

        Charset cn = Charset.forName("GBK");
        CharsetEncoder encoder = cn.newEncoder();
        CharsetDecoder decoder = cn.newDecoder();

        // 创建CharBuffer，参考Buffer抽象类的原理：position()/limit()、mark()、capacity()
        CharBuffer buffer = CharBuffer.allocate(8);
        buffer.put("孙");
        buffer.put("悟");
        buffer.put("空");
        buffer.flip();  // 注意使用flip()锁定可读范围

        // 将字符序列转为字节序列
        ByteBuffer byteBuffer = encoder.encode(buffer);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            System.out.print(byteBuffer.get(i) + " ");
        }

        System.out.println();
        System.out.println(decoder.decode(byteBuffer));
    }

    /**
     * 使用指定字符集读写文件
     */
    public static void testUTF8File() {
        // 测试utf8编码方式
        Charset charset = Charset.forName("UTF-8");
        Path file = Paths.get("tmp", "utf8.txt");

        // 测试gbk编码方式
//        Path file = Paths.get("tmp", "gbk.txt");

        try (
                FileInputStream inputStream = new FileInputStream(file.toFile());
                BufferedInputStream buffer  = new BufferedInputStream(inputStream)
                ) {

            byte[] bytes = new byte[1024];
            while ((buffer.read(bytes)) > -1) {
                // 使用ByteBuffer包装字节序列
                ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, 0, bytes.length);
                // 使用utf8解析字节序列为字符序列
                System.out.println(charset.decode(byteBuffer)); // todo 为什么输出的内容开头会有?符号？
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试字符集写文件
     * 以utf8的方式输出到gbk文件中，验证乱码
     */
    public static void testCharsetWriteFile() {
        // 设置utf8编码
        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder encoder = charset.newEncoder();

        // 理解Buffer模型
        CharBuffer charBuffer = CharBuffer.wrap("这是设置utf8编码的字符集");

        /**
         * 假设tmp目录存在
         * output.txt文件不存在时创建会以utf8的编码格式创建
         * 如果output.txt文件存在并且编码是gbk的，会被覆盖为utf8
         */
        Path output = Paths.get("tmp", "output.txt");

        try (
                FileOutputStream outputStream = new FileOutputStream(output.toFile());
                ) {
            // 转为字符序列
            ByteBuffer byteBuffer = encoder.encode(charBuffer);
            // 转为字节数组
            outputStream.write(byteBuffer.array());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
