package annotation.annotations;

import java.lang.annotation.*;

/**
 * 继承注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InheritableAnnotation {
}
