package cn.code.leet.codetop;

import cn.code.leet.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_4 {

    List<List<Integer>> nodes = new ArrayList<>();

    public boolean isCompleteTree(TreeNode root) {
        //层序遍历  左子树的分数是 2*root 右子树是 2*root+1 层序遍历然后赋值放到列表里，如果不是连续的就断了
        put(root, 1, 0);
        //2^i 打头 中间不能断

        for (int i = 0; i < nodes.size(); i++) {
            List<Integer> nums = nodes.get(i);
            int x = 1 << i;
            if (x != nums.get(0)) return false;
            //如果不是最后一行 需要是完整的个数
            if (i < nodes.size() - 1 && nums.size() < x) return false;
            for (int j = 1; j < nums.size(); j++) {
                if (nums.get(j) != (nums.get(j - 1) + 1)) return false;
            }
        }

        return true;
    }


    public void put(TreeNode root, int rootNum, int depth) {
        if (root == null)
            return;
        if (nodes.size() < depth + 1)
            nodes.add(new ArrayList<>());
        nodes.get(depth).add(rootNum);
        //深度+1昂放子树的分数
        put(root.left, 2 * rootNum, depth + 1);
        put(root.right, 2 * rootNum + 1, depth + 1);
    }
}
