package cn.code.leet.codetop;

import cn.code.leet.structure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {

    //无重叠区间 先排序
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1)
            return 0;
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int count = 0;
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < right) {
                count++;
                right = Math.min(right, intervals[i][1]);
            } else {
                right = intervals[i][1];
            }
        }
        return count;
    }

    //合并重叠区间
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0)
            return new int[0][2];
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{intervals[0][0], intervals[0][1]});
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= right) {
                //重叠了 把right换成较大的 ，left不动
                right = Math.max(intervals[i][1], right);
                list.set(list.size() - 1, new int[]{left, right});
            } else {
                left = intervals[i][0];
                right = intervals[i][1];
                list.add(new int[]{left, right});
            }
        }

        return list.toArray(new int[list.size()][]);
    }

//
//    int max = Integer.MIN_VALUE;
//    public int maxPathSum(TreeNode root) {
//        //最大路径和
//        //过这个节点的最大路径和  左树的最大路径和，右树的最大路径和 自己最大路径和
//
//
//    }
    StringBuilder sumNumbersStr = new StringBuilder();
    List<String> sumNumbersResult = new ArrayList<>();
    public int sumNumbers(TreeNode root) {
        //回溯 放到列表里 然后相加
        buildNum(root);
        int sum = 0;
        for (String str: sumNumbersResult){
            sum+=Integer.parseInt(str);
        }
        return sum;
    }
    private void buildNum(TreeNode root) {
        if(root==null)
            return ;
        sumNumbersStr.append(root.val);
        if(root.left==null&&root.right==null){
//           是叶子节点 把数拼出来 放进列表 然后回溯
            sumNumbersResult.add(new String(sumNumbersStr));
        }

        System.out.println(sumNumbersStr);
        buildNum(root.left);
        buildNum(root.right);
        sumNumbersStr.deleteCharAt(sumNumbersStr.length()-1);
    }

    //相同节点的最长路径
    int longestPathmax = 0;
    public int longestUnivaluePath(TreeNode root) {
        findLongestPath(root);
        return longestPathmax;
    }

    public int findLongestPath(TreeNode root){
        if(root==null)
            return 0;
        //相等就+1，不相等就不+1
        int left = findLongestPath(root.left);
        int right = findLongestPath(root.right);
        int result = 1;
        if(root.left!=null&&root.val==root.left.val&&root.right!=null&&root.right.val==root.val){
            //左右都相等
            result = left+right+1;
            longestPathmax = Math.max(result, longestPathmax);
            return 1+Math.max(left,right);
        }else if(root.left!=null&&root.val==root.left.val){
            result =  left+1;
        }else if(root.right!=null&&root.right.val==root.val){
            result =  right+1;
        }
        longestPathmax = Math.max(result, longestPathmax);
        return result;
    }


    int maxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        findMaxPathSum(root);
        return maxPathSum;
    }

    public int findMaxPathSum(TreeNode root){
        if(root==null)
            return 0;
        int left = findMaxPathSum(root.left);
        int right = findMaxPathSum(root.right);
        int result = root.val;
        if(left>0&&right>0){
            int sum = left+right+root.val;
            maxPathSum = Math.max(sum, maxPathSum);
            return Math.max(left,right)+root.val;
        }else if(left>0){
            result = root.val+left;
        }else if(right>0){
            result = root.val+right;
        }
        maxPathSum = Math.max(result, maxPathSum);
        return result;
    }



}
