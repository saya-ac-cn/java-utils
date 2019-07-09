package ac.cn.saya.principle.inversion;

/**
 * @Title: DependecyInversionUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-07 23:20
 * @Description:
 */

public class DependecyInversionUtil1 {

    public static void main(String [] args){
        Person person = new Person();
        person.receive(new Email());
    }

}

class Email {
    public String getInfo(){
        return "电子邮件消息：hello world";
    }
}

/**
 * 方式1分析
 * 简单、比较容易想到
 * 如果我们获取的对象是微信，短信等等，则新增类，同时Person也要增加相应的接收方法
 * 解决思路：引入一个抽象的接口IReceiver，表示接收者，这样Person类与接口IReceiver发送依赖
 * 因为Email，WeiXin等等属于接收范围，他们各自实现IReceiver接口
 */
class Person{
    public void receive(Email email){
        System.out.println(email.getInfo());
    }
}
