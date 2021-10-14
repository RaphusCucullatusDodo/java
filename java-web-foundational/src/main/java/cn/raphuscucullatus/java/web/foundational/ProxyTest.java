package cn.raphuscucullatus.java.web.foundational;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * 动态代理的举例
 *
 * @author raphus cucullatus
 * @version 2021/8/1310:45
 * @since JDK8
 */
// 测试
public class ProxyTest {
    public static void main(String[] args) {
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(new SuperMan());
        System.out.println("我的信仰是：" + proxyInstance.getBilief());
        proxyInstance.eat("四川麻辣烫");

        ClothFactory clothFactory = (ClothFactory) ProxyFactory.getProxyInstance(new ErkeClothFactory());
        clothFactory.produceCloth();


    }
}

// 接口、协议
interface Human {
    String getBilief();

    void eat(String food);
}

//  被代理类
class SuperMan implements Human {

    @Override
    public String getBilief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}
/*
    如何根据加载到内存中的被代理类(obj)，动态创建一个代理类及其对象
        Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), myInvocationHandler)
    当通过代理类的对象调用方法时，动态的去调用被代理类的同名方法
        InvocationHandler
*/

//  通过Proxy类的newProxyInstance方法实现动态创建代理类
class ProxyFactory {
    //    传入被代理类对象，返回代理类对象
    public static Object getProxyInstance(Object obj) {
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.bind(obj);
        Object o = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), myInvocationHandler);
        return o;
    }
}

class MyInvocationHandler implements InvocationHandler {
//  通过Proxy类的newProxyInstance方法生成的动态代理类对象 代理类对象调用方法a 时，就会自动调用如下方法invoke()

    //    将被代理类要执行的方法a 的功能 声明在invoke（）中
    private Object obj;

    public void bind(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        代理类对象调用的方法，也就是被代理类要调用的方法
        Object returnValue = method.invoke(obj, args);
        return returnValue;
    }
}





