package ac.cn.saya.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: ReenterLockUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-08 21:50
 * @Description:可重入锁及递归锁证明
 * 可重入锁（也叫递归锁）
 * 指的是同一个线程外层函数获得锁之后，内层递归函数任然能获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * 也即是说，线程可以进入任何一个它已经拥有的锁所同步着 的代码块
 */
class Phone extends Thread{
    public synchronized void sendMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t 发短信");
        this.sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t 发邮件");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        this.get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 发短信");
            this.set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 发邮件");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ReenterLockUtilTest {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                phone.sendMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

        System.out.println("——————————————————");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread1 = new Thread(phone);
        Thread thread2 = new Thread(phone);
        thread1.start();
        thread2.start();
    }

}
