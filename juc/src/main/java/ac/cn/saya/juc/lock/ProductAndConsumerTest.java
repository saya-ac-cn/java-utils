package ac.cn.saya.juc.lock;

/**
 * @Title: ProductAndConsumerTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-21 16:59
 * @Description:
 * 生产者 消费者 实例
 */

public class ProductAndConsumerTest {
    public static void main(String[] args){
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(productor,"生产者1").start();
        new Thread(consumer,"消费者1").start();
        new Thread(productor,"生产者2").start();
        new Thread(consumer,"消费者1").start();

    }
}

class Clerk{
    private int product = 0;

    // 进货
    public synchronized void get(){
        // 为了避免虚假传唤问题，应该总是使用在循环中
        while (product >= 1){
            System.out.println("产品已满");
            try{
                this.wait();
            }catch (InterruptedException e){}
        }
        System.out.println(Thread.currentThread().getName() + ":"+ ++product);
        this.notifyAll();
    }

    // 卖货
    public synchronized void sale(){
        while (product <= 0){
            System.out.println("缺货");
            try{
                this.wait();
            }catch (InterruptedException e){}
        }
        System.out.println(Thread.currentThread().getName() + ":"+ --product);
        this.notifyAll();
    }
}

// 生产者
class Productor implements Runnable{

    private Clerk clerk;

    public Productor(Clerk clerk) {
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
class Consumer implements Runnable{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
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