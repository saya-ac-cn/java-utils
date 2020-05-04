package ac.cn.saya.juc.pro;

import java.util.concurrent.CompletableFuture;

/**
 * @Title: CompletableFutureUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-05-04 17:26
 * @Description: 异步回调
 */

public class CompletableFutureUtil1 {

    public static void main(String[] args) throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 6666;
        });
        System.out.println("7777");
        future.whenComplete((t,u)->{
            System.out.println("--t:"+t);
            System.out.println("--u:"+u);
        }).exceptionally(f->{
            System.out.println("--exception:"+f.getMessage());
            return 777;
        }).get();
    }

}
