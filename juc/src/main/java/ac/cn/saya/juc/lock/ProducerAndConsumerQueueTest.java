package ac.cn.saya.juc.lock;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Title: ProducerAndConsumerQueueTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-09 22:31
 * @Description:
 * 使用LinkedBlockingQueue实现生产消费者
 */

public class ProducerAndConsumerQueueTest {

    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10 ; i++) {
            service.submit(new ProducerAndConsumerQueueProducer(queue,"富士康"+i));
            service.submit(new ProducerAndConsumerQueueProducer(queue,"比亚迪"+i));
        }
        for (int i = 0; i < 10 ; i++) {
            service.submit(new ProducerAndConsumerQueueConsumer(queue,"华为"+i));
            service.submit(new ProducerAndConsumerQueueConsumer(queue,"小米"+i));
        }
        service.shutdown();
    }

}

class ProducerAndConsumerQueueProducer implements Runnable{
    private BlockingQueue<String> queue;
    private String producer;

    public ProducerAndConsumerQueueProducer(BlockingQueue<String> queue, String producer) {
        this.queue = queue;
        if (null != producer){
            this.producer = producer;
        }else {
            this.producer = "null";
        }

    }

    @Override
    public void run() {
        String uuid = UUID.randomUUID().toString();
        try {
            Thread.sleep(200);
            queue.put(producer+":"+uuid);
            System.out.println("Produce \"" + producer + "\" : " + uuid + " " + Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ProducerAndConsumerQueueConsumer implements Runnable{
    private BlockingQueue<String> queue;
    private String consumer;

    public ProducerAndConsumerQueueConsumer(BlockingQueue<String> queue, String consumer) {
        this.queue = queue;
        if (null != consumer){
            this.consumer = consumer;
        }else {
            this.consumer = "null";
        }
    }

    @Override
    public void run() {
        try {
            String uuid = queue.take();
            System.out.println(consumer + " decayed " + uuid
                    + " " + Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}