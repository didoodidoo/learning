package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.TreeNode;

//二叉树展开为链表
public class Solution_114 {

//    写递归你甚至不需要知道自己写的是啥。。。。
//    这个函数是把一个树捋成链表
//    用这个把左边捋一下，右边捋一下，然后把左右接到一起就行了
    public void flatten(TreeNode root) {
        if(root==null)
            return ;
        flatten(root.left);
        flatten(root.right);
//      这个时候已经捋直了
        TreeNode tmp = root.right;
        TreeNode leaf = root.left;
        while(leaf.right!=null)
            leaf = leaf.right;
        leaf.right = tmp;
        root.left = null;
    }
}
