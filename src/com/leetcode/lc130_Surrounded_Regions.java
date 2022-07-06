package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc130_Surrounded_Regions {
    public void solve(char[][] board) {
        // flood from 4-sides
        for (int i = 0; i < board.length; i++) {
            flood(board, i, 0);
            flood(board, i, board[0].length-1);
        }
        for (int i = 0; i < board[0].length; i++) {
            flood(board, 0, i);
            flood(board, board.length-1, i);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    private void flood(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return;

        if (board[row][col] != 'O')
            return;

        board[row][col] = '#';
        flood(board, row+1, col);
        flood(board, row-1, col);
        flood(board, row, col+1);
        flood(board, row, col-1);
    }

    @Test
    public void test01() {
        lc130_Surrounded_Regions solu = new lc130_Surrounded_Regions();
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        solu.solve(board);
        Assertions.assertArrayEquals(new char[]{'X', 'X', 'X', 'X'}, board[0]);
        Assertions.assertArrayEquals(new char[]{'X', 'X', 'X', 'X'}, board[1]);
        Assertions.assertArrayEquals(new char[]{'X', 'X', 'X', 'X'}, board[2]);
        Assertions.assertArrayEquals(new char[]{'X', 'O', 'X', 'X'}, board[3]);
    }
}
