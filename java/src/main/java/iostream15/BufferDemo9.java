package iostream15;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * NIO处理输入输出
 */
public class BufferDemo9 {

    public static void main(String[] args)
    {
        // todo 对比新IO传统IO存在问题
        // 1. 线程阻塞：传统IO都会阻塞线程的执行
        // 2. 字节处理：传统IO面向流操作一次只处理一个字节，效率低

        // todo NIO的实现
        // 1. 使用内存映射文件的方式处理输入/输出（模拟操作系统的虚拟内存），效率高

        // todo 核心概念：
        // 1. Channel(通道，由节点流创建不同的通道)
        // 2. Buffer(缓冲，内部结构像数组保存相同类型的数据，以抽象类的形式封装了底层字节数组的操作get/put)
        //    基本类型+Buffer，常用的是ByteBuffer类
        // 3. Charset(将字符串映射为序列字节或逆映射)

        BufferDemo9 buffer = new BufferDemo9();
        // 1. Buffer
        //buffer.bufferTest();

        // 2. Channel的创建
        buffer.channelTest();
        // todo 会导致线程stuck，原因不明白？
        // 产生的stuck进程无法杀掉
        //buffer.createChannelTest();

        // channel.read
//        buffer.channelReadTest();
    }

    /**
     * Buffer概念模型
     */
    public void bufferTest()
    {
        // todo Buffer重要概念：
        // 1. 容量：capacity，缓冲区Buffer的容量
        // 2. 界限：limit，第一个不可被读出或不可写入位置索引
        // 3. 位置：position，下一个可读可写的缓冲区位置索引

        // todo Buffer的主要作用就是装入数据，类似与流中竹筒，具体操作：
        // 1. xxxBuffer.allocate()
        // 2. put()装入数据或从Channel获取数据(map/read)
        // 3. flip()封印limit到capacity的无法读写区，准备数据的输出
        // 4. 通过get()/put()相对或绝对输出数据
        // 5. clear()重置缓冲区，但并清空缓冲区数据，为下次装入数据做准备

        // 创建Buffer
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());

        // 放入元素
        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        System.out.println("增加三个元素的后position：" + buffer.position());

        // todo flip()：锁定数据输出区域，修改limit为第一个不可被读取或写入的位置索引，将position索引位置归0，准备数据输出
        buffer.flip();
        System.out.println("flip后的limit：" + buffer.limit());
        System.out.println("相应的position：" + buffer.position());

        // 输出数据
        System.out.println("第一个元素：" + buffer.get());
        // todo IndexOutOfBoundsException
        // System.out.println("第一个元素：" + buffer.get(3));
        System.out.println("取出第一个元素后position = " + buffer.position());

        // todo clear()：重置缓冲区position,limit，为下次数据装入准备
        buffer.clear();

        // clear后缓冲区状态
        System.out.println("clear后的limit = " + buffer.limit());
        System.out.println("clear后的position = " + buffer.position());
        System.out.println("clear后缓冲区的第三个数据元素：" + buffer.get(2));
        // todo 输出null
        //System.out.println("clear后缓冲区的第三个数据元素：" + buffer.get(3));

        // 绝对访问并不会修改当前位置索引，所以还是0
        System.out.println("执行绝对访问后，position =" + buffer.position());
    }

    /**
     * Channel操作
     */
    public void channelTest()
    {
        System.out.println("-----------我是分割线----------");

        // todo Channel操作
        // 1. 将指定文件全部或部分映射成Buffer
        // 2. 程序不能直接访问Channel，必须通过Buffer访问，Channel只和Buffer交互

        // todo 创建Channel，不同类型的Channel由不同的节点流来创建（没有构造器）：
        // 1. FileChannel：通过InputStream/OutputStream.getChannel创建，通过RandomAccessFile.getChannel创建
        // 2. Pipe.SinkChannel/Pipe.SourceChannel/SocketChannel等

        // todo Channel操作方法：map/read/write

        File file = new File("src/main/java/iostream15/BufferDemo9.java");
        // todo 注意channel_out.txt文件权限？
        File out  = new File("channel_out.txt");
        try (
            FileChannel inChannel = new FileInputStream(file).getChannel();
            FileChannel outChannel = new FileOutputStream(out).getChannel();
        ) {
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            // 输出到指定文件即可结束不需要执行下面代码
            //outChannel.write(buffer);

            // 使用字符集创建解码器：todo 需要根据系统设置编码格式，否则会抛MalformedInputException异常
            // Charset charset = Charset.forName("GBK");
            Charset charset = Charset.forName("UTF8");

            // 输出buffer数据
            outChannel.write(buffer);

            // clear重置position/limit位置
            buffer.clear();

            // 创建解码器对象
            CharsetDecoder decoder = charset.newDecoder();
            // 使用解码器将ByteBuffer转换成CharBuffer
            CharBuffer charBuffer = decoder.decode(buffer);

            System.out.println(charBuffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @// TODO: 2018/5/6 RandomAccessFile创建channel存在问题
     * 1. 无法实现追加
     * 2. 无法打印内容
     * 通过RandomAccessFile创建channel
     */
    public void createChannelTest()
    {
        System.out.println("-----------我是分割线----------");

        // todo ??? Channel的write方法会产生stuck进程？
        File file = new File("channel.txt");
//        File file = new File("src/main/java/iostream15/BufferDemo9.java");

        try (
            RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
            FileChannel channel = accessFile.getChannel();
        ) {
            ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

            // todo 使用Channel的记录指针
            channel.position(file.length());

            // todo 将Buffer中的数据输出追加到文件末尾
            // todo ??? 此操作会引起进程stuck
            // channel.write(buffer);

            // 将字节转为字符
            Charset charset = Charset.forName("UTF8");
            CharBuffer charBuffer = charset.decode(buffer);
            System.out.println(charBuffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Channel重复读取
     * ??? 存在问题
     */
    public void channelReadTest()
    {
        System.out.println("-----------我是分割线----------");

        File file = new File("channel_read.txt");

        try (
            // RandomAccessFile accessFile = new RandomAccessFile(file, "r");
            // FileChannel channel = accessFile.getChannel();

            FileInputStream stream = new FileInputStream(file);
            FileChannel channel = stream.getChannel();
        ) {
            // todo ??? 为什么分配更小容量时抛异常？
//            ByteBuffer buffer = ByteBuffer.allocate(64);
            ByteBuffer buffer = ByteBuffer.allocate(256);

            while (channel.read(buffer) != -1) {
                buffer.flip();

                Charset charset = Charset.forName("UTF8");
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer charBuffer = decoder.decode(buffer);
                System.out.println(charBuffer);

                // 重置Buffer，为下一次数据做准备
                buffer.clear();
            }
            /*
            */

            /*
            ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            buffer.flip();

            // todo 为什么clear前置才可以输出？
            // 重置Buffer，为下一次数据做准备
            buffer.clear();

            Charset charset = Charset.forName("UTF8");
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer);
            System.out.println(charBuffer);
            */
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
