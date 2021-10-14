package cn.raphuscucullatus.java.web.foundational;

/**
 * 静态代理举例
 *
 * @author raphus cucullatus
 * @version 2021/8/1310:30
 * @since JDK8
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        ErkeClothFactory Erke = new ErkeClothFactory();
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(Erke);
        proxyClothFactory.produceCloth();

    }
}

interface ClothFactory {
    void produceCloth();
}

/**
 * 代理类
 */
class ProxyClothFactory implements ClothFactory {
    //    代理类依赖被代理类
    private ClothFactory factory;

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    public ProxyClothFactory() {

    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂初始化。。。");
        factory.produceCloth();
        System.out.println("代理工厂做后续收尾工作");
    }
}

class ErkeClothFactory implements ClothFactory {
    public ErkeClothFactory() {
    }

    ;

    @Override
    public void produceCloth() {
        System.out.println("鸿星尔克全力生产中");
    }
}
