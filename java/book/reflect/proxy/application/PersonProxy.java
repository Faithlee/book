package reflect.proxy.application;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Proxy动态代理Person应用
 */
public class PersonProxy {

    public static void main(String[] args) {
        InvocationHandler handler = new MyInvocationHandler();

        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);
        person.walk();
        person.sayHello("Java");
    }
}

/**
 * 定义Person接口
 */
interface Person {

    void walk();

    void sayHello(String name);
}


class MyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        System.out.println("正在执行的方法:" + method);

        if (args != null) {
            System.out.println("下面是执行该方法传入的参数:");
            for (Object arg : args) {
                System.out.println(arg);
            }
        } else {
            System.out.println("调用该方法没有参数!");
        }

        return null;
    }
}
