package annotation14.annotation;

import java.lang.annotation.*;

/**
 * 重复注解测试
 */

// todo 使用元注解标识
@Repeatable(FkTags.class)

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FkTag {
    String name() default "疯狂软件";
    float price() default 100;
}
