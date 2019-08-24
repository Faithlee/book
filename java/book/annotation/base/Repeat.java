package annotation.base;

import annotation.annotations.RepeatTagAnnotation;
import annotation.annotations.RepeatTagsAnnotation;

// 重复注解用法1，使用容器
//@RepeatTagsAnnotation({@RepeatTagAnnotation(name = "annotation1"), @RepeatTagAnnotation(name = "annotation2")})

// 用法2
@RepeatTagAnnotation(name = "annotation3")
@RepeatTagAnnotation(name = "annotation4")
public class Repeat {

    public static void main(String[] args) {
        Class<Repeat> clazz = Repeat.class;

        RepeatTagAnnotation[] annotations = clazz.getDeclaredAnnotationsByType(RepeatTagAnnotation.class);
        for (RepeatTagAnnotation annotation : annotations) {
            System.out.println("重复注解: " + annotation.name());
        }

        //
        RepeatTagsAnnotation annotation = clazz.getDeclaredAnnotation(RepeatTagsAnnotation.class);
        System.out.println(annotation);
    }
}
