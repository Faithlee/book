package array4;

/**
 * 数组运行机制
 */
public class ArrayRunMechanism {

    public static void main(String[] args)
    {
        // todo 定义并初始化一个数组时，在内存中会分配两个空间，
        // todo 数组元素与数组变量在内存中是分开储存的
        // 数组引用变量是储存在栈内存中
        // 数组元素存储在堆内存中

        // todo 垃圾回收机制：内存中的数组不再有引用变量指向时，数组会成为垃圾;
        // 通过将数组变量赋值为null，实际数组会成为垃圾被回收

        ArrayRunMechanism arr = new ArrayRunMechanism();

        // 1. 基本类型数组初始化
        arr.baseTypeTest();

        // 2. 引用类型的数组初始化
        arr.referenceTypeTest();

        // 3. 二维数组测试
        arr.twoDimensionTest();
    }

    /**
     * 基本类型数组测试
     */
    public void baseTypeTest()
    {
        // 1. 在栈内存中创建空引用
        // 2. 为数组分配内存空间分配初始默认值
        // 3. 通过循环或显式为数组元素赋值

        int[] arr;
        arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 10;
        }

        System.out.println("arr元素：" + arr.toString());
    }

    /**
     * 引用类型数组
     */
    public void referenceTypeTest()
    {
        System.out.println("------------我是分割线-------------------");
        // todo 注意区分栈内存与堆内存

        // todo 定义引用类型的数组，
        Person[] students;
        // todo 初始化引用类型的数组，系统默认分配元素的值为null
        students = new Person[2];

        // todo 创建Person的实例赋值给zhang的变量
        // todo zhang引用变量保存在栈内存中，实例对象保存在堆内存中
        Person zhang = new Person();
        zhang.name = "张";
        zhang.age  = 23;

        // 与zhang创建机制相同
        Person lee = new Person();
        lee.name = "李";
        lee.age  = 12;

        // todo 将students[0]元素的引用指向Person对象实例
        students[0] = zhang;
        students[1] = lee;

        // 下面代码输出结果完全一样，因为lee与students[1]指向相同的对象实例
        lee.info();
        students[1].info();
    }

    /**
     * 二维数组测试
     */
    public void twoDimensionTest()
    {
        System.out.println("------------我是分割线-------------------");

        // todo java支持多维数组的语法，但从运行机制讲是没有多维数组的
        // 元素类的类型也可以是引用类型，再指向真实地内存地址
        // todo 无限可扩展的数组：可以定义一个Object[]类型的数组

        // 二维数组语法：type[][] arrName;
        // arrName = new type[length][];

        // 定义
        int[][] a;
        // todo 将a当作一维数组初始化，长度为4
        // todo a的数组元素又是引用类型，数组元素为int[]
        a = new int[4][];

        // 初始化a数组的第一个元素
        a[0] = new int[2];
        a[0][1] = 6;

        // 遍历a数组第一个元素（即一维数组）
        for (int i = 0; i < a[0].length; i++) {
            System.out.println("a数组第一个元素: " + a[0][i]);
        }


        // todo 同时初始化二维数组的两个维数
        int[][] b = new int[3][4];

        // todo 使用静态初始化初始二维数组
        String[][] str1 = new String[][]{
                new String[]{"Java", "PHP", "Python"},
                new String[2]
        };
        // 简化写法
        String[][] str2 = {
                new String[]{"Java", "PHP", "Python"},
                new String[2]
        };
    }
}

/**
 * 引用类型数组测试
 */
class Person
{
    public String name;
    public int age;

	/**
	 * info
	 */
    public void info()
    {
        System.out.printf("我的姓名: %s, 我的年龄: %d\n", this.name, this.age);
    }
}
