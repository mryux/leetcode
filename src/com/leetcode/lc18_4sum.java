package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;


class lc18_4sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        ArrayList<List<Integer>> res = new ArrayList<>();
        int average = target/4;

        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > average) {
                break;
            }

            for (int j = i+1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                int lo = j+1;
                int hi = nums.length-1;

                while (lo < hi) {
                    int sum4 = sum + nums[lo] + nums[hi];

                    if (sum4 == target) {
                        List<Integer> list = new ArrayList<>();
                        int loValue = nums[lo];
                        int hiValue = nums[hi];

                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(loValue);
                        list.add(hiValue);
                        res.add(list);

                        while (lo < hi && nums[lo] == loValue) {
                            lo++;
                        }
                        while (lo < hi && nums[hi] == hiValue) {
                            hi--;
                        }
                    }
                    else if (sum4 < target) {
                        lo++;
                    }
                    else {
                        hi--;
                    }
                }

                while (j < nums.length-1 && nums[j] == nums[j+1]) {
                    j++;
                }
            }

            while (i < nums.length-1 && nums[i] == nums[i+1]) {
                i++;
            }
        }

        return res;
    }

    @Test
    public void test1() {
        lc18_4sum solu = new lc18_4sum();

        List<List<Integer>> found = solu.fourSum(new int[]{1,0,-1,0,-2,2}, 0);

        Assertions.assertEquals(3, found.size());
    }

    @Test
    public void test2() {
        lc18_4sum solu = new lc18_4sum();

        List<List<Integer>> found = solu.fourSum(new int[]{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}, 8);

        Assertions.assertEquals(1, found.size());
    }

    @Test
    public void test3() {
        lc18_4sum solu = new lc18_4sum();

        List<List<Integer>> found = solu.fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0);

        Assertions.assertEquals(8, found.size());
    }

    @Test
    public void test4() {
        lc18_4sum solu = new lc18_4sum();

        List<List<Integer>> found = solu.fourSum(new int[]{1,-2,-5,-4,-3,3,3,5}, -11);

        Assertions.assertEquals(1, found.size());
    }
}
