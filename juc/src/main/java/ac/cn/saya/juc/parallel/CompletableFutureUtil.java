package ac.cn.saya.juc.parallel;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @Title: CompletableFutureUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-09-20 22:06
 * @Description:
 * CompletableFuture累计求和
 */

public class CompletableFutureUtil {

    public static void main(String[] args) {
        Instant beginTime = Instant.now();
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();
        List<CompletableFuture<Long>> list = new ArrayList<>();
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
            list.add(CompletableFuture.supplyAsync(new FutureSumTask(numbers, start, end), threadPool));
        }
        List<Long> rs = null;
        try {
            rs = list.stream().map(CompletableFuture::join).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        long total = 0L;
        for (Long item : rs) {
            total += item;
        }
        Instant endTime = Instant.now();
        System.out.println(total);
        System.out.println("耗费时间：" + Duration.between(beginTime, endTime).toMillis());
    }
}

class FutureSumTask implements Supplier<Long> {
    private long[] numbers;
    private int start;
    private int end;

    public FutureSumTask(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    public Long get() {
        long total = 0;
        for (int i = start; i <= end; i++) {
            total += numbers[i];
        }
        return total;
    }
}
