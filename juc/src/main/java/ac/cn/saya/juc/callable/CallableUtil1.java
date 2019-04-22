package ac.cn.saya.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Title: CallableUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-21 15:56
 * @Description:
 * 一、创建线程的方式三：实现Callable接口，相较于实现Runnable接口的方式，方法可以有返回值，可以抛出异常
 *
 * 二、执行Callable方式，需要FutureTask需要类的支持，用于接收运算的结果。FutureTask是Future接口的实现类
 */

public class CallableUtil1 {

    public static void main(String[] args){
        CallableThread callableThread = new CallableThread();
        // 1、执行Callable方式，需要有FutureTask实现类的支持，用于接收运算结果
        FutureTask<Integer> result = new FutureTask<>(callableThread);
        new Thread(result).start();
        // 2、接收运算结果
        try{
            Integer sum = result.get();
            System.out.println("-----------");
            System.out.println(sum);
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
}

class CallableThread implements Callable<Integer>{
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++){
            sum += i;
        }
        return sum;
    }
}
