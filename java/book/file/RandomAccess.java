package file;

import java.io.*;

/**
 * 文件任意读写
 */
public class RandomAccess {

    public static void main(String[] args) throws IOException {
        // 1.读取文件
        // testRandomAccessRead();

        // 2.以读写的方式访问文件内容
        // testRandomAccessReadWrite();

        // 3.指定位置插入内容
        testInsertContent();
    }

    /**
     * 测试任意读取文件内容
     */
    public static void testRandomAccessRead() {
        File file = new File("book\\file\\RandomAccess.java");
        try (
                RandomAccessFile accessFile = new RandomAccessFile(file, "r");
                ) {
            // 文件指针位置
            System.out.println(accessFile.getFilePointer());

            // 移动指针
            accessFile.seek(300);
            System.out.println(accessFile.getFilePointer());

            // 读取指针位置300以后的内容
            int hasRead;
            byte[] bytes = new byte[1024];
            while ((hasRead=accessFile.read(bytes)) > -1) {
                System.out.println(new String(bytes, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以读写的方式访问文件
     */
    public static void testRandomAccessReadWrite() {
        File file = new File("tmp\\random.txt");

        try (
                RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
                ) {
            // 将文件指针移动到文件末尾
            accessFile.seek(accessFile.length());

            // 追加新内容
            accessFile.write(("追加的新内容, time: " + System.currentTimeMillis() + "\n").getBytes());

            // 1. 读取所有内容，输出的内容有乱码
            accessFile.seek(0);
            String line;
            while ((line = accessFile.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("=============分界线==================");

            accessFile.seek(0);
            // 2.以字节的方式读取，正常显示
            int hasRead;
            byte[] bytes = new byte[1024];
            while ((hasRead=accessFile.read(bytes)) > -1) {
                System.out.println(new String(bytes, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }

    /**
     * 向指定文件指定位置插入内容
     */
    public static void testInsertContent() throws IOException{
        File insert = new File("tmp\\insert.txt");
        File tmp = File.createTempFile("tmp\\tmp", null);

        /**
         * 注意换行符
         */
        long pos = 100;
        String append = "这是插入的内容" + System.currentTimeMillis() + "\r\n";

        try (
               RandomAccessFile accessFile = new RandomAccessFile(insert, "rw");
               FileOutputStream outputStream = new FileOutputStream(tmp);
               FileInputStream inputStream = new FileInputStream(tmp)
                ) {

            // 设置指定位置开始插入
            accessFile.seek(pos);

            // 将指定位置以后内容写入临时文件中
            byte[] bytes = new byte[1024];
            int hasRead;
            while ((hasRead = accessFile.read(bytes)) > -1) {
                outputStream.write(bytes, 0, hasRead);
            }

            // 将插入的内容追加进来
            accessFile.seek(pos);
            accessFile.write(append.getBytes());

            // 将临时文件的内容再追加到末尾
            while ((hasRead = inputStream.read(bytes)) > -1) {
                accessFile.write(bytes, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }
}

