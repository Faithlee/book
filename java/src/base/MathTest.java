package base;

import java.math.BigDecimal;

/**
 * Math类
 */
public class MathTest {

    public static void main(String[] args) {
        testMath();
    }

    public static void testMath() {
        double a = 12.81;
        int b = (int)a;
        System.out.println("类型强转" + b);

        long c = Math.round(a);
        System.out.println("c:" + c);

        double d = Math.ceil(a);
        System.out.println("d:" + d);

        double f = Math.floor(a);
        System.out.println("f:" + f);

        double g = Math.random();
        int h = (int)(Math.random() * 100);
        System.out.println("g:" + g + "; h:" + h);

        int[] num = new int[10];
    }

    /**
     * BigDecimal测试
     */
    public static void testBigDecimal() {
        BigDecimal decimal = new BigDecimal("12.61");
        System.out.println(decimal);
    }
}
