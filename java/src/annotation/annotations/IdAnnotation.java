package annotation.annotations;

import java.lang.annotation.*;

/**
 * 标识属性
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdAnnotation {

    String column();

    String type();

    String generator();
}
