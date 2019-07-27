package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Base {

    public static void main(String[] args) throws IOException{
        // 1. 文件过滤器
        // fileFilter();

        // 2.Paths工具类
        // testPaths();

        // 3.Files工具类
        // testFiles();
    }


    /**
     * 文件过滤器，不使用过滤器则返回全部
     */
    public static void fileFilter() {
        File file = new File("book\\file");
        // 返回字符串
        String[] nameList = file.list(((dir, name) -> name.endsWith(".java") || new File(name).isDirectory()));
        // 返回file数组
        File[] fileList = file.listFiles((dir, name) -> name.endsWith(".java") || new File(name).isDirectory());

        // 只打印文件名
        for (String name : nameList) {
            System.out.println(name);
        }

        // 打印全路径
        for (File filename : fileList) {
            System.out.println(filename);
        }
    }

    /**
     * NIO.2：提供全面的文件IO和文件系统的访问
     * Path接口:与平台无关的平台路径
     * Files、Paths工具类
     * 类似的还有Arrays、Collections工具类
     */

    /**
     * Paths工具类测试，返回Path接口
     */
    public static void testPaths() {
        Path path = Paths.get(".");
        System.out.println("路径数量：" + path.getNameCount());  // todo 不是当前路径下包含的文件数量

        // 根路径
        System.out.println(path.getRoot());

        // 绝对路径
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        System.out.println("绝对路径数量：" + absolutePath.getNameCount());

        // 绝对路径的根路径
        System.out.println(absolutePath.getRoot());

        // 多路径组合构建Path对象
        //Path path1 = Paths.get("path1", "path2", "path3");

        /**
         * todo 1.Path接口的实现类呢？
         */
    }

    /**
     * Files工具类
     */
    public static void testFiles() throws IOException {
        // 创建临时目录
        File tmp = new File("tmp");
        if (!tmp.exists()) {
            tmp.mkdir();
            System.out.println("tmp目录创建成功");
        }

        // 目标文件
        Path targetFile = Paths.get(tmp.toString(), "targe.txt");

        Path base = Paths.get("book", "file", "Base.java");
        //System.out.println(base.toAbsolutePath());

        // 复制文件
        Files.copy(base, new FileOutputStream(targetFile.toFile()));

        // 读取文件的所有行
        List<String> lines = Files.readAllLines(base, Charset.forName("gbk"));
        lines.forEach(System.out::println);

        // 查看文件大小
        System.out.println("base size: " + Files.size(base));
        System.out.println("target size: " + Files.size(targetFile));

        // 将内容写入指定文件中
        Path poemFile = Paths.get(tmp.toString(), "poem.txt");
        List<String> poem = new ArrayList<>();
        poem.add("测试1");
        poem.add("测试2");
        Files.write(poemFile, poem, Charset.defaultCharset());
        System.out.println("poem写入完成!");

        // 读取指定文件内容
        Files.lines(base, Charset.defaultCharset()).forEach(System.out::println);

        // 列出文件及子目录
        Files.list(Paths.get(".")).forEach(System.out::println);

        // 统计磁盘空间
        FileStore store = Files.getFileStore(Paths.get("."));
        System.out.println(store.getTotalSpace()/1024/1024);

        /**
         * todo 1. 计算文件大小（人可读的）
         */
    }

    /**
     * FileVistor遍历文件和目录
     * 递归遍历复杂并且灵活性也不高
     */
    public static void testFileVistor() {
        // 使用walkFileTree方法遍历所有文件及子目录
        // todo

    }

    /**
     * 监控文件的变化：WatchService
     *
     * 以前则需要启动一个后台线程定时去扫描指定目录文件。通过对比不同则认为发生了变化
     */
    public static void testWatchService() {
        // 通过Path类提供的register方法监听文件的变化
        // todo

    }

    /**
     * 通过file.attribute包下提供的工具类读取修改文件的属性
     *
     * 对比File类只能访问简单的文件属性如大小等
     */
    public static void testVisitFileAttribute() {
        // todo
    }
}
