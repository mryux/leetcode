package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc289_GameofLife {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = getNearbyCount(board, i, j);

                if ((board[i][j] & 1) == 1) {
                    // Any live cell with fewer than two live neighbors dies as if caused by under-population.
                    // Any live cell with more than three live neighbors dies, as if by over-population.
                    if (count < 2 || count > 3) {
                        board[i][j] = 1;    // new state -> 01
                    }
                    else {
                        // Any live cell with two or three live neighbors lives on to the next generation.
                        board[i][j] = 3;    // new state -> 11
                    }
                }
                else {
                    // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
                    if (count == 3) {
                        board[i][j] = 2;    // new state -> 10
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }


    private int getNearbyCount(int[][] board, int y, int x) {
        int[] dx = new int[] {-1, 0, 1, -1, 1, -1, 0, 1 };
        int[] dy = new int[] {-1, -1, -1, 0, 0, 1, 1, 1 };
        int ret = 0;

        for (int i = 0; i < dx.length; i++) {
            int newx = x + dx[i];
            int newy = y + dy[i];

            if (newx >= 0 && newx < board[0].length
                    && newy >=0 && newy < board.length) {
                if ((board[newy][newx] & 1) == 1) {
                    ret++;
                }
            }
        }

        return ret;
    }

    @Test
    public void test01() {
        lc289_GameofLife solu = new lc289_GameofLife();

        int[][] board = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};

        solu.gameOfLife(board);
        Assertions.assertArrayEquals(new int[]{0,0,0}, board[0]);
        Assertions.assertArrayEquals(new int[]{1,0,1}, board[1]);
        Assertions.assertArrayEquals(new int[]{0,1,1}, board[2]);
        Assertions.assertArrayEquals(new int[]{0,1,0}, board[3]);
    }
}
