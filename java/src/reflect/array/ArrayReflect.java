package reflect.array;

import java.lang.reflect.Array;

/**
 * Array动态创建数组
 */
public class ArrayReflect {

    public static void main(String[] args) {

        try {
            // 长度为10的、元素类型为String的数组
            Object array = Array.newInstance(String.class, 10);

            Array.set(array, 5, "疯狂Java讲义");
            Array.set(array, 6, "JavaEE应用");

            Object book1 = Array.get(array, 5);
            Object book2 = Array.get(array, 6);
            System.out.println(book1);
            System.out.println(book2);
        } catch (Throwable e) {
            System.err.println(e);
        }


        // 多维数组反射
        multiDimesionsArray();
    }

    /**
     * 多维数组反射操作
     * todo 维度关系: 通过Array.getLength查看
     */
    public static void multiDimesionsArray() {
        Object array = Array.newInstance(String.class, 3, 4, 10);
        System.out.println(Array.getLength(array));

        Object arrayObj = Array.get(array, 2);
        System.out.println(Array.getLength(arrayObj));

        Array.set(arrayObj, 2, new String[]{
                "Java讲义",
                "JavaEE"
        });

        Object anArr = Array.get(arrayObj, 3);
        System.out.println(Array.getLength(anArr));
        Array.set(anArr, 8, "Android");

        String[][][] cast = (String[][][])array;

        // Android
        System.out.println(cast[2][3][8]);
        System.out.println(cast[2][2][0]);
        System.out.println(cast[2][2][1]);
    }
}
