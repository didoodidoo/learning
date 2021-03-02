package cn.code.leet.codetop;

import cn.code.leet.structure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {


    //    经典三数和 典中典
    public List<List<Integer>> threeSum(int[] nums) {
//      先排序然后变成两数和
        List<List<Integer>> result = new ArrayList<>();
//        先排序 然后降级为寻找两数和
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            findTwoSum(0 - nums[i], nums, i + 1, result);
        }
        return result;
    }

    /**
     * @param sum    和
     * @param nums   数组
     * @param start  起始位置
     * @param result 结果直接添加到这里
     */
    public void findTwoSum(int sum, int[] nums, int start, List<List<Integer>> result) {
//            是一个有序数组 直接双指针
        int startPos = start;
        int fin = nums.length - 1;
        while (start < fin) {
            if (nums[start] + nums[fin] > sum) {
                // 偏大
                fin--;
            } else if (nums[start] + nums[fin] < sum) {
                // 偏小
                start++;
            } else {
                if (start > startPos && nums[start] == nums[start - 1]) {
                    start++;
                    continue;
                }
                result.add(Arrays.asList(-sum, nums[start], nums[fin]));
                start++;
            }
        }
    }


    //当然可以层序遍历之后直接取 但是还有没有更好的呢

    List<Integer> rightSideView = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        fillRightSideView(root, 0);
        return rightSideView;
    }

    private void fillRightSideView(TreeNode root, int depth) {
        if (root == null)
            return;
        if (rightSideView.size() <= depth)
            rightSideView.add(root.val);
//        把右子树最右边的加进去 如果右子树是空的话就不会加数进去 就会把左子树加进去
        fillRightSideView(root.right, depth + 1);
        fillRightSideView(root.left, depth + 1);
    }


    //最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        如果左边有一个数 右边有一个数 那么就是这个数
        if (root == p || root == q)
            return root;
        if (root != null) {
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null)
                return root;
            else if (left == null)
                return right;
            else
                return left;
        }
        return null;
    }
}
