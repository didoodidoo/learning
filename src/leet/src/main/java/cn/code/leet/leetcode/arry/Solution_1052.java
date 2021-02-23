package cn.code.leet.leetcode.arry;

//傻逼老板爱生气
//窗口内 加权最大和
public class Solution_1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int max = 0;
        int left = 0, right = 0;
        int sum = 0;
        int sum1 = 0;
//        先把确定满意的加起来 之后只需要计算不满意的就行了
//        for (int i = 0; i < grumpy.length; i++) { //放在下面加
//            if (grumpy[i] == 0)
//                sum += customers[i];
//        }
//      计算窗口内不满意的用户的总和取最大的
        while (right < customers.length) {
            int num = customers[right];
            if (grumpy[right] == 1)
                sum1 += num;
            else
                sum += customers[right];
            right++;
            while (right - left > X) {
                if (grumpy[left] == 1) {
                    sum1 -= customers[left++];
                } else {
                    left++;
                }
            }
            max = Math.max(sum1, max);
        }

        return max + sum;
    }


}
