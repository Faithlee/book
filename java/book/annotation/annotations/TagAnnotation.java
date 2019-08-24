package annotation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 标签注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TagAnnotation {

    String name() default "Bill";

    int age() default 22;
}
