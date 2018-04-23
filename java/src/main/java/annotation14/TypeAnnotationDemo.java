package annotation14;

import annotation14.annotation.NotNull;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

/**
 * 类型注解
 */
// todo 类型注解可以在任何用到类型的地方使用
// todo 比如创建对象、类型转换、使用implements实现接口、使用throws抛出的异常
// todo 注意：需要实现Type Annotation的检查框架，从而让编译器执行更加严格的检查，保证代码健壮

public class TypeAnnotationDemo implements @NotNull Serializable{

    public static void main(String[] args)
            throws @NotNull FileNotFoundException
    {
        Object obj = "java.org";
        String str = (@NotNull String)obj;

        Object win = new @NotNull JFrame("疯狂软件");
    }

    public @NotNull String test(List<@NotNull String> info)
    {
        return "info";
    }
}
