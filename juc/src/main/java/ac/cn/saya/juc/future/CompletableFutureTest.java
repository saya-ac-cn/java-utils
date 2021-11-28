package ac.cn.saya.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Title: CompletableFutureTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 11/22/21 22:28
 * @Description:
 */

public class CompletableFutureTest {


    /**
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     * 获取结果和触发计算的方式有：
     * get()
     * join()
     * get(timeout,uint)
     * getNow()
     */
    public static void main1(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1000;
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("去completableFuture拿结果");
        //System.out.println(completableFuture.get(1,TimeUnit.SECONDS));
        System.out.println(completableFuture.getNow(444));
    }


    /**
     * thenApply + whenCompleteAsync
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main2(String[] args) throws ExecutionException, InterruptedException {
        //当一个线程依赖另一个线程时用thenApply 法来把这两个线程串行化
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1024;
        }).thenApply(f -> {
            System.out.println("222");
            return f + 1;
        }).thenApply(f -> {
            int age = 10/0;
            // 异常情况：那步出错就停在那步。
            System.out.println("333");
            return f + 1;
        }).whenCompleteAsync((v, e) -> {
            System.out.println("*****v: " + v);
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
        System.out.println("-----主线程结束，END");
        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException{
        //当一个线程依赖另一个线程时用handle 方法来把这两个线程串行化
        // 异常情况：有异常也可以往下一步走，根据带的异常参数可以进一步处理
        CompletableFuture.supplyAsync(() -> {
            // 暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1024;

        }).handle((f,e) -> {
            int age = 10/0;
            System.out.println("222");
            return f + 1;
        }).handle((f,e) -> {
            System.out.println("333");
            return f + 1;
        }).whenCompleteAsync((v,e) -> {
            System.out.println("*****v: " +v);
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
        System.out.println("-----主线程结束END");
        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
