package reflect.proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP实现Dog接口行为
 */
public class DogAop {

    public static void main(String[] args) throws Exception {
        GunDog gunDog = new GunDog();

        Dog dog = (Dog) ProxyFactory.getProxy(gunDog);
        dog.info();
        dog.run();
    }
}

interface Dog {

    void info();

    void run();
}

class GunDog implements Dog {

    @Override
    public void info() {
        System.out.println("我是猎狗");
    }

    @Override
    public void run() {
        System.out.println("猎狗在跑");
    }
}

/**
 * 实现对Dog接口的方法的解耦，在代理调用时使用
 */
class DogUtil {

    public void method1() {
        System.out.println("=====模拟第一个通用方法=======");
    }

    public void method2() {
        System.out.println("========模拟第二个方法========");
    }
}

/**
 * 实现Dog接口的方法
 */
class MyInvocationHandler implements InvocationHandler {

    private Object target;

    /**
     * 设置需要被代理的对象
     * @param target
     */
    public void setTarget(Object target) {
        this.target = target;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception{

        DogUtil util = new DogUtil();
        util.method1();
        Object result = method.invoke(target, args);
        util.method2();

        return result;
    }
}

/**
 * 动态代理对象
 */
class ProxyFactory {

    public static Object getProxy(Object target) throws Exception {
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.setTarget(target);

        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
