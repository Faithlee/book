package file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件目录遍历
 */
public class ListDir {

   public static List<File> fileList = new ArrayList<>();

    public static void main(String[] args) {
        File dir = new File("D:\\Web开发资料\\Java\\codes\\01");

        // todo 1. 使用foreach循环
//        List<File> files = listDir(dir);
//        System.out.println(files);

        // todo 2. 遍历目录使用迭代器
        List<File> files = listFile(dir);
        System.out.println(files);
    }

    public static List<File> listDir(File dir) {
        if (!dir.exists()) {
            throw new IllegalArgumentException(dir + "目录不存在!");
        }

        File[] files = dir.listFiles();
        for (File file: files) {
            if (file.isDirectory()) {
                listDir(file);
            } else {
                fileList.add(file);
            }
        }

        return fileList;
    }

    /**
     * 遍历目录
     * @param dir
     * @return
     */
    public static List<File> listFile(File dir) {
        if (!dir.exists()) {
            throw new IllegalArgumentException(dir + "目录不存在!");
        }

        File[] files = dir.listFiles();
        List<File> dirs = Arrays.asList(files);
        dirs.stream().forEach((file -> {
            if (file.isDirectory()) {
                listDir(file);
            } else if (null != file && file.exists()) {
                fileList.add(file.getAbsoluteFile());
                System.out.println(file);
            }
        }));

        return fileList;
    }
}
