package Generic09;

import java.util.*;

/**
 *  类型通配符
 */
public class WildcardDemo {

    public static void main(String[] args)
    {
        // todo Foo是Bar的子类，G是泛型类或接口，G<Foo>并不是G<Bar>的子类型；

        arrayTransferTest();

        // 通配符
        wildcardTest();

        // 通配符上限
        upperBoundTest();

        // 通配符下限
        lowerBoundTest();

        // 类型
        classTypeUpperBoundTest();

        // 定制排序
        treeSetTest();
    }

    /**
     * 数组型变
     */
    public static void arrayTransferTest()
    {
        // todo 数组和泛型不同，Foo是Bar的子类，那么Foo[]依然Bar[]的子类型；

        Integer[] ia = new Integer[5];
        Number[] na = ia;

        na[0] = 1;
        // todo 代码编码正常，运行时会引发ArrayStoreException
        // na[1] = 0.5;

        for (int i = 0; i < ia.length; i++) {
            System.out.println("打印数组：" + ia[i]);
        }
        for (int i = 0; i < na.length; i++) {
            System.out.println("打印数组：" + na[i]);
        }
    }


    /**
     * 使用类型通配符
     */
    public static void wildcardTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo 表示泛型List的父类：List<?>，但并不能将元素加入到其中

        List<String> list = new ArrayList<>();

        list.add("java");
        list.add("php");
        list.add("python");

        wildcard(list);
    }

    // todo ？表示未知类型
    private static void wildcard(List<?> list)
    {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * 通配符上限
     */
    public static void upperBoundTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        List<Circle> circleList = new ArrayList<>();
        circleList.add(new Circle());

        List<Rectangle> rectangleList = new ArrayList<>();
        rectangleList.add(new Rectangle());

        Canvas c = new Canvas();

        // 画圆形
        c.drawAll(circleList);

        // 画长方形
        c.drawAll(rectangleList);
    }

    /**
     * 通配符下限
     */
    public static void lowerBoundTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        List<Number> dest = new ArrayList<>();
        List<Integer> src = new ArrayList<>();

        src.add(5);

        // 最后一个被复制元素是Integer
        Integer last = MyUtils.copy(dest, src);
        System.out.println(dest);

        System.out.println("last: " + last);
    }

    /**
     * 类型形参上限
     */
    public static void classTypeUpperBoundTest()
    {
        // Number类及其子类均可
        Fruit<Double> apple = new Fruit<>();
        Fruit<Integer> pear = new Fruit<>();

        // todo String传给T形参
        //Fruit<String> banana = new Fruit<String>();
    }

    /**
     * TreeSet定制排序，通过关联的Comparator对象下限通配符
     */
    public static void treeSetTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // comparator的实际类型是TreeSet的元素类型的父类
        TreeSet<String> set = new TreeSet<>(
                new Comparator<Object>() {
                    public int compare(Object o1, Object o2) {
                        return o1.hashCode() > o2.hashCode() ? 1
                                : o1.hashCode() < o2.hashCode() ? -1 : 0;
                    }
                }
        );
        set.add("hello");
        set.add("wa");

        TreeSet<String> set2 = new TreeSet<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.length() > o2.length() ? -1
                                : o1.length() < o2.length() ? 1 : 0;
                    }
                }
        );

        set2.add("hello");
        set2.add("wa");

        System.out.println("hashCode排序：" + set);
        System.out.println("length排序：" + set2);
    }
}


/**
 * 实现通配符上限
 */
class Canvas
{
    // todo List<Shape>与List<Circle>不存在继承关系，无法将Circle对象实参传入
    // 通过 <? extends Shape>扩展通配符的上限
    public void drawAll(List<? extends Shape> shapes)
    {
        for (Shape shape: shapes) {
            shape.draw(this);
        }
    }

    // 修改输出
    public String toString()
    {
        return "[canvas]";
    }
}

abstract class Shape
{
    public abstract void draw(Canvas c);
}

class Circle extends Shape
{
    public void draw(Canvas c)
    {
        System.out.println("在画面" + c + "上画一个圆!");
    }
}

class Rectangle extends Shape
{
    public void draw(Canvas c)
    {
        System.out.println("在画布" + c + "上画一个矩形!");
    }
}


/**
 * 通配符下限demo
 */
// 将src集合里的元素都复制到dest集合中
// dest集合元素类型应该src元素类型的父类（但不能确定具体的父类）
class MyUtils
{
    public static <T> T copy(Collection<? super T> dest, Collection<T> src)
    {
        T last = null;
        for (T ele: src) {
            dest.add(ele);

            last = ele;
        }

        return last;
    }
}

/**
 * 类型形参demo
 */
// todo 设置类型形参上限为Number，则Number的子类均可使用
class Fruit<T extends Number>
{
    T kilogram;
}
