package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.ListNode;
import cn.code.leet.structure.TreeNode;

//判断对称二叉树
public class Solution_101 {

    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }


    // 公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        这个函数是找节点的公共祖先
        if (root == p || root == q) {
            return root;
        }
        if (root != null) {
            TreeNode lNode = lowestCommonAncestor(root.left, p, q);
            TreeNode rNode = lowestCommonAncestor(root.right, p, q);
            if (lNode != null && rNode != null)
                return root;
            else if (lNode == null) {//两个都在右子树
                return rNode;
            } else { //两个都在左子树里面
                return lNode;
            }
        }
        return null;
    }

    //DFS
    public TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q)
            return root;
        if (root != null) {
            TreeNode lNode = find(root.left, p, q);
            TreeNode rNode = find(root.right, p, q);
            if (lNode != null & rNode != null) {
//                两个都至少有一个节点
                return root;
            } else if (rNode == null) {
                return lNode;
            } else {
                return rNode;
            }
        }
        return null;
    }

}
