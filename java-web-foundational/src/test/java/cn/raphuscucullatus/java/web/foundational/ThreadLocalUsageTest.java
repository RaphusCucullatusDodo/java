package cn.raphuscucullatus.java.web.foundational;

import org.testng.annotations.Test;

/**
 * ThreadLocalTest的使用
 *
 * @author raphus cucullatus
 * @version 2021/8/1120:49
 * @since JDK8
 */
public class ThreadLocalUsageTest {
    /**
     * ThreadLocal线程本地容器
     * 作用：保证在同一个线程使用的对象相同，也就是在同一个线程中只能存放一个数据，
     */
    @Test
    public void testThreadLocalUsage() {

    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("c++");
        System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());
//        在main线程中创建一个线程
        new Thread(() -> {
//            在新创建的线程中设置threadlocal中的值该为java
            threadLocal.set("Java");
            System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());

        }).start();
        System.out.println(Thread.currentThread().getName() + "--->" + threadLocal.get());

    }


    @Test
    public void test() {
        double remission = Calculation.remission(new Traveller(81, 0), new Enemy(92), false);
        System.out.println(remission);
    }
}

class Traveller {
    double LV;
    double DEF;

    public Traveller(int LV, int DEF) {
        this.LV = LV;
        this.DEF = DEF;
    }
}

class Enemy {
    double LV;
    double DEF;

    public Enemy(int LV) {
        this.LV = LV;
        this.DEF = 5 * LV + 500;
    }
}

class Calculation {
    /**
     * who:ture为旅行者受到攻击
     * false为敌人收到攻击
     */
    public static double remission(Traveller t, Enemy e, boolean who) {
        return who ? e.DEF / (e.DEF + t.DEF) : (5 * t.LV + 500) / (5 * t.LV + 500 + e.DEF);
    }
}