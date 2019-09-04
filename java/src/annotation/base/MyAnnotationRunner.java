package annotation.base;

import annotation.annotations.TestAnnotation;
import annotation.apt.MyAnnotationProcessor;

/**
 * 注解处理
 */
public class MyAnnotationRunner {

    public static void main(String[] args) {
        // 运行APT
        try {
            String clazz = "annotation.base.MyAnnotationRunner";
            MyAnnotationProcessor.process(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TestAnnotation
    public static void m1() {

    }

    public static void m2() {

    }

    @TestAnnotation
    public static void m3() {
        throw new IllegalArgumentException("参数出错了");
    }

    public static void m4() {

    }

    @TestAnnotation
    public static void m5() {

    }

    public static void m6() {

    }

    @TestAnnotation
    public static void m7() {
        throw new RuntimeException("程序业务出现异常!");
    }

    public static void m8() {

    }
}
