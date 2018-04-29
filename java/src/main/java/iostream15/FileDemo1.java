package iostream15;


import java.io.File;
import java.io.IOException;

public class FileDemo1 {

    public static void main(String[] args) throws IOException
    {
        // 以当前路径创建File对象(处理目录或文件)
        File file = new File(".");

        // 文件名，输出点
        System.out.println("文件名: " + file.getName());

        // todo 如果使用相对路径，默认是把当前路径的最后一级目录返回，但当前路径为点
        System.out.println("父路径：" + file.getParent());

        // todo 绝对路径，返回File对象
        System.out.println("绝对路径：" + file.getAbsoluteFile());
        System.out.println("父级路径：" + file.getAbsoluteFile().getParent());
        // todo 返回绝对路径字符串
        System.out.println("绝对路径：" + file.getAbsolutePath());


        // todo 在指定路径创建临时文件
        File tmpFile = File.createTempFile("aaa", ".txt", file);
        // 当JVM退出时删除
        tmpFile.deleteOnExit();

        // 以当前系统时间作为新文件名创建文件
        File newFile = new File(System.currentTimeMillis() + "");
        System.out.println("newFile对象是否存在：" + newFile.exists());
        // 项目根目录下创建
        newFile.createNewFile();

        // 以系统时间创建目录(存在当前时间的文件导致创建失败)
        Boolean ret = newFile.mkdir();
        System.out.println("创建目录是否成功：" + ret);

        // todo list()方法列出当前路径的文件
        String[] fileList = file.list();
        System.out.println("========当前路径下的所有文件和路径============");
        for (String  fileName : fileList) {
            System.out.println(fileName);
        }

        // todo listRoots()静态方法列表出所有磁盘路径
        File[] roots = File.listRoots();
        System.out.println("========系统根路径下的所有文件===========");
        for (File root : roots) {
            System.out.println(root);
        }
    }
}
