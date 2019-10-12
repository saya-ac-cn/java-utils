package ac.cn.saya.juc.pro;

import java.util.concurrent.TimeUnit;

/**
 * @Title: Lock8
 * @ProjectName juc
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/10/12 0012 09:11
 * @Description:
 * 线程8锁
 * 1、标准访问，请问是先打印邮件哈还是短信
 * 2、邮件方法暂停4秒，请问先打印邮件还是短信
 * 3、新增一个普通方法hello，请问是先打印邮件还是短信
 * 4、两部手机、请问先打印邮件还是短信
 * 5、两个静态同步方法，同一部手机，请问先打印邮件还是短信
 * 6、两个静态同步方法，两部手机，请问先打印邮件还是短信
 * 7、1个普通同步方法，1个静态同步方法，一部手机，请问先打印邮件还是短信
 * 8、1个普通同步方法，1个静态同步方法，两部手机，请问先打印邮件还是短信
 *
 * 笔记：
 * 都换成静态同步方法后，情况又变化
 * new this，具体的一部手机
 * 静态 class，唯一的一个模板
 *
 * 所有的非静态同步方法用的都是同一把锁-对象实例锁
 * synchronized实现同步的基础：java找那个的每一个对象都可以作为锁
 * 具体表现为以下的3种形式；
 * 作用于普通同步方法，锁的是当前实例对象
 * 作用于静态同步方法，锁的是当前实现类的Class对象
 * 作用于同步代码块，锁的是synchronized括号里的配置对象
 *
 * 当一个线程视图访问同步代码块时，它首先必须获得锁，退出或抛出异常时必须释放锁。
 * 也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其它非静态同步方法必须等待获取锁的方法释放锁后才能获取锁。
 * 可是锁的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法的是不同的锁。
 * 所以无须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取它们自己的锁
 *
 * 所有的非静态同步方法用的也是同一把锁-类对象本身，
 * 这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞争条件的
 * 但是一旦一个静态同步方法获取锁后，其它的静态同步方法都必须等待该方法释放锁后才能获取锁，
 * 而不管是同一个实例对象的静态同步方法之间，还是不同的实例对象的静态同步方法之间，只要它们是同一个类的实例对象
 */

class Phone{

    public static synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------Email---------");
    }

    public synchronized void sendSMS(){
        System.out.println("---------SMS--------------");
    }

    public void hello(){
        System.out.println("-----------hello-----------------");
    }

}

public class Lock8 {

    public static void main(String[] args) throws InterruptedException{
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{phone1.sendEmail();},"A").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(()->{
            //phone1.sendSMS();
            //phone1.hello();
            phone2.sendSMS();
            },"B").start();
    }

}
