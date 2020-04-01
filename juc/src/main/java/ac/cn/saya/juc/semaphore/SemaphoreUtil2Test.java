package ac.cn.saya.juc.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Title: SemaphoreUtil2Test
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-10 22:44
 * @Description:
 * Semaphore是一个计数信号量。
 * 从概念上将，Semaphore包含一组许可证。
 * 每个acquire()方法都会阻塞，直到获取一个可用的许可证。
 * 每个release()方法都会释放持有许可证的线程，并且归还Semaphore一个可用的许可证。
 * 然而，实际上并没有真实的许可证对象供线程使用，Semaphore只是对可用的数量进行管理维护。
 *
 * 假若一个银行有5位服务人员，但是有8位顾客，同一时间1位服务人员只能给1位顾客办理业务，只有办理完毕后，其他顾客才能办理。那么我们就可以通过Semaphore来实现：
 */

public class SemaphoreUtil2Test {

    public static void main(String[] args) {
        // 顾客
        int num = 8;
        // 客服数
        Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i <= num; i++){
            new SemaphoreBank(i,semaphore).start();
        }
    }

    static class SemaphoreBank extends Thread{
        private int num;
        private Semaphore semaphore;

        public SemaphoreBank(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run(){
            try {
                semaphore.acquire();
                System.out.println("第"+num+"位顾客,开始办理业务....");
                Thread.sleep(2000);
                System.out.println("第"+num+"位顾客,办理业务完毕....");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}