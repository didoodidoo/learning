package cn.code.leet.leetcode.stack;


//下一个更大元素

import java.util.*;

/*给你两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。

请你找出 nums1中每个元素在nums2中的下一个比其大的值。

nums1中数字x的下一个更大元素是指nums2中对应位置的右边的第一个比大的元素。如果不存在，对应位置输出 -1 。
。*/
public class Solution_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //把比它高的都先入栈了，
        int[] result = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int j : nums2) {
            while (!stack.isEmpty() && j > stack.peek()) {
                map.put(stack.pop(), j);
            }
            stack.push(j);
        }
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }

    public int[] dailyTemperatures(int[] T) {
        //  气温 标准单调栈 栈里存i

        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<T.length;i++){
            while(!stack.isEmpty()&&T[stack.peek()]<T[i]){
                int pos = stack.peek();
                result[pos] = i-stack.pop();
            }
            stack.push(i);
        }
        return result;
    }

}
