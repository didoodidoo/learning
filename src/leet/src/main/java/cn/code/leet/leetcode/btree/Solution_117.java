package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

//同样的连左右但是不是完美二叉树
//有一个数据就行了，长度是1就行 放要连接的那个
//层序遍历 把本来加到数组到操作变成连节点
public class Solution_117 {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return result;
        put(root, 0);
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
}
