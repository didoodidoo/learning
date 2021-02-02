package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

public class Solution_226 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
//        翻转左子树
        invertTree(root.left);
//        翻转右子树
        invertTree(root.right);
//        翻转自己
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }
}
