package ac.cn.saya.juc.lock;

/**
 * @Title: ProdConsumerTraditionTestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-14 22:31
 * @Description:
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
 * 1    线程  操作（方法）  资源类
 * 2    判断  干活  通知
 * 3    防止虚假唤醒
 */

class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        try {
            lock.lock();
            // 多线程判断必须用while
            while (number != 0){
                // 等待，不能生产
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        try {
            lock.lock();
            // 多线程判断必须用while
            while (number == 0){
                // 等待，不能消费
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class ProdConsumerTraditionTestUtil {

    public static void main(String[] args){
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++){
                    shareData.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++){
                    shareData.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
