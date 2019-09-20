package ac.cn.saya.juc.parallel;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;
/**
 * @Title: CallableUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-09-20 22:05
 * @Description:
 * Callable使用Future 线程池累计求和
 */

public class CallableUtil {

    public static void main(String[] args) {
        Instant beginTime = Instant.now();
        // 定义盛放有返回值的集合
        List<Future<Long>> results = new ArrayList<>();
        // 定义要参与运算的数组集合
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();
        // 每个线程的任务数
        int thurshold = 100000;
        // 任务数
        int tasks = numbers.length / thurshold;
        ExecutorService threadPool = new ThreadPoolExecutor(
                5,
                tasks,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        for (int i = 0; i < tasks; i++) {
            // 该子线程开始索引
            int start = i * thurshold;
            // 该子线程结束索引
            int end = (i + 1) * thurshold - 1;
            // 如果最后一个线程的索引大于任务长度，则以任务长度为准
            end = end > numbers.length ? numbers.length - 1 : end;
            results.add(threadPool.submit(new SumTask(numbers, start, end)));
        }
        long total = 0L;
        try {
            for (Future<Long> future : results) {
                // 可能会阻塞
                total += future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        Instant endTime = Instant.now();
        System.out.println(total);
        System.out.println("耗费时间：" + Duration.between(beginTime, endTime).toMillis());
    }

}

class SumTask implements Callable<Long> {
    private long[] numbers;
    private int start;
    private int end;

    public SumTask(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() {
        long total = 0;
        for (int i = start; i <= end; i++) {
            total += numbers[i];
        }
        return total;
    }
}
