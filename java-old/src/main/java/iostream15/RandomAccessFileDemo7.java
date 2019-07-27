package iostream15;

import java.io.*;

/**
 * 任意访问文件操作
 */
public class RandomAccessFileDemo7 {

    public static void main(String[] args) throws Exception
    {
        // todo RandomAccessFile可以任意访问读写文件，同时允许自由定位文件记录指针
        // todo RandomAccessFile 无法访问非文件的IO节点

        // 构造方法：指定文件名的字符串或使用File指定
        // 操作文件指针方法：getFilePointer()/seek()
        // 读写方法与stream的相同, read/write

        RandomAccessFileDemo7 accessFileDemo = new RandomAccessFileDemo7();

        // 基本操作
        accessFileDemo.baseTest();

        // 追加内容
        accessFileDemo.appendContentTest();

        // 指定位置插入内容
        accessFileDemo.insertContent("insert_content.txt", 32, "测试");
    }

    /**
     * RandomAccessFile基本操作
     */
    public void baseTest()
    {
        String file = "./src/main/java/iostream15/RandomAccessFileDemo7.java";

        try (
                // 以只读的方式打开文件
                RandomAccessFile accessFile = new RandomAccessFile(file, "r");
        ) {
            // 初始位置为0
            System.out.println("文件指针的初始位置：" + accessFile.getFilePointer());

            // 移动文件指针
            accessFile.seek(300);
            byte[] buffer = new byte[1024];
            int hasRead;
            // 循环输出
            while ((hasRead = accessFile.read(buffer)) > 0) {
                // 将字节转为字符串输出
                System.out.println(new String(buffer, 0, hasRead));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 以读写的方式操作
     */
    public void appendContentTest()
    {
        String file = "append_content.txt";
        try (
                RandomAccessFile accessFile = new RandomAccessFile(file, "rw")
        ) {
            // 移动记录指针到文件末尾
            accessFile.seek(accessFile.length());
            // 将字符转化字节
            accessFile.write("这是追加的内容\n". getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 在指定的位置插入内容
     */
    public void insertContent(String file, long position, String content) throws IOException
    {
        File tmp = File.createTempFile("tmp", null);
        tmp.deleteOnExit();

        try (
            RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
            FileInputStream input = new FileInputStream(tmp);
            FileOutputStream output = new FileOutputStream(tmp);
        ) {
            accessFile.seek(position);

            byte[] buffer = new byte[64];
            int hasRead;
            while ((hasRead = accessFile.read(buffer)) > 0) {
                output.write(buffer, 0, hasRead);
            }

            // 移动指针到指定位置
            accessFile.seek(position);
            // 插入内容，指定字符编码，否则会导致乱码
            accessFile.write(content.getBytes());

            // 将tmp临时文件中的内容追加到文件中
            while ((hasRead = input.read(buffer)) > 0) {
                accessFile.write(buffer, 0, hasRead);
            }
        }
    }
}
