package cn.code.leet.interview;

import cn.code.leet.structure.ListNode;
import cn.code.leet.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 * 示例：
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *         1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *    /
 *   8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 */
public class Solution_0403 {


//  这不就是层序遍历吗。。。。

    List<List<TreeNode>> result = new ArrayList<>();

    public List<List<TreeNode>>  listOfDepth(TreeNode tree) {
        if (tree == null)
            return  result;
        put(tree, 0);
        return result;
    }

    public void put(TreeNode root, int depth) {
//        把这个树放到属于自己深度的链表中，放自己 +1放左子树 放右子树
        if (root == null)
            return;
        if (result.size() < depth - 1)
            result.add(new ArrayList<>());
        result.get(depth).add(root);
//      深度+1 放左子树，放右子树
        depth++;
        put(root.left, depth);
        put(root.right, depth);
    }
}
