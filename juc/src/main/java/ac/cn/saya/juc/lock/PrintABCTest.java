package ac.cn.saya.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: PrintABCTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-21 17:37
 * @Description:
 * 编写一个程序，开启3个线程，这3个线程的ID分别为A B C ，每个线程将自己的ID在屏幕上打印10遍，要求输出的结果必须按顺序显示
 * 如：ABCABC
 */

public class PrintABCTest {
    public static void main(String[] args){
        PrintABC printABC = new PrintABC();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++){
                    printABC.loopA(i);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++){
                    printABC.loopB(i);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++){
                    printABC.loopC(i);
                }
            }
        },"C").start();
    }
}

class PrintABC{
    private String number = "A";
    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    // 打印A
    public void loopA(int totalLoop){
        lock.lock();
        try{
            // 1、判断
            if(!number.equals("A")){
                conditionA.await();
            }
            // 2、打印
            for (int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+ i + "\t" + totalLoop);
            }
            // 3、通知唤醒,让B打印
            number = "B";
            conditionB.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    // 打印B
    public void loopB(int totalLoop){
        lock.lock();
        try{
            // 1、判断
            if(!number.equals("B")){
                conditionB.await();
            }
            // 2、打印
            for (int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+ i + "\t" + totalLoop);
            }
            // 3、唤醒,让C打印
            number = "C";
            conditionC.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    // 打印C
    public void loopC(int totalLoop){
        lock.lock();
        try{
            // 1、判断
            if(!number.equals("C")){
                conditionC.await();
            }
            // 2、打印
            for (int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+ i + "\t" + totalLoop);
            }
            System.out.println("--------");
            // 3、唤醒,让A打印
            number = "A";
            conditionA.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}