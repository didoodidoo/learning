package cn.code.leet;

public class Solution_MST_08_03 {

    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i])
                return i;
        }
        return -1;
    }
}
