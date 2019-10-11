package ac.cn.saya.juc.condition;

/**
 * @Title: ThreadWaitNotifyUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-11 22:04
 * @Description:
 * 线程虚假唤醒
 * 题目：两个线程，可以操作初始值为零的一个变量
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 交替10轮，变量的初始值为零
 *
 * 高耦合，低内聚下，线程操作资源类
 * 判断、干活、通知
 * 多线程交互中，必须要防止虚假唤醒，即（判断只用while，不用if）
 */
class ThreadWaitNotifyUtil1Condition{
    private int number = 0;

    public synchronized  void increment() throws InterruptedException{
        // 判断，为了防止虚假唤醒，不能用if
        while (number != 0){
            this.wait();
        }
        // 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 通知
        this.notifyAll();
    }

    public synchronized  void decrement() throws InterruptedException{
        // 判断，为了防止虚假唤醒，不能用if
        while (number == 0){
            this.wait();
        }
        // 干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 通知
        this.notifyAll();
    }

}
public class ThreadWaitNotifyUtil1 {

    public static void main(String[] args) throws InterruptedException{
        ThreadWaitNotifyUtil1Condition condition = new ThreadWaitNotifyUtil1Condition();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    condition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    condition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    condition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    condition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}
