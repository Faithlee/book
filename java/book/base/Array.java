package base;

import java.util.Arrays;
import java.util.List;

/**
 * 数组
 */
public class Array {

    public static void main(String[] args) {
        // 1.数组复制
        // testArrayCopy();

        // 2.数组遍历
        testIterator();
    }

    /**
     * 测试数组复制，注意开始位置与偏移量
     */
    public static void testArrayCopy() {
        Integer[] a = {1, 2, 3, 4, 5};
        Integer[] b = new Integer[]{6,7,8};
//        {
//                1,1,1,1,1
//        };

        // 测试复制数据（字符复制）
        System.arraycopy(a, 1, b, 0, 3);
        System.out.println(b[2]);
    }

    /**
     * 数组迭代遍历
     */
    public static void testIterator() {
        Integer[] a = {1, 2, 3, 4, 5};

        for (Integer i : a) {
            System.out.println("foreach遍历: " + i);
        }

        // 迭代遍历
        List list = Arrays.asList(a);
        list.iterator().forEachRemaining(System.out::println);
    }
}
