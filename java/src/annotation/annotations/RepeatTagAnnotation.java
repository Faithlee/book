package annotation.annotations;

import java.lang.annotation.*;

/**
 * 重复注解测试
 */
@Repeatable(RepeatTagsAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RepeatTagAnnotation {

    String name();
}
