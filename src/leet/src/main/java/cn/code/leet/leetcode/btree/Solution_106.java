package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

/*
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class Solution_106 {
    //    后序遍历的最后一个节点肯定是根
//    假定元素唯一，在中序遍历中找到这个跟就说明左边是左子树，右边是右子树
//    接下来的问题就是怎么把后序遍历也切成两个数组呢。。。
//    后序是左右根 左子树的大小是可以知道的，减去这个size就是左子树，剩下的就是右子树

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                              int[] postorder, int poStart, int poEnd) {
//        递归就一把梭 别想有的没得，就只看根节点
        if (poEnd < poStart)
            return null;
        int rootVal = postorder[poEnd];
        TreeNode root = new TreeNode(rootVal);
//      找到中序遍历的根的位置 然后把左子树切两半
        int pos = findPos(inorder, rootVal);
//        根据size把后序遍历的切两半,左子树的大小就是中序遍历左边的部分
        int leftSize = pos - inStart;
        int rightSize = inEnd - pos;
        root.left = buildTree(inorder, inStart, pos - 1,
                postorder, poStart, poStart + leftSize - 1);
        root.right = buildTree(inorder, pos + 1, inEnd,
                postorder, poEnd - rightSize, poEnd - 1);
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
