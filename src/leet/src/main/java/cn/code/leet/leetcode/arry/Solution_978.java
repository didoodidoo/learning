package cn.code.leet.leetcode.arry;

/*
 *
 * 当 A的子数组A[i], A[i+1], ..., A[j]满足下列条件时，我们称其为湍流子数组：

若i <= k < j，当 k为奇数时，A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
或 若i <= k < j，当 k 为偶数时，A[k] > A[k+1]，且当 k为奇数时，A[k] < A[k+1]。
也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。

返回 A 的最大湍流子数组的长度。

示例 1：
输入：[9,4,2,10,7,8,8,1,9]
输出：5
解释：(A[1] > A[2] < A[3] > A[4] < A[5])
示例 2：

输入：[4,8,12,16]
输出：2
示例 3：

输入：[100]
输出：1

提示：

1 <= A.length <= 40000
0 <= A[i] <= 10^9
 */
public class Solution_978 {

    public int maxTurbulenceSize(int[] arr) {

        int len = arr.length;
        if (len <= 1)
            return len;

        int left = 0;
        int right = 1;
//      不管怎么样，最短的肯定是 1
        int maxLen = 1;
//      记录当前子数组的长度
        int wLen = 1;
        boolean increase = false;
//      数组基调 是递增还是递减 增的话则下一个数需要递减，减的话，下个数需要递增
        while (right < len) {
            if (wLen == 1) {
                //相当于初值 相等则接着往后走
                if (arr[left] == arr[right]) {
                    left++;
                    right++;
                    wLen = 1;
                } else {
//                    有可能是 left为0 所以这一段要单独拿出来做判断
                    increase = arr[right] > arr[left];
                    left++;
                    right++;
                    wLen++;
                    maxLen = Math.max(maxLen, wLen);
                }
            } else {
//            走到这里说明双指针已经往前走了一段时间了 有了一定的长度了
                if (increase) {
//                    前面是递增的 则现在要递减
                    if (arr[right] < arr[left]) {
//                        符合要求
                        wLen++;
                        maxLen = Math.max(maxLen, wLen);
                        right++;
                        left++;
                        increase = false;
                    } else {
//                      不符合条件了 重置序列 再算一次
                        wLen = 1;
                    }

                } else {
//                    前面是递减的 现在要递增
                    if (arr[right] > arr[left]) {
//                        符合要求
                        wLen++;
                        maxLen = Math.max(maxLen, wLen);
                        right++;
                        left++;
                        increase = true;
                    } else {
//                      不符合条件了 重置序列
                        wLen = 1;
                    }
                }
            }
            // System.out.println("现在左指针是"+ left+" 右指针是"+right+" 窗口长度为"+wLen);
        }
        return maxLen;
    }
}
