package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class lc77_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        visit(n, 1, k, new ArrayList<>(), ans);
        return ans;
    }

    private void visit(int n, int curr, int k, List<Integer> path, List<List<Integer>> ans) {
        if (k == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = curr; i <= n-k+1; i++) {
            path.add(i);
            visit(n, i+1, k-1, path, ans);
            path.remove(path.size()-1);
        }
    }

    @Test
    public void test01() {
        lc77_Combinations solu = new lc77_Combinations();
        Set<List<Integer>> set = new HashSet<>(solu.combine(4,2));

        Assertions.assertEquals(6, set.size());
        Assertions.assertTrue(set.contains(List.of(2,4)));
        Assertions.assertTrue(set.contains(List.of(3,4)));
        Assertions.assertTrue(set.contains(List.of(2,3)));
        Assertions.assertTrue(set.contains(List.of(1,2)));
        Assertions.assertTrue(set.contains(List.of(1,3)));
        Assertions.assertTrue(set.contains(List.of(1,4)));
    }

    @Test
    public void test02() {
        lc77_Combinations solu = new lc77_Combinations();
        Set<List<Integer>> set = new HashSet<>(solu.combine(1,1));

        Assertions.assertEquals(1, set.size());
        Assertions.assertTrue(set.contains(List.of(1)));
    }
}
