package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc40_Combination_Sum_II {
    /*
    Given a collection of candidate numbers (candidates) and a target number (target),
     find all unique combinations in candidates where the candidate numbers sum to target.
    Each number in candidates may only be used once in the combination.
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        visit(candidates, 0, target, new ArrayList<>());

        return res;
    }

    private List<List<Integer>> res = new ArrayList<>();

    private void visit(int[] candidates, int idx, int rest, List<Integer> path) {
        if (rest == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (candidates[i] > rest)
                break;

            path.add(candidates[i]);
            visit(candidates, i+1, rest - candidates[i], path);
            path.remove(path.size()-1);

            while (i+1 < candidates.length && candidates[i+1] == candidates[i]) {
                i++;
            }
        }
    }

    @Test
    public void test01() {
        lc40_Combination_Sum_II solu = new lc40_Combination_Sum_II();

        List<List<Integer>> lists = solu.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        Assertions.assertArrayEquals(new int[]{1,1,6}, Common.ToArray(lists.get(0)));
        Assertions.assertArrayEquals(new int[]{1,2,5}, Common.ToArray(lists.get(1)));
        Assertions.assertArrayEquals(new int[]{1,7}, Common.ToArray(lists.get(2)));
        Assertions.assertArrayEquals(new int[]{2,6}, Common.ToArray(lists.get(3)));
    }

    @Test
    public void test02() {
        lc40_Combination_Sum_II solu = new lc40_Combination_Sum_II();

        List<List<Integer>> lists = solu.combinationSum2(new int[]{2,5,2,1,2}, 5);
        Assertions.assertArrayEquals(new int[]{1,2,2}, Common.ToArray(lists.get(0)));
        Assertions.assertArrayEquals(new int[]{5}, Common.ToArray(lists.get(1)));
    }
}
