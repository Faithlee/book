

package Collection08.List;

import java.util.*;

public class ListTest {
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<String>();

        list.add("Hello");
        list.add("World");
        list.add("Tests");

        // 1. 使用for循环遍历
        for (String str: list) {
            System.out.println(str);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // todo 2.将链表变为数组相关的内容遍历
        System.out.println("2.将链表变为数组相关的内容遍历\n\n");
        String[] strArr = new String[list.size()];
        list.toArray(strArr);
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i]);
        }

        // todo 通过迭代器
        Iterator<String> ite = list.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }
    }
}
