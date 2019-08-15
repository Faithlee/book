package reflect.clazz;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(Annotations.class)
@interface Annotation {}

@Retention(RetentionPolicy.RUNTIME)
@interface Annotations {
    Annotation[] value();
}
