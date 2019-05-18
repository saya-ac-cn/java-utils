package ac.cn.saya.juc.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: ProdConsumerBlockQueueUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-15 20:14
 * @Description:
 */
class MyResource{
    /**
     * 默认开启，进行生产+消费
     */
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void prod() throws Exception{
        String data = null;
        boolean result;
        while (flag){
            data = atomicInteger.incrementAndGet() + "";
            result = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if (result){
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println(Thread.currentThread().getName()+"\t生产线已关闭，停止生产");
    }

    public void consumer() throws Exception{
        String result = null;
        while (flag){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t生产线已关闭，停止消费");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列"+result+"成功");
        }
    }

    public void stop(){
        this.flag = false;
    }
}

public class ProdConsumerBlockQueueUtilTest {

    public static void main(String[] args){
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            System.out.println();
            System.out.println();
            try{
                myResource.prod();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            System.out.println();
            System.out.println();
            try{
                myResource.consumer();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"Consumer").start();

        try{TimeUnit.SECONDS.sleep(5);}catch (InterruptedException e){e.printStackTrace();}

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("5秒钟到，停止生产线");
        myResource.stop();
    }
}
