package Lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static Scanner scan = new Scanner(System.in);
    private static Random random = new Random();
    private int fieldSize;
    private static final int dotsToWin = 3;
    private static char[][] playField;
    private static final char emptyDot = '*';
    private char humanDot = 'X';
    private char computerDot = 'O';
    private static char[] dotSymbols = {'X', 'O'};
    private static final int MIN_FIELD_SIZE = 3;

    private enum Mode {HUMAN, COMPUTER}

    ;
    private static int MIN_DOTS_TO_CHECK = 2;


    public void play (int userFieldSize, char userDotSymbol) {

        setFieldSize(userFieldSize);
        setDotSymbols(userDotSymbol);

        initMap();
        printMap();

        while (true) {
            humanStep();
            printMap();
            if (checkWin(humanDot)) {
                System.out.println("Вы выиграли!");
                break;
            }
            if (fieldIsFull()) {
                System.out.println("Ничья");
                break;
            }
            computerStep();
            System.out.println("Ход компьютера:");
            printMap();
            if (checkWin(computerDot)) {
                System.out.println("Вы проиграли");
                break;
            }
            if (fieldIsFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    private void computerStep() {
        if (!findBestStep()) {
            int x;
            int y;
            do {
                x = random.nextInt(fieldSize);
                y = random.nextInt(fieldSize);
            } while (!checkXY(x, y, Mode.COMPUTER));
            playField[x][y] = computerDot;
        }
    }


    private boolean findBestStep() {

        if (dotsToWin < MIN_DOTS_TO_CHECK) {
            return false;
        } else {
            for (int i = dotsToWin; i > 1; i--) {
                if (checkAllLinesForNextStep(i, Mode.COMPUTER) || checkAllLinesForNextStep(i, Mode.HUMAN)) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean checkAllLinesForNextStep(int stepsToCheck, Mode mode) {

        for (int j = 0; j < fieldSize; j++) {
            for (int k = 0; k < fieldSize; k++) {
                if (checkLineForNextStep(computerDot, mode, stepsToCheck, j, k, 1, 0) ||
                        checkLineForNextStep(computerDot, mode, stepsToCheck, j, k, 0, 1) ||
                        checkLineForNextStep(computerDot, mode, stepsToCheck, j, k, 1, 1) ||
                        checkLineForNextStep(computerDot, mode, stepsToCheck, j, k, -1, 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLineForNextStep(char dotSymbolToSet, Mode mode, int stepsToCheck, int x, int y, int dx, int dy) {
        if (checkBoundary(x, y, dx, dy)) {
            int emptyFields = 0;
            int[] x1 = new int[dotsToWin];
            int[] y1 = new int[dotsToWin];
            for (int i = 0; i < dotsToWin; i++) {
                if (!foundBreakDot(mode, playField[x][y]) ||
                        (playField[x][y] == emptyDot && emptyFields > dotsToWin - stepsToCheck)) {
                    return false;
                } else if (playField[x][y] == emptyDot && emptyFields <= dotsToWin - stepsToCheck) {
                    emptyFields++;
                    x1[emptyFields - 1] = x;
                    y1[emptyFields - 1] = y;
                }
                x += dx;
                y += dy;
            }
            if (emptyFields > 0) {
                int p = emptyFields == 1 ? 0 : random.nextInt(emptyFields - 1);
                playField[x1[p]][y1[p]] = dotSymbolToSet;
                return true;
            }
        }
        return false;
    }

    private boolean foundBreakDot (Mode mode, char currentDot) {
        if ((mode == Mode.HUMAN && currentDot == computerDot) ||(mode == Mode.COMPUTER && currentDot == humanDot)) {
            return false;
        }
        return true;
    }


    private boolean checkWin(char dotSymbol) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (playField[i][j] == dotSymbol && (checkLine(dotSymbol, i, j, 1, 0) ||
                        checkLine(dotSymbol, i, j, 0, 1) ||
                        checkLine(dotSymbol, i, j, 1, 1) ||
                        checkLine(dotSymbol, i, j, -1, 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLine(char dotSymbol, int x, int y, int dx, int dy) {
        if (checkBoundary(x, y, dx, dy)) {
            for (int i = 0; i < dotsToWin; i++) {
                if (playField[x][y] != dotSymbol) {
                    return false;
                }
                x += dx;
                y += dy;
            }
            return true;
        }
       return false;
    }

    private boolean checkBoundary (int x, int y, int dx, int dy) {
        if ((x + dotsToWin * dx) > fieldSize || (y + dotsToWin * dy) > fieldSize || (x + (dotsToWin - 1) * dx) < 0 || (y + (dotsToWin - 1) * dy) < 0) {
            return false;
        } else return true;
    }

    private boolean fieldIsFull() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (playField[i][j] == emptyDot) {
                    return false;
                }
            }
        }
        return true;
    }


    private void humanStep() {
        System.out.println("Введите координаты в формате X Y:");
        int x;
        int y;
        do {
            x = scan.nextInt() - 1;
            y = scan.nextInt() - 1;
        } while (!checkXY(x, y, Mode.HUMAN));
        playField[x][y] = humanDot;
    }

    private boolean checkXY(int x, int y, Mode mode) {
        if (x < 0 || x >= fieldSize || y < 0 || y >= fieldSize) {
            System.out.println("Каждая координата должна быть в диапазоне от 1 до " + fieldSize);
            return false;
        } else if (playField[x][y] != emptyDot) {
            if (mode == Mode.HUMAN) {
                System.out.println("Ячейка уже занята. Пожалуйста, выберите другую");
            }
            return false;
        } else {
            return true;
        }
    }


    private void printMap() {
        for (int i = 0; i <= fieldSize; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < fieldSize; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(playField[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void initMap() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                playField[i][j] = emptyDot;
            }
        }
    }

    public static char[] getAvailableDotSymbols () {
        return dotSymbols;
    }

    public static boolean checkFieldSize (int fieldSize) {
        if (fieldSize < MIN_FIELD_SIZE) {
            return false;
        }
        return true;
    }

    public void setFieldSize(int userFieldSize) {
        fieldSize = userFieldSize;
        playField = new char[fieldSize][fieldSize];
    }

    public void setDotSymbols(char userDotSymbol) {
        if (humanDot != userDotSymbol) {
            computerDot = humanDot;
            humanDot = userDotSymbol;
        }
    }

    public static int getMinFieldSize () {
        return MIN_FIELD_SIZE;
    }


}
