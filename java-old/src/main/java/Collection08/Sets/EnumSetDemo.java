package Collection08.Sets;

import java.util.EnumSet;

/**
 * 专门为枚举类设计的集合
 */
public class EnumSetDemo {

    public static void main(String[] args)
    {
        // 所有元素必须是指定枚举类型的枚举值

        enumSetTest();

    }

    public static void enumSetTest()
    {

        // 创建包含所有枚举值的集合
        EnumSet es1 = EnumSet.allOf(Season.class);
        System.out.println("allOf: " + es1);

        // 创建空枚举集合
        EnumSet es2 = EnumSet.noneOf(Season.class);
        System.out.println("noneOf: " + es2);
        es2.add(Season.SPRING);
        es2.add(Season.SUMMER);
        System.out.println("add: " + es2);

        // 以指定的枚举值创建枚举集合
        EnumSet es3 = EnumSet.of(Season.SPRING, Season.FALL);
        System.out.println("of: " + es3);

        EnumSet es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
        System.out.println("range: " + es4);

        // 取枚举类的补集
        EnumSet es5 = EnumSet.complementOf(es3);
        System.out.println("complementOf: " + es5);

        // 创建一个与指定集合相同的EnumSet集合
        EnumSet es6 = EnumSet.copyOf(es3);
        System.out.println("complementOf: " + es6);
    }
}

enum Season
{
    SPRING,SUMMER,FALL,WINTER
}
