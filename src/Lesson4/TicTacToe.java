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
        } else if (findSecondBestStep(humanDot, computerDot)) {
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

        if (dotsToWin < 2) {
            return false;
        } else {
            for (int i = dotsToWin; i > 2; i--) {
                if (findBestWay(computerDot, computerDot, i)) {
                    return true;
                } else if (findBestWay(humanDot, computerDot, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean findBestWay(char dotSymbolToFind, char dotSymbolToSet, int stepsToCheck) {
        int x1 = 0;
        int y1 = 0;
        int emptyFields = 0;
        if (stepsToCheck > 1) {
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    if (playField[i][j] == dotSymbolToFind) {
                        if (i + stepsToCheck - 1 < fieldSize) {
                            for (int k = 1; k <= stepsToCheck - 1; k++) {
                                if (playField[i + k][j] != dotSymbolToFind && k < stepsToCheck - 1) {
                                    if (emptyFields == 0 && playField[i + k][j] == emptyDot) {
                                        x1 = i + k;
                                        y1 = j;
                                        emptyFields += 1;
                                    } else {
                                        break;
                                    }
                                } else if (k == stepsToCheck - 1 && playField[i + k][j] == emptyDot  && emptyFields == 0) {
                                    playField[i + k][j] = dotSymbolToSet;
                                    return true;
                                } else if (k == stepsToCheck - 1 && playField[i + k][j] == dotSymbolToFind && emptyFields == 1) {
                                    playField[x1][y1] = dotSymbolToSet;
                                    return true;

                                }
                            }
                            emptyFields = 0;
                        }
                        if (i-stepsToCheck + 1 >= 0) {
                            for (int k = 1; k <= stepsToCheck - 1; k++) {
                                if (playField[i - k][j] != dotSymbolToFind && k < stepsToCheck - 1) {
                                    if (emptyFields == 0 && playField[i - k][j] == emptyDot) {
                                        x1 = i - k;
                                        y1 = j;
                                        emptyFields += 1;
                                    } else {
                                        break;
                                    }
                                } else if (k == stepsToCheck - 1 && playField[i - k][j] == emptyDot && emptyFields == 0) {
                                    playField[i - k][j] = dotSymbolToSet;
                                    return true;
                                } else if (k == stepsToCheck - 1 && playField[i - k][j] == dotSymbolToFind && emptyFields == 1) {
                                    playField[x1][y1] = dotSymbolToSet;
                                    return true;
                                }
                            }

                            emptyFields = 0;
                        }
                        if (j + stepsToCheck - 1 < fieldSize) {
                            for (int k = 1; k <= stepsToCheck - 1; k++) {
                                if (playField[i][j + k] != dotSymbolToFind && k < stepsToCheck - 1) {
                                    if (emptyFields == 0 && playField[i][j+ k] == emptyDot) {
                                        x1 = i;
                                        y1 = j + k;
                                        emptyFields += 1;
                                    } else {
                                        break;
                                    }
                                } else if (k == stepsToCheck - 1 && playField[i][j + k] == emptyDot && emptyFields == 0) {
                                    playField[i][j + k] = dotSymbolToSet;
                                    return true;
                                } else if (k == stepsToCheck - 1 && playField[i][j + k] == dotSymbolToFind && emptyFields == 1) {
                                    playField[x1][y1] = dotSymbolToSet;
                                    return true;
                                }
                            }
                            emptyFields = 0;
                        }
                        if (j - stepsToCheck + 1 >= 0) {
                            for (int k = 1; k <= stepsToCheck - 1; k++) {
                                if (playField[i][j-k] != dotSymbolToFind && k < stepsToCheck - 1){
                                    if (emptyFields == 0 && playField[i][j-k] == emptyDot) {
                                        x1 = i;
                                        y1 = j - k;
                                        emptyFields += 1;
                                    } else {
                                        break;
                                    }
                                } else if (k == stepsToCheck - 1 && playField[i][j-k] == emptyDot && emptyFields == 0) {
                                    playField[i][j-k] = dotSymbolToSet;
                                    return true;
                                }  else if (k == stepsToCheck - 1 && playField[i][j - k] == dotSymbolToFind && emptyFields == 1) {
                                    playField[x1][y1] = dotSymbolToSet;
                                    return true;
                                }
                            }
                            emptyFields = 0;
                        }
                        if (i + stepsToCheck - 1 < fieldSize && j+stepsToCheck - 1 < fieldSize) {
                            for (int k = 1; k <= stepsToCheck - 1; k++) {
                                if (playField[i+k][j+k] != dotSymbolToFind && k < stepsToCheck - 1){
                                    if (emptyFields == 0 && playField[i+k][j+k] == emptyDot) {
                                        x1 = i + k;
                                        y1 = j + k;
                                        emptyFields += 1;
                                    } else {
                                        break;
                                    }
                                } else if (k == stepsToCheck - 1 && playField[i+k][j+k] == emptyDot && emptyFields == 0) {
                                    playField[i+k][j+k] = dotSymbolToSet;
                                    return true;
                                } else if (k == stepsToCheck - 1 && playField[i+k][j+k] == dotSymbolToFind && emptyFields == 1) {
                                    playField[x1][y1] = dotSymbolToSet;
                                    return true;
                                }
                            }
                            emptyFields = 0;
                        }
                        if (i - stepsToCheck + 1 >= 0 && j - stepsToCheck + 1 >= 0) {
                            for (int k = 1; k <= stepsToCheck - 1; k++) {
                                if (playField[i-k][j-k] != dotSymbolToFind && k < stepsToCheck - 1){
                                    if (emptyFields == 0 && playField[i-k][j-k] == emptyDot) {
                                        x1 = i - k;
                                        y1 = j - k;
                                        emptyFields += 1;
                                    } else {
                                        break;
                                    }
                                } else if (k == stepsToCheck - 1 && playField[i-k][j-k] == emptyDot && emptyFields == 0) {
                                    playField[i-k][j-k] = dotSymbolToSet;
                                    return true;
                                } else if (k == stepsToCheck - 1 && playField[i-k][j-k] == dotSymbolToFind && emptyFields == 1) {
                                    playField[x1][y1] = dotSymbolToSet;
                                    return true;
                                }
                            }
                            emptyFields = 0;
                        }
                        if (i - stepsToCheck + 1 >= 0 && j + stepsToCheck - 1 < fieldSize) {
                            for (int k = 1; k <= stepsToCheck - 1; k++) {
                                if (playField[i-k][j+k] != dotSymbolToFind && k < stepsToCheck - 1){
                                    if (emptyFields == 0 && playField[i-k][j+k] == emptyDot) {
                                        x1 = i - k;
                                        y1 = j + k;
                                        emptyFields += 1;
                                    } else {
                                        break;
                                    }
                                } else if (k == stepsToCheck - 1 && playField[i-k][j+k] == emptyDot && emptyFields == 0) {
                                    playField[i-k][j+k] = dotSymbolToSet;
                                    return true;
                                } else if (k == stepsToCheck - 1 && playField[i-k][j+k] == dotSymbolToFind && emptyFields == 1) {
                                    playField[x1][y1] = dotSymbolToSet;
                                    return true;
                                }
                            }
                            emptyFields = 0;
                        }
                        if (j - stepsToCheck + 1 >= 0 && i + stepsToCheck - 1 < fieldSize) {
                            for (int k = 1; k <= stepsToCheck - 1; k++) {
                                if (playField[i+k][j-k] != dotSymbolToFind && k < stepsToCheck - 1){
                                    if (emptyFields == 0 && playField[i+k][j-k] == emptyDot) {
                                        x1 = i + k;
                                        y1 = j - k;
                                        emptyFields += 1;
                                    } else {
                                        break;
                                    }
                                } else if (k == stepsToCheck - 1 && playField[i+k][j-k] == emptyDot && emptyFields == 0) {
                                    playField[i+k][j-k] = dotSymbolToSet;
                                    return true;
                                } else if (k == stepsToCheck - 1 && playField[i+k][j-k] == dotSymbolToFind && emptyFields == 1) {
                                    playField[x1][y1] = dotSymbolToSet;
                                    return true;
                                }
                            }
                            emptyFields = 0;
                        }
                    }

                }

            }
        }
        return false;
    }

    private static boolean findSecondBestStep (char enemyDotSymbol, char dotSymbolToSet) {
        int wayToCheck = random.nextInt(4);
        if (wayToCheck == 0) {
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    if (playField[i][j] == emptyDot && findDotinLine(dotSymbolToSet, i, j) && !findDotinLine(enemyDotSymbol, i, j)) {
                        playField[i][j] = dotSymbolToSet;
                        return true;
                    }
                }

            }
        } else if (wayToCheck == 1) {
            for (int i = 0; i < fieldSize; i++) {
                for (int j = fieldSize-1; j < 0; j--) {
                    if (playField[i][j] == emptyDot && findDotinLine(dotSymbolToSet, i, j) && !findDotinLine(enemyDotSymbol, i, j)) {
                        playField[i][j] = dotSymbolToSet;
                        return true;
                    }
                }

            }
        } else if (wayToCheck == 2) {
            for (int i = fieldSize-1; i < 0; i--) {
                for  (int j = 0; j < fieldSize; j++) {
                    if (playField[i][j] == emptyDot && findDotinLine(dotSymbolToSet, i, j) && !findDotinLine(enemyDotSymbol, i, j)) {
                        playField[i][j] = dotSymbolToSet;
                        return true;
                    }
                }
            }
        } else {
            for (int i = fieldSize-1; i < 0; i--) {
                for  (int j = fieldSize-1; j < 0; j--) {
                    if (playField[i][j] == emptyDot && findDotinLine(dotSymbolToSet, i, j) && !findDotinLine(enemyDotSymbol, i, j)) {
                        playField[i][j] = dotSymbolToSet;
                        return true;
                    }
                }
            }
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
        if ((x + dotsToWin*dx) > fieldSize || (y + dotsToWin*dy) > fieldSize || (x + dotsToWin*dx) < 0 || (y + dotsToWin*dy) < 0 ) {
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
        }

         else if (mode == Mode.COMPUTER && DotsExist(Mode.COMPUTER)) {
            return findDotinLine(computerDot, X, Y);

        } else if (mode == Mode.COMPUTER && DotsExist(Mode.HUMAN)) {
            return findDotinLine(humanDot, X, Y);
        }   else {
            return true;
        }
    }

    private static boolean findDotinLine (char dotSymbol, int x, int y) {
        for (int i = 0; i < fieldSize; i++) {
            if (playField[i][y] == dotSymbol && (i - x < dotsToWin || i + x < dotsToWin)) {
                return true;
            }
            if (playField[x][i] == dotSymbol && (i - y < dotsToWin || i + y < dotsToWin)) {
                return true;
            }
            if (x + i < fieldSize && y + i < fieldSize) {
                if (playField[x + i][y + i] == dotSymbol && (i < dotsToWin)) {
                    return true;
                }
            }
            if (x - i >= 0 && y - i >= 0) {
                if (playField[x - i][y - i] == dotSymbol && (i < dotsToWin)) {
                    return true;
                }
            }
            if (x + i < fieldSize && y - i >= 0) {
                if (playField[x + i][y - i] == dotSymbol && (i < dotsToWin)) {
                    return true;
                }
            }
            if (x - i >= 0 && y + i < fieldSize) {
                if (playField[x - i][y + i] == dotSymbol && (i < dotsToWin)) {
                    return true;
                }
            }
        }
        return false;
    }



    private static boolean DotsExist (Mode mode) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (mode== Mode.HUMAN && playField[i][j] == humanDot) {
                    return true;
                }
                if (mode== Mode.COMPUTER && playField[i][j] == computerDot) {
                    return true;
                }
            }
        }
        return false;
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
