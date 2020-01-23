package hr.fer.zpr.nasp.simplex;

/**
 * Class Helper represents helper class with static helper methods such as initializing and printing out table.
 *
 * @author Elena Vukelic
 * */
public class Helper {

    /** Static method for printing out table.
     * @param table Table which will be printed out
     * */
    public static void printTable(double[][] table) {
        System.out.print("\nTablica:\n");
        System.out.print("\t\t\t");
        for (int i = 0; i < Constants.columns.length; i++) {
            System.out.print(Constants.columns[i] + "\t\t\t\t");
        }
        System.out.println();
        for (int i = 0; i < Constants.ROW_NUM; i++) {
            System.out.print(Constants.rows[i] + "\t\t\t");
            for (int j = 0; j < Constants.COLUMN_NUM; j++) {
                System.out.print(String.format("%.2f", table[i][j]) + "\t\t\t");
            }
            System.out.println();
        }
    }

    /** Method which initializes table with predefined input.
     * @param table Table which will be initialized */
    public static void initTable(double[][] table) {
        table[0] = new double[] { 6, 8, 1, 0, 0, 0, 36 };
        table[1] = new double[] { 200, 400, 0, 1, 0, 0, 1600 };
        table[2] = new double[] { -6, -12, 0, 0, 1, 0, -20 };
        table[3] = new double[] { -1000, -1800, 0, 0, 0, 1, 0 };
    }
}
