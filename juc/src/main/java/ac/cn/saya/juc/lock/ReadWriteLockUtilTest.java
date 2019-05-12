package ac.cn.saya.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Title: ReadWriteLockUtilTest
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-12 16:36
 * @Description:
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是，如果有一个线程想去写共享资源来，就不应该在有其它线程可以对该资源进行读或写
 * 总结：
 * 读-读 不互斥
 * 读-写 互斥
 * 写-写 互斥
 */

class MyCache{

    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key,int value){
        try {
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"\t 正在写入：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成" );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key){
        try {
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"\t 正在读取" );
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result= map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成："+result );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockUtilTest {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for(int i = 1 ; i <= 5 ; i++){
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"",tempInt);
            },String.valueOf(tempInt)).start();
        }
        for(int i = 1 ; i <= 5 ; i++){
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            },String.valueOf(tempInt)).start();
        }
    }
}
