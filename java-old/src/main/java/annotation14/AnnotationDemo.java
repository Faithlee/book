package annotation14;

import java.util.ArrayList;
import java.util.List;

public class AnnotationDemo {

    public static void main(String[] args)
    {
        // todo java.lang中定义的基本注解
        // @Override
        //   标示子类必须重写父类，防止子类重写父类命名错误，只能修饰方法

        // @Deprecated:
        //   标示类或方法过时（属性since,forRemoval）

        // @SuppressWarning：抑制编译器警告
        // @SafeVarargs：抑制堆污染警告

        // @FunctionInterface：
        //   检查函数式接口

        test();

        heapPollution();
    }


    public static void test()
    {
        Apple apple = new Apple();
        apple.info();
        // todo 提示过时
        apple.color();

        // todo SuppressWarnigs
        List<String> list = new ArrayList();
    }

    @SuppressWarnings("unchecked")
    public static void heapPollution()
    {
        List list = new ArrayList();
        list.add(20);

        //List<String> ls = list;
//        System.out.println(ls.get(0));
    }
}

/**
 * Override注解
 */
class Fruit {

    public void info()
    {
        System.out.println("水果类");
    }
}

class Apple extends Fruit {
    @Override
    public void info()
    {
        System.out.println("苹果类");
    }

    @Deprecated
    public void color()
    {
        System.out.println("blue");
    }
}

/**
 * 限制函数式接口
 */
@FunctionalInterface
interface FunInterface
{
    public void demo();

    // todo 只允许定义一个抽象方法
    //public void test();

    default void foo()
    {
        System.out.println("foo");
    }

    static void bar()
    {
        System.out.println("bar");
    }
}
