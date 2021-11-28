package ac.cn.saya.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title: LRUCache1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 4/4/21 22:54
 * @Description:借助LinkedHashMap实现LRU算法
 */

public class LRUCache1 {

    private LinkedHashMap<Integer,Integer> cache;

    public LRUCache1(int capacity) {
        new LinkedHashMap<Integer,Integer>(capacity,0.75f,true){

            private static final long serialVersionUID = 2042715170465078553L;

            /**
             * 重写移除方法，只要超出指定的负载，即删除
             * @param eldest
             * @return
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key){
        return cache.getOrDefault(key,-1);
    }

    public void put(int key,int value){
        cache.put(key,value);
    }

}
