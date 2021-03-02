package cn.code.leet.util;

public class NumArray {

    int[] sum;
    int[] num;

    public NumArray(int[] nums) {
        this.num = nums;
        this.sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public void update(int index, int val) {
        //更新后要更新所有的值哦 另外搞一个数组记录下变化呢
        int sub = val - num[index];
        num[index] = val;
        for (int i = index+1; i < sum.length; i++) {
            sum[i]+=sub;
        }

    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

}
