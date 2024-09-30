import java.awt.Point;

/**
 * SudokuSolver.
 * 
 * 
 * 
 * @author Milan Versteegh
 * @ID 2136279
 * @author Egor Bryantsev
 * @ID 2087782
 */

public class SudokuSolver {
    private SudokuGrid grid;

    public SudokuSolver(SudokuGrid grid) {
        this.grid = grid; 
    }

    public boolean solve() {
        Point emptyCell = grid.findEmptyCell();
        if (emptyCell == null) {
            return true;
        }
        int r = emptyCell.x;
        int c = emptyCell.y;
        for(int d = 1; d <= 9; d++) {
            if (!grid.givesConflict(r, c, d)) {
                grid.fillCell(r, c, d);
                if (solve()) {
                    return true;
                }
                grid.fillCell(r, c, 0);
            }
        }
        return false;
    }
    
    public void solveIt() {
        if (solve()) {
            System.out.println("Solution found:");
            grid.print();
        } else {
            System.out.println("No solution");
        }
    }

    public static void main(String[] args) {
        int[][] sudoku = {
            {0, 0, 0, 0, 0, 0, 0, 0, 9},
            {5, 9, 0, 0, 0, 0, 3, 0, 0},
            {4, 0, 0, 0, 6, 0, 0, 0, 5},
            {0, 0, 2, 0, 0, 0, 8, 7, 0},
            {0, 0, 6, 0, 0, 4, 0, 0, 0},
            {0, 3, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 0, 6, 0, 0, 0, 3, 2},
            {0, 0, 0, 3, 0, 0, 0, 1, 0},
            {0, 0, 0, 5, 4, 9, 0, 0, 0} 
        };

        SudokuGrid grid = new SudokuGrid(sudoku);
        SudokuSolver solver = new SudokuSolver(grid);

        solver.solveIt();
    }
} 