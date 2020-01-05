import java.util.Arrays;
import java.util.List;

public class BattleField {


    private static int checkRaw(int[][] field, int i, int j) {
        int len = 0;
        while (true) {
            if (field[i][j + len] == 1) {
                //field[i][j + len] = 0;
                len++;
            } else break;
        }
        return len;
    }

    private static int checkColumn(int[][] field, int i, int j) {
        int len = 0;
        while (true) {
            if (field[i + len][j] == 1) {
                //field[i + len][j] = 0;
                len++;
            } else break;
        }
        return len;
    }

    private static void setZeroColumn(int[][] field, int i, int j, int len) {
        while (len >= 0) {
            field[i + len][j] = 0;
            --len;
        }
    }

    private static void setZeroRaw(int[][] field, int i, int j, int len) {
        while (len >= 0) {
            field[i][j + len] = 0;
            --len;
        }
    }
    private static void checkNeigboursRaw(int[][] field,int i,int j, int len){

    }
    public static boolean fieldValidator(int[][] field) {
        int[][] battleship = {{1}, {4}};
        int[][] cruiser = {{2}, {3}};
        int[][] destroyer = {{3}, {2}};
        int[][] submarines = {{4}, {1}};
        int d = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1) {
                    int x = checkColumn(field, i, j);
                    int y = checkRaw(field, i, j);
                    if (x > y)setZeroColumn(field,i,j,x);
                    else setZeroRaw(field,i,j,y);
                    System.out.println(Math.max(x,y));
                }
            }
        }
        if (d != 20)
            return false;


        return true;
    }

    private static int[][] battleField = {
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public static void main(String[] args) {
        fieldValidator(battleField);
    }
}