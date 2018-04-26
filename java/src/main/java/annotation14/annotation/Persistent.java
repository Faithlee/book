package annotation14.annotation;

import java.lang.annotation.*;

/**
 * 修饰持久类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Persistent
{
    String table();
}
