package reflect.method.pool;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AdvanceObjectFactory {
    /**
     * 对象池
     */
    private Map<String, Object> objectPool = new HashMap<>();

    /**
     * 设置属性文件配置
     */
    private Properties config = new Properties();

    /**
     * 配置文件加载
     * @param fileName
     */
    public void init(String fileName) {
        try (
                FileInputStream inputStream = new FileInputStream(fileName)
        ) {
            config.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建对象
     * @param clazzName
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    private Object createObject(String clazzName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = Class.forName(clazzName);

        return clazz.newInstance();
    }

    /**
     * 根据配置文件初始化对象池
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public void initPool() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        for (String name : config.stringPropertyNames()) {
           // 将key/val中key不包含%的用来创建对象
           if (!name.contains("%")) {
               objectPool.put(name, createObject(config.getProperty(name)));
           }
       }
    }

    /**
     * 设置属性值
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public void initProperty() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (String name : config.stringPropertyNames()) {
            // 从key/val对中获取key包含%的，用来设置setter属性值
            if (name.contains("%")) {
                String[] prop = name.split("%");
                // 从对象池中获取对象
                Object target = getObject(prop[0]);
                // 得到setter方法
                String methodName = "set" + prop[1].substring(0, 1).toUpperCase() + prop[1].substring(1);

                Class<?> targetClass = target.getClass();
                Method method = targetClass.getMethod(methodName, String.class);
                method.invoke(target, config.getProperty(name));
            }
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


class Person {

    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}