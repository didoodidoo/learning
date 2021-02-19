package cn.code.leet.leetcode.arry;

import java.util.PriorityQueue;

public class KthLargest {

    PriorityQueue<Integer> queue = new PriorityQueue<>();
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        queue.offer(val);
        if (queue.size() > k) {
            queue.poll();
        }
        return queue.peek();
    }

}
