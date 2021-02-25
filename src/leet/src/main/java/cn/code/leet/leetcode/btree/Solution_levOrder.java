package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//层序遍历 需要借助于一个queue
public class Solution_levOrder {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return result;
        put(root, 0);

//        for (int i = 0; i < result.size(); i++) {
//            if (i % 2 == 1) {
//                Collections.reverse(result.get(i));
//            }
//        }
        return result;
    }

    public void put(TreeNode root, int depth) {
//        把这个树放到属于自己深度的链表中，放自己 +1放左子树 放右子树
        if (root == null)
            return;
        if (result.size() < depth + 1)
            result.add(new ArrayList<>());
        result.get(depth).add(root.val);
//      深度+1 放左子树，放右子树
        depth++;
        put(root.left, depth);
        put(root.right, depth);
    }
//
//    public List<List<Integer>> levelOrder(TreeNode root) {
//
//        List<TreeNode> lst = new ArrayList<>();
//        List<List<Integer>> result = new ArrayList<>();
//        if (root == null)
//            return result;
//        lst.add(root);
//        while (lst.size() != 0) {
//            List<TreeNode> list2 = new ArrayList<>();
//            List<Integer> nums = new ArrayList<>();
//            for (TreeNode treeNode : lst) {
//                if (treeNode != null) {
//                    nums.add(treeNode.val);
//                    list2.add(treeNode.left);
//                    list2.add(treeNode.right);
//                }
//            }
//            lst = list2;
//            if (nums.size() > 0)
//                result.add(nums);
//        }
//        return result;
//
//    }

}
