package cn.code.leet.codetop;

import cn.code.leet.structure.TreeNode;

import java.util.*;

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


    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left != right) {
            int sum = nums[left] + nums[right];
            if (sum > target)
                right--;
            else if (sum < target)
                left++;
            else
                return new int[]{left, right};
        }
        return new int[2];
    }


    public List<Integer> spiralOrder(int[][] matrix) {
        int[][] flag = new int[matrix.length][matrix[0].length];
        List<Integer> result = new ArrayList<>();
        int direction = 1;
        check(0, 0, flag, result, matrix, direction);
        return result;
    }

    // 右 下 左 上 1,2,3,4   direction+1
    public void check(int i, int j, int[][] flag, List<Integer> result, int[][] matrix, int direction) {
        //全都不能检查就结束
        if (i >= flag.length || j >= flag[0].length || i < 0 || j < 0 || flag[i][j] == 1)
            return;
        result.add(matrix[i][j]);
        flag[i][j] = 1;
        //决定下一个的方向

        switch (direction) {
            case 1: {
                //方向向右 检查能否继续向右，不能则向下
                if (j + 1 < matrix[0].length && flag[i][j + 1] == 0)
                    check(i, j + 1, flag, result, matrix, direction);
                else
                    check(i + 1, j, flag, result, matrix, 2);
                break;
            }
            case 2: {//检查是否继续向下 不能则向左
                if (i + 1 < matrix.length && flag[i + 1][j] == 0)
                    check(i + 1, j, flag, result, matrix, direction);
                else
                    check(i, j - 1, flag, result, matrix, 3);
                break;
            }
            case 3: {//是否能继续向左 不能就向上
                if (j - 1 >= 0 && flag[i][j - 1] == 0)
                    check(i, j - 1, flag, result, matrix, direction);
                else
                    check(i - 1, j, flag, result, matrix, 4);
                break;
            }
            case 4: { //能否向上 不能则向右
                if (i - 1 >= 0 && flag[i - 1][j] == 0)
                    check(i - 1, j, flag, result, matrix, direction);
                else
                    check(i, j + 1, flag, result, matrix, 1);
                break;
            }
            default:
                break;
        }

    }


    public int[] countBits(int num) {

        int[] result = new int[num + 1];
        boolean flag = true;
        //如果该数是奇数 那么在原有基础上加一
        result[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (flag) {
                result[i] = result[i - 1];
            } else {
                result[i] = result[i / 2];
            }
            flag = !flag;
        }
        return result;
    }

    //平方根
    public int mySqrt(int x) {
        //二分查找吧
        int left = 0;
        int right = x;
        int mid = -1;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            long num = (long) mid * mid;
            if (mid * mid > x) {
                right = mid - 1;
            } else if (mid * mid < x) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return mid;
    }


    List<List<Integer>> pathSum = new ArrayList<>();
    Deque<Integer> queue = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        //在子树中找和
        findPath(targetSum, root);
        return pathSum;
    }

    public void findPath(int target, TreeNode root) {
        if (root == null)
            return;
        if (root.val == target && root.left == null && root.right == null) {
            queue.offer(root.val);
            pathSum.add(new ArrayList<>(queue));
            queue.pollLast();
        } else {
            queue.offer(root.val);
            findPath(target - root.val, root.left);
            findPath(target, root.right);
        }
    }


    int depth = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        depth(root);
        return depth - 1;
    }

    public int depth(TreeNode root) {
        if (root == null)
            return 0;
        //左子树最大深度
        int left = depth(root.left);
        //右子树最大深度
        int right = depth(root.right);
        depth = Math.max(depth, left + right + 1);
        return Math.max(left, right) + 1;
    }


    boolean balance = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return false;
        return balance && Math.abs(height(root.left) - height(root.right)) <= 1;
    }

    public int height(TreeNode root) {
        if (root == null || !balance) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            balance = false;
            return 0;
        }
        return Math.max(leftHeight, rightHeight);
    }



    List<List<Integer>> permuteResult = new ArrayList<>();
    Deque<Integer> permuteQueue = new LinkedList<>();
    //全排列
    public List<List<Integer>> permute(int[] nums) {
        int[] flag = new int[nums.length];
        fillQueue(nums,flag);
        return permuteResult;
    }

    public void fillQueue(int[] nums, int[] flag){
        for(int i = 0;i<nums.length;i++){
            if(flag[i]==0){
                flag[i]=1;
                permuteQueue.offer(nums[i]);
            }
            if(permuteQueue.size()==nums.length){
                //排列完了
                permuteResult.add(new ArrayList<>(queue));
            }else{
                fillQueue(nums, flag);
            }
            permuteQueue.pollLast();
            flag[i] = 0;
        }
    }

}
