package ac.cn.saya.juc.lock;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Title: AtomicStampedReferenceUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-27 21:37
 * @Description:带有时间戳的引用
 * 案例：
 * 有一家蛋糕店，为了挽留客户，决定在贵宾卡里余额小于20元的客户一次性赠送20元，刺激消费者充值和消费，但每位客户只能被赠送1次
 */

public class AtomicStampedReferenceUtilTest {

    public static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19,0);

    public static void main(String[] args){
        // 模拟多个线程同时更新后台数据库，为用户充值
        for(int i=0; i < 3; i++){
            // 取出版本号
            final int timestamp = money.getStamp();
            new Thread(() -> {
                while (true){
                    Integer m = money.getReference();
                    if(m < 20){
                        if(money.compareAndSet(m,m+20,timestamp,timestamp+1)){
                            System.out.println("余额小于20元，充值成功，余额："+money.getReference()+"元");
                            break;
                        }
                    }else{
                        break;
                    }
                }
            }).start();
        }

        // 用户消费线程，模拟消费行为
        new Thread(() -> {
            for (int i = 0; i < 100; i++){
                while (true){
                    int timestamp = money.getStamp();
                    Integer m = money.getReference();
                    if(m > 10){
                        System.out.println("余额大于10元。");
                        if(money.compareAndSet(m,m-10,timestamp,timestamp+1)){
                            System.out.println("成功消费10元，余额："+money.getReference()+"元");
                            break;
                        }
                    }else{
                        System.out.println("穷逼，没钱啦。");
                        break;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
