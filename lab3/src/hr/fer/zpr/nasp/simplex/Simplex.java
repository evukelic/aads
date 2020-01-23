package hr.fer.zpr.nasp.simplex;

/**
 * Class Simplex represents class which solves Simplex algorithm for a problem defined in advance.
 *
 * @author Elena Vukelic
 * */
public class Simplex {

    /** @param table Matrix */
    private double[][] table = new double[Constants.ROW_NUM][];
    /** @param maxRow Maximal row element */
    private int maxRow;
    /** @param maxColumn Maximal column element */
    private int maxColumn;
    /** @param pivotColumn Pivot column */
    private int pivotColumn;

    /** Default constructor for Simplex class. */
    public Simplex() {
        this.maxRow = Constants.ROW_NUM - 1;
        this.maxColumn = Constants.COLUMN_NUM - 1;
        Helper.initTable(this.table);
    }

    /** Method which starts Simplex algorithm and calls some helper functions to do so. */
    public void startSimplex() {
        Helper.printTable(this.table);
        while(true) {
            double minimumPivotColumn = this.minimalPivotColumn();

            if (minimumPivotColumn >= 0) {
                break;
            }

            int pivotRow = this.getPivotRow(this.pivotColumn);
            double pivot = this.table[pivotRow][this.pivotColumn];

            System.out.println("\nPivot je broj: " +
                                String.format("%.2f", pivot) + ", na lokaciji (" + pivotRow + "," + this.pivotColumn + ") -> " +
                                "(" + Constants.rows[pivotRow] + "," + Constants.columns[this.pivotColumn] + ")");

            this.switchSX(this.pivotColumn, pivotRow);
            this.calcPivotRow(pivotRow, pivot);
            this.calcOtherRows(pivotRow, this.pivotColumn);

            Helper.printTable(this.table);
        }
    }

    /** Method which searches for pivot column.
     * @return Index of pivot column */
    private double minimalPivotColumn() {
        this.pivotColumn = -1;
        double minimalPivotColumn = 0;
        for (int i=0; i<Constants.COLUMN_NUM; i++) {
            if (minimalPivotColumn > this.table[this.maxRow][i]) {
                minimalPivotColumn = this.table[this.maxRow][i];
                this.pivotColumn = i;
            }
        }
        return minimalPivotColumn;
    }

    /** Method which searches for pivot row.
     * @return Index of pivot row */
    private int getPivotRow(int pivotColumn) {
        double minimumPivotRow = -1;
        int pivotRow = -1;
        for (int i=0; i<Constants.ROW_NUM-1; i++) {
            double calculated = this.table[i][maxColumn] / this.table[i][pivotColumn];
            if (calculated < 0) {
                continue;
            }
            if (minimumPivotRow == -1 || minimumPivotRow > calculated) {
                minimumPivotRow = calculated;
                pivotRow = i;
            }
        }
        return pivotRow;
    }

    /** Method which fills out pivot row based on given pivot. */
    private void calcPivotRow(int pivotRow, double pivot) {
        for (int i=0; i<Constants.COLUMN_NUM; i++) {
            this.table[pivotRow][i] = this.table[pivotRow][i] / pivot;
        }
    }

    /** Method which fills out other rows based on given pivot row and column. */
    private void calcOtherRows(int pivotRow, int pivotColumn) {
        for (int i=0; i<Constants.ROW_NUM; i++) {
            if (i == pivotRow) {
                continue;
            }
            double pivot = this.table[i][pivotColumn];
            for (int j = 0; j < Constants.COLUMN_NUM; j++) {
                this.table[i][j] = this.table[i][j] - (pivot * this.table[pivotRow][j]);
            }
        }
    }

    /** Method which switches S with X.
     * @param pivotColumn Pivot column
     * @param pivotRow Pivot row */
    private void switchSX(int pivotColumn, int pivotRow) {
        if (Constants.columns[pivotColumn].contains("x")) {
            System.out.println("Stožerni razvoj: ");
            System.out.println(Constants.rows[pivotRow] + " izlazi iz rješenja, ulazi: " + Constants.columns[pivotColumn]);
            Constants.rows[pivotRow] = Constants.columns[pivotColumn];
        }
    }








}
