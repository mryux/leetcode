package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc36_Valid_Sudoku {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0)
            return false;

        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] blocks = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;

                int v = board[i][j] - '0';
                int blockNo = i/3*3+j/3;

                if (rows[i][v] || cols[j][v] || blocks[blockNo][v])
                    return false;

                rows[i][v] = true;
                cols[j][v] = true;
                blocks[blockNo][v] = true;
            }
        }

        return true;
    }

    @Test
    public void test01() {
        lc36_Valid_Sudoku solu = new lc36_Valid_Sudoku();

        Assertions.assertTrue(solu.isValidSudoku(new char[][] {
                 {'5','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}
        }));

        Assertions.assertFalse(solu.isValidSudoku(new char[][] {
                 {'8','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}
        }));
    }
}
