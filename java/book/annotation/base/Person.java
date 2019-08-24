package annotation.base;

import annotation.annotations.PersonAnnotation;
import annotation.annotations.TagAnnotation;

import java.lang.annotation.Annotation;

@PersonAnnotation(name = "Kim", age = 25, heigt = 170, weight = 65.0)
public class Person {

    public static void main(String[] args) throws Exception{

        Annotation[] annotations = Class.forName("annotation.base.Person").getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        Annotation[] tagAnnotations = Class.forName("annotation.base.Person").getMethod("info").getAnnotations();
        for (Annotation annotation : tagAnnotations) {
            System.out.println(annotation);
        }
    }

    @TagAnnotation
    public void info() {

    }
}
