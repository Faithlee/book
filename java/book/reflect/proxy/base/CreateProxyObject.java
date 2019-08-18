package reflect.proxy.base;

import java.lang.reflect.*;

/**
 * 使用Proxy创建代理对象
 */
public class CreateProxyObject {

    public static void main(String[] args) {

    }

    /**
     * 使用Proxy创建代理类，然后实例化
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static void proxyCreateClass()
        throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        InvocationHandler handler = new MyInvocationHandler();

        Class proxy = Proxy.getProxyClass(Foo.class.getClassLoader(), new Class[]{Foo.class});

        Constructor constructor = proxy.getConstructor(new Class[]{InvocationHandler.class});

        Foo foo = (Foo) constructor.newInstance(new Object[]{handler});
    }

    /**
     * 使用Proxy创建对象
     */
    public static void proxyCreateInstance2() {
        InvocationHandler handler = new MyInvocationHandler();

        Foo foo = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(), new Class[]{Foo.class}, handler);
    }
}


class MyInvocationHandler implements InvocationHandler {

    public MyInvocationHandler(Object... object) {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        return null;
    }
}

class Foo {

    public Foo(MyInvocationHandler handler) {

    }
}
