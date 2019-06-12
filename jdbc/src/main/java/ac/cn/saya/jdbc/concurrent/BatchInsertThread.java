package ac.cn.saya.jdbc.concurrent;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Title: BatchInsertThread
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-12 23:39
 * @Description:
 * 多线程批量添加
 */

public class BatchInsertThread implements Runnable{

    private List<String> list;
    private CountDownLatch begin;
    private CountDownLatch end;

    /**
     * @描述 创建个构造函数初始化 list,和其他用到的参数
     * @参数
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-12
     * @修改人和其它信息
     */
    public BatchInsertThread(List<String> list, CountDownLatch begin, CountDownLatch end) {
        this.list = list;
        this.begin = begin;
        this.end = end;
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
        try {
            for (int i = 0; i < list.size(); i++) {
                //这里还要说一下，，由于在实质项目中，当处理的数据存在等待超时和出错会使线程一直处于等待状态
                //这里只是处理简单的，
                //分批 批量插入
            }

            //执行完让线程直接进入等待
            begin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //这里要主要了，当一个线程执行完 了计数要减一不然这个线程会被一直挂起
            // ，end.countDown()，这个方法就是直接把计数器减一的
            end.countDown();
        }
    }
}
