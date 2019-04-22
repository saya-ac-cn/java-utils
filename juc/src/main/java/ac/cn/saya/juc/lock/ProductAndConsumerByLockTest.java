package ac.cn.saya.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: ProductAndConsumerTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-21 16:59
 * @Description:
 * 生产者 消费者 实例
 */

public class ProductAndConsumerByLockTest {
    public static void main(String[] args){
        ClerkOnLock clerk = new ClerkOnLock();
        ProductorOnLock productor = new ProductorOnLock(clerk);
        ConsumerOnLock consumer = new ConsumerOnLock(clerk);
        new Thread(productor,"生产者1").start();
        new Thread(consumer,"消费者1").start();
        new Thread(productor,"生产者2").start();
        new Thread(consumer,"消费者1").start();

    }
}

class ClerkOnLock{
    private int product = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // 进货
    public void get(){
        lock.lock();
        try{
            // 为了避免虚假传唤问题，应该总是使用在循环中
            while (product >= 1){
                System.out.println("产品已满");
                try{
                    condition.await();
                }catch (InterruptedException e){}
            }
            System.out.println(Thread.currentThread().getName() + ":"+ ++product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    // 卖货
    public void sale(){
        lock.lock();
        try {
            while (product <= 0){
                System.out.println("缺货");
                try{
                    condition.await();
                }catch (InterruptedException e){}
            }
            System.out.println(Thread.currentThread().getName() + ":"+ --product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

// 生产者
class ProductorOnLock implements Runnable{

    private ClerkOnLock clerk;

    public ProductorOnLock(ClerkOnLock clerk) {
        this.clerk = clerk;
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
        for(int i = 0; i < 20; i++){
            clerk.get();
        }
    }
}

// 消费者
class ConsumerOnLock implements Runnable{
    private ClerkOnLock clerk;

    public ConsumerOnLock(ClerkOnLock clerk) {
        this.clerk = clerk;
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
        for(int i = 0; i < 20; i++){
            clerk.sale();
        }
    }
}