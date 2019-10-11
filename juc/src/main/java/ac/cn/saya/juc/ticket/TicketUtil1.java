package ac.cn.saya.juc.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: TicketUtil1
 * @ProjectName juc
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/10/11 0011 16:48
 * @Description:
 * 买票
 */

public class TicketUtil1 {

    public static void main(String[] args) {
        TicketUtil1Source source = new TicketUtil1Source();
        // lambda 口诀：拷贝小括号，写死右箭头，落地大括号
        new Thread(()->{
            for (int i = 0; i < 40; i++){
                source.saleTicket();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++){
                source.saleTicket();
            }
        },"B").start();
    }

}

/**
 * @描述 资源类
 * @参数
 * @返回值
 * @创建人  saya.ac.cn-刘能凯
 * @创建时间  2019/10/11 0011
 * @修改人和其它信息
 */
class TicketUtil1Source{
    private int number = 30;
    Lock lock = new ReentrantLock();

    public void saleTicket(){
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+",还剩："+number);
            }
        } finally {
            lock.unlock();
        }
    }

}
