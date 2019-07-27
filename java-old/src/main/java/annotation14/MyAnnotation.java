package annotation14;

import annotation14.apt.TestableProcessTool;
import annotation14.annotation.MyTags;
import annotation14.annotation.MyTag;
import annotation14.annotation.Testable;

import java.lang.annotation.Annotation;

/**
 * 自定义注解
 */
public class MyAnnotation {

    public static void main(String[] args) throws Exception
    {
        // todo Annotation修饰的程序元素不会自动生效，需要开发相应的工具来提取

        MyAnnotation annotation = new MyAnnotation();

        // 1. 反射查看注解信息
        annotation.getInfoAnnotationTest();

        // 2. 获取注解的元数据
        annotation.getAnnotationMetaDataTest();

        // 3. 注解处理工具演示：指定方法测试注解来运行该测试方法
        annotation.aptTest();

        // 4. 重复注解
        annotation.repeatableTest();
    }

    /**
     * 通过反射查看info方法的注解
     */
    public void getInfoAnnotationTest() throws Exception
    {
        System.out.println("-------------------我是分割线-------------------------");

        this.info();

        Annotation[] list = Class.forName("annotation14.MyAnnotation").getMethod("info").getAnnotations();
        for (Annotation i: list) {
            System.out.println(" > my annotation: " + i);
        }
    }

    @MyTag(name = "hello", age = 33)
    public void info()
    {
        System.out.println("annotation test:");
    }

    /**
     * 获取注解的元数据
     */
    public void getAnnotationMetaDataTest() throws Exception
    {
        System.out.println("-------------------我是分割线-------------------------");

        Annotation[] annotations = this.getClass().getMethod("info").getAnnotations();

        // 遍历注解对象
        for (Annotation tag: annotations) {
            if (tag instanceof MyTag) {
                System.out.println("Tag is:" + tag);

                // 将tag强制类型转换为MyTag
                // 输出tag对象的method1和method2两个成员变量的值
                System.out.println("tag.name(): " + ((MyTag)tag).name());
                System.out.println("tag.age(): " + ((MyTag)tag).age());
            }
            // 是否是其它注解
            if (tag instanceof MyTag) {
                // 注解的其它操作
            }
        }
    }


    /**
     * 注解处理工具
     *
     * @throws Exception
     */
    public void aptTest() throws Exception
    {
        System.out.println("-------------------我是分割线-------------------------");

        // todo 使用@Testable标记方法是否可测试
        // todo 仅使用注解对程序元素不会有任何影响，需要注解处理工具解析

//        String clazz = MyAnnotation.class.getName();
//        System.out.println(clazz);
//        TestableProcessTool.process(clazz);

        TestableProcessTool.process("annotation14.MyAnnotation");
    }

    @Testable
    public void m1()
    {

    }

    public void m2()
    {

    }

    public void m3()
    {

    }

    @Testable
    public void m4()
    {
        throw new IllegalArgumentException("参数非法!");
    }

    @Testable
    public void m5()
    {

    }

    public void m6()
    {

    }

    @Testable
    public void m7()
    {
        throw new RuntimeException("运行异常!");
    }

    public void m8()
    {

    }

    /**
     * java8以前重复注解测试
     */
    @MyTags({@MyTag(name = "hello", age = 23), @MyTag(name = "world", age = 24)})
    public void repeatableTest()
    {
        System.out.println("-------------------我是分割线-------------------------");

        System.out.printf("Java8 以前重复注解: @MyTags({@MyTag(name = \"hello\", age = 23), @MyTag(name = \"world\", age = 24)})");
    }
}
