package cn.code.leet.leetcode.btree;

import cn.code.leet.structure.Node;

public class Solution_116 {

    public Node connect(Node root) {
        if (root == null)
            return null;
//        把左右连起来，然后把中间的也连起来
        connectTwo(root.left, root.right);
        return root;
    }

    public void connectTwo(Node node1, Node node2) {
        if (node1 == null || node2 == null)
            return;
        node1.next = node2;
        connectTwo(node1.left, node1.right);
        connectTwo(node2.left, node2.right);
        connectTwo(node1.right, node1.left);
    }
}
