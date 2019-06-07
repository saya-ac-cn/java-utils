package ac.cn.saya.juc.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @Title: LockSupportUtilsTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-22 21:32
 * @Description:
 */

public class LockSupportUtilsTest {

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.setName("mt");
        mt.start();
        try {
            Thread.currentThread().sleep(10);
            mt.park();
            Thread.currentThread().sleep(10000);
            mt.unPark();
            Thread.currentThread().sleep(10000);
            mt.park();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MyThread extends Thread {
    private boolean isPark = false;

    @Override
    public void run() {
        System.out.println(" Enter Thread running.....");
        while (true) {
            if (isPark) {
            System.out.println(Thread.currentThread().getName()+"Thread is Park.....");
            LockSupport.park();
        }
        //do something
        System.out.println(Thread.currentThread().getName()+">> is running");
        try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void park() {
        isPark = true;
    }

    public void unPark() {
        isPark = false;
        LockSupport.unpark(this);
        System.out.println("Thread is unpark.....");
    }
}
