package annotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RepeatTagsAnnotation {

    // 可以接受多个@RepeatTagAnnotation
    RepeatTagAnnotation[] value();
}
