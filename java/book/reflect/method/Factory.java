package reflect.method;

import reflect.method.pool.AdvanceObjectFactory;
import reflect.method.pool.ObjectPoolFactory;

public class Factory {

    public static void main(String[] args) throws Exception {
        // 测试对象池工厂
        testObjectPoolFactory();

        // 测试高级对象工厂
        testAdvanceObjectFactory();
    }

    /**
     * 测试对象池工厂
     *
     * @throws Exception
     */
    public static void testObjectPoolFactory() throws Exception {
        String file = "book\\reflect\\method\\resources\\object.ini";

        ObjectPoolFactory factory = new ObjectPoolFactory();
        factory.initPool(file);
        Object date = factory.getObject("a");
        System.out.println("当前时间: " + date);
    }


    public static void testAdvanceObjectFactory() throws Exception {
        String file = "book\\reflect\\method\\resources\\advance.ini";

        AdvanceObjectFactory factory = new AdvanceObjectFactory();
        factory.init(file);
        factory.initPool();
        factory.initProperty();
        System.out.println(factory.getObject("a"));
    }
}
