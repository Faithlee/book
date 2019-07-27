package annotation14.apt;

import annotation14.annotation.Testable;

import java.lang.reflect.Method;

/**
 * 可测试注解处理工具
 */
public class TestableProcessTool {
    public static void process(String clazz)
            throws Exception
    {
        int passed = 0;
        int failed = 0;
        Object obj = Class.forName(clazz).newInstance();
        for (Method m : Class.forName(clazz).getMethods()) {
            if (m.isAnnotationPresent(Testable.class)) {
                try {
                    // todo m方法为成员方法时，invoke时第一个参数必须传入来源的对象
                    // todo m方法为静态方法时，invoke时第一个参数为null
                    m.invoke(obj);
                    // 测试通过+1
                    passed++;
                } catch (Exception e) {
                    System.out.printf("方法%s运行失败，异常：%s\n", m, e.getCause());
                    // 测试异常+1
                    failed++;
                }
            }
        }

        // 统计测试结果

        System.out.printf("共运行了%d个方法, 成功方法%d个，失败方法%d个\n", passed + failed, passed, failed);
    }
}
