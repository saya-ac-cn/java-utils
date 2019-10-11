package ac.cn.saya.juc.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
 * 使用线程池+Callable+FutureTask实现
 */

public class AlibabaUnit1 {

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
            for (Future<List<Long>> future : result) {
                // 可能会阻塞
                dbList.addAll(future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new AlibabaUnit1Consumer(0, dbList.size()-1, dbList);
        long sum = pool.invoke(task);
        pool.shutdown();
        System.out.println("sum:"+sum);
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
class AlibabaUnit1Producer implements Callable<List<Long>> {

    // 存放生产数据的线程
    private List db;

    public AlibabaUnit1Producer() {
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
class AlibabaUnit1Consumer extends RecursiveTask<Long> {

    private static final long serialVersionUID = -7871230106700698925L;

    private int start;
    private int end;
    private List<Long> list;

    public AlibabaUnit1Consumer(int start, int end, List<Long> list) {
        this.start = start;
        this.end = end;
        this.list = list;
    }


    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= 20) {
            long sum = 0;
            for (int i = start; i <= end; i++) {
                sum += list.get(i);
            }
            return sum;
        } else {
            int middle = (end + start) / 2;
            AlibabaUnit1Consumer left = new AlibabaUnit1Consumer(start, middle, list);
            AlibabaUnit1Consumer right = new AlibabaUnit1Consumer(middle + 1, end, list);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}