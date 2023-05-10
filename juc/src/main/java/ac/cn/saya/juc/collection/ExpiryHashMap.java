package ac.cn.saya.juc.collection;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 带过期时间的Map
 *
 * @Title: ExpiryHashMap
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2022/6/5 22:28
 * @Description:
 */

public class ExpiryHashMap<K, V> extends ConcurrentHashMap<K, V> {

    /**
     * 记录每个entity的到期时间
     */
    private ConcurrentHashMap<K, LocalDateTime> expireMap = new ConcurrentHashMap<K, LocalDateTime>(32);

    /**
     * 判断entity是否到期
     *
     * @param key key
     * @return 过期为true
     */
    private boolean isExpire(K key) {
        if (super.get(key) == null) {
            return true;
        }
        LocalDateTime expireDateTime = this.expireMap.get(key);
        boolean flag = LocalDateTime.now().isAfter(expireDateTime);
        if (flag) {
            // 过期的数据及时清理掉
            this.expireMap.remove(key);
        }
        return flag;
    }

    /**
     * 存
     *
     * @param key key
     * @param val value
     * @param ttl 有效期（单位：秒）
     * @return
     */
    public V put(K key, V val, Long ttl) {
        this.expireMap.put(key, LocalDateTime.now().plusSeconds(ttl));
        return super.put(key, val);
    }

    /**
     * 取
     *
     * @param key key
     * @return value
     */
    @Override
    public V get(Object key) {
        K k = (K) key;
        if (isExpire(k)) {
            return null;
        }
        return super.get(k);
    }

    @Override
    public V remove(Object key) {
        K k = (K) key;
        if (this.expireMap.containsKey(key)) {
            this.expireMap.remove(key);
        }
        return super.remove(key);
    }

    @Override
    public boolean containsKey(Object key) {
        K k = (K) key;
        if (isExpire(k)) {
            return false;
        }
        return super.containsKey(key);
    }

}