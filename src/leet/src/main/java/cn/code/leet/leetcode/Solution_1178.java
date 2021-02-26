package cn.code.leet.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
字谜的迷面puzzle 按字符串形式给出，如果一个单词word符合下面两个条件，那么它就可以算作谜底：
单词word中包含谜面puzzle的第一个字母。
单词word中的每一个字母都可以在谜面puzzle中找到。
例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及"based"（其中的 "s" 没有出现在谜面中）。
返回一个答案数组answer，数组中的每个元素answer[i]是在给出的单词列表 words 中可以作为字谜迷面puzzles[i]所对应的谜底的单词数目。
*/
public class Solution_1178 {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
//        二进制序列
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (String puzzle : puzzles) {
            int total = 0;
            int mask = 0;
            //转换成二进制表示
            for (int i = 1; i < 7; ++i) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subset = mask;
            do {
//                不管怎么样 首字母那一位必须是1
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
//                怎么获得下一个子序列呢
//
                subset = (subset - 1) & mask;
            } while (subset != mask);
            //变成000000的时候会相等 跳出循环

            ans.add(total);
        }
        return ans;
    }

//    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
//        List<Integer> result = new ArrayList<>();
//        for (String puzzle : puzzles) {
//            int[] arr = new int[26];
//            char first = puzzle.charAt(0);
//            for (char c : puzzle.toCharArray()) {
//                arr[c - 'a'] += 1;
//            }
//            int count = 0;
//            for (String word : words) {
//                if (check(word, first, arr))
//                    count++;
//            }
//            result.add(count);
//        }
//        return result;
//    }
//
//    public boolean check(String word, char first, int[] arr) {
//        boolean containsFirst = false;
//        for (char c : word.toCharArray()) {
//            if (c == first)
//                containsFirst = true;
//            if (arr[c - 'a'] == 0)
//                return false;
//        }
//
//        return containsFirst;
//    }

}
