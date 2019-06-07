package ac.cn.saya.juc.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: SearchUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-01 20:37
 * @Description:
 * 并行搜索
 */

public class SearchUtil {

    public static int []arr ={1,4,2,7,9,8,3,6,45,12,34,56,23};
    public static ExecutorService pool = Executors.newCachedThreadPool();
    public static final int threadSize = 2;

    public static AtomicInteger result = new AtomicInteger(-1);

    public static int search(int searchValue,int beginIndex,int endIndex){
        int i = 0;
        for(i = beginIndex; i < endIndex; i++){
            if(result.get() >= 0){
                return result.get();
            }
            if(arr[i] == searchValue){
                // 如果设置失败表示其它线程已经找到
                if(!result.compareAndSet(-1,i)){
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }
    public static void searchMain(int searchValue){

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int subArraySize = arr.length/threadSize;
        List<Future<Integer>> re = new ArrayList<>();
        for(int i = 0; i < arr.length; i+=subArraySize){
            int end = i + subArraySize;
            if(end >= arr.length){
                end = arr.length;
            }
            re.add(pool.submit(new SearchTask(i,end,45)));
        }
        for (Future<Integer> fu:re){
            //System.out.println("结果："+fu.get());
            if(fu.get() >= 0){
                //return fu.get();
                System.out.println("结果："+fu.get());
            }
        }
        return;
    }

    public static class SearchTask implements Callable<Integer>{
        int begin,end,searchValue;

        public SearchTask(int begin, int end, int searchValue) {
            this.begin = begin;
            this.end = end;
            this.searchValue = searchValue;
        }

        /**
         * 调用并发搜索
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Integer call() throws Exception {
            int result = search(searchValue,begin,end);
            return result;
        }
    }

}
