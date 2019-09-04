package annotation.base;

import annotation.annotations.InheritableAnnotation;

/**
 * @Inherited元注解修饰
 */
public class Inherit {

    public static void main(String[] args) {
        // 使用类的反射查看子类是否有@Inheritable修饰
        System.out.println(Child.class.isAnnotationPresent(annotation.annotations.InheritableAnnotation.class));
    }
}

@InheritableAnnotation
class Parent {

}

/**
 * 判断是否有@Inheritable修饰
 */
class Child extends Parent {

}
