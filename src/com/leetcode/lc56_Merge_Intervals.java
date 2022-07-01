package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc56_Merge_Intervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return null;

        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        int start = intervals[0][0];
        int end = intervals[0][1];
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 1; i < intervals.length; i++) {
            if (end < intervals[i][0]) {
                list.add(List.of(start, end));
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }

        list.add(List.of(start, end));

        int count = list.size();
        int[][] ans = new int[count][2];

        for (int i = 0; i < count; i++) {
            ans[i][0] = list.get(i).get(0);
            ans[i][1] = list.get(i).get(1);
        }

        return ans;
    }

    @Test
    public void test01() {
        lc56_Merge_Intervals solu = new lc56_Merge_Intervals();

        int[][] ans = solu.merge(new int[][]{
                {1,3}, {2,6}, {8,10},{15,18}
        });

        int iii = ans.length;

        //Assertions.assertEquals(3, ans.length);
        Set<List<Integer>> set = arrayToSet(ans);

        Assertions.assertTrue(set.contains(List.of(1,6)));
        Assertions.assertTrue(set.contains(List.of(8,10)));
        Assertions.assertTrue(set.contains(List.of(15,18)));
    }

    @Test
    public void test02() {
        lc56_Merge_Intervals solu = new lc56_Merge_Intervals();

        int[][] ans = solu.merge(new int[][]{
                {1, 4}, {4, 5}
        });

        Assertions.assertEquals(1, ans.length);
        Set<List<Integer>> set = arrayToSet(ans);

        Assertions.assertTrue(set.contains(List.of(1,5)));
    }

    private Set<List<Integer>> arrayToSet(int[][] ans) {
        Set<List<Integer>> set = new HashSet<>();

        for (int[] p : ans) {
            List<Integer> list = new ArrayList<>();
            list.add(p[0], p[1]);
            set.add(list);
        }

        return set;
    }
}
