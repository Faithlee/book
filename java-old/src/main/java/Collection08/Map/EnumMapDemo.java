package Collection08.Map;

import java.util.EnumMap;

public class EnumMapDemo {

    public static void main(String[] args)
    {
        // todo 内部实现以数组形式保存，实现非常紧凑高效
        // todo 创建时必须显式或隐式的指定枚举类
        // todo key必须为枚举类的枚举值
        // todo 不允许使用null作为key

        enumMapTest();
    }

    public static void enumMapTest()
    {
        EnumMap enumMap = new EnumMap(Season.class);

        enumMap.put(Season.SUMMER, "夏日炎炎");
        enumMap.put(Season.SPRING, "春暖花开");

        System.out.println(enumMap);
    }
}

enum Season
{
    SPRING, SUMMER, FALL, WINTER;
}
