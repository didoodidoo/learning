package cn.code.leet.leetcode;


import java.util.ArrayList;
import java.util.List;

/*
 *给你两个长度相同的字符串，s 和 t。

将 s中的第i个字符变到t中的第 i 个字符需要|s[i] - t[i]|的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。

用于变更字符串的最大预算是maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。

如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。

如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。

示例 1：

输入：s = "abcd", t = "bcdf", cost = 3
输出：3
解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
示例 2：

输入：s = "abcd", t = "cdef", cost = 3
输出：1
解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
示例 3：

输入：s = "abcd", t = "acde", cost = 0
输出：1
解释：你无法作出任何改动，所以最大长度为 1。

提示：

1 <= s.length, t.length <= 10^5
0 <= maxCost <= 10^6
s和t都只含小写英文字母。
 */
public class Solution_1208 {
    public int equalSubstring(String s, String t, int maxCost) {
        int maxLen = 0;
        int left = 0;
        int right = 0;
        int cost = 0;
        while (right < s.length()) {
            char sChar = s.charAt(right);
            char tChar = t.charAt(right);
//          如果想移动右指针左指针不动带来的消耗
            int num = Math.abs(sChar - tChar);
            cost += num;
            right++;
//          如果上面带来的消耗是承受不了的，就只能尝试移动左指针，把消耗减掉
//          是否要考虑左右指针相遇呢，否，如果左右指针相遇了，那么消耗为0
//          那么等于的情况呢，等于则消耗刚好，完美不用移动左指针，下一步的时候右指针移动自然会超出
            while (cost > maxCost) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            maxLen = Math.max(maxLen, right - left);
        }

        return maxLen;
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> sNums = new ArrayList<>(nums.length);
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            for(int j = 0;j<sNums.size();j++){
//                找到num在数组中的位置，可以用二分查找更快
                if(sNums.get(j)>num){
//                把他插入进去，他的位置就是比他大的数的个数,应该是长度减去他的位置
                    sNums.add(j,num);
                    result.set(i,j);
                }
            }
        }
        return result;
    }
}
