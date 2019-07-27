package annotation14.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 定义类型注解
 */

@Target(ElementType.TYPE_USE)
public @interface NotNull
{
}
