package ac.cn.saya.juc.lock;

import org.springframework.objenesis.instantiator.sun.UnsafeFactoryInstantiator;

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
 * 使用内置对象锁手动加锁和解锁
 */

public class LockUtil2 {

    public static void main(String[] args){
        Ticket2 ticket = new Ticket2();
        //new Thread(ticket,"1#").start();
        new Thread(ticket,"2#").start();
        new Thread(ticket,"3#").start();
    }
}

class Ticket2 implements Runnable{
    private int ticket = 10;
    private static Object object = new Object();

    @Override
    public void run() {
        while (ticket > 0){
            UnsafeInstance.reflectGetUnsafe().monitorEnter(object);
            try{
                if(ticket > 0){
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException e){

                    }
                    System.out.println(Thread.currentThread().getName()+" 完成售票，余票："+ --ticket);
                }
            }finally {
                UnsafeInstance.reflectGetUnsafe().monitorExit(object);
            }
        }
    }
}
