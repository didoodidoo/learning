package cn.code.leet.util;

//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class LRUCache extends LinkedHashMap<Integer, Integer> {
//    private final int capacity;
//
//    public LRUCache(int capacity) {
//        super(capacity, 0.75F, true);
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        return super.getOrDefault(key, -1);
//    }
//
//    public void put(int key, int value) {
//        super.put(key, value);
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//        return size() > capacity;
//    }
//}


import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {
    //    双端队列+map
    int capacity;
    Deque<Integer> queue = new LinkedList<>();
    Map<Integer, Integer> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
//            移动到队首
            queue.remove(key);
            queue.offer(key);
        }
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        queue.offer(key);
        map.put(key, value);
        if (queue.size() > capacity) {
            Integer old = queue.pollFirst();
            map.remove(old);
        }
    }

}