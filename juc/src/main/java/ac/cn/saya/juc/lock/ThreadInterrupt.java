package ac.cn.saya.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 线程终端示例
 * @Title: ThreadInterrupt
 * @ProjectName java-utils
 * @Author saya
 * @Date: 2021/12/4 15:29
 * @Description: TODO
 */

public class ThreadInterrupt {

    public static volatile boolean isStop = false;

    public static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void method1(){
        new Thread(()->{
            while (true){
                if (isStop){
                    System.err.println("线程结束运行");
                    break;
                }
                System.out.println("线程正在运行");
            }
        },"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            isStop = true;
        },"t2").start();
    }

    public static void method2(){
        new Thread(()->{
            while (true){
                if (atomicBoolean.get()){
                    System.err.println("线程结束运行");
                    break;
                }
                System.out.println("线程正在运行");
            }
        },"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
             atomicBoolean.set(true);
        },"t2").start();
    }

    public static void method3(){
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.err.println("线程结束运行");
                    break;
                }
                System.out.println("线程正在运行");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            // 在sleep，wait，join中调用将抛出异常
            t1.interrupt();// 设置中断的标志位，仅此而已，不会引起线程的中断
        },"t2").start();
    }

    public static void check1(){
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.err.println("线程结束运行");
                    break;
                }
                System.out.println("线程正在运行");
            }
        }, "t1");
        t1.start();
        System.out.println("**********************"+t1.isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println("**********************"+t1.isInterrupted());
    }

    public static void check2(){
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.err.println("isInterrupted()=true");
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // 正在sleep的线程调用interrupt后将抛出异常，中断状态被还原。想要完全中断，还需要再次中断
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("线程正在运行");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            // 在sleep，wait，join中调用将抛出异常，中断的标志位将被清空，还原成false
            t1.interrupt();// 设置中断的标志位，仅此而已，不会引起线程的中断
        },"t2").start();
    }

    public static void main(String[] args) {
        check2();
    }

}
