package annotation14;

import annotation14.annotation.FkTag;
import annotation14.annotation.FkTags;

/**
 * 使用@Repeatable测试重复注解
 */
@FkTag(name = "疯狂java讲义", price = 99)
@FkTag(name = "疯狂ajax讲义", price = 66)
public class RepeatAnnotation {

    public static void main(String[] args)
    {
        Class<RepeatAnnotation> clazz = RepeatAnnotation.class;

        // todo 使用java8新增的getDecleardAnnotationByType方法获取
        FkTag[] tags = clazz.getDeclaredAnnotationsByType(FkTag.class);
        for (FkTag tag : tags) {
            System.out.println(tag.name() + "--->" + tag.price());
        }

        // java8 以前获取修饰类的注解
        FkTags container = clazz.getDeclaredAnnotation(FkTags.class);
        System.out.println(container);
    }
}
