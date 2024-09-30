import java.awt.Point;

public class SudokuGrid {
    public static final int SIZE = 9;
    public static final int DIGIT_RANGE = 9;

    public int[][] grid;
    public int rEmpty = -1, cEmpty = -1;

    public SudokuGrid() {
        grid = new int[SIZE][SIZE];  // Initializes an empty grid
    }
 
    public SudokuGrid(int[][] sudoku) {
        grid = new int[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            System.arraycopy(sudoku[r], 0, grid[r], 0, SIZE);
        }
    }

    public SudokuGrid copy() {
        int[][] newGrid = new int[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                newGrid[r][c] = this.grid[r][c];
            }
        }
        return new SudokuGrid(newGrid);
    }

    public Point findEmptyCell() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c] == 0) {
                    return new Point(r, c);  
                }
            }
        }
        return null; 
    }

    public void print() {
        System.out.println("+-----------------+");
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (c == 0 || c == 3 || c == 6) {
                    System.out.print("|");
                }
                System.out.print(grid[r][c] == 0 ? " " : grid[r][c]);  
                if (c % 3 != 2 && c != SIZE - 1) {
                    System.out.print(" ");
                }
            } 
            System.out.print("|");
            System.out.println(); 
            if (r == 2 || r == 5 || r == 8) {
                System.out.println("+-----------------+");
            }
        }
        System.out.println();
    }

    public void fillCell(int r, int c, int d) {
        grid[r][c] = d;
    }

    public boolean givesConflict(int r, int c, int d) {
        return rowConflict(r, d) || colConflict(c, d) || boxConflict(r, c, d);
    }

    private boolean rowConflict(int r, int d) {
        for (int c = 0; c < SIZE; c++) {
            if (grid[r][c] == d) {
                return true;
            }
        }
        return false;
    }

    private boolean colConflict(int c, int d) {
        for (int r = 0; r < SIZE; r++) {
            if (grid[r][c] == d) {
                return true;
            }
        }
        return false;
    }

    private boolean boxConflict(int r, int c, int d) {
        int rowStart = (r / 3) * 3;
        int colStart = (c / 3) * 3;
        for (int rq = 0; rq < 3; rq++) {
            for (int cq = 0; cq < 3; cq++) {
                if (grid[rowStart + rq][colStart + cq] == d) {
                    return true;
                }
            }
        }
        return false;
    }
}