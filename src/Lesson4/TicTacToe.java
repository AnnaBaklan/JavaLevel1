package Lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static Scanner scan = new Scanner(System.in);
    private static Random random = new Random();
    private static int fieldSize = 5;
    private static int dotsToWin = 5;
    private static char[][] playField = new char[fieldSize][fieldSize];
    private static char emptyDot = '*';
    private static char humanDot = 'X';
    private static char computerDot = 'O';
    private static int X;
    private static int Y;
    private enum Mode {HUMAN, COMPUTER};
    private static int MIN_DOTS_TO_CHECK = 2;

    public static void main(String[] args) {

        initMap();
        printMap();

        while(true){
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

    private static void computerStep() {
        if (findBestStep()) {
            return;
        } else  {
                do {
                    X = random.nextInt(fieldSize);
                    Y = random.nextInt(fieldSize);
                } while (!checkXY (X, Y, Mode.COMPUTER));
            playField[X][Y] = computerDot;
            }
    }




    private static boolean findBestStep() {

        if (dotsToWin < MIN_DOTS_TO_CHECK) {
            return false;
        } else {
            for (int i = dotsToWin; i > 1; i--) {
                if (checkAllLinesForNextStep(i, Mode.COMPUTER)) {
                    return true;
                } else if (checkAllLinesForNextStep(i, Mode.HUMAN)) {
                    return true;
                }

            }
            return false;
        }
    }

    private static boolean checkAllLinesForNextStep (int stepsToCheck, Mode mode) {

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

    private static boolean checkLineForNextStep (char dotSymbolToSet, Mode mode, int stepsToCheck, int x, int y, int dx, int dy) {
        if ((x + dotsToWin*dx) > fieldSize || (y + dotsToWin*dy) > fieldSize || (x + (dotsToWin-1)*dx) < 0 || (y + (dotsToWin-1)*dy) < 0 ) {
            return false;
        }
        int emptyFields = 0;
        int [] x1 = new int [dotsToWin];
        int [] y1 = new int [dotsToWin];
        for (int i = 0; i < dotsToWin; i++) {
            if (mode == Mode.COMPUTER && playField[x][y] == humanDot) {
                return false;
            } else if (mode == Mode.HUMAN && playField[x][y] == computerDot){
                return false;
            } else if (playField[x][y] == emptyDot && emptyFields <= dotsToWin - stepsToCheck) {
                emptyFields += 1;
                x1[emptyFields-1] = x;
                y1[emptyFields-1] = y;
            } else if (playField[x][y] == emptyDot && emptyFields > dotsToWin - stepsToCheck) {
                return false;
            }
            x+=dx;
            y+=dy;
        }
        if (emptyFields > 0)  {
            int p = emptyFields == 1 ? 0 : random.nextInt(emptyFields - 1);
            playField [x1[p]][y1[p]] = dotSymbolToSet;
            return true;
        }
        return false;
    }



    private static boolean checkWin(char DotSymbol) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (playField[i][j] == DotSymbol && (checkLine(DotSymbol, i, j, (byte)1, (byte)0) ||
                        checkLine(DotSymbol, i, j, (byte)0, (byte)1)||
                        checkLine(DotSymbol, i, j, (byte)1, (byte)1)||
                        checkLine(DotSymbol, i, j, (byte)-1, (byte)1))) {
                        return true;
                    }
                }
            }
        return false;
    }

    private static boolean checkLine(char dotSymbol, int x, int y, byte dx, byte dy) {
        if ((x + dotsToWin*dx) > fieldSize || (y + dotsToWin*dy) > fieldSize || (x + (dotsToWin-1)*dx) < 0 || (y + (dotsToWin-1)*dy) < 0 ) {
            return false;
        }
        for (int i = 0; i < dotsToWin; i++) {
            if (playField[x][y] != dotSymbol) {
                return false;
            }
            x += dx;
            y += dy;
        }
        return true;

    }

    private static boolean fieldIsFull() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (playField[i][j] == emptyDot) {
                    return false;
                }
            }
        }
        return true;
    }


    private static void humanStep() {
        System.out.println("Введите координаты в формате X Y:");
        do {
            X = scan.nextInt()-1;
            Y = scan.nextInt()-1;
        } while (!checkXY (X, Y, Mode.HUMAN));
        playField[X][Y] = humanDot;
    }

    private static boolean checkXY(int X, int Y, Mode mode) {
        if (X < 0 || X >= fieldSize || Y < 0 || Y >= fieldSize) {
            System.out.println("Каждая координата должна быть в диапазоне от 1 до " + fieldSize);
            return false;
        } else if (playField[X][Y] != emptyDot) {
            if (mode == Mode.HUMAN) {
                System.out.println("Ячейка уже занята. Пожалуйста, выберите другую");
            }
            return false;
        }   else {
            return true;
        }
    }


    private static void printMap() {
        for (int i = 0; i <= fieldSize; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < fieldSize; i++) {
            System.out.print((i+1) + " ");
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(playField[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initMap() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                playField[i][j] = emptyDot;
            }
        }
    }

}
