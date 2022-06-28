package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc1306_Jump_Game_III {
    public boolean canReach(int[] arr, int start) {
        return visit(arr, start, new boolean[arr.length]);
    }

    private boolean visit(int[] arr, int curr, boolean[] visited) {
        if (curr < 0 || curr >= arr.length || visited[curr])
            return false;

        if (arr[curr] == 0)
            return true;

        visited[curr] = true;
        boolean ret = visit(arr, curr - arr[curr], visited)
                || visit(arr, curr + arr[curr], visited);
        visited[curr] = false;

        return ret;
    }

    @Test
    public void test01() {
        lc1306_Jump_Game_III solu = new lc1306_Jump_Game_III();

        Assertions.assertTrue(solu.canReach(new int[]{4,2,3,0,3,1,2}, 5));
        Assertions.assertTrue(solu.canReach(new int[]{4,2,3,0,3,1,2}, 0));
        Assertions.assertFalse(solu.canReach(new int[]{3,0,2,1,2}, 2));
    }
}
