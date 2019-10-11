package ac.cn.saya.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: ThreadOrderAccess
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-11 23:01
 * @Description:
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 *
 * 高耦合，低内聚下，线程操作资源类
 * 判断、干活、通知
 * 多线程交互中，必须要防止虚假唤醒，即（判断只用while，不用if）
 * 标志位
 */

//线程资源
class ThreadOrderAccessSource{

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    /**
     * 1打印A；2打印B；3打印C
     */
    private int number = 1;

    public void printA(){
        lock.lock();
        try {
            // 判断
            while (number != 1){
                conditionA.await();
            }
            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            number = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            // 判断
            while (number != 2){
                conditionB.await();
            }
            // 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            number = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            // 判断
            while (number != 3){
                conditionC.await();
            }
            // 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            number = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class ThreadOrderAccess {

    public static void main(String[] args) {
        ThreadOrderAccessSource accessSource = new ThreadOrderAccessSource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                accessSource.printA();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                accessSource.printB();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                accessSource.printC();
            }
        },"C").start();
    }

}
