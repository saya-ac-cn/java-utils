package ac.cn.saya.juc.pro;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: UnSafeUtil1
 * @ProjectName juc
 * @Description: TODO
 * @Author Administrator
 * @Date: 2019/10/12 0012 10:12
 * @Description:
 * 集合不安全实例
 * 1、故障现象
 *  java.util.ConcurrentModificationException（并发修改异常）
 * 2、导致原因
 *
 * 3、解决方案
 *      1）Vector()
 *      2）Collectins.synchronizedList(new ArrayList<>())
 *      3）CopyOnWriteArrayList()
 * 4、优化建议
 *
 * 笔记：
 * 写时复制：
 * CopyOnWrite容器即写时复制的容器，往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，
 * 复制出一个新的容器Object[] newElements,然后往新的容器Object[] newElements里添加元素，添加完元素后，
 * 再将原容器的引用指向新的容器setArray(newElements);这样做的好处是可以对CopyOnWrite容器进行并发的读，
 * 而不需要加锁，因为当前容器不会添加元素，所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
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

public class UnSafeUtil1 {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
                        ///Collections.synchronizedMap(new HashMap<>());
                        //ConcurrentHashMap(); //安全
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setUnSafe() {
        // HashSet底层是HashMap,只不过set的值是存放在map的key中，map的value存的是默认PRESENT
        Set<String> set = new HashSet<>();
                            // Collectins.synchronizedSet(new HashSet<>()); //安全
                            // CopyOnWriteArraySet();//安全，读写分离的思想
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listUnSafe() {
        List<String> list = new ArrayList<>();
                            // new Vector<>(); //安全，但不建议
                            // Collectins.synchronizedList(new ArrayList<>()); //安全，但效率低
                            // CopyOnWriteArrayList();//安全，读写分离的思想
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

}
