package cn.code.leet.leetcode.compettion;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

public class Solution_0306 {

    public int nearestValidPoint(int x, int y, int[][] points) {
        int result = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == x || points[i][1] == y) {
                int d = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
                if (d < min) {
                    min = d;
                    result = i;
                }
            }
        }
        return result;
    }


    public boolean checkPowersOfThree(int n) {
        int[] nums = new int[15];
        nums[0] = 1;
        nums[1] = 3;
        for (int i = 2; i < nums.length; i++) {
            nums[i] = nums[i - 1] * 3;
        }
        int pos = 14;
        while (pos >= 0) {
            if (n > nums[pos]) {
                n -= nums[pos];
            } else if (n == nums[pos]) {
                return true;
            }
            pos--;
        }
        return false;
    }

    //出现频率最高字符与出现频率最低字符
    public int beautySum(String s) {
        int sum = 0;
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(nums, 0);
            nums[s.charAt(i) - 'a']++;
            for (int j = i + 1; j < s.length(); j++) {
                nums[s.charAt(j)]++;
                sum += getSub(nums);
            }
        }
        return sum;
    }

    private int getSub(int[] nums) {
        //最大值和最小值的差值
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int j : nums) {
            if (j > max)
                max = j;
            if (j != 0 && j < min)
                min = j;
        }
        if(max==0)
            return 0;
        return max - min;
    }


}
