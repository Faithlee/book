package annotation14.apt;

import annotation14.annotation.ActionListenerFor;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class ActionListenerProcess
{
    /**
     * APT
     *
     * @param obj
     */
    public static void processAnnotations(Object obj)
    {
        try {
            Class clazz = obj.getClass();
            for (Field field: clazz.getDeclaredFields()) {
                //System.out.println(field.getName());

                // 设置成员可见性
                field.setAccessible(true);

                // 获取成员变量上ActionListenerFor类型的Annotation ActionListenerFor.class
                ActionListenerFor annotation = field.getAnnotation(ActionListenerFor.class);
                System.out.println(annotation);

                // 获取成员变量field的值
                Object fieldObj = field.get(obj);
                if (annotation != null && fieldObj != null && fieldObj instanceof AbstractButton) {
                    // 获取ActionListenerFor的元数据
                    Class<? extends ActionListener> listenerClass = annotation.listener();
                    //System.out.println(listenerClass);
                    // todo listenerClass实例抛异常，需要排除问题
                    ActionListener listenerIns = listenerClass.newInstance();
                    AbstractButton listenerBtn = (AbstractButton)fieldObj;

                    listenerBtn.addActionListener(listenerIns);
                }
                /*
                */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
