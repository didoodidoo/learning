package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

//层序遍历 需要借助于一个queue
public class Solution_levOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<TreeNode> lst = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        lst.add(root);
        while (lst.size() != 0) {
            List<TreeNode> list2 = new ArrayList<>();
            List<Integer> nums = new ArrayList<>();
            for (TreeNode treeNode : lst) {
                if (treeNode != null) {
                    nums.add(treeNode.val);
                    list2.add(treeNode.left);
                    list2.add(treeNode.right);
                }
            }
            lst = list2;
            if (nums.size() > 0)
                result.add(nums);
        }
        return result;

    }
}
