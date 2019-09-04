package reflect.clazz.generic;

import java.lang.reflect.Array;

/**
 * 使用泛型避名class类的类型强制转换
 */
public class ObjectFactory {

    public static void main(String[] args) {

        // 一维数组
        String[] strings = ObjectFactory.newInstance(String.class, 10);
        strings[5] = "疯狂java讲义";

        // 二维数组
        int[][] ints = ObjectFactory.newInstance(int[].class, 5);
        ints[1] = new int[]{23,12};

        System.out.println(strings[5]);
        System.out.println(ints[1][1]);
    }

    /**
     * 对象类型未知，实际使用过程中需要强制转化
     * @param className
     * @return
     */
    public static Object createObject(String className) {
        try {
            Class clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用泛型化的Class对象实例化对象，不需要进制强制转化
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getInstance(Class<T> tClass) {
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建返回数组对象，避免强制类型转换
     * @param componentType
     * @param length
     * @param <T>
     * @return
     */
    public static <T> T[] newInstance(Class<T> componentType, int length) {
        return (T[])Array.newInstance(componentType, length);
    }
}

