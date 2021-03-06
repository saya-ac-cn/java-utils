package ac.cn.saya.juc.pool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Title: ScheduledThreadPoolTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-22 21:47
 * @Description:
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待的线程。避免了创建与销毁额外开销，提高了响应的速度。
 * 二、线程池的体系结构：
 * java.util.concurrent.Executor:负责线程的使用与调度的根接口
 *      |--**ExcutorService 子接口：线程池的主要接口
 *          |--ThreadPoolExcutor 线程池的实现类
 *          |--ScheduledExcutorService 子接口：负责线程的调度
 *              |--ScheduledThreadPoolExecutor：继承ThreadPoolExcutor，实现ScheduledExecutorService
 *
 * 三、工具类：Excutors
 * ExcutorService newFixedThreadPool（）：创建固定大小的线程池
 * ExcutorService newCacheThreadPool（）：缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * ExcutorService newSingleThreadExcutor（）：创建单个线程池，线程池中只有一个线程
 *
 * ScheduleExcutorService newScheduledThreadPool（）：创建固定大小的线程，可以延迟或定时的执行任务
 */

public class ScheduledThreadPoolTest {

    public static void main(String[] args) throws Exception{
        // 1,创建线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for(int i = 0; i < 10; i++){
            Future<Integer> future = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    return num;
                }
            },2,TimeUnit.SECONDS);
            System.out.println(future.get());
        }
        // 不加这个线程不会关闭
        pool.shutdown();
    }

}
