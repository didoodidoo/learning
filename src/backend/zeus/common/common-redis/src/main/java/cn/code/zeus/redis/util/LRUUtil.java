package cn.code.zeus.redis.util;

import java.util.LinkedHashSet;
import java.util.Set;

public class LRUUtil {

    public Set<String> set = new LinkedHashSet<>();


    public LRUUtil update(String key){
//        get/set触发这个方法 更新一下
        set.remove(key);
        set.add(key);
        return this;
    }

    public void print(){
        System.out.println(set);
    }



    public static void main(String[] args) {
//        删除的时候 removeFirst
        new LRUUtil().update("a").update("b").update("c").update("a").print();
    }

}
