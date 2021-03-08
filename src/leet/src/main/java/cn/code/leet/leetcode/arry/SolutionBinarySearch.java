package cn.code.leet.leetcode.arry;

public class SolutionBinarySearch {

    public int search(int[] nums, int target) {
        if(nums.length==0)
            return -1;
        if(nums.length==1){
            if(nums[0]==target) return 0;else return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left+right)/2;
            if(target==nums[mid])
                return mid;
            //找一个升序的
            if(nums[mid]>=nums[left]){
                //左边是升序 看偏大还是偏小
                if(target>=nums[left]&&target<nums[mid]){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }else{
                //右边是升序
                if(target>nums[mid]&&target<=nums[right]){
                    left = mid+1;
                }else{
                    right = mid-1;
                }

            }
        }
        return -1;
    }

}
