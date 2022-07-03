package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class lc78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        visit(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void visit(int[] nums, int curr, List<Integer> path, List<List<Integer>> ans) {
        if (curr == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // not include this value
        visit(nums, curr+1, path, ans);

        // include this value
        path.add(nums[curr]);
        visit(nums, curr+1, path, ans);
        path.remove(path.size()-1);
    }

    @Test
    public void test01() {
        lc78_Subsets solu = new lc78_Subsets();
        Set<List<Integer>> set = new HashSet<>(solu.subsets(new int[]{1,2,3}));

        Assertions.assertEquals(set.size(), 8);
        Assertions.assertTrue(set.contains(List.of()));
        Assertions.assertTrue(set.contains(List.of(1)));
        Assertions.assertTrue(set.contains(List.of(2)));
        Assertions.assertTrue(set.contains(List.of(3)));
        Assertions.assertTrue(set.contains(List.of(1,2)));
        Assertions.assertTrue(set.contains(List.of(1,3)));
        Assertions.assertTrue(set.contains(List.of(2,3)));
        Assertions.assertTrue(set.contains(List.of(1,2,3)));
    }

    @Test
    public void test02() {
        lc78_Subsets solu = new lc78_Subsets();
        Set<List<Integer>> set = new HashSet<>(solu.subsets(new int[]{0}));

        Assertions.assertEquals(set.size(), 2);
        Assertions.assertTrue(set.contains(List.of()));
        Assertions.assertTrue(set.contains(List.of(0)));
    }
}
