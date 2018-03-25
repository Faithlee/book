package Collection08.Sets;

import java.util.*;

public class TreeSetDemo {

    public static void main(String[] args) {

        // todo TreeSet：可以确保元素的值处于有序状态
        treeSetTest();

        // 定制排序
        defineSortTest();

        // compareTo()、equals()同时影响元素是否相等导致集合操作异常
        // todo 结论：把一个对象存储到有序集合中，则应当保证equals方法与compareTo方法一致
        compareToTest();

        // 测试可变对象的实例变量被修改导致产生重复元素
        // todo 结论：不要修改放入HashSet与TreeSet集合中的元素的关键实例变量
        modifyObjectCompareTo();
    }

    /**
     * 有序的集合
     */
    public static void treeSetTest()
    {
        // todo 实现机制：采用红黑树数据结构存储元素
        // todo 默认实现自然排序
        // todo 常用方法：first()/last()/lower()/higher()/subSet()

        TreeSet set = new TreeSet();

        set.add(5);
        set.add(2);
        set.add(10);
        set.add(-9);

        System.out.println(set);

        System.out.println("first:" + set.first() + "; last:" + set.last());

        // todo 不包含4的子集
        System.out.println(set.headSet(4));

        // todo 包含5子集
        System.out.println(set.tailSet(5));

        // 返回大于等于-3小于4
        System.out.println(set.subSet(-3, 4));
    }

    /**
     *  TreeSet自然排序：从小到大
     */
    public static void natureSortTest()
    {
        // todo 自然排序要求元素必须实现Comparable接口的compareTo(Object obj)方法
        // todo 常用类已经实现Comparable接口，如果把一个引用对象添加到TreeSet时，必须实现compareTo方法
        // todo 只能添加同一类对象
        // todo 判断对象是否相同的标准：compareTo()比较是否为0

        // 参考treeSetTest()
    }

    /**
     *  定制排序
     */
    public static void defineSortTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo 通过实现Comparator接口实现compare(T o1,T o2)方法实现
        // Comparator是函数式接口，可以通过lambda实现
        TreeSet set = new TreeSet((o1, o2) -> {
            M m1 = (M)o1;
            M m2 = (M)o2;
            return  m1.age > m2.age ? -1 :
                    m1.age < m2.age ? 1 : 0;
        });

        set.add(new M(5));
        set.add(new M(-3));
        set.add(new M(9));

        System.out.println("降序排列：" + set);
    }

    // todo 排序判断唯一标准：对比compareTo是否为0

    /**
     * 自然排序时判断元素相等异常操作
     */
    public static void compareToTest()
    {
        System.out.println("\n------------------我是分割线--------------------");

        TreeSet set = new TreeSet();

        Z z1 = new Z(6);
        set.add(z1);

        // 再添加同一对象，true表明成功
        System.out.println("是否添加成功： " + set.add(z1));

        // 修改第一个元素的age变量
        ((Z)set.first()).age = 9;

        // 检查最后一个元素的变量
        System.out.println("最后一个元素变量的age：" + ((Z)set.last()).age);
    }

    /**
     * 修改可变对象的实例变量
     */
    public static void modifyObjectCompareTo()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo 修改可变对象实例变量，可能会导致它与其它对象大小顺序发生变化，但TreeSet并不会改变元素的顺序
        // todo TreeSet可以删除没有改变的实例变量，且不与其它被修改实例变量对象相同的元素
        TreeSet set = new TreeSet();

        set.add(new R(5));
        set.add(new R(-3));
        set.add(new R(9));
        set.add(new R(-2));

        System.out.println("元素排序后：" + set);

        // 修改第一个元素
        R first = (R)set.first();
        first.count = 20;

        // 修改最后一个元素
        R last = (R)set.last();
        last.count = -2;

        // 查看元素排序状态
        System.out.println("元素排序被打乱：" + set);

        // 删除实例变量被改变的元素: false
        System.out.println("删除已存在元素失败：" + set.remove(new R(-2)));
        // 对比删除后的元素
        System.out.println("元素并没有被删除：" + set);

        System.out.println("删除没有修改实例变量: " + set.remove(new R(5)));
        System.out.println("没有修改的元素被删除：" + set);
    }

}

class M {
    int age;

    public M(int age)
    {
        this.age = age;
    }

    public String toString()
    {
        return "M [age:" + age + "]";
    }
}

/**
 * 判断排序集合元素是否相等：主要标准是compareTo()
 */
class Z implements Comparable
{
    int age;

    public Z(int age)
    {
        this.age = age;
    }

    public int compareTo(Object obj)
    {
        return  1;
    }

    public boolean equals(Object obj)
    {
        return true;
    }
}

/**
 * 测试修改对象的实例变量，打乱排序顺序
 */
class R implements Comparable
{
    int count;

    public R(int count)
    {
        this.count = count;
    }

    @Override
    public String toString() {
        return  "R[count:" + count + "]";
    }

    public boolean equals(Object obj)
    {
        if (this == obj) {
            return  true;
        }
        if (obj != null && obj.getClass() == R.class) {
            //return true;
            R r = (R)obj;
            return r.count == this.count;
        }

        return false;
    }

    public int compareTo(Object obj)
    {
        R r = (R)obj;
        return count > r.count ? 1 :
                count < r.count ? -1 : 0;
    }
}
