package ac.cn.saya.juc.interview;


import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: ProductAndConsumerUnit
 * @ProjectName spring-boot-webflux
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/3/10 0010 14:56
 * @Description:生产消费者模式
 */

public class ProductAndConsumerUnit {

    public void method1(){
        ShareDate shareDate = new ShareDate();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareDate.consumer();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareDate.product();
            }
        }).start();
    }

    // 用test达不到效果
    public static void main(String[] args){
        try {
            BlockingQueue<String> queue = new LinkedBlockingQueue<String>(1);
            ProductAndConsumerUnit_Product product = new ProductAndConsumerUnit_Product(queue);
            ProductAndConsumerUnit_Consumer consumer = new ProductAndConsumerUnit_Consumer(queue);
            for (int i = 0; i < 5; i++) {
                new Thread(product).start();
                new Thread(consumer).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

// 利用 lock锁实现
class ShareDate{
    private volatile int flog = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void product(){
        lock.lock();
        try {
            while (flog!=0){
                condition.await();
            }
            flog = flog+1;
            System.out.println("生产");
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer(){
        lock.lock();
        try {
            while (flog==0){
                condition.await();
            }
            flog = flog-1;
            System.out.println("消费");
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

// 生产测
class ProductAndConsumerUnit_Product implements Runnable{

    private BlockingQueue<String> queue;

    public ProductAndConsumerUnit_Product(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String uuid = UUID.randomUUID().toString();
        try {
            System.out.println("生产");
            this.queue.put(uuid);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 消费测
class ProductAndConsumerUnit_Consumer implements Runnable{
    private BlockingQueue<String> queue;

    ProductAndConsumerUnit_Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String message = this.queue.take();
            System.out.println("消费："+message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}