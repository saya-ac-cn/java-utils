package ac.cn.saya.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: PrintABCPro1Test
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-09-26 21:46
 * @Description: 升级版多线程打印ABC不借助Condition
 */

public class PrintABCPro1Test {

    private static volatile int state = 0;

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        Thread A = new Thread(() -> {
            while (state <= 30) {
                lock.lock();
                if (state % 3 == 0) {
                    System.out.print("A");
                    state++;
                }
                lock.unlock();
            }
        });

        Thread B = new Thread(() -> {
            while (state <= 30) {
                lock.lock();
                if (state % 3 == 1) {
                    System.out.print("B");
                    state++;
                }
                lock.unlock();
            }
        });

        Thread C = new Thread(() -> {
            while (state <= 30) {
                lock.lock();
                if (state % 3 == 2) {
                    System.out.println("C");
                    state++;
                }
                lock.unlock();
            }
        });
        C.start();
        A.start();
        B.start();
    }

}
