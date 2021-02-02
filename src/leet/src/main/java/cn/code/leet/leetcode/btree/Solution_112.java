package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

public class Solution_112 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
//        如果是root即叶子节点，判断是否相等
        if (root.left == null && root.right == null)
            return root.val == targetSum;
//        判断左子树有没有符合要求的值，判断右子树有没有符合条件的值

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
