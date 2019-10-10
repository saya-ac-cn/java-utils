package ac.cn.saya.juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Title: SemaphoreUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-12 17:55
 * @Description:
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 */

public class SemaphoreUtilTest {

    public static void main(String[] args) throws Exception{
        // 模拟3个车位
        Semaphore semaphore = new Semaphore(3);
        for(int i = 1; i <= 6 ; i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t 停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
