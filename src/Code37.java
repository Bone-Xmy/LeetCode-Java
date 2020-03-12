public class Code37 {
    // box size
    int n = 3;
    // row size
    int N = n * n;

    int [][] rows = new int[N][N + 1];
    int [][] columns = new int[N][N + 1];
    int [][] boxes = new int[N][N + 1];

    char[][] board;

    boolean sudokuSolved = false;


    public void solveSudoku(char[][] board) {
        this.board = board;

        // init rows, columns and boxes
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j); // 记录已经存在值的行、列、块状态
                }
            }
        }

        backtrack(0, 0);
    }

    public void backtrack(int row, int col) {
    /*
    Backtracking
    */

        /**
         * 5    3	.	.	7	.	.	.	.
         * 6	.	.	1	9	5	.	.	.
         * .	9	8	.	.	.	.	6	.
         * 8	.	.	.	6	.	.	.	3
         * 4	.	.	8	.	3	.	.	1
         * 7	.	.	.	2	.	.	.	6
         * .	6	.	.	.	.	2	8	.
         * .	.	.	4	1	9	.	.	5
         * .	.	.	.	8	.	.	7	9
         */

        // if the cell is empty
        if (board[row][col] == '.') {
            // iterate over all numbers from 1 to 9
            for (int d = 1; d < 10; d++) { // 注意：如果cell从1~9都不能填写，则backtrack方法执行结束，方法栈出站
                if (couldPlace(d, row, col)) { // 8
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col); // 2,3      5,6,7
                    // if sudoku is solved, there is no need to backtrack
                    // since the single unique solution is promised

                    System.out.println("行：" + row + "; 列：" + col);
                    printResult(board);


                    if (!sudokuSolved) removeNumber(d, row, col);
                }
            }
        }
        else placeNextNumbers(row, col); // 0,1     4
    }

    private void printResult(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "\t");
                if (j == board[i].length - 1)
                    System.out.print("\n");
            }
        }
    }


    public boolean couldPlace(int d, int row, int col) {
    /*
    Check if one could place a number d in (row, col) cell
    */
        int idx = (row / n ) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    public void placeNumber(int d, int row, int col) {
    /*
    Place a number d in (row, col) cell
    */
        int idx = (row / n ) * n + col / n;

        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char)(d + '0');
    }

    public void placeNextNumbers(int row, int col) {
        System.out.println("上次判断cell ==> " + "(" + row + "," + col + ")");
        /*
        Call backtrack function in recursion
        to continue to place numbers
        till the moment we have a solution
        */

        // if we're in the last cell
        // that means we have the solution
        if ((col == N - 1) && (row == N - 1)) {
            sudokuSolved = true;
        }
        // if not yet
        else {
            // if we're in the end of the row
            // go to the next row
            if (col == N - 1) backtrack(row + 1, 0);
                // go to the next column
            else backtrack(row, col + 1);
        }
    }

    public void removeNumber(int d, int row, int col) {
        System.out.println("执行remove ==> " + "(" + row + "," + col + ")");
    /*
    Remove a number which didn't lead to a solution
    */
        int idx = (row / n ) * n + col / n;
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }

    public static void main(String[] args) {
        char[][] board = new char[9][9];

        board[0] = new char[] {'5','3','.','.','7','.','.','.','.'};
        board[1] = new char[] {'6','.','.','1','9','5','.','.','.'};
        board[2] = new char[] {'.','9','8','.','.','.','.','6','.'};
        board[3] = new char[] {'8','.','.','.','6','.','.','.','3'};
        board[4] = new char[] {'4','.','.','8','.','3','.','.','1'};
        board[5] = new char[] {'7','.','.','.','2','.','.','.','6'};
        board[6] = new char[] {'.','6','.','.','.','.','2','8','.'};
        board[7] = new char[] {'.','.','.','4','1','9','.','.','5'};
        board[8] = new char[] {'.','.','.','.','8','.','.','7','9'};

        Code37 code = new Code37();
        code.solveSudoku(board);
    }

}
