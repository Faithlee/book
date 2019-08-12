package classloader;

/**
 * 类的初始化
 */
public class ClassInit {

    public static void main(String[] args) {
        // 变量初始化块生效，输出6
        System.out.println(StaticVars.b);

        // 对比静态变量初始化与静态初始化块，输出9，后面覆盖前面
        System.out.println(StaticInit.b);
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
    }

    static int a = 5;
    // 申明变量b的值
    static int b = 9;

    static int c;
}
