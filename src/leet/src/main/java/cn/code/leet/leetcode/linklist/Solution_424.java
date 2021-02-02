package cn.code.leet.leetcode.linklist;

/*
 * 424. 替换后的最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意：字符串长度 和 k 不会超过 104。
 *
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 */

import java.util.*;

public class Solution_424 {

//    public int characterReplacement(String s, int k) {
////      相当于找一个窗口内 有多少重复的char+k就能得到最大的值了
//        int len = s.length();
//        int left = 0, right = 0;
//        int result = 0;
//        int max = 0;
////      用来统计重复的字符
//        Map<Character, Integer> map = new HashMap<>();
//
////        窗口最小的应该就是k，先把右指针往后滑
//        while (right < len) {
//            char c = s.charAt(right);
////            对窗口内的数据做一些处理
//            if (!map.containsKey(c))
//                map.put(c, 1);
//            else
//                map.put(c, map.get(c) + 1);
//            right++;
//
////            如何判断左边是否需要收缩
////            窗口大小大于 最大重复字符+k
//            while ((right - left) > k + getMax(map)) {
//                char leftC = s.charAt(left);
//                left++;
//                map.put(leftC, map.get(leftC) - 1);
//            }
//            max = Math.max(max,right - left);
//        }
//        return max;
//    }
//
//    private int getMax(Map<Character, Integer> map) {
//        List<Map.Entry<Character, Integer>> nums = new ArrayList<>(map.entrySet());
//        nums.sort((o1, o2) -> o2.getValue() - o1.getValue());
//        return nums.get(0).getValue();
//    }

    public int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            int i = s.charAt(right) - 'A';
            num[i]++;
            maxn = Math.max(maxn, num[i]);
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }

}
