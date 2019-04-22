package ac.cn.saya.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: LockUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-21 16:09
 * @Description:
 * 用于解决多线程安全问题的方式
 * synchronized：隐式锁
 * 1、同步代码块
 * 2、同步方法
 * jdk 1.5后：
 * 3、同步锁Lock
 * 注意：lock是一个显示锁，需要通过locl()方法上锁，必须通过unlock()方式进行释放锁
 */

public class LockUtil1 {

    public static void main(String[] args){
        Ticket ticket = new Ticket();
        //new Thread(ticket,"1#").start();
        new Thread(ticket,"2#").start();
        new Thread(ticket,"3#").start();
    }
}

class Ticket implements Runnable{
    private int ticket = 100;
    private Lock lock = new ReentrantLock();

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true){
            lock.lock();
            try{
                if(ticket > 0){
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException e){

                    }
                    System.out.println(Thread.currentThread().getName()+" 完成售票，余票："+ --ticket);
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
