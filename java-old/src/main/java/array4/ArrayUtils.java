package array4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * 数组工具类Arrays
 */
public class ArrayUtils {

    public static void main(String[] args)
    {
        ArrayUtils utils = new ArrayUtils();

        utils.baseTest();

        // 并行操作数组
        utils.parallelTest();
    }

    /**
     * 基本方法
     */
    public void baseTest()
    {
        int[] a = {3,4,5,6};
        int[] b = new int[]{3,4,5,6};

        // Arrays.equals()
        System.out.println("a数组和b数组是否相等：" + Arrays.equals(a, b));

        // copyOf()
        int[] c = Arrays.copyOf(a, 6);
        System.out.println("a数组和c数组是否相等：" + Arrays.equals(a, c));
        System.out.println("c数组元素：" + Arrays.toString(c));

        // fill：赋值操作（左闭右开）
        Arrays.fill(c, 2, 4, 1);
        System.out.println("c数组元素赋值：" + Arrays.toString(c));

        // sort排序
        Arrays.sort(c);
        System.out.println("c数组元素排序：" + Arrays.toString(c));
    }

    /**
     * 数组并行操作，利用多核CPU
     */
    public void parallelTest()
    {
        System.out.println("\n---------我是分割线-----------");
        // todo 充分利用多核CPU并行能力提高赋值、排序性能
        int[] arr = new int[]{3, -4, 25, 16, 30, 18};

        Arrays.parallelSort(arr);
        System.out.println("并行排序：" + Arrays.toString(arr));

        // 指定计算公式将计算得到的结果作为新元素(left,right,left默认为1)
        int[] b = {3, -4, 25, 16, 30, 18};
        Arrays.parallelPrefix(b, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                // left表示数组前一个引用元素，默认值为1,
                // right表示当前元素
                return left * right;
            }
        });
        System.out.println("并行赋值结果：" + Arrays.toString(b));

        // 使用生成器赋值
        int[] c = new int[5];
        Arrays.parallelSetAll(c, new IntUnaryOperator() {
            // operand 表示当前操作元素的索引
            @Override
            public int applyAsInt(int operand) {
                return operand * 5;
            }
        });
        System.out.println("并行使用生成器结果：" + Arrays.toString(c));
    }
}
