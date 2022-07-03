package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc639_Decode_Ways_II {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int modMax = 1000000007;
        char[] arr = s.toCharArray();
        int N = arr.length;
        long[] dp = new long[N+1];

        dp[N] = 1;
        for (int index = N-1; index >= 0; index--) {
            if (arr[index] == '0')
                continue;

            int top = arr[index] == '*' ? 9 : 1;

            dp[index] = countLong(dp[index+1], top, modMax);

            if (index+1 >= N)
                continue;

            if (arr[index] == '*') {
                top = 1;

                if (arr[index+1] == '*')
                    top = 15;
                else if (arr[index+1] <= '6')
                    top = 2;
            } else {
                top = 0;

                if (arr[index + 1] == '*') {
                    if (arr[index] < '2')
                        top = 9;    // 11 -- 19
                    else if (arr[index] == '2')
                        top = 6;    // 21 -- 26
                } else {
                    if (arr[index] < '2' || (arr[index] == '2' && arr[index + 1] <= '6'))
                        top = 1;
                }
            }
            dp[index] = (dp[index] + countLong(dp[index+2], top, modMax)) % modMax;
        }

        return (int)dp[0];
    }

    public int numDecodings_dfs(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int modMax = 1000000007;

        char[] arr = s.toCharArray();
        return visit(arr, 0, modMax);
    }

    /*
    simplify visit02
     */
    private int visit(char[] arr, int index, int modMax) {
        if (index == arr.length)
            return 1;

        if (arr[index] == '0')
            return 0;

        int ways = 0;
        int nextTop = arr[index] == '*' ? 9 : 1;

        ways = count(visit(arr, index+1, modMax), nextTop, modMax);

        if (index+1 < arr.length) {
            if (arr[index] == '*') {
                nextTop = 1;

                if (arr[index+1] == '*')
                    nextTop = 15;
                else if (arr[index+1] <= '6')
                    nextTop = 2;
            } else {
                nextTop = 0;

                if (arr[index + 1] == '*') {
                    if (arr[index] < '2')
                        nextTop = 9;    // 11 -- 19
                    else if (arr[index] == '2')
                        nextTop = 6;    // 21 -- 26
                } else {
                    if (arr[index] < '2' || (arr[index] == '2' && arr[index + 1] <= '6'))
                        nextTop = 1;
                }
            }
            ways += count(visit(arr, index+2, modMax), nextTop, modMax);
        }

        return ways % modMax;
    }

    /*
    find a solution with times.
     */
    private int visit02(char[] arr, int index, int modMax) {
        if (index == arr.length)
            return 1;

        if (arr[index] == '0')
            return 0;

        int ways = 0;

        if (arr[index] == '*') {
            ways = count(visit(arr, index+1, modMax), 9, modMax);

            if (index+1 < arr.length) {
                int top = 1;

                if (arr[index+1] == '*')
                    top = 15;
                else if (arr[index+1] <= '6')
                    top = 2;

                ways += count(visit(arr, index+2, modMax), top, modMax);
            }
        } else {
            ways = visit(arr, index+1, modMax);

            if (index+1 < arr.length) {
                int top = 0;

                if (arr[index + 1] == '*') {
                    if (arr[index] < '2')
                        top = 9;    // 11 -- 19
                    else if (arr[index] == '2')
                        top = 6;    // 21 -- 26
                } else {
                    if (arr[index] < '2' || (arr[index] == '2' && arr[index + 1] <= '6'))
                        top = 1;
                }

                ways += count(visit(arr, index + 2, modMax), top, modMax);
            }
        }

        return ways % modMax;
    }

    private long countLong(long c, int times, int modMax) {
        return (c * times) % modMax;
    }

    private int count(int c, int times, int modMax) {
        int ret = 0;

        for (int i = 0; i < times; i++) {
            ret += c;
            ret %= modMax;
        }

        return ret;
    }

    /*
    implement by brutal force.
     */
    private int visit01(char[] arr, int index) {
        if (index == arr.length)
            return 1;

        if (arr[index] == '0')
            return 0;

        int ways = 0;

        if (arr[index] == '*') {
            for (int i = 0; i < 9; i++) {
                arr[index] = (char)('1' + i);

                ways += visit01(arr, index);
            }
            arr[index] = '*';
        } else {
            ways = visit01(arr, index+1);

            if (index+1 < arr.length) {
                int top = 0;

                if (arr[index+1] == '*') {
                    if (arr[index] < '2')
                        top = 9;    // 11 -- 19
                    else if (arr[index] == '2')
                        top = 6;    // 21 -- 26
                } else {
                    if (arr[index] < '2' || (arr[index] == '2' && arr[index+1] <= '6'))
                        top = 1;
                }

                if (top > 0)
                    ways += top * visit01(arr, index+2);
            }
        }

        return ways;
    }

    @Test
    public void test01() {
        lc639_Decode_Ways_II solu = new lc639_Decode_Ways_II();

        Assertions.assertEquals(882201566, solu.numDecodings("1*6*7*1*9*6*2*9*2*3*3*6*3*2*2*4*7*2*9*6*0*6*4*4*1*6*9*0*5*9*2*5*7*7*0*6*9*7*1*5*5*9*3*0*4*9*2*6*2*5*7*6*1*9*4*5*8*4*7*4*2*7*1*2*1*9*1*3*0*6*"));
        Assertions.assertEquals(196465252, solu.numDecodings("7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*"));
        Assertions.assertEquals(11, solu.numDecodings("*1"));
        Assertions.assertEquals(9, solu.numDecodings("*"));
        Assertions.assertEquals(96, solu.numDecodings("**"));
        Assertions.assertEquals(18, solu.numDecodings("1*"));
        Assertions.assertEquals(15, solu.numDecodings("2*"));
    }
}
