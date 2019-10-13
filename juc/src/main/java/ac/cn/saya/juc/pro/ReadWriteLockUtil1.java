package ac.cn.saya.juc.pro;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Title: ReadWriteLockUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-13 17:12
 * @Description:
 * ReadWriteLock 读写锁
 */

class ReadWriteLockUtilCache{
    private volatile Map chache = new HashMap<String,Object>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void writr(String key,Object value){
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"写入数据："+value);
            TimeUnit.SECONDS.sleep(1);
            chache.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void read(String key){
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"读取数据");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"读取完成："+chache.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

}

public class ReadWriteLockUtil1 {

    public static void main(String[] args) {
        ReadWriteLockUtilCache cache = new ReadWriteLockUtilCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                cache.writr(Thread.currentThread().getName(), tempInt);
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                cache.read(Thread.currentThread().getName());
            },String.valueOf(i)).start();
        }
    }

}
