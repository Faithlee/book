package reflect;

@SuppressWarnings(value = "unchecked")
@Deprecated
@Annotation
@Annotation
public class ClassTest {

    // 私有构造器
    private ClassTest() {

    }

    public ClassTest(String name) {
        System.out.println("构造器参数：" + name);
    }

    // 无参方法
    public void info() {

    }

    // 有参方法
    public void info(String str) {

    }

    // 内部类
    class Inner {

    }


}

