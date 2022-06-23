package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc37_Sudoku_Solver {
    public void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] blocks = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;

                int v = board[i][j] - '0';

                rows[i][v] = true;
                cols[j][v] = true;
                blocks[i/3*3+j/3][v] = true;
            }
        }

        visit(board, 0, 0, rows, cols, blocks);
    }

    private boolean visit(char[][] board, int row, int col, boolean[][] rows, boolean[][] cols, boolean[][] blocks) {
        int N = blocks.length;

        if (row == N)
            return true;

        int nextRow = col == N-1 ? row + 1 : row;
        int nextCol = col == N-1 ? 0 : col+1;

        if (board[row][col] != '.')
            return visit(board, nextRow, nextCol, rows, cols, blocks);

        for (int v = 1; v <= 9; v++) {
            int blockNo = row/3*3 + col/3;

            if (rows[row][v] || cols[col][v] || blocks[blockNo][v])
                continue;

            board[row][col] = (char)('0' + v);
            rows[row][v] = true;
            cols[col][v] = true;
            blocks[blockNo][v] = true;

            if (visit(board, nextRow, nextCol, rows, cols, blocks))
                return true;

            board[row][col] = '.';
            rows[row][v] = false;
            cols[col][v] = false;
            blocks[blockNo][v] = false;
        }

        return false;
    }

    @Test
    public void test01() {
        lc37_Sudoku_Solver solu = new lc37_Sudoku_Solver();

        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        solu.solveSudoku(board);
        Assertions.assertTrue(new lc36_Valid_Sudoku().isValidSudoku(board));
    }
}
