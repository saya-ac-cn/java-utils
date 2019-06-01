package ac.cn.saya.juc.parallel;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: ShellSortUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-01 19:35
 * @Description:
 * 并行希尔排序
 */

public class ShellSortUtil {

    public static int []arr ={1,4,2,7,9,8,3,6,45,12,34,56,23};

    public static void main(String[] args) throws InterruptedException{
        System.out.println(Arrays.toString(arr));
        int h = 1;
        CountDownLatch latch = null;
        ExecutorService pool = Executors.newCachedThreadPool();
        while (h <= arr.length/3){
            h = h*3+1;
        }
        while (h >0){
            System.out.println("h="+h);
            if(h >= 4){
                //采用并行
                latch = new CountDownLatch(arr.length-h);
            }
            for (int i = h; i < arr.length; i++){
                // 控制线程数
                if(h >= 4){
                    pool.execute(new ShellSortTask(i,h,latch));
                }else {
                    if(arr[i] < arr[i-h]){
                        int temp = arr[i];
                        int j = i - h;
                        while (j >= 0 && arr[j] > temp){
                            arr[j+h] = arr[j];
                            j = j - h;
                        }
                        arr[j+h] = temp;
                    }
                }
            }
            // 等待线程排序完成，进入下一次排序
            latch.await();
            // 计算下一个h值
            h = (h-1)/3;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static class ShellSortTask implements Runnable{

        private int i = 0;
        private int h = 0;

        CountDownLatch l;

        public ShellSortTask(int i, int h, CountDownLatch l) {
            this.i = i;
            this.h = h;
            this.l = l;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            System.out.println("使用并行排序");
            if(arr[i] < arr[i-h]){
                int temp = arr[i];
                int j = i - h;
                while (j >= 0 && arr[j] > temp){
                    arr[j+h] = arr[j];
                    j = j - h;
                }
                arr[j+h] = temp;
            }
            l.countDown();
        }
    }

}
