package cn.code.leet.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution_3 {

    public int lengthOfLongestSubstring(String s) {  //        滑动窗口往后滑 重要的是检查

        if (s.length() <= 1)
            return s.length();
        int maxLen = 1;
        int left = 0;
        int right = 1;
        Map<Character, Integer> subCharMap = new HashMap<>();
        subCharMap.put(s.charAt(left), left);
        while (right < s.length()) {
//          记录字符位置
            char rightChar = s.charAt(right);
            if (subCharMap.containsKey(rightChar) && subCharMap.get(rightChar) >= left) {
//                在map里面找到了这个字符 而且这个字符串在left的右边(等于的话就是刚好自己) 说明在当前的这个滑动窗口之中有重复的了
//                确认这个重复字符的位置  让左指针从这个字符的下一位开始滑动
//                右边的可以接着走  要把之前的字符清掉
                maxLen = Math.max(maxLen, right - left - 1);
                left = subCharMap.get(rightChar) + 1;
            } else {
                maxLen = Math.max(maxLen, right - left + 1);
            }
//          right指的这个在当前窗口中没有重复
//          把这个字符加到当前的窗口中
//          指针后移  最大长度加1
            subCharMap.put(s.charAt(right), right);
            right++;
        }
        return maxLen;
    }
}
