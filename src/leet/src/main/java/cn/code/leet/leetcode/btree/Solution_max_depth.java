package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

public class Solution_max_depth {

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
