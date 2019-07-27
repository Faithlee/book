package iostream15;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

public class CharsetDemo10 {

    public static void main(String[] args) throws Exception
    {
        // 所有文件在底层都是二进制文件，即字节码
        // 文本文件中的字符，是系统将底层二进制序列转换为字符

        // todo 核心概念：编码、解码
        // java默认使用unicode字符集，通过Charset提供的字节与字符序列转换

        CharsetDemo10 charset = new CharsetDemo10();

        // 1. 查看字符集
        charset.getCharsetTest();

        // 2. 字节字符序列转换
        charset.charsetTransferTest();
    }

    /**
     * 查看JDK支持的字符集
     */
    public void getCharsetTest()
    {
        SortedMap<String,Charset> map = Charset.availableCharsets();

        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
    }

    /**
     * 字节字符序列转换
     */
    public void charsetTransferTest() throws Exception
    {
        System.out.println("-----------我是分割线----------");

        // todo 创建字符集对象: Charset.forName("charset")
        // 创建编码器或解码器：CharEncode/CharDecode

        // todo 不能设置成UTF8
        //Charset charset = Charset.forName("UTF8");
        Charset charset = Charset.forName("GBK");

        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer buffer = CharBuffer.allocate(8);

        buffer.put("孙");
        buffer.put("悟");
        buffer.put("空");

        buffer.flip();


        ByteBuffer byteBuffer = encoder.encode(buffer);

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            System.out.println(byteBuffer.get(i) + " ");
        }

        System.out.println("\n" + decoder.decode(byteBuffer));
    }
}
