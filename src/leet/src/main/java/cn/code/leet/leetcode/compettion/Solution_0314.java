package cn.code.leet.leetcode.compettion;

import java.util.*;

public class Solution_0314 {

    //一次交换两数相等 需要count<=2
    public boolean areAlmostEqual(String s1, String s2) {
        int count = 0;//不同的字符个数
        int pos1 = -1;
        int pos2 = -1;
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                count++;
                if (count >= 3) return false;
                if (count == 1)
                    pos1 = i;
                else
                    pos2 = i;
            }
        }
        System.out.println(pos1 + " " + pos2);
        if (count == 0) return true;
        if (count == 1) return false;
        char c1 = s1.charAt(pos1);
        char c2 = s1.charAt(pos2);
        return c2 == s2.charAt(pos1) && c1 == s2.charAt(pos2);
    }

    //和其他的都相连
    public int findCenter(int[][] edges) {
        int max = Integer.MIN_VALUE;
        int result = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int v1 = map.getOrDefault(edges[i][0], 0) + 1;
            int v2 = map.getOrDefault(edges[i][1], 0) + 1;
            map.put(edges[i][0], v1);
            map.put(edges[i][1], v2);
            if (v1 > max) {
                result = edges[i][0];
                max = v1;
            }
            if (v2 > max) {
                result = edges[i][1];
                max = v2;
            }
        }
        return result;
    }


    public double maxAverageRatio(int[][] classes, int extraStudents) {
        double[] ration = new double[classes.length];
        for (int i = 0; i < classes.length; i++) {
            ration[i] = (classes[i][0] * 1.0) / (classes[i][1] * 1.0);
        }
        for (int i = 0; i < extraStudents; i++) {
            //计算每个学生最大的价值
            double max = -1;
            int clzz = -1;
            for (int j = 0; j < classes.length; j++) {
                double r2 = (classes[j][0] + 1.0) / (classes[j][1] + 1.0) - ration[j];
                if (r2 > max) {
                    max = r2;
                    clzz = j;
                }
            }
            classes[clzz][0]++;
            classes[clzz][1]++;
            ration[clzz] = ration[clzz]+max;
        }

        double all = 0.0;
        for (int i = 0; i < ration.length; i++) {
            all += ration[i];
        }
        return all / (ration.length * 1.0);
    }


    public int maximumScore(int[] nums, int k) {
        //用一个treeMap维护下窗口内的值 9732
        //只有一个值时
        int max = nums[k];
        TreeSet<Integer> leftSet = new TreeSet<>();
        for (int left = k; left >= 0; left--) {
            leftSet.add(nums[left]);
            TreeSet<Integer> set = new TreeSet<>(leftSet);
            for (int right = k; right < nums.length; right++) {
                set.add(nums[right]);
                int score = set.first()*(right-left+1);
                max = Math.max(max,score);
            }
        }
        return max;
    }

}
