package cn.code.leet.leetcode.arry;

public class Solution_137 {

    //出现三次 有一个数出现1次
    public int singleNumber(int[] nums) {
        return 0;
    }

    public int[] singleNumber3(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        int div = 1;
        while ((div & sum) == 0) {
            div <<= 1;
        }
        int a = 0;
        int b = 0;
        for (int num : nums) {
            if ((num & div) == 0)
                a ^= num;
            else
                b ^= num;
        }
        return new int[]{a,b};
    }
}
