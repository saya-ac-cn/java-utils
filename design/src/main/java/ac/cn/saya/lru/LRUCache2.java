package ac.cn.saya.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: LRUCache2
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 4/4/21 22:24
 * @Description: LRU算法手写
 * 思路，不用LinkedHashMap。使用map和手写的一个双向链表实现，使用map是因为
 * 对于随机的访问时间复杂度为1，手写双向链表是保证有序，经常使用的数据，提升地位
 */

public class LRUCache2 {

    /**
     * 表示双向链表节点
     */
    class Node<K,V>{
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;

        /**
         * 构造初始化时，首尾为空
         */
        public Node() {
            this.prev = this.next = null;
        }

        public Node(K key, V value) {
            super();
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 表示一个双向的链表(新加入的节点在头部，活跃的节点在左边)
     * @param <K>
     * @param <V>
     */
    class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;

        public DoubleLinkedList() {
            this.head = new Node<K,V>();
            this.tail = new Node<K,V>();
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        public void addHead(Node<K,V> node){
            node.next = this.head.next;
            node.prev = this.head;
            this.head.next.prev = node;
            this.head.next = node;
        }

        public void removeNode(Node<K,V> node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }

        public Node<K,V> getLast(){
            if (this.tail.prev == this.head){
                return null;
            }
            return this.tail.prev;
        }

    }

    private int cacheSize;
    private Map<Integer,Node<Integer,Integer>> map;
    private DoubleLinkedList<Integer,Integer> linked;

    public LRUCache2(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new HashMap<>();
        linked = new DoubleLinkedList<>();
    }

    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        Node<Integer,Integer> node = map.get(key);
        // 提升一次权重
        // 删除一次旧值，然后重新放入
        linked.removeNode(node);
        linked.addHead(node);
        return node.key;
    }

    public void put(int key,int value){
        if (map.containsKey(key)){
            // 该key，已经存在，提升一次权重，删除一次，重新放入
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key,node);

            linked.removeNode(node);
            linked.addHead(node);
        }else{
            // 该值在之前不存在，执行一次添加，但添加前，需要判断容量，如果不够
            // 需要移除链表最后一个元素
            if (map.size() == cacheSize){
                Node<Integer, Integer> lastNode = linked.getLast();
                map.remove(lastNode.key);
                linked.removeNode(lastNode);
            }
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key,newNode);
            linked.addHead(newNode);
        }
    }

}
