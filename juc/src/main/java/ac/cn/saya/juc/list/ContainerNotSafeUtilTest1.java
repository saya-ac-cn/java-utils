package ac.cn.saya.juc.list;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Title: ContainerNotSafeUtilTest1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-06 21:27
 * @Description: list线程不安全举例
 */

public class ContainerNotSafeUtilTest1 {

    public static void main(String[] args) {
        //Map<String,String> list = new HashMap<>();
        //Map<String,String> list = Collections.synchronizedMap(new HashMap<>());
        Map<Integer,Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 11; i++) {
            map.put(i,i);
        }
        map.put(11,11);
        map.put(12,12);
//        for (int i = 0; i < 30; i++) {
//            new Thread(() -> {
//                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
//            }).start();
//        }
    }



    public static void setNotSafe(String[] args) {
        Set<String> list = new HashSet<String>();
        //Set<String> list = Collections.synchronizedSet(new HashSet<String>());
        //Set<String> list = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();
        }
    }

    public static void listNotSafe(String[] args) {
        List<String> list = new ArrayList<String>();
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<String>());
        // List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();
        }
    }

    /**
     * 故障现象：java.util.ConcurrentModificationException
     *
     * 导致原因：并发争抢修改导致，参考上课签到情况
     * 一个人正在签名，另一个过来争抢，导致数据不一致异常，并发修改异常
     *
     * 解决方案：
     * new Vector<>();
     * Collections.synchronizedList(new ArrayList<String>());
     * new CopyOnWriteArrayList<>(); 写时复制
     */
    /**
     *写时复制
     * CopyOnWrite容器即写时复制容器，往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy
     * 复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完成之后
     * 再将原来的容器的引用指向新的容器setArray(newElements)；这样做的好处是可以对CopyOnWrite容器进行并发的读
     * 而不需要加锁，因为当前容器不会添加任何元素，所以CopyOnWrite容器也是一个读写分离的思想，读和写不同的容器
     *     public boolean add(E e) {
     *         final ReentrantLock lock = this.lock;
     *         lock.lock();
     *         try {
     *             Object[] elements = getArray();
     *             int len = elements.length;
     *             Object[] newElements = Arrays.copyOf(elements, len + 1);
     *             newElements[len] = e;
     *             setArray(newElements);
     *             return true;
     *         } finally {
     *             lock.unlock();
     *         }
     *     }
     */

}
