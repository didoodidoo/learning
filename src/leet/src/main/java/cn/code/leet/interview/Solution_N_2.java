package cn.code.leet.interview;

import java.util.*;

public class Solution_N_2 {

    public static void main(String[] args) {

        List<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(1);
        l.add(1);
        Scanner sc = new Scanner(System.in);
        String value = sc.next();
        value = value.replaceAll("\\(|\\)", "");
        String[] vList = value.split(",");
        int len = vList.length / 2;

        int[][] nums = new int[len][2];
        for (int i = 0; i < len; i++) {
            nums[i][0] = Integer.valueOf(vList[2 * i]);
            nums[i][1] = Integer.valueOf(vList[2 * i + 1]);
        }
//        System.out.println(value);
//        System.out.println(solution1(l));
    }

    public static Integer solution1(List<Integer> nums) {

        List<Integer> left = nums;
//        每回合产生新的剩余数组  直到数组的长度<=2
        while (left.size() >= 3) {
            left.sort(((o1, o2) -> o2 - o1));
            Integer x, y, z;
            x = left.get(0);
            y = left.get(1);
            z = left.get(2);
            if (x.equals(y) && y.equals(z)) {
                z = 0;
            } else if (x.equals(y)) {
                z = Math.abs(y - z);
            } else if (y.equals(z)) {
                z = Math.abs(y - x);
            } else {
                z = Math.abs((z - y) - (y - x));
            }
            left = left.subList(3, left.size());
            if (z != 0)
                left.add(z);
        }
        if (left.size() == 2)
            return Math.max(left.get(0), left.get(1));
        if (left.size() == 1)
            return (left.get(0));
        return 0;
    }

    public static String solution(int[][] nums) {
//        一个2列二维数组
        StringBuilder s = new StringBuilder();
        int i = nums.length;
        int j = nums[0].length;
        Set<Integer> set = new HashSet<>();
        Set<Integer> hasPut = new HashSet<>();
        for (int m = 0; m < i; m++) {
            set.add(nums[m][1]);
        }
        List<Integer> l = new ArrayList<>(set);
        l.sort((o1, o2) -> o2 - o1);
        for (Integer m : l) {
            for (int n = 0; n < i; n++) {
                if (nums[n][1] == m && !hasPut.contains(nums[n][0])) {
                    s.append(nums[n][0]);
                    s.append(",");
                    hasPut.add(nums[n][0]);
                }
            }
        }
        return s.substring(0, s.length() - 1);
    }

    public String solution3() {
//        1代表红 2代表蓝
        int[][] arr = new int[5][6];
        int[] nums = {1, 2, 3, 4, 5};

        int i = arr.length;
        int j = arr[0].length;

        for (int m = 0; m < nums.length; m++) {
//            每落一子记录棋盘的状态
//            落子时检查是否胜利
//          偶数红方落子 奇数蓝方落子
            int lie = nums[m];
//1代表红 2代表蓝
            int color = m % 2 == 0 ? 1 : 2;

            if (arr[0][lie] != 0 || lie > j || lie < 1) {
                int err = m + 1;
                return err + ",error";
            }
//            下标
            lie = lie--;

//          从最后一行开始落到棋盘上
            for (int hang = i; hang > 0; hang--) {
                if (arr[hang][lie] == 0){
                    arr[hang][lie] = color;
                    if(win(hang,lie,arr,color)){
                        int step = m+1;
                        return color==1?step+",red":step+",blue";
                    }
                }
//                检查是否胜利
                break;
            }
        }
        return "0,draw";
    }

    public static boolean win(int hang, int lie, int[][] arr, int color) {

        int i = arr.length;
        int j = arr[0].length;
//        这个子落在 [hang][lie]
//        检查左边
        boolean win = true;
        if (lie - 3 >= 0) {
            for (int m = 1; m < 4; m++) {
                if (arr[hang][lie - m] != color) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
            win = true;
        }
//右边
        if (lie + 3 < j) {
            for (int m = 1; m < 4; m++) {
                if (arr[hang][lie + m] != color) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
            win = true;
        }

//        检查上边
        if (hang - 3 >= 0) {
            for (int m = 1; m < 4; m++) {
                if (arr[hang - m][lie] != color) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
            win = true;
        }

        //        左斜排
        if (hang - 3 >= 0 && lie - 3 >= 0) {
            for (int m = 1; m < 4; m++) {
                if (arr[hang - m][lie - m] != color) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
            win = true;
        }

        //        右斜排
        if (hang - 3 >= 0 && lie + 3 < j) {
            for (int m = 1; m < 4; m++) {
                if (arr[hang - m][lie + m] != color) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
            win = true;
        }

        return false;
    }
}
