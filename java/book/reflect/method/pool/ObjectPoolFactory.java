package reflect.method.pool;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 简单对象池
 */
public class ObjectPoolFactory {

    /**
     * 对象池，key是对象名，后面是实际对象
     */
    private Map<String, Object> objectPool = new HashMap<>();

    /**
     * 创建对象
     * @param className
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    private Object createObject(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = Class.forName(className);

        return clazz.newInstance();
    }

    /**
     * 初始化对象池
     * @param fileName
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public void initPool(String fileName) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        try (
                FileInputStream inputStream = new FileInputStream(fileName)
                ) {
            Properties properties = new Properties();
            properties.load(inputStream);
            for (String name : properties.stringPropertyNames()) {
                objectPool.put(name, createObject(properties.getProperty(name)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象
     * @param name
     * @return
     */
    public Object getObject(String name) {
        return objectPool.get(name);
    }
}
