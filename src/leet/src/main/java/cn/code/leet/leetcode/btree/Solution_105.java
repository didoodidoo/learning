package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

/*
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class Solution_105 {
    //与后续遍历的类似，先序遍历则第一个节点是root
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return buildTree(preorder,
                0,
                preorder.length - 1,
                inorder,
                0,
                inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                              int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd)
            return null;
        int rootVal = preorder[preStart];
        int pos = findPos(inorder, rootVal);
        int leftSize = pos - inStart;
        int rightSize = inEnd - pos;
        TreeNode root = new TreeNode(rootVal);

        root.left = buildTree(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, pos - 1);
        root.right = buildTree(preorder, preEnd - rightSize + 1, preEnd, inorder, pos + 1, inEnd);
        return root;
    }

    private int findPos(int[] inorder, int rootVal) {

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal)
                return i;
        }
        return -1;
    }
}
