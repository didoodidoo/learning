package cn.code.leet.leetcode;

import cn.code.leet.util.ArrayUtil;
import org.junit.jupiter.api.Test;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 */
public class Solution_76 {
    public String minWindow(String s, String t) {
        int[] nums = new int[t.length()];
        int left = 0, right = 0;
        int min = Integer.MAX_VALUE;
        int resultLeft = 0;
        while (right < s.length()) {
//          存一下窗口里符合条件的字符串有多少了
            char c = s.charAt(right);
            int pos = t.indexOf(c);
            if (pos > -1) {
//                说明是符合条件的字符 给他放到记录的那个里面去
                nums[pos]++;
            }
            System.out.println(right);
            ArrayUtil.printArr(nums);
            right++;
            if (containsAll(nums)) {
                if(min>right-left){
                    min = right-left;
                    resultLeft = left;
                }
            }
//          一直移动到 left所指的字符有且只有一个的时候
//          这里重复检测了，傻逼代码
            while (needMoveLeft(s.charAt(left), t, nums)) {
                System.out.println("尝试移动左指针");
                int lPos = t.indexOf(s.charAt(left));
                if (lPos == -1){
                    left++;
                }
                else if(nums[lPos]>1){
                    nums[lPos]--;
                    left++;
                }
//                能进来说明至少还有一个 符合条件
                if(min>right-left){
                    min = right-left;
                    resultLeft = left;
                }
                System.out.println(left+"   "+min);
            }
        }

        if(min>s.length())
            return "";

        return s.substring(resultLeft, resultLeft+min);

    }

    private boolean containsAll(int[] nums) {
        for (int i : nums) {
            if (i == 0)
                return false;
        }
        return true;
    }

    private boolean needMoveLeft(char c, String t, int[] nums) {
        if(!containsAll(nums))
            return false;
//        left所指的字符有且只有一个的时候停下左移
        int pos = t.indexOf(c);
        if (pos == -1)
            return true;
        return nums[pos] > 1;
    }

    @Test
    public void test(){
        System.out.println(minWindow("ADOBECODEBANC","ABC"));
    }
}
