package ac.cn.saya.juc.pool;


import java.util.concurrent.*;

/**
 * @Title: MyThreadPoolTestUtils
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-16 21:59
 * @Description:
 */

public class MyThreadPoolTestUtils {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                3,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try{
            for(int i=1;i<=10;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit(){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
            for(int i=1;i<=10;i++){
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
