package ac.cn.saya.juc.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @Title: CountDownLatchUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-12 17:24
 * @Description:
 * 到计数类，等待前面的线程完成了才可以继续操作
 */

public class CountDownLatchUtilTest {

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i = 0 ; i <= 10; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t  上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("人数走完，可以关门");
    }
}
