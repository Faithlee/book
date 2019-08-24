package annotation.apt;

import annotation.annotations.TestAnnotation;

import java.lang.reflect.Method;

/**
 * 检查@TestAnnotation元素，用于测试
 */
public class MyAnnotationProcessor {

    public static void process(String clazz) throws ClassNotFoundException{
        int passed = 0;
        int failed = 0;

        for (Method method : Class.forName(clazz).getMethods()) {
            if (method.isAnnotationPresent(TestAnnotation.class)) {
                try {
                    // 调用方法
                    method.invoke(null);
                    passed++;
                } catch (Exception e) {
                    System.out.println("方法" + method + "运行失败，异常: " + e.getCause());
                    failed++;
                }
            }
        }

        System.out.println("共运行了" + (passed + failed) + "个方法，成功: " + passed + ";失败: " + failed);
    }
}
