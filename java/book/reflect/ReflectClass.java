package reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * 反射类信息
 */
public class ReflectClass {

    public static void main(String[] args) throws Exception {
        Class<ClassTest> clazz = ClassTest.class;

        // 获取所有构造器
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        // 只能获取public修饰的构造器
        Constructor[] publicConstructors = clazz.getConstructors();
        for (Constructor constructor : publicConstructors) {
            System.out.println(constructor);
        }

        // 获取所有公共方法，所把继承自object的方法也打印出来
        Method[] publicMethods = clazz.getMethods();
        for (Method method: publicMethods) {
            System.out.println("公共方法: " + method);
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("所有方法: " + method);
        }

        // 获取指定的方法
        System.out.println("String参数的info方法:" + clazz.getMethod("info", String.class));

        // 注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("全部注解: " + annotation);
        }

        // 打印为null，SuppressWarning注解只保留到源码级别
        System.out.println("@SuppressWarnings注解：" + Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)));

        // 按注解类型查看
        System.out.println("@Annotation注解: " + Arrays.toString(clazz.getAnnotationsByType(reflect.Annotation.class)));

        // 内部类
        Class<?>[] innerClasses = clazz.getDeclaredClasses();
        for (Class innerClass : innerClasses) {
            System.out.println("内部类: " + innerClass);
        }

        // 加载内部类
        Class innerClass = Class.forName("reflect.ClassTest$Inner");
        System.out.println("获取inner类对应的外部类: " + innerClass.getDeclaredClasses());

        // 包
        System.out.println("包: " + clazz.getPackage());

        // 父类
        System.out.println("父类: " + clazz.getSuperclass());


        System.out.println("====================分界线================");

        /**
         * 反射Test类的方法
         */
        Class<Test> testClass = Test.class;
        Method method = testClass.getMethod("replace", String.class, List.class);

        // 反射方法中的参数
        Parameter[] parameters = method.getParameters();
        Integer argc = method.getParameterCount();
        for (int i = 0; i < argc; i++) {
            System.out.println("方法的参数: " + parameters[i]);
        }

        for (Parameter parameter : parameters) {
            if (parameter.isNamePresent()) {
                System.out.println("参数名: " + parameter.getName());
                System.out.println("形参类型: " + parameter.getType());
                System.out.println("泛型类型: " + parameter.getParameterizedType());
            }
        }
    }
}

// 测试类
class Test {

    public void replace(String str, List<String> list) {

    }
}
