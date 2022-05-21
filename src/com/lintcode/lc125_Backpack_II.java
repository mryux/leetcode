package com.lintcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc125_Backpack_II {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param a: Given n items with size A[i]
     * @param v: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] a, int[] v) {
//        return visit(a, v, 0, m);
        return visit_dp(a, v, m);
    }

    private int visit_dp(int[] a, int[] v, int target) {
        int[][] dp = new int[a.length+1][target+1];

        for (int i = a.length-1; i >= 0; i--) {
            for (int j = 0; j <= target; j++) {
                int no = dp[i+1][j];
                int yes = j >= a[i] ? v[i] + dp[i+1][j-a[i]] : 0;

                dp[i][j] = Math.max(no, yes);
            }
        }

        return dp[0][target];
    }

    private int visit(int[] a, int[] v, int curr, int target) {
        if (curr >= a.length)
            return 0;

        int no = visit(a, v, curr+1, target);
        int yes = 0;

        if (target >= a[curr])
            yes = v[curr] + visit(a, v, curr+1, target-a[curr]);

        return Math.max(no, yes);
    }

    @Test
    public void test01() {
        lc125_Backpack_II solu = new lc125_Backpack_II();

        Assertions.assertEquals(9, solu.backPackII(10, new int[]{2,3,5,7}, new int[]{1,5,2,4}));
        Assertions.assertEquals(10, solu.backPackII(10, new int[]{2,3,8}, new int[]{2,5,8}));
    }
}
