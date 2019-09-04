package reflect.generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 反射获取泛型信息
 */
public class ReflectGeneric {

    private Map<String, Integer> score;

    public static void main(String[] args) throws Exception{
        Class<ReflectGeneric> clazz = ReflectGeneric.class;

        Field field = clazz.getDeclaredField("score");

        Class<?> fieldType = field.getType();
        System.out.println("score类型是: " + fieldType);

        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            // 泛型类型
            ParameterizedType type = (ParameterizedType)genericType;
            // 原始类型
            Type rawType = type.getRawType();
            System.out.println("原始类型: " + rawType);
            // 泛型类型的泛型参数
            Type[] typeArgs = type.getActualTypeArguments();
            for (int i = 0; i < typeArgs.length; i++) {
                System.out.println(String.format("第%d个泛型类型是: %s", i, typeArgs[i]));
            }
        } else {
            System.out.println("获取泛型类型出错!");
        }
    }
}

