package ac.cn.saya.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: ThreadWaitNotifyUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-11 22:04
 * @Description:
 * 线程虚假唤醒
 * 题目：两个线程，可以操作初始值为零的一个变量
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 交替10轮，变量的初始值为零
 *
 * 高耦合，低内聚下，线程操作资源类
 * 判断、干活、通知
 * 多线程交互中，必须要防止虚假唤醒，即（判断只用while，不用if）
 */
class ThreadWaitNotifyUtil2Condition{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public  void increment(){
        lock.lock();
        try {
            // 判断，为了防止虚假唤醒，不能用if
            while (number != 0){
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            // 判断，为了防止虚假唤醒，不能用if
            while (number == 0){
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class ThreadWaitNotifyUtil2 {

    public static void main(String[] args) throws InterruptedException{
        ThreadWaitNotifyUtil2Condition condition = new ThreadWaitNotifyUtil2Condition();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                condition.increment();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                condition.increment();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                condition.decrement();
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                condition.decrement();
            }
        },"D").start();
    }

}
