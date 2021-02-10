package cn.code.leet.leetcode.arry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * <p>
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * <p>
 * 返回 A 中好子数组的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 * <p>
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_992 {
    public int subarraysWithKDistinct(int[] A, int K) {


        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
//        维护一个map，里面记录元素以及个数
//        一直移动右指针直到不符合条件
//        不符合条件时一直移动左指针直到符合条件
        int left = 0, right = 0;
        int len = A.length;
        while (right < len) {
            int num = A[right++];
            if (!map.containsKey(num))
                map.put(num, 1);
            else
                map.put(num, map.get(num) + 1);
            while (map.size() == K) {
                sum++;
                System.out.println("left is " + left + " right is " + (right - 1) + "sum is " + sum);
//先尝试右移看看符不符合条件
                if (right < len && map.containsKey(A[right])) {
                    map.put(A[right], map.get(A[right]) + 1);
                    right++;
                } else {
                    //              移动之后不合适了
//                    int l = A[left++];
//                    if (map.get(l) == 1) {
//                        map.remove(l);
//                    } else {
//                        map.put(l, map.get(l) - 1);
//                    }

//                    产生了一个子数组 要把这个子数组里面符合条件都加进来

                }

            }
        }
        return sum;
    }

    public int  sub(int nums,int left,int right){
        return 0;
    }
}
