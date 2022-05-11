package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class lc740_Delete_and_Earn {
    public int deleteAndEarn(int[] nums) {
        int max = 0;

        for (int v : nums) {
            max = Math.max(max, v);
        }

        int[] buf = new int[max+1];

        for (int v : nums) {
            buf[v] += v;
        }

        return rob_dp2(buf, max);
    }

    private int rob_dp2(int[] nums, int max) {
        int dp[]=new int[max+1];

        dp[0]=0;
        dp[1]=Math.max(dp[0],nums[1]);

        for(int i=2;i<=max;i++)
            dp[i]=Math.max(dp[i-1], dp[i-2]+nums[i]);
        return dp[max];
    }

    private int rob_dp(int[] nums) {
        int[][] dp = new int[nums.length+1][2];

        for (int i = nums.length-1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) {
                if (j == 1)
                    dp[i][j] = nums[i] + dp[i+1][0];
                else
                    dp[i][j] = Math.max(dp[i+1][1], dp[i+1][0]);
            }
        }

        return Math.max(dp[0][0], dp[0][1]);
    }

    private int visit(Map<Integer, Integer> map) {
        int ret = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int points = entry.getKey() * entry.getValue();
            Map<Integer, Integer> childMap = new HashMap<>(map);

            childMap.remove(key);
            childMap.remove(key+1);
            childMap.remove(key-1);
            ret = Math.max(ret, points + visit(childMap));
        }

        return ret;
    }

    @Test
    public void test01() {
        lc740_Delete_and_Earn solu = new lc740_Delete_and_Earn();

        Assertions.assertEquals(5578, solu.deleteAndEarn(new int[]{52,2,43,45,36,56,73,44,23,14,42,82,80,19,61,30,56,83,65,33,14,96,29,5,56,12,82,11,5,52,17,62,65,6,23,14,44,37,19,95,89,44,40,3,44,71,20,13,18,33,83,98,60,74,91,20,11,12,16,79,43,46,71,63,9,84,100,10,14,51,52,66,3,18,54,100,17,85,70,43,4,16,30,58,83,65,53,55,27,28,56,60,53,87,23,30,87,50,80,99,91,4,87,44,28,86,99,88,18,32,77,62,64,15,84,33,23,12,92,72,43,34,54,31,81,5,16,88,11,82,59,87,34,41,60,37,63,39,55,27,27,13,96,22,78,42,59,55,21,98,28,52,63,33,38,5,58,6,8,44,59,40,44,98,82,66,58,6,18,13,25,85,60,100,17,41,52,47,23,94,16,50,62,50,32,97,24,97,45,51,39,32,60,36,66,2,88,38,49,21}));
        Assertions.assertEquals(3451, solu.deleteAndEarn(new int[]{12,32,93,17,100,72,40,71,37,92,58,34,29,78,11,84,77,90,92,35,12,5,27,92,91,23,65,91,85,14,42,28,80,85,38,71,62,82,66,3,33,33,55,60,48,78,63,11,20,51,78,42,37,21,100,13,60,57,91,53,49,15,45,19,51,2,96,22,32,2,46,62,58,11,29,6,74,38,70,97,4,22,76,19,1,90,63,55,64,44,90,51,36,16,65,95,64,59,53,93}));
        Assertions.assertEquals(6, solu.deleteAndEarn(new int[]{3,4,2}));
        Assertions.assertEquals(9, solu.deleteAndEarn(new int[]{2,2,3,3,3,4}));
    }
}
