package Collection08.Sets;

import java.util.*;

public class HashSetDemo {

    public static void main(String[] args)
    {
        // 储存元素及遍历
        hashSetTest();

        // 对比hashCode、equals方法
        hashCodeAndEquealTest();

        // 可变对象修改实例变量导致集合元素冲突
        // 结论：可变对象添加到集合中，尽量不要修改集合元素的中参与计算的hashCode与equals实例变量，否则会无法操作这些集合元素
        modifyObjectHashCodeOrEquals();
    }

    /**
     * hashSet储存元素
     */
    public static void hashSetTest()
    {
        HashSet set = new HashSet();

        // 不能保证元素的顺序，集合元素可以是null
        // todo 储存时会对比元素的hashCode与equal是否相同
        // todo hashCode确定元素的存储位置
        set.add(new Count(1));
        set.add(new Count(2));
        set.add(new Count(3));

        System.out.println(set);

        // todo 通过迭代器遍历，注意千万不要修改集合中的元素
        Iterator it = set.iterator();
        Count couter = (Count) it.next();
        System.out.println(couter);

        // lambda遍历集合
        set.forEach(obj -> System.out.println("迭代集合元素: " + obj));
    }

    /**
     *  对比元素的hashCode与equals方法，确保元素储存正常
     */
    public static void hashCodeAndEquealTest()
    {
        HashSet set = new HashSet();

        // equals返回相同，hashCode不同，会储存到不同的桶位中，是两个对象
        set.add(new A());
        set.add(new A());

        // hashCode返回相同，会储存到同一桶位中，影响存取性能,是两个对象
        set.add(new B());
        set.add(new B());

        // equals与hashCode返回相同
        set.add(new C());
        set.add(new C());

        System.out.println(set);
    }

    /**
     * 集合中存储可变对象时修改实例变量
     */
    public static void modifyObjectHashCodeOrEquals()
    {
        System.out.println("\n------------------我是分割线--------------------");

        // todo 被修改元素与其它元素的hashCode与equals相同，导致元素重复
        HashSet set = new HashSet();
        set.add(new R1(5));
        set.add(new R1(-3));
        set.add(new R1(9));
        set.add(new R1(-2));

        System.out.println("集合中的元素: " + set);

        Iterator it = set.iterator();
        R1 first = (R1)it.next();
        // todo 修改实例变量为已存在的
        first.count = -3;
        System.out.println("查看重复元素：" + set);

        // todo 删除重复元素，只有一个元素被删除
        set.remove(new R(-3));
        System.out.println("查看删除后的元素：" + set);

        // todo 检查是否存在元素，结果为false
        System.out.println("检查指定元素-3是否存在: " + set.contains(new R(-3)));

        // false
        System.out.println("检查指定元素-2是否存在: " + set.contains(new R(-2)));
    }
}

class Count {
    public int counter;

    public Count(int counter) {
        this.counter = counter;
    }

    public String toString() {
        return "Count[" + counter + "]";
    }
}

class A {
    public boolean equals(Object obj)
    {
        return  true;
    }
}

class B {
    public int hashCode()
    {
        return 1;
    }
}

class C {
    public boolean equals(Object obj)
    {
        return true;
    }

    public int hashCode()
    {
        return 2;
    }
}

/**
 * 可变对象的实例变量修改后可能会导致Set中存在重复元素
 */
class R1 {
    public int count;

    public R1(int count)
    {
        this.count = count;
    }

    public String toString()
    {
        return "R[count:" + count + "]";
    }

    /**
     * 对比是否同一对象
     * @param obj
     * @return
     */
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == R.class) {
            R r = (R)obj;
            return r.count == this.count;
        }

        return false;
    }

    public int hashCode()
    {
        return this.count;
    }
}
