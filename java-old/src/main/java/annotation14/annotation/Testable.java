package annotation14.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记可测试的方法
 */

// todo 使用JDK的元数据Annotation：@Retention，限定保留时长
@Retention(RetentionPolicy.RUNTIME)
// todo 使用JDK的元数据Annotation：@Target，限定修饰方法
@Target(ElementType.METHOD)
public @interface Testable
{
}
