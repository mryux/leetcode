package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Given an array of integers temperatures represents the daily temperatures,
return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
If there is no future day for which this is possible, keep answer[i] == 0 instead.
 */
public class lc739_Daily_Temperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Deque<Integer> que = new ArrayDeque<>();

        // 使用单调栈保存的是从大到小的index值
        for (int i = 0; i < temperatures.length; i++) {
            while (que.size() > 0 && temperatures[i] > temperatures[que.peekLast()]) {
                int idx = que.pollLast();
                ans[idx] = i - idx;
            }
            que.add(i);
        }

        return ans;
    }

    @Test
    public void test01() {
        lc739_Daily_Temperatures solu = new lc739_Daily_Temperatures();

        Assertions.assertArrayEquals(new int[]{1,1,4,2,1,1,0,0}, solu.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73}));
        Assertions.assertArrayEquals(new int[]{1,1,1,0}, solu.dailyTemperatures(new int[]{30,40,50,60}));
        Assertions.assertArrayEquals(new int[]{1,1,0}, solu.dailyTemperatures(new int[]{30,60,90}));
    }
}
