package cn.code.leet.leetcode;

import cn.code.leet.structure.TreeNode;

public class Solution_104 {
//    一个简单的递归
//    高度等于max(左子树的高度,右子树的高度) +1   //他自己
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
//            加上自己的高度
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
