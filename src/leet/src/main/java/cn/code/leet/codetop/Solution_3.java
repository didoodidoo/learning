package cn.code.leet.codetop;

import cn.code.leet.structure.TreeNode;

import java.net.InetAddress;
import java.util.*;

public class Solution_3 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(nums.length, (o1, o2) -> o2 - o1);
        for (int num : nums) {
            pq.offer(num);
        }
        for (int i = 1; i < k; i++) {
            pq.poll();
        }
        return pq.peek();
    }

    public String reverseWords(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    //每行的元素从左到右升序排列。
    //每列的元素从上到下升序排列
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        //左上最小 右下最大
        int r1 = 0, c1 = 0;
        int r2 = matrix.length - 1;
        int c2 = matrix[0].length - 1;
        //对角线二分
        while (r1 < r2 || c1 < c2) {
            System.out.println(r1 + "," + c1 + " " + r2 + "," + c2);
            int rMid = (r1 + r2) / 2;
            int cMid = (c1 + c2) / 2;
            //切成四块
            // (r1,c1)+(rMid,cMid) (rMid,c1)+(r2,cMid) (r1,cMid)+(rMid,c2)  (rMid,cMid)+(r2,c2)
            int num1 = matrix[r1][c1];
            int num2 = matrix[rMid][cMid];
            int num3 = matrix[rMid][c1];
            int num4 = matrix[r2][cMid];
            int num5 = matrix[r1][cMid];
            int num6 = matrix[rMid][c2];
            int num7 = matrix[rMid][cMid];
            int num8 = matrix[r2][c2];
            if (target == num1 || target == num2 || target == num3 || target == num4 || target == num5 || target == num6 || target == num7 || target == num8)
                return true;
            if (target > num1 && target < num2) {
                r2 = rMid;
                c2 = cMid;
            } else if (target > num3 && target < num4) {
                r1 = rMid;
                c2 = cMid;
            } else if (target > num5 && target < num6) {
                r1 = rMid;
                c2 = cMid;
            } else if (target > num7 && target < num8) {
                r1 = rMid;
                c2 = cMid;
            } else {
                return false;
            }

        }
        //最后被限制在一个小格子里面
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (matrix[i][j] == target)
                    return true;
            }
        }
        return false;
    }


    public int findLength(int[] A, int[] B) {
        //还是滑动窗口爱我啊
        int right = 0;
        int max = 0;
        while (right < A.length) {
            //指向数组b
            int pos = 0;
            //如果剩下的都不及最长的 那就没必要找了
            if (A.length - right < max)
                break;
            while (B.length - pos > max) {
                //在B里面还有可能匹配到更长的
                int a = right;
                int b = pos;
                while (a < A.length && b < B.length && A[a] == B[b]) {
                    // System.out.println(a+" "+right);
                    max = Math.max(a - right + 1, max);
                    a++;
                    b++;
                }
                pos++;
            }
            right++;
        }
        return max;
    }

    //从i到j在另外一个数组里面存不存在
    // 表示第一个数组 A 前 i 个元素和数组 B 前 j 个元素组成的最长公共子数组(相当于子串)的长度。
    public int findLengthDP(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        //假如只有一个字符 那么就只要对比当前的是不是一样
        //往前找一个相等的 然后等于前面的+1
        int max = 0;
        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                } else {
                    //如果不相等 那么不可能构成子串了
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    public int minSubArrayLen(int target, int[] nums) {
        //大于等于这个数的最短子数组 滑动窗口
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (right < nums.length) {
            int num = nums[right];
            sum += num;
            while (sum >= target) {
                min = Math.min(min, right - left + 1);
                sum -= nums[left++];
            }
            right++;
        }
        return min;
    }


    //最长公共子序列
    public int longestCommonSubsequence(String text1, String text2) {
        int max = 0;
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {//如果这两个不相等
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public int[] exchange(int[] nums) {
//        双指针 原地转换
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 == 0) {
                //是偶数在后面找一个奇数和他换
                while (left < right && nums[right] % 2 == 0) right--;
                //这时候right指奇数
                if (right > left) {
                    int tmp = nums[right];
                    nums[right] = nums[left];
                    nums[left] = tmp;
                }
                right--;
            }
            left++;
        }
        return nums;
    }

    public int[] searchRange(int[] nums, int target) {
        int start = -1;
        int fin = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (start == -1) {
                    start = i;
                    fin = i;
                } else {
                    fin++;
                }

            }
        }
        return new int[]{start, fin};
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int len = classes.length;
        PriorityQueue<double[]> cls = new PriorityQueue<>(
                Comparator.comparingDouble(a -> (a[0] - a[1]) / (a[1] * (a[1] + 1))));
        for (int i = 0; i < len; i++) {
            cls.add(new double[]{classes[i][0], classes[i][1]});
        }
        while (extraStudents-- > 0) {
            double[] temp = cls.remove();
            temp[0]++;
            temp[1]++;
            cls.add(temp);
        }
        double res = 0;
        while (cls.size() > 0) {
            double[] temp = cls.remove();
            res += temp[0] / temp[1];
        }
        return res / len;
    }
}
