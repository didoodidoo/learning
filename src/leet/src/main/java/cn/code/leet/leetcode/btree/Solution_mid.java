package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 中序遍历 左 根 右
public class Solution_mid {

    List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null)
            return result;
        inorderTraversal(root.left);
        result.add(root.val);
        inorderTraversal(root.right);
        return result;
    }

    Stack<TreeNode> stack = new Stack<>();

    public List<Integer> firstTraversal(TreeNode root) {
        if(root==null)
            return result;
        inorderTraversal(root.left);
        result.add(root.val);
        inorderTraversal(root.right);
        return result;
    }
}
