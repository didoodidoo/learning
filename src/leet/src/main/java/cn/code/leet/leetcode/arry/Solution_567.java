package cn.code.leet.leetcode.arry;

import cn.code.leet.util.ArrayUtil;
import org.junit.jupiter.api.Test;

/*567. 字符串的排列
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

示例1:

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").


示例2:

输入: s1= "ab" s2 = "eidboaoo"
输出: False


注意：

输入的字符串只包含小写字母
两个字符串的长度都在 [1, 10,000] 之间*/
public class Solution_567 {

    public boolean checkInclusion(String s1, String s2) {

        int[] nums1 = new int[26];
//        s1拥有的字符
        for (char c : s1.toCharArray()) {
            nums1[c - 'a']++;
        }
        //滑动窗口滑动就是了
        int[] nums2 = new int[26];
        int left = 0;
        int right = 0;

        while (right < s2.length()) {
            //长度不够直接往后走
            if (right - left < s1.length() - 1) {
                nums2[s2.charAt(right++) - 'a']++;
                continue;
            }
//          此时right还没有加进去
//            这时候长度相等了 判断序列是否相同，不同就左出右进
            nums2[s2.charAt(right++) - 'a']++;
            System.out.println(left + " " + right);
            if (arrEquals(nums1, nums2))
                return true;
            nums2[s2.charAt(left++) - 'a']--;
        }
        if (arrEquals(nums1, nums2))
            return true;
        return false;
    }

    private boolean arrEquals(int[] nums1, int[] nums2) {
        ArrayUtil.printArr(nums2);
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] != nums2[i])
                return false;
        }
        return true;
    }

    @Test
    public void test() {
        checkInclusion("ab", "eidbaooo");
    }
}
