package Generic09;

import java.util.*;

/**
 *  泛型
 */
public class GenericDemo {

    public static void main(String[] main)
    {
        // todo 泛型的作用：帮助集合检查元素类型并记住元素的类型，使用代码更健壮简洁(集合不使用泛型时，元素都以Object处理)

        genericTest();
    }

    /**
     * 使用泛型：通过泛型约束集合的元素
     */
    public static void genericTest()
    {
        // todo 通过泛型<String>，只能保存String类型的元素，同时避免其它类型的元素插入
        // todo 使用菱形语法，效果与new ArrayList<String>一样，编译器会自行推断
        List<String> list = new ArrayList<>();

        list.add("疯狂Java讲义");
        list.add("疯狂Andriod讲义");

        // 引起编译错误
        //list.add(5);

        // todo 不需要明确地将元素转为String类型
        list.forEach(ele -> System.out.println(ele));
        list.forEach(ele -> System.out.println(ele.length()));
    }

    // todo 泛型的使用对象：接口、实现类、

    /**
     * 通过菱形语法省略构造方法后类型约束
     */
    public static void rhombusSyntaxTest()
    {
        // todo 使用菱形语法，编译器会自行推断菱形中的元素类型

        // 写法1
        List<String> strList = new ArrayList<String>();
        Map<String, Integer> scores = new HashMap<String, Integer>();

        // 写法2：
        List<String> strList1 = new ArrayList<>();
        Map<String, Integer> scores1 = new HashMap<>();
    }

    // 如何定义泛型接口、类
    // 允许定义类、接口、方法时使用类型形参，这个类型形参将在声明变量、创建对象、调用方法时动态指定实际的类型参数

    // todo 可以为任何类、接口增加泛型声明（不局限于集合，泛型是集合使用的重要场所）



}
