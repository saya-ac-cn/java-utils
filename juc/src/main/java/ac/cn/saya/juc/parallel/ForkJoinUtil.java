package ac.cn.saya.juc.parallel;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
/**
 * @Title: ForkJoinUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-09-20 22:08
 * @Description:
 * 使用ForkJoin累计求和
 */

public class ForkJoinUtil {

    public static void main(String[] args) {
        Instant start = Instant.now();
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(numbers,0, numbers.length - 1);
        Long sum =pool.invoke(task);
        pool.shutdown();
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间：" + Duration.between(start, end).toMillis());
    }

}

class ForkJoinSumCalculate extends RecursiveTask<Long>{


    private static final long serialVersionUID = -520434462416457993L;

    private long[] numbers;
    private int start;
    private int end;

    //临界值
    private static final long THURSHOLD = 100000L;

    public ForkJoinSumCalculate(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    /**
     * 拆分任务
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        long length = end - start;
        //小于临界值，则不进行拆分，直接计算初始值到结束值之间所有数之和
        if(length <= THURSHOLD){
            long sum = 0L;
            for (int i = start; i <= end; i++) {
                sum += numbers[i];
            }
            return sum;
        }else {
            //大于临界值，取中间值进行拆分，递归调用
            int middle = (start + end) / 2;
            // 切割后的左半部分
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(numbers, start, middle);
            // 进行拆分，同时加入线程队列
            left.fork();

            // 切割后的右半部分
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(numbers,middle+1,end);
            right.fork();

            return left.join() + right.join();
        }
    }
}

