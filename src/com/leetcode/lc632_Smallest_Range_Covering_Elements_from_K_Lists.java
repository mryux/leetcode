package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class lc632_Smallest_Range_Covering_Elements_from_K_Lists {

    class Item {
        public int arrId;
        public int itemId;
        public int val;

        public Item(int arrId, int itemId, int val) {
            this.arrId = arrId;
            this.itemId = itemId;
            this.val = val;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums.size() == 1) {
            return new int[]{nums.get(0).get(0), nums.get(0).get(0)};
        }

        TreeSet<Item> set = new TreeSet<>((o1, o2) -> o1.val == o2.val ? o1.arrId - o2.arrId : o1.val - o2.val);
        int[] ans = new int[2];

        for (int i = 0; i < nums.size(); i++)
            set.add(new Item(i, 0, nums.get(i).get(0)));

        ans[0] = 0;
        ans[1] = Integer.MAX_VALUE;
        while (true) {
            Item small = set.pollFirst();
            Item large = set.last();

            // get ans
            if (large.val - small.val < ans[1] - ans[0]) {
                ans[0] = small.val;
                ans[1] = large.val;
            }

            // add next item to set
            int nextId = small.itemId+1;
            List<Integer> list = nums.get(small.arrId);
            if (nextId >= list.size())
                break;

            set.add(new Item(small.arrId, nextId, list.get(nextId)));
        }

        return ans;
    }

    @Test
    public void test01() {
        lc632_Smallest_Range_Covering_Elements_from_K_Lists solu = new lc632_Smallest_Range_Covering_Elements_from_K_Lists();
        List<List<Integer>> list = new ArrayList<>();

        list.add(Arrays.asList(4,10,15,24,26));
        list.add(Arrays.asList(0,9,12,20));
        list.add(Arrays.asList(5,18,22,30));
        Assertions.assertArrayEquals(new int[]{20,24}, solu.smallestRange(list));
    }

    @Test
    public void test02() {
        lc632_Smallest_Range_Covering_Elements_from_K_Lists solu = new lc632_Smallest_Range_Covering_Elements_from_K_Lists();
        List<List<Integer>> list = new ArrayList<>();

        list.add(Arrays.asList(1,2,3));
        list.add(Arrays.asList(1,2,3));
        list.add(Arrays.asList(1,2,3));
        Assertions.assertArrayEquals(new int[]{1,1}, solu.smallestRange(list));
    }

    @Test
    public void test03() {
        lc632_Smallest_Range_Covering_Elements_from_K_Lists solu = new lc632_Smallest_Range_Covering_Elements_from_K_Lists();
        List<List<Integer>> list = new ArrayList<>();

        list.add(Arrays.asList(1));
        Assertions.assertArrayEquals(new int[]{1,1}, solu.smallestRange(list));
    }
}
