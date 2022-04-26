package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class lc216_Combination_Sum_III {
    /*
    Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
        Only numbers 1 through 9 are used.
        Each number is used at most once.
    Return a list of all possible valid combinations.
    The list must not contain the same combination twice, and the combinations may be returned in any order.
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        visit(1, k, n, new ArrayList<>());

        return res;
    }

    private List<List<Integer>> res = new ArrayList<>();
    private void visit(int start, int k, int rest, List<Integer> path) {
        if (k < 0)
            return;

        if (k == 0 && rest == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i > rest)
                break;

            path.add(i);
            visit(i+1, k-1, rest-i, path);
            path.remove(path.size()-1);
        }
    }

    @Test
    public void test01() {
        lc216_Combination_Sum_III solu = new lc216_Combination_Sum_III();
        Set<List<Integer>> set = new HashSet<>();

        set.addAll(solu.combinationSum3(3, 7));
        Assertions.assertTrue(set.contains(List.of(1,2,4)));
    }

    @Test
    public void test02() {
        lc216_Combination_Sum_III solu = new lc216_Combination_Sum_III();
        Set<List<Integer>> set = new HashSet<>();

        set.addAll(solu.combinationSum3(3, 9));
        Assertions.assertTrue(set.contains(List.of(1,2,6)));
        Assertions.assertTrue(set.contains(List.of(1,3,5)));
        Assertions.assertTrue(set.contains(List.of(2,3,4)));
    }

    @Test
    public void test03() {
        lc216_Combination_Sum_III solu = new lc216_Combination_Sum_III();
        Set<List<Integer>> set = new HashSet<>();

        set.addAll(solu.combinationSum3(4, 1));
        Assertions.assertTrue(set.isEmpty());
    }
}
