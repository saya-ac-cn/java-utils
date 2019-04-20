package ac.cn.saya.juc.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: AtomicTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-20 22:16
 * @Description:
 * 一、i++的原子问题：i++的操作分为三步"读-写-改"
 *      int i = 10
 *      i = i++; // 10
 *
 *      int temp = i;
 *      i = i + 1;
 *      i = temp;
 * 原子变量：jdk1.5后java.util.concurrent.atomic包下提供了常用的原子变量：
 *      1、volatile 保证内存可见性
 *      2、CAS（Compare-And-Swap）算法保证数据的原子性
 *      CAS算法是硬件对于并发操作共享数据的支持
 *      CAS 包含了三个操作数：
 *      内存值 V
 *      预估值 A
 *      更新值 B
 *      当且仅当 V == A 时，V == B,否则不对任何操作
 */

public class AtomicTest {

    public static void main(String[] args){
        AtomicIntegerTest thread = new AtomicIntegerTest();
        for (int i = 0;i < 10; i++){
            new Thread(thread).start();
        }
    }
}

class AtomicIntegerTest implements Runnable{
    private AtomicInteger serialNumber = new AtomicInteger(0);

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }

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
        try {
            Thread.sleep(200);
        }catch (InterruptedException e){

        }
        System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
    }
}
