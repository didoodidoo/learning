package cn.code.leet.leetcode.compettion;

import java.util.*;

public class Solution_0228 {

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        Map<String, Integer> typeMap = new HashMap<>();
        Map<String, Integer> colorMap = new HashMap<>();
        Map<String, Integer> nameMap = new HashMap<>();
//        String[] keys = new String[]{"type", "color", "name"};
        for (List<String> item : items) {
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    typeMap.put(item.get(i), typeMap.getOrDefault(item.get(i), 0) + 1);
                }
                if (i == 1) {
                    colorMap.put(item.get(i), colorMap.getOrDefault(item.get(i), 0) + 1);
                }
                if (i == 2) {
                    nameMap.put(item.get(i), nameMap.getOrDefault(item.get(i), 0) + 1);
                }
            }
        }

        if (ruleKey.equals("type")) {
            return typeMap.getOrDefault(ruleValue, 0);
        }
        if (ruleKey.equals("color")) {
            return colorMap.getOrDefault(ruleValue, 0);

        }
        if (ruleKey.equals("name")) {
            return nameMap.getOrDefault(ruleValue, 0);
        }
        return 0;
    }


    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
//      一眼看过去用该是动态规划了
//      先计算出所有配料排列组合的价格 后面直接加进去了
//        升序排序
        List<Integer> toppings = initCost(toppingCosts);

//      最接近的数
        int num = Integer.MAX_VALUE;
//        差值
        int sub = Integer.MAX_VALUE;
        for (int baseCost : baseCosts) {
            for (Integer topping : toppings) {
//                计算差值 在差值越来越大的时候停止寻找 其实可以用二分但是不想写
                int sum = baseCost + topping;
                int sumSub = Math.abs(sum - target);
                if (sum == target)
                    return sum;
                if (sumSub < sub) {
                    sub = sumSub;
                    num = sum;
                } else if (sumSub == sub) {
                    if (num >= sum) {
                        num = sum;
                    }
                }
            }
        }
        return num;
    }

    private List<Integer> initCost(int[] toppingCosts) {
        List<Integer> toppings = new ArrayList<>(toppingCosts.length * toppingCosts.length * 6);
        toppings.add(0);
        for (int cost : toppingCosts) {
            int size = toppings.size();
            if (size == 0) {
                toppings.add(cost);
                toppings.add(cost * 2);
            } else {
                for (int j = 0; j < size; j++) {
                    toppings.add(toppings.get(j) + cost);
                    toppings.add(toppings.get(j) + 2 * cost);
                }
            }
        }
        return toppings;
    }


    public int minOperations(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int sum1 = 0;
        int sum2 = 0;
        for (int num : nums1)
            sum1 += num;
        for (int num : nums2)
            sum2 += num;

        if (sum1 == sum2)
            return 0;
        int count = 0;
        int p1;
        int p2;
        if (sum1 < sum2) {
            p1 = 0;
            p2 = nums2.length - 1;
            while (sum1 != sum2) {
                if (p1 == nums1.length && p2 < 0)
                    break;
//            看看变换的差值谁比较大
                int sub1;
                if (p1 < nums1.length) {
                    sub1 = 6 - nums1[p1];
                } else {
                    sub1 = Integer.MIN_VALUE;
                }

                int sub2 ;
                if (p2 >= 0) {
                    sub2 = nums2[p2] - 1;
                } else {
                    sub2 = Integer.MIN_VALUE;
                }

                if (sum2 - sum1 <= sub1 || sum2 - sum1 <= sub2)
                    return ++count;
                if (sub1 >= sub2) {
//                  变换的更多
                    sum1 += sub1;
                    p1++;
                } else {
                    sum2 -= sub2;
                    p2--;
                }
                count++;
            }

        } else {
            p1 = nums1.length - 1;
            p2 = 0;
            while (sum1 != sum2) {
                if (p2 == nums2.length && p1 < 0)
                    break;
//            看看变换的差值谁比较大
                int sub1;
                if (p1 >=0) {
                    sub1 =nums1[p1]-1;
                } else {
                    sub1 = Integer.MIN_VALUE;
                }
                int sub2;
                if (p2 < nums2.length) {
                    sub2 = 6 - nums2[p2];
                } else {
                    sub2 = Integer.MIN_VALUE;
                }
                if (sum1 - sum2 <= sub1 || sum1 - sum2 <= sub2)
                    return ++count;
                if (sub1 >= sub2) {
//                  变换的更多
                    sum1 -= sub1;
                    p1--;
                } else {
                    sum2 += sub2;
                    p2++;
                }
                count++;
            }
        }
        if (sum1 == sum2)
            return count;


        return -1;
    }
}
