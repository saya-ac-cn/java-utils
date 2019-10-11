package ac.cn.saya.juc.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Title: CallableUtil1
 * @ProjectName juc
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/10/11 0011 14:38
 * @Description:
 * 一、创建线程的方式三：实现Callable接口，相较于实现Runnable接口的方式，方法可以有返回值，可以抛出异常
 *
 * 二、执行Callable方式，需要FutureTask需要类的支持，用于接收运算的结果。FutureTask是Future接口的实现类
 */

public class CallableUtil1 {

    public static void main(String[] args) {
        CallableUtil1Thead thead = new CallableUtil1Thead();
        FutureTask<List<Integer>> result = new FutureTask<>(thead);
        new Thread(result).start();
        try{
            System.out.println("list:\n"+result.get());
            System.out.println("-----------");
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

}

class CallableUtil1Thead implements Callable<List<Integer>>{
    @Override
    public List<Integer> call() throws Exception {
        List longs = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            // 生产数据
            longs.add((Math.random() * 10000));
            // 模拟耗时1s
            TimeUnit.SECONDS.sleep(1);
        }
        return longs;
    }
}
