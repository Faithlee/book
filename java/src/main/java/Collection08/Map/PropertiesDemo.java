package Collection08.Map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *  Hashtable类的子类Properties
 */
public class PropertiesDemo {

    public static void main(String[] args) throws Exception
    {
        // todo 用于处理属性文件时比较方便
        // key-value都是字符串类型

        propertiesTest();
        loadPropertiesTest();
    }

    /**
     * 保存属性到文件
     *
     * @throws Exception
     */
    public static void propertiesTest() throws Exception
    {
        // todo 实现方法
        // getProperty()
        // setProperty()
        // load()
        // store()

        Properties props = new Properties();
        props.setProperty("username", "zhangsan");
        props.setProperty("password", "123456");

        // 保存属性到指定文件
        props.store(new FileOutputStream("/tmp/test.ini"), "comment line");
    }

    /**
     * 加载属性文件
     *
     * @throws Exception
     */
    public static void loadPropertiesTest() throws Exception
    {
        Properties props = new Properties();

        // 添加属性
        props.setProperty("gender", "male");

        props.load(new FileInputStream("/tmp/test.ini"));

        System.out.println("查看属性:" + props);
    }
}
