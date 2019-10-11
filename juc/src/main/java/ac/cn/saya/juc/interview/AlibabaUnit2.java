package ac.cn.saya.juc.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Title: AlibabaUnit1
 * @ProjectName juc
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/10/11 0011 10:07
 * @Description: 阿里面试1
 * 题干：实现一个生产者、消费者场景。假设现在有一个任务调度系统负责处理数据，A线程负责从DB里拉待处理的任务放到队列；B线程组负责从队列中处理任务；
 * 由于任务处理量较大，所以B线程会将任务进行拆分子线程并行处理。当所有子线程处理完成后，由B线程汇总结果并统一落库。
 * 要求：
 * A线程组负责生产数据data；B线程组负责消费data数据；A、B线程组要实现阻塞；请尽量考虑异常场景的处理；
 * B线程组的一个处理线程在获取到一个数据后，需要再拆分5个子线程并行处理数据，当5个子线程全部处理完成，B的处理线程将结果合并；请尽量考虑异常场景的处理；
 * 使用线程池+Callable+CompletableFuture实现
 */

public class AlibabaUnit2 {

    public static void main(String[] args) {
        // 存放返回线程数据的方法
        List<Future<List<Long>>> result = new ArrayList<>();
        List dbList = new ArrayList<Long>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                10,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 10; i++) {
            result.add(threadPoolExecutor.submit(new AlibabaUnit1Producer()));
        }
        try {
            List<CompletableFuture<Long>> futures = new ArrayList<>();
            // 提取生产的数据，然后进行处理
            for (Future<List<Long>> future : result) {
                // 可能会阻塞
                dbList.addAll(future.get());
                futures.add(CompletableFuture.supplyAsync(new AlibabaUnit2Consumer(future.get()),threadPoolExecutor));
            }
            List<Long> collect = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
            long total = 0L;
            for (Long item : collect) {
                total += item;
            }
            System.out.println("sum:\n"+total);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}

/**
 * @描述 生产数据的线程
 * @参数
 * @返回值
 * @创建人 saya.ac.cn-刘能凯
 * @创建时间 2019/10/11 0011
 * @修改人和其它信息
 */
class AlibabaUnit2Producer implements Callable<List<Long>> {

    // 存放生产数据的线程
    private List db;

    public AlibabaUnit2Producer() {
        this.db = new ArrayList(10);
    }

    /**
     * @描述 生产数据并返回
     * @参数
     * @返回值
     * @创建人 saya.ac.cn-刘能凯
     * @创建时间 2019/10/11 0011
     * @修改人和其它信息
     */
    @Override
    public List<Long> call() throws Exception {
        for (int i = 0; i < 10; i++) {
            // 生产数据
            db.add((long) (Math.random() * 10000));
        }
        // 模拟耗时1s
        TimeUnit.SECONDS.sleep(1);
        return db;
    }
}

/**
 * @描述 处理数据
 * @参数
 * @返回值
 * @创建人  saya.ac.cn-刘能凯
 * @创建时间  2019/10/11 0011
 * @修改人和其它信息
 */
class AlibabaUnit2Consumer implements Supplier<Long> {

    private static final long serialVersionUID = -7871230106700698925L;

    private List<Long> list;

    public AlibabaUnit2Consumer(List<Long> list) {
        this.list = list;
    }

    @Override
    public Long get() {
        long sum = 0;
        for (long item:list) {
            sum += item;
        }
        return sum;
    }
}