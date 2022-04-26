package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc494_Target_Sum {
    /*
    You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
    For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
    Return the number of different expressions that you can build, which evaluates to target.
     */
    public int findTargetSumWays1(int[] nums, int target) {
        return visit1(nums, 0, 0, target, new HashMap<String, Integer>());
    }

    private int visit1(int[] nums, int idx, int curr, int target, Map<String, Integer> map) {
        String key = "" + idx + "_" + curr;

        if (map.containsKey(key))
            return map.get(key);

        if (curr == target && idx == nums.length) {
            map.put(key, 1);
            return 1;
        }

        if (idx >= nums.length)
            return 0;

        int v = visit1(nums, idx+1, curr + nums[idx], target, map)
                + visit1(nums, idx+1, curr - nums[idx], target, map);
        map.put(key, v);
        return v;
    }

    public int findTargetSumWays_dp1(int[] nums, int target) {
        int total = Arrays.stream(nums).sum();
        if (target < -total || target > total)
            return 0;

        int[][] dp = new int[nums.length+1][total*2+1];

        dp[nums.length][target+total] = 1;
        for (int row = nums.length-1; row >= 0; row--) {
            for (int col = -total; col <= total; col++) {
                if (dp[row+1][col+total] > 0) {
                    if (col+total-nums[row] >= 0)
                        dp[row][col+total-nums[row]] += dp[row+1][col+total];
                    if (col+total+nums[row] <= total*2)
                        dp[row][col+total+nums[row]] += dp[row+1][col+total];
                }
            }
        }

        return dp[0][total];
    }

    /*
     findTargetSumWays_dp2 is slower than findTargetSumWays_dp
     */
    public int findTargetSumWays_dp2(int[] nums, int target) {
        int total = Arrays.stream(nums).sum();
        if (target < -total || target > total)
            return 0;

        int[][] dp = new int[nums.length+1][total*2+1];
        Set<Integer> set = new HashSet<>();

        dp[nums.length][target+total] = 1;
        set.add(target);
        for (int row = nums.length-1; row >= 0; row--) {
            int[] arr = toArray(set);

            set.clear();
            for (int col : arr)
            {
                if (col+total-nums[row] >= 0) {
                    dp[row][col+total-nums[row]] += dp[row+1][col+total];
                    if (!set.contains(col-nums[row]))
                        set.add(col-nums[row]);
                }
                if (col+total+nums[row] <= total*2) {
                    dp[row][col+total+nums[row]] += dp[row+1][col+total];
                    if (!set.contains(col+nums[row]))
                        set.add(col+nums[row]);
                }
            }
        }

        return dp[0][total];
    }

    private int[] toArray(Set<Integer> set) {
        int[] arr = new int[set.size()];
        int i = 0;

        for (int v : set) {
            arr[i++] = v;
        }

        return arr;
    }

    public int findTargetSumWays(int[] nums, int target) {
        return visit2(nums, 0, target);
    }
    private int visit2(int[] nums, int idx, int rest) {
        if (rest == 0  && idx == nums.length) {
            return 1;
        }

        if (idx >= nums.length)
            return 0;

        return visit2(nums, idx+1, rest + nums[idx])
                + visit2(nums, idx+1, rest - nums[idx]);
    }

    public int findTargetSumWays_dp(int[] nums, int target) {
        int total = Arrays.stream(nums).sum();
        if (target < -total || target > total)
            return 0;

        int[][] dp = new int[nums.length+1][total*2+1];

        dp[nums.length][total] = 1;
        for (int row = nums.length-1; row >= 0; row--) {
            for (int rest = -total; rest <= total; rest++) {
                int col = rest+total;

                if (col+nums[row] <= total*2)
                    dp[row][col] += dp[row+1][col+nums[row]];
                if (col-nums[row] >= 0)
                    dp[row][col] += dp[row+1][col-nums[row]];
            }
        }

        return dp[0][total+target];
    }

    @Test
    public void test01() {
        lc494_Target_Sum solu = new lc494_Target_Sum();

        Assertions.assertEquals(5, solu.findTargetSumWays_dp(new int[]{1,1,1,1,1}, 3));
        Assertions.assertEquals(1, solu.findTargetSumWays_dp(new int[]{1}, 1));
        Assertions.assertEquals(0, solu.findTargetSumWays_dp(new int[]{1}, 2));
        Assertions.assertEquals(2, solu.findTargetSumWays_dp(new int[]{1,0}, 1));
        Assertions.assertEquals(0, solu.findTargetSumWays_dp(new int[]{100}, -200));
        Assertions.assertEquals(2, solu.findTargetSumWays_dp(new int[]{1,2,1}, 0));
    }
}
