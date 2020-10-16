package cn.code.leet.leetcode;

import java.util.*;

public class Solution_336 {

    public List<List<Integer>> palindromePairs(String[] words) {
//    一次遍历找出这个字符串能和他构成回文的所有序列然后放到map里面去
//    可能一个会和多个字符产生匹配
//    2n 以及算回文序列的消耗
        Map<String, List<Integer>> map = new HashMap<>(words.length);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (String palindrome : getPalindrome(word)) {
                if(!map.containsKey(palindrome))
                    map.put(palindrome,new LinkedList<>());
                map.get(palindrome).add(i);
            }
        }
        for (int i = 0; i < words.length; i++) {
            if(map.containsKey(words[i])){
                for(int x:map.get(words[i])){
//                    value在前
                    result.add(Arrays.asList(x,i));
                }
            }
        }
        return result;
    }


    private List<String> getPalindrome(String s) {
//        找出这个字符串的所有回文序列
        List<String> result = new LinkedList<>();
//  abc 这种  cba 和ba bbbbbbcba
//        找不尽的
//        bbbb这种

        return result;
    }

}
