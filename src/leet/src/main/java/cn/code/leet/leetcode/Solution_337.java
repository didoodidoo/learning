package cn.code.leet.leetcode;

import cn.code.leet.structure.TreeNode;

public class Solution_337 {

    //    因为不能打劫相邻的房子  所以父子节点只能选一个
    // 当前节点有偷和不偷两种状态
//    如果当前节点偷的话 那么孙子节点成为根往下遍历 当前节点不偷则儿子节点成为根往下遍历

    public int rob(TreeNode root) {
//        用一个数组来存储偷和不偷两种状态能偷多少钱
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
//        没得偷了就是2个0
        if (root == null) return new int[2];
        int[] result = new int[2];
        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);
//      如果不偷的话 就是他左边的儿子和右边的儿子相加
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
//        如果偷的话 就是左边不偷+右边不偷+他自己
        result[1] = left[0] + right[0] + root.val;
        return result;
    }
}

