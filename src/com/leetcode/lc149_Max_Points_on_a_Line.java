package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class lc149_Max_Points_on_a_Line {
    public int maxPoints(int[][] points) {
        int ans = 0;

        for (int i = 0; i < points.length; i++) {
            int samePoint = 0;
            int sameX = 0;
            int sameY = 0;
            int sameDeltaXY = 0;
            Map<String, Integer> map = new HashMap<>();

            for (int j = i; j < points.length; j++) {
                if (points[j][0] == points[i][0] && points[j][1] == points[i][1])
                    samePoint++;
                else if (points[j][0] == points[i][0])
                    sameX++;
                else if (points[j][1] == points[i][1])
                    sameY++;
                else {
                    int deltaX = points[j][0] - points[i][0];
                    int deltaY = points[j][1] - points[i][1];
                    int gcd = gcd(deltaX, deltaY);

                    String key = "" + deltaX/gcd + "_" + deltaY/gcd;

                    map.put(key, map.getOrDefault(key, 0) + 1);
                    sameDeltaXY = Math.max(sameDeltaXY, map.get(key));
                }
            }

            int max = Math.max(Math.max(sameX, sameY), sameDeltaXY) + samePoint;

            ans = Math.max(ans, max);
        }

        return ans;
    }

    // greatest common divisor
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }

    @Test
    public void test_gcd() {
        Assertions.assertEquals(5, gcd(5,10));
        Assertions.assertEquals(2, gcd(6,8));
        Assertions.assertEquals(6, gcd(30,24));
    }

    @Test
    public void test01() {
        lc149_Max_Points_on_a_Line solu = new lc149_Max_Points_on_a_Line();

        Assertions.assertEquals(2, solu.maxPoints(new int[][] {
                {1,0},{0,0}
        }));
        Assertions.assertEquals(3, solu.maxPoints(new int[][] {
                {1,1},{2,2},{3,3}
        }));
        Assertions.assertEquals(4, solu.maxPoints(new int[][] {
                {1,1},{3,2},{5,3},{4,1},{2,3},{1,4}
        }));
    }
}
