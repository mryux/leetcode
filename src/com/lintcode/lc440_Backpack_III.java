package com.lintcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc440_Backpack_III {
    /*
    Given n kinds of items, and each kind of item has an infinite number available. The i-th item has size A[i] and value V[i].
    Also given a backpack with size m. What is the maximum value you can put into the backpack?
     */
    /**
     * @param a: an integer array
     * @param v: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] a, int[] v, int m) {
//        return visit(a, v, 0, m);
        return visit_dp(a, v, m);
    }

    private int visit_dp(int[] a, int[] v, int m) {
        int N = a.length;
        int[] dp = new int[m+1];

        for (int i = 0; i < N; i++) {
            for (int j = a[i]; j <= m; j++) {
                dp[j] = Math.max(dp[j], dp[j-a[i]]+v[i]);
            }
        }

        return dp[m];
    }

    private int visit_dp2(int[] a, int[] v, int m) {
        int N = a.length;
        int[][] dp = new int[N][m+1];

        for (int i = 0; i <= m; i++) {
            dp[0][i] = (i / a[0])*v[0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i-1][j], j >= a[i] ? dp[i][j-a[i]]+v[i] : 0);
            }
        }

        return dp[N-1][m];
    }

    private int visit(int[] a, int[] v, int curr, int target) {
        if (curr >= a.length)
            return 0;

        int max = 0;

        for (int i = curr; i < a.length; i++) {
            for (int j = 0; j <= target/a[i]; j++) {
                max = Math.max(max, visit(a, v, curr+1, target-a[i]*j) + v[i]*j);
            }
        }

        return max;
    }

    @Test
    public void test01() {
        lc440_Backpack_III solu = new lc440_Backpack_III();

        Assertions.assertEquals(2200, solu.backPackIII(new int[]{95,75,23,73,50,22,6,57,89,98}, new int[]{89,59,19,43,100,72,44,16,7,64}, 300));
        Assertions.assertEquals(15, solu.backPackIII(new int[]{2, 3, 5, 7}, new int[]{1, 5, 2, 4}, 10));
        Assertions.assertEquals(5, solu.backPackIII(new int[]{1, 2, 3}, new int[]{1, 2, 3}, 5));
    }
}
