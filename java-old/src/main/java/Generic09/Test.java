package Generic09;

import java.util.*;

public class Test {

    public static void main(String[] args)
    {
        List<Number> list = new ArrayList<>();

        list.add(110);
        System.out.println(list);

        List<Integer> str = new ArrayList<>();
        str.add(10);

        list.addAll(str);

        // 引用关系
        String[] s = {"a", "b", "c"};
        Object[] o = s;
        o[2] = "d";
        System.out.println(s[2]);
    }
}

class MyUtil<E>
{
    public static <Z> MyUtil<Z> nil()
    {
        return null;
    }

    public E head()
    {
        return null;
    }
}
