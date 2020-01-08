/*
3 kyu codewars
Write a method that takes a field for well-known board game "Battleship" as
 an argument and returns true if it has a valid disposition of ships,
false otherwise. Argument is guaranteed to be 10*10 two-dimension array.
 Elements in the array are numbers, 0 if the cell is free and 1 if occupied by ship.

        Battleship (also Battleships or Sea Battle) is a guessing game for two players.
         Each player has a 10x10 grid containing several "ships" and objective is to destroy
         enemy's forces by targetting individual
        cells on his field. The ship occupies one or more cells in the grid. Size and number
        of ships may differ from version to version. In this kata we will use Soviet/Russian
         version of the game.
         efore the game begins, players set up the board and place the ships accordingly to
          the following rules:
There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers (size 2)
 and 4 submarines (size 1). Any additional ships are not allowed, as well as missing ships.
Each ship must be a straight line, except for submarines, which are just single cell.
The ship cannot overlap or be in contact with any other ship, neither by edge nor by corner.
         */

public class BattleField {

    private static int checkRaw(int[][] field, int i, int j) {
        int len = 0;
        while (true) {
            if (j + len >= field.length) break;
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
            if (i + len >= field.length) break;
            if (field[i + len][j] == 1) {
                //field[i + len][j] = 0;
                len++;
            } else break;
        }
        return len;
    }

    private static void setZeroColumn(int[][] field, int i, int j, int len) {
        --len;
        while (len >= 0) {
            field[i + len][j] = 0;
            --len;
        }
    }

    private static void setZeroRaw(int[][] field, int i, int j, int len) {
        --len;
        while (len >= 0) {
            field[i][j + len] = 0;
            --len;
        }
    }

    private static boolean checkNeigboursColumn(int[][] field, int i, int j, int len) {
        --len;
        while (len >= 0) {
            if (i - 1 + len >= 0 && j - 1 >= 0) {
                if (field[i - 1 + len][j - 1] == 1) return false;
            }
            if (i - 1 + len >= 0 && j + 1 < field.length) {
                if (field[i - 1 + len][j + 1] == 1) return false;
            }
            if (i + len + 1 < field.length && j - 1 >= 0) {
                if (field[i + 1 + len][j - 1] == 1) return false;
            }
            if (i + len + 1 < field.length && j + 1 < field.length) {
                if (field[i + 1 + len][j + 1] == 1) return false;
            }
            --len;
        }
        return true;
    }

    private static boolean checkNeigboursRaw(int[][] field, int i, int j, int len) {
        --len;
        while (len >= 0) {

            if (i - 1 >= 0 && j + len - 1 >= 0) {
                if (field[i - 1][j + len - 1] == 1) return false;
            }
            if (i - 1 >= 0 && j + len + 1 < field[i].length) {
                if (field[i - 1][j + len + 1] == 1) return false;
            }
            if (i + 1 < field[i].length && j + len - 1 >= 0) {

                if (field[i + 1][j + len - 1] == 1) return false;
            }
            if (i + 1 < field[i].length && j + len + 1 < field[i].length) {
                if (field[i + 1][j + len + 1] == 1) return false;
            }
            --len;
        }
        return true;
    }

    public static boolean fieldValidator(int[][] field) {

        int b = 1, c = 2, d = 3, s = 4;
        int h = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1) {
                    int y = checkColumn(field, i, j);
                    int x = checkRaw(field, i, j);
                    switch (Math.max(x, y)) {
                        case 1:
                            s--;
                            if (s < 0) return false;
                            break;
                        case 2:
                            d--;
                            if (d < 0) return false;
                            break;
                        case 3:
                            c--;
                            if (c < 0) return false;
                            break;
                        case 4:
                            b--;
                            if (b < 0) return false;
                            break;
                        default:
                            return false;
                    }
                    if (x < y) {
                        if (!checkNeigboursColumn(field, i, j, y)) return false;
                        setZeroColumn(field, i, j, y);
                    } else {
                        if (!checkNeigboursRaw(field, i, j, x)) return false;
                        setZeroRaw(field, i, j, x);
                    }
                    h += Math.max(x, y);
                }
            }
        }

        return h == 20;
    }

    private static int[][] battleField = {
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};


    public static void main(String[] args) {
        System.out.println(fieldValidator(battleField));
    }
}