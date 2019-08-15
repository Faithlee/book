package reflect.constructor;

import java.lang.reflect.Constructor;

public class ReflectJFrame {

    public static void main(String[] args) throws Exception{
        Class<?> jframeClazz = Class.forName("javax.swing.JFrame");
        Constructor constructor = jframeClazz.getConstructor(String.class);
        Object object = constructor.newInstance("测试窗口");
        System.out.println(object);
    }
}
