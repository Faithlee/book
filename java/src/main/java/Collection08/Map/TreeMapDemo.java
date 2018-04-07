package Collection08.Map;

import java.util.TreeMap;

/**
 * SortedMap接口的实现类TreeMap
 */
public class TreeMapDemo {

    public static void main(String[] args)
    {
        // todo 采用红黑树数据结构，key-value为一个节点
        // todo 储存key-value节点时，根据key对节点排序，
        // todo 能够保证key-value的有序状态

        natureSortTreeMapTest();
    }


    /**
     * 自然排序的TreeMap
     */
    public static void natureSortTreeMapTest()
    {
        // todo 只能添加同一类元素

        TreeMap tree = new TreeMap();

        tree.put(new R(3), "轻量级Java EE企业应用实战");
        tree.put(new R(-5), "疯狂Java讲义");
        tree.put(new R(9), "疯狂Andriod讲义");

        System.out.println(tree);

        System.out.println("firstEntry: " + tree.firstEntry());

        System.out.println("lastKey: " + tree.lastKey());

        // todo 返回比2大的最小key
        System.out.println("higherKey: " + tree.higherKey(new R(2)));

        // todo 返回比2小的最大的Entry
        System.out.println("lowerEntry: " + tree.lowerEntry(new R(4)));

        // 左闭右开区间
        System.out.println("subMap: " + tree.subMap(new R(-1), new R(4)));
    }
}

class R implements Comparable
{
    int count;

    public R(int count)
    {
        this.count = count;
    }

    public String toString()
    {
        return "R[count:" + count + "]";
    }

    // 根据count判断是否相等
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == R.class) {
            return this.count == ((R)obj).count;
        }

        return false;
    }

    // todo 根据count比较对象的大小，用于排序
    public int compareTo(Object obj)
    {
        R r = (R)obj;

        return count > r.count ? 1 :
                count < r.count ? -1 : 0;
    }
}
