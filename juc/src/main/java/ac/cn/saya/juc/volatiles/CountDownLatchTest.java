package ac.cn.saya.juc.volatiles;

import java.util.concurrent.CountDownLatch;

/**
 * @Title: CountDownLatchTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-04-21 15:31
 * @Description:闭锁
 * CountDownLatchUtilTest：闭锁，在完成某些运算时，只有其他所有的线程运损全部完成，当前运算在继续执行
 */

public class CountDownLatchTest {

    public static void main(String[] args){
        final CountDownLatch latch = new CountDownLatch(5);
        LatchDemo latchDemo = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++){
            new Thread(latchDemo).start();
        }
        try{
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间："+(end-start));
    }
}

class LatchDemo implements Runnable{

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        synchronized (this){
            try{
                for (int i = 0; i < 50000; i++){
                    if(i % 2 == 0){
                        System.out.println(i);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                // 指数扣减
                latch.countDown();
            }
        }
    }
}
