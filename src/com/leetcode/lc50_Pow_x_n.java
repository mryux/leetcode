package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc50_Pow_x_n {
    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE && x > 1)
            return 0.0;

        if (n < 0) {
            x = 1/x;
            n = -n;
        }

        double ans = 1.0;
        int mask = 1;

        while (n > 0) {
            if ((n & mask) > 0) {
                ans *= x;
                n &= ~mask;
            }
            x *= x;
            mask <<= 1;
        }

        return ans;
    }

    public double myPow_dfs(double x, int n) {
        if(n==0)
            return 1;

        double temp = myPow_dfs(x,n/2);
        if(n%2 == 0)
            return temp*temp;

        if( n>0)
            return x * temp* temp;

        return (temp * temp)/x;
    }

    @Test
    public void test01() {
        lc50_Pow_x_n solu = new lc50_Pow_x_n();

        Assertions.assertEquals(0.0, solu.myPow(2.0, Integer.MIN_VALUE));
        Assertions.assertEquals(1024.0, solu.myPow(2.0, 10));
        //Assertions.assertEquals(9.261, solu.myPow(2.1, 3));
        //Assertions.assertEquals(0.25, solu.myPow(2.0, -2));
    }
}
