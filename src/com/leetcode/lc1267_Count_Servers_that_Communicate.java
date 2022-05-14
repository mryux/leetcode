package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc1267_Count_Servers_that_Communicate {
    public int countServers(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[] rows = new int[N];
        int[] cols = new int[M];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == 1) {
                    rows[row]++;
                    cols[col]++;
                }
            }
        }

        int ans = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == 1) {
                    if (rows[row] > 1 || cols[col] > 1)
                        ans++;
                }
            }
        }

        return ans;
    }

    public int countServers2(int[][] grid) {
        int ans = 0;

        for (int row = 0; row < grid.length; row++) {
            int count = 0;

            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1)
                    count++;
            }

            if (count > 1) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (grid[row][col] == 1)
                        grid[row][col] = 2;
                }
                ans += count;
            }
        }

        for (int col = 0; col < grid[0].length; col++) {
            int count1 = 0;
            int count2 = 0;

            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1)
                    count1++;
                else if (grid[row][col] == 2)
                    count2++;
            }

            if (count1 > 1)
                ans += count1;
            else if (count1 == 1) {
                if (count2 > 0)
                    ans += count1;
            }
        }

        return ans;
    }

    @Test
    public void test01() {
        lc1267_Count_Servers_that_Communicate solu = new lc1267_Count_Servers_that_Communicate();

        Assertions.assertEquals(3, solu.countServers(new int[][]{
                {1,0,0,1,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
        }));
        Assertions.assertEquals(0, solu.countServers(new int[][]{
                {1,0},
                {0,1}
        }));
        Assertions.assertEquals(3, solu.countServers(new int[][]{
                {1,0},
                {1,1}
        }));
        Assertions.assertEquals(4, solu.countServers(new int[][]{
                {1,1,0,0},
                {0,0,1,0},
                {0,0,1,0},
                {0,0,0,1}
        }));
    }
}
