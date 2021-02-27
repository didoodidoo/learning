package cn.code.leet.leetcode.arry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 395. 至少有 K 个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * 示例 1：
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
public class Solution_395 {

    public int longestSubstring(String s, int k) {
        if (k == 1)
            return s.length();
//        统计出现次数小于K的字符 分治剪枝
        if (s.length() <= 1)
            return 0;
        return getLongestSubstring(0, s.length() - 1, s, k);
    }

    //    先不剪枝
    public int getLongestSubstring(int start, int end, String s, int k) {
        // System.out.println(start+" : "+end);
        if(end - start + 1<k)
            return 0;
        //每次set都是要重算的啊
        int[] count = new int[26];
        for (int i = start;i<=end;i++) {
            count[s.charAt(i) - 'a']++;
        }

        boolean success = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && count[i] < k) {
                success = false;
                break;
            }
        }
        if (success)
            return end - start + 1;
//        需要分隔字符串了 这里可以分成n个字符串对比 相当于剪枝了 直接分两个 先c了再说
        int sp = start;
        List<Integer> spPos = new ArrayList<>();
        while (sp <= end) {
            int pos = s.charAt(sp) - 'a';
            if (count[pos] > 0 && count[pos] < k) {
                spPos.add(sp++);
            } else {
                sp++;
            }
        }

        if(spPos.isEmpty())
            return 0;
        int max = getLongestSubstring(start, spPos.get(0) - 1, s, k);
        for (int i = 1; i <=spPos.size(); i++) {
            if(i==spPos.size())
                max = max = Math.max(max, getLongestSubstring(spPos.get(i - 1) + 1, end, s, k));
            else
                max = Math.max(max, getLongestSubstring(spPos.get(i - 1) + 1, spPos.get(i) - 1, s, k));
        }
        return max;
    }
}
