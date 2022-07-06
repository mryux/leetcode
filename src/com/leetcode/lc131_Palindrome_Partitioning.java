package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class lc131_Palindrome_Partitioning {
    public List<List<String>> partition(String s) {
        char[] arr = s.toCharArray();
        boolean[][] dp = getDp(arr);
        List<List<String>> ans = new ArrayList<>();

        visit(arr, 0, arr.length, dp, new ArrayList<>(), ans);
        return ans;
    }

    private void visit(char[] arr, int start, int end, boolean[][] dp, List<String> path, List<List<String>> ans) {
        if (start >= end) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < end; i++) {
            if (dp[start][i]) {
                path.add(String.valueOf(arr, start, i-start+1));
                visit(arr, i+1, end, dp, path, ans);
                path.remove(path.size()-1);
            }
        }
    }

    private boolean[][] getDp(char[] arr) {
        int N = arr.length;
        boolean[][] dp = new boolean[N][N];

        for (int i = 0; i < N-1; i++) {
            dp[i][i] = true;
            dp[i][i+1] = (arr[i] == arr[i+1]);
        }
        dp[N-1][N-1] = true;

        for (int i = N-3; i >= 0; i--) {
            for (int j = N-1; j > i+1; j--) {
                dp[i][j] = (dp[i+1][j-1] && arr[i] == arr[j]);
            }
        }

        return dp;
    }

    @Test
    public void test01() {
        lc131_Palindrome_Partitioning solu = new lc131_Palindrome_Partitioning();

        List<List<String>> ans = solu.partition("aab");
        Assertions.assertEquals(2, ans.size());
        Assertions.assertArrayEquals(new String[]{"a", "a", "b"}, Common.toArray(String.class, ans.get(0)));
        Assertions.assertArrayEquals(new String[]{"aa", "b"}, Common.toArray(String.class, ans.get(1)));
    }
}
