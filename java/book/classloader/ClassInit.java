package classloader;

/**
 * 类的初始化
 */
public class ClassInit {

    public static void main(String[] args) throws Exception {
        // 变量初始化块生效，输出6
        System.out.println(StaticVars.b);
        System.out.println();

        // 对比静态变量初始化与静态初始化块，输出9，后面覆盖前面
        System.out.println(StaticInit.b);
        System.out.println();

        // final修饰的静态常量编译时可以确定下来，不会初始化类
        System.out.println(FinalStaticConstant.compileConstant);
        System.out.println();

        // final修饰的静态常量需要在运行时期才能确定下来，会初始化类
        System.out.println(FinalStaticVars.compileConstant);
        System.out.println();

        // 对比加载和初始化
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.loadClass("classloader.MyClassLoader");
        System.out.println("系统加载MyClassLoader");

        // 初始化MyClassLoader
        Class.forName("classloader.MyClassLoader");
    }
}


/**
 * 静态变量初始化
 */
class StaticVars {

    // 静态变量申明
    static int a = 5;

    static int b;

    static int c;

    static {
        // 静态初始化块指定变量b的值
        b = 6;
    }
}

/**
 * 静态初始化块
 */
class StaticInit {
    /**
     * 使用静态初始化块申明b的值
     */
    static {
        b = 6;

        System.out.println("============执行静态初始化块============");
    }

    static int a = 5;
    // 申明变量b的值
    static int b = 9;

    static int c;
}

/**
 * 使用static final修饰静态变量
 *
 * 如果在编译的时候可以确定下来，类变量会被当作宏变量处理，相当于常量，类不会被初始化
 */
class FinalStaticConstant {

    static {
        System.out.println("我不会被执行，编译时类变量已经确定了");
    }

    static final String compileConstant = "java";
}

/**
 * 使用static final修饰静态变量
 *
 * 在编译时无法确定，在运行时才能确定，类会被初始化
 */
class FinalStaticVars {

    static {
        System.out.println("我在运行时才能确定，会初始化类");
    }

    static final String compileConstant = System.currentTimeMillis() + "";
}

/**
 * ClassLoader只负责加载，不会初始化类
 *
 * Class.forName才会强制初始化类
 *
 * todo Class.forName会加载类吗?
 */
class MyClassLoader {

    static {
        System.out.println("MyClassLoader静态初始化块...");
    }
}