package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class lc39_Combination_Sum {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        visit(candidates, 0, target, new ArrayList<>());

        return res;
    }

    private void visit(int[] candidates, int curr, int rest, List<Integer> currPath) {
        if (rest == 0) {
            res.add(new ArrayList<>(currPath));
            return;
        }

        for (int i = curr; i < candidates.length; i++) {
            if (rest - candidates[i] >= 0) {
                currPath.add(candidates[i]);
                visit(candidates, i, rest - candidates[i], currPath);
                currPath.remove(currPath.size()-1);
            }
        }
    }

    @Test
    public void test01() {
        lc39_Combination_Sum solu = new lc39_Combination_Sum();
        List<List<Integer>> res = solu.combinationSum(new int[]{2,3,6,7}, 7);

        Assertions.assertArrayEquals(new int[]{2,2,3}, Common.ToArray(res.get(0)));
        Assertions.assertArrayEquals(new int[]{7}, Common.ToArray(res.get(1)));
    }
}
