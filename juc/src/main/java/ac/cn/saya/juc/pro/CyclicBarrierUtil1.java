package ac.cn.saya.juc.pro;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Title: CyclicBarrierUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-13 16:24
 * @Description:
 * CyclicBarrier 循环栅栏
 */

public class CyclicBarrierUtil1 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,() -> {
            System.out.println("与会人员到齐，开会");
        });
        for (int i = 0; i < 10; i++) {
            final int temlInt = i;
            new Thread(() -> {
                System.out.println("第"+temlInt+"号员工到达会场");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
