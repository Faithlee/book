package annotation.annotations;

import java.lang.annotation.*;

/**
 * 修饰持久化类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersistentAnnotation {
    String table();
}
