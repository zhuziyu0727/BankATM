import java.util.ArrayList;

public class Conversion {

    /**
     * Change the type of input variable.
     * Assume each single element in "original" has the same length.
     */
    public static String[][] parseArrayList(ArrayList<ArrayList<String>> original) {
        int rowNum = original.size();
        int colNum = original.get(0).size();
        String[][] copy = new String[rowNum][colNum];
        for (int row=0; row<rowNum; row++) {
            for (int col=0; col<colNum; col++) {
                copy[row][col] = original.get(row).get(col);
            }
        }
        return copy;
    }
}
