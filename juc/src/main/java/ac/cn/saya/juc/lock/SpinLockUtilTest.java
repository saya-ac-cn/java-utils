package ac.cn.saya.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: SpinLockUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-09 21:19
 * @Description:
 * 实现一个自旋锁
 * 自旋转的好处：循环比较获取，直到成功为止，没有类似wait的阻塞
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒，B随后进来发现当前线程有线程持有锁，不是null
 * 所以只能自旋等待，直到A释放锁后B随后抢到。
 */

public class SpinLockUtilTest {

    /**
     * 原子引用线程
     */
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t 即将进入锁");
        while (!atomicReference.compareAndSet(null,thread)){
            //System.out.println("快出来~");
        }
    }

    public void myUnLuck(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t 释放锁");
    }

    public static void main(String[] args) {
        SpinLockUtilTest test = new SpinLockUtilTest();
        new Thread(()->{
            test.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.myUnLuck();
        },"AA").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            test.myLock();
            test.myUnLuck();
        },"BB").start();
    }

}
