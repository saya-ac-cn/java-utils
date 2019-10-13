package ac.cn.saya.juc.pro;

import java.util.concurrent.CountDownLatch;

/**
 * @Title: CountDownLatchUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-13 16:10
 * @Description:
 * CountDownLatch 倒计时
 */

public class CountDownLatchUtil1 {

    public static void main(String[] args) throws InterruptedException{
        CountDownLatch downLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"离开教室");
                downLatch.countDown();
            },String.valueOf(i)).start();
        }
        downLatch.await();
        System.out.println("人走完毕，关门");
    }

}
