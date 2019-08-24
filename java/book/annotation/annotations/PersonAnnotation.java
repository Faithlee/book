package annotation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解，修饰人类
 *
 * 使用Annotation修饰后，这些注解不会自动生效，必须开发APT（注解处理工具）
 *
 * Annotation接口表示程序元素的注解，是所有注解的父接口
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonAnnotation {

    // todo 定义成员变量:
    //  使用时需要为该成员变量赋值
    //  如果指定默认值，可以不用赋值
    String name();

    int age();

    double weight();

    double heigt();

    boolean gender() default true;
}
