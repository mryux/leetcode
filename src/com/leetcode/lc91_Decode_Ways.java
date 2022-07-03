package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc91_Decode_Ways {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;

        char[] arr = s.toCharArray();
        int N = arr.length;
        int[] dp = new int[N+1];

        dp[N] = 1;
        for (int i = N-1; i >= 0; i--) {
            if (arr[i] == '0')
                continue;

            dp[i] = dp[i+1];
            if (i+1 >= N)
                continue;

            if (arr[i] < '2' || (arr[i] == '2' && arr[i+1] <= '6'))
                dp[i] += dp[i+2];
        }

        return dp[0];
    }

    public int numDecodings_dfs(String s) {
        if (s == null || s.length() == 0)
            return 0;

        return visit(s.toCharArray(), 0);
    }

    private int visit(char[] arr, int curr) {
        if (curr == arr.length)
            return 1;

        if (arr[curr] == '0')
            return 0;

        int ways = visit(arr, curr+1);
        if (curr+1 >= arr.length)
            return ways;

        if (arr[curr] < '2' || (arr[curr] == '2' && arr[curr+1] <= '6'))
            ways += visit(arr, curr+2);

        return ways;
    }

    @Test
    public void test01() {
        lc91_Decode_Ways solu = new lc91_Decode_Ways();

        Assertions.assertEquals(2, solu.numDecodings("12"));
        Assertions.assertEquals(3, solu.numDecodings("226"));
        Assertions.assertEquals(0, solu.numDecodings("06"));
    }
}
