package ac.cn.saya.juc.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Title: ReadWriteLockTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-21 20:05
 * @Description:
 * ReadWriteLock 读写锁
 * 写写、读写 需要互斥
 * 读读 不需要互斥
 */

public class ReadWriteLockTest {

    public static void main(String[] args){
        ReadWriteLockUtil1 writeLockUtil1 = new ReadWriteLockUtil1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                writeLockUtil1.set((int)(Math.random()*101));
            }
        },"Write:").start();
        for (int i = 0; i < 100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    writeLockUtil1.get();
                }
            },"Read").start();
        }
    }
}

class ReadWriteLockUtil1{
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    // 读
    public void get(){
        // 上锁
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+":"+number);
        }finally {
            // 释放锁
            lock.readLock().unlock();
        }
    }

    public void set(int number){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        }finally {
            lock.writeLock().unlock();
        }
    }
}
