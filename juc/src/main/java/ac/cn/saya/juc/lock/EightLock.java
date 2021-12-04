package ac.cn.saya.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Title: EightLock
 * @ProjectName java-utils
 * @Author saya
 * @Date: 2021/11/28 22:33
 * @Description: TODO
 * 线程8锁案例
 * 1、标准访问a和b两个方法，请问是先打印邮件还是短信
 * 2、sendEmail方法暂停3秒钟，请问是先打印邮件还是短信
 * 3、新增一个普通hello方法，请问是先打印邮件还是hello
 * 4、有两部手机，请问是先打印邮件还是短信
 * 5、两个静态同步方法，同一部手机，请问是先打印邮件还是短信
 * 6、两个静态同步方法，两部手机，请问是先打印邮件还是短信
 * 7、1个静态方法、1个普通同步方法，同一部手机，请问是先打印邮件还是短信
 * 8、1个静态方法、1个普通同步方法，两部手机，请问是先打印邮件还是短信
 *
 * 总结
 * 1-2
 * 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法，其它的线程都只能
 * 等待，换句话说，某一个时刻内，只能有唯一的一个线程去访问这些synchronized方法。
 * synchronized锁的方法时当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法。
 * 3-4
 * 加个普通方法后，发现和同步锁无关
 * 换成两个对象后，不是统一把锁了，情况立刻变化
 * 5-6
 * synchronized对于普通同步方法，锁的是当前实例对象，通常指this，具体的一部部手机，所有的普通同步方法用的都是同一把锁-实例对象本身
 * 对于静态同步方法，锁的是当前类的class对象。如EightLockPhone唯一的一个模板
 * 对于同步方法块，锁的是synchronized括号内的对象
 * 7-8
 * 当一个线程视图访问同步代码时它首先必须得到锁，退出或抛出异常时必须释放锁
 * 所有的普通同步方法用的都是同一把锁-实例对象本身，就是new出来的具体实例对象本身，本类this
 * 也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其它普通同步方法必须等待获取锁的方法释放锁后才能获得锁
 * 所有的静态同步方法用的是也是同一把锁-类对象本身，就是我们说过的唯一模板class
 * 具体实例对象this和唯一模板class，这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞态条件的
 * 但是一旦一个静态同步方法获取锁后，其它的静态同步方法都必须等待该方法释放锁后才能获得锁
 */

class EightLockPhone{

    public synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------------sendEmail");
    }

    public synchronized void sendSMS(){
        System.out.println("-------------sendSMS");
    }

    public void hello(){
        System.out.println("-------------hello");
    }

}

public class EightLock {

    public static void main(String[] args) {
        // 资源类
        EightLockPhone phone1 = new EightLockPhone();
        EightLockPhone phone2 = new EightLockPhone();
        new Thread(()->{
            phone1.sendEmail();
        },"A").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            phone1.sendSMS();
            // phone.hello();
        },"B").start();
    }

}
