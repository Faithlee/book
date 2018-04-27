package array4;

public class ArrayDemo1 {

    public static void main(String[] args)
    {
        // 数组初始化：为数组元素分配内存空间，并为数组元素赋值；
        ArrayDemo1 arr = new ArrayDemo1();

        // 1. 静态初始化：显式指定每个数组元素的初始值，由系统决定长度，并且不能修改
        arr.staticArrayTest();

        // 2. 动态初始化：
        arr.dynamticArrayTest();

        // 3. 数组的遍历使用
        arr.useArrayTest();

    }

    /**
     * 静态数组初始化
     */
    public void staticArrayTest()
    {
        System.out.println("-------------我是分割线-------------");
         //arrayName = new type[]{ele1, ele2, ele3……};

        // todo 静态初化时，显式指定的数组元素的类型必须与new关键字后的type类型相同，或者是其子类的实例

        // 1. 基本类型初始化
        int[] intArr;
        // 静态初始化时只指定元素的初始值，不指定数组长度
        intArr = new int[]{5,6,8,20};

        // 2.
        Object[] objArr;
        // todo 初始化数组元素的类型(String)是定义数组时所指定的数组元素类型(Object)的子类
        objArr = new String[]{"Java", "PHP"};

        // 3.
        Object[] objArr2;
        objArr2 = new Object[]{"Java", "PHP"};

        // 4. 数组简化写法
        int[] arr = {5,6,7,8};
    }

    /**
     * 动态初始化数组
     */
    public void dynamticArrayTest()
    {
        System.out.println("-------------我是分割线-------------");

        // todo 动态初始化数组只指定长度，由系统为每个元素指定初始值(系统会根据初始化数组元素的类型分配指定的默认值)；
        // todo 比如引用类型：系统默认分配元素的值为null
        // arrayName = new type[length];

        // 数组的定义与初始化同时完成
        int[] prices = new int[5];
        Object[] books = new String[5];
    }

    /**
     * 使用数组
     */
    public void useArrayTest()
    {
        System.out.println("-------------我是分割线-------------");

        // 动态初始化
        //Object[] objects = new String[]{"Java", "PHP"};
        Object[] objects = new String[4];

        // 赋值
        objects[0] = "String";

        // todo 使用时一定要注意数组索引不要越界，编译时不有提示，运行时会抛异常
        System.out.println(objects[0]);
        System.out.println(objects[1]);

        // todo 索引越界：ArrayIndexOutOfBoundsException
        //System.out.println(objects[4]);


        // todo 所有数组都有length属性，遍历时通过索引访问数组元素
        for (int i = 0; i < objects.length; i++) {
            System.out.printf("object[%d]的元素为：%s\n", i, objects[i]);
        }


        // todo foreach循环遍历，无须获取数组的长度，也不需要索引来访问元素
        // todo 注意此处需要使用定义数组的类型，不能使用初始化数组的类型(String)，否则会报检查性异常
        // todo 遍历时不要对变量进行赋值，容易引起错误；循环变量相当于临时变量，只保存了数组元素的值；
        System.out.println("\nforeach循环遍历：");
        for (Object str : objects) {
            System.out.println(str);
        }
    }
}
