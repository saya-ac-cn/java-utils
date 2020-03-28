package ac.cn.saya.juc.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: LoopPrintUtil
 * @ProjectName spring-boot-webflux
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/3/10 0010 12:11
 * @Description:循环打印abc 10次 3根 线程
 */

public class LoopPrintUnit {

    public static volatile int flog = 0;

    public static void main(String[] args) {
        LoopPrintContidion loopPrintContidion = new LoopPrintContidion();
        new Thread(()->{
            for (int i = 0; i < 10; i++){
                loopPrintContidion.printA();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++){
                loopPrintContidion.printB();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++){
                loopPrintContidion.printC();
            }
        }).start();

    }

    public static void main1(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            while (flog <= 30){
                lock.lock();
                while (flog%3==0){
                    System.out.println("A");
                    flog++;
                }
                lock.unlock();
            }
        }).start();
        new Thread(()->{
            while (flog <= 30){
                lock.lock();
                while (flog%3==1){
                    System.out.println("B");
                    flog++;
                }
                lock.unlock();
            }
        }).start();
        new Thread(()->{
            while (flog <= 30){
                lock.lock();
                while (flog%3==2){
                    System.out.println("C");
                    flog++;
                }
                lock.unlock();
            }
        }).start();
    }

}

class LoopPrintContidion{
    private static volatile String flog = "A";

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA(){
        try {
            lock.lock();
            if (!"A".equals(flog)){
                conditionA.await();
            }
            for (int i = 0; i < 1; i++) {
                System.out.println("A");
            }
            flog = "B";
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        try {
            lock.lock();
            if (!"B".equals(flog)){
                conditionB.await();
            }
            for (int i = 0; i < 1; i++) {
                System.out.println("B");
            }
            flog = "C";
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        try {
            lock.lock();
            if (!"C".equals(flog)){
                conditionC.await();
            }
            for (int i = 0; i < 1; i++) {
                System.out.println("C");
            }
            flog = "A";
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}