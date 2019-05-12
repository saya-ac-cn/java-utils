package ac.cn.saya.juc.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Title: CyclicBarrierUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-12 17:40
 * @Description:
 * 集齐七科龙珠，就可以召唤神兽
 */

public class CyclicBarrierUtilTest {

    public static void main(String[] args) throws Exception{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{System.out.println("集齐了七科龙珠，可以召唤神兽");});
        for (int i = 1; i <= 7 ; i++){
            final int tempInt = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " \t 收集到第：" + tempInt + "颗龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
