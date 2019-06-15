package ac.cn.saya.jdbc.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Title: BatchInsert
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-12 23:47
 * @Description:
 */

public class BatchInsert {


    public static void main(String[] args) {
        // 创建测试的数据
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        ExecutorService threadPool = new ThreadPoolExecutor(
                5,
                10,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(20);
        try{
            for(int i=1;i<=20;i++){
                final int item = i;
                threadPool.execute(new BatchInsertThread(list,countDownLatch));
//                threadPool.execute(()->{
//                    System.out.println("---------"+item);
//                    System.out.println(Thread.currentThread().getName()+"\t  上完自习，离开教室");
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    countDownLatch.countDown();
//                });
            }
            countDownLatch.await();
            System.out.println("人数走完，可以关门");
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
