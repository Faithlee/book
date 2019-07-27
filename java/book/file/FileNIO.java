package file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * NIO
 */
public class FileNIO {

    public static void main(String[] args) {
        // 1.Buffer模型应用
        // testBuffer();

        // 2.将channel映射为buffer，并输出到文件
        //testFileChannel();

        // 3.使用RandomAccessFile的Channel任意读写文件
        // testRandomAccessFileChannel();

        // 4.循环从Channel中读取数据到buffer中，防止文件过大全部映射到内存中
        testLoopReadChannel();
    }

    /**
     * nio中的Buffer使用
     * Buffer理解为容器，类似于传统流中的竹筒
     * Buffer是抽象类，最常用的ByteBuffer
     */
    public static void testBuffer() {
        // 创建Buffer
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        // 测试放入元素
        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        buffer.flip();  // 锁定数据区域

        System.out.println(buffer.position());
        System.out.println(buffer.limit());

        // 获取元素
        System.out.println(buffer.get());
        System.out.println(buffer.position());

        System.out.println("调用clear()");
        buffer.clear();
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        // 执行绝对读取
        System.out.println(buffer.get(2));
        System.out.println(buffer.position());
    }

    /**
     * FileChannel的数据映射为ByteBuffer操作读写
     */
    public static void testFileChannel() {
        File file = new File("tmp\\file_inchannel.txt");
        Path out  = Paths.get("tmp", "file_outchannel.txt");

        //Charset charset = Charset.forName("gbk");
        Charset charset = Charset.defaultCharset();

        try (
                FileChannel inChannel = new FileInputStream(file).getChannel();
                FileChannel outChannel = new FileOutputStream(out.toFile()).getChannel()
                ) {
            // 将FileChannel输入文件全部映射到Buffer
            //System.out.println(file.length());
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

            // channel.map执行后映射到Buffer区域中，没有不可读数据，所以不需要执行flip()
            //buffer.flip();    // 无需执行此步

            outChannel.write(buffer);

            // 复原输入buffer的位置
            buffer.clear();

            // 将字节转字符解码输出
            CharBuffer charBuffer = charset.decode(buffer);
            System.out.println(charBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }

    /**
     * 使用RandomAccessFile创建Channel任意读写
     */
    public static void testRandomAccessFileChannel() {
        File file = new File("tmp\\file_outchannel.txt");

        try (
                RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
                FileChannel channel = accessFile.getChannel()
                ) {
            ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

            // todo 移动Channel的记录指针到最后，类似与操作传统流的文件指针
            channel.position(file.length());

            // 追加同样的内容
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }

    /**
     * Channel中的数据循环读取到Buffer中
     */
    public static void testLoopReadChannel() {
        File file = new File("tmp\\file_inchannel.txt");

        Charset charset = Charset.defaultCharset();
        try (
                FileChannel channel = new FileInputStream(file).getChannel()
                ) {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            while ((channel.read(buffer)) != -1) {
                buffer.flip();  // 锁定空白区域
                CharBuffer charBuffer = charset.decode(buffer);
                System.out.println(charBuffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }
}
