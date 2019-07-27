package annotation14.annotation;


import java.awt.event.ActionListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionListenerFor {
    // 成员变量用于设置元数据，保存监听器实现类
    Class<? extends ActionListener> listener();
}
