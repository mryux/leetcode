package com.lintcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc_92_Backpack {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param a: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] a) {
//        return visit(a, 0, m);
        return visit_dp(m, a);
    }

    private int visit_dp(int max, int[] arr) {
        int N = arr.length;
        int[] dp = new int[max+1];

        for (int i = 0; i < N-1; i++) {
            for (int j = max; j >= arr[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-arr[i]]+arr[i]);
            }
        }

        // 最后一行的dp[max]可以直接取出来，不需要再走一遍循环
        if (max > arr[N-1])
            return Math.max(dp[max], dp[max-arr[N-1]]+arr[N-1]);
        return dp[max];
    }

    private int visit_dp_x2(int max, int[] arr) {
        int N = arr.length;
        int[][] dp = new int[2][max+1];

        for (int i = 0; i <= max; i++) {
            if (i >= arr[0])
                dp[0][i] = arr[0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= max; j++) {
                dp[i&1][j] = dp[(i-1)&1][j];
                if (j >= arr[i])
                    dp[i&1][j] = Math.max(dp[i&1][j], dp[(i-1)&1][j-arr[i]]+arr[i]);
            }
        }

        return dp[(N-1)&1][max];
    }

    private int visit_dp1(int max, int[] arr) {
        int N = arr.length;
        int[][] dp = new int[N][max+1];

        for (int i = 0; i <= max; i++) {
            if (i >= arr[0])
                dp[0][i] = arr[0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= max; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= arr[i])
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-arr[i]]+arr[i]);
            }
        }

        return dp[N-1][max];
    }

    private int visit_dp_fromdfs(int max, int[] arr) {
        int[][] dp = new int[arr.length+1][max+1];

        for (int i = arr.length-1; i >= 0; i--) {
            for (int j = 0; j <= max; j++) {
                dp[i][j] = dp[i+1][j];
                if (j >= arr[i])
                    dp[i][j] = Math.max(dp[i][j], dp[i+1][j-arr[i]]+arr[i]);
            }
        }

        return dp[0][max];
    }

    private int visit(int[] arr, int idx, int rest) {
        if (idx >= arr.length) {
            return 0;
        }

        int no = visit(arr, idx+1, rest);
        int yes = 0;

        if (arr[idx] <= rest)
            yes = visit(arr, idx+1, rest - arr[idx]) + arr[idx];

        return Math.max(no, yes);
    }

    @Test
    public void test01() {
        lc_92_Backpack solu = new lc_92_Backpack();

        Assertions.assertEquals(9, solu.backPack(10, new int[]{3,4,8,5}));
        Assertions.assertEquals(12, solu.backPack(12, new int[]{2,3,5,7}));
    }
}
