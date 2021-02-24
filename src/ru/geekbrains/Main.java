package ru.geekbrains;

public class Main {

    private static int [] zeroOneInt = {0,1,1,0,1,1,0};
    private static int [] arithProgression = new int[8];
    private static int [] randomArray = {1,5,3,2,11,4,5,2,4,8,9,1};
    private static int [][] squareArray = new int [9][9];
    private static int [] bubble = new int [15];
    private static int [] rangeArray = {2, 2, 2, 1, 2, 2, 10, 1};
    private static int [] shArray = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) {
        revertArray (zeroOneInt);
        System.out.println();
        fillByArithmeticProgression(arithProgression);
        System.out.println();
        doubleLittleNumbers(randomArray);
        System.out.println();
        fillSquareArray(squareArray);
        fillRandomArray(bubble);
        System.out.println();
        findMaxAndMin(bubble);
        System.out.println();
        checkEquilSumFromtartAndFromEnd(rangeArray);
        System.out.println();
       // fillRandomArray(shArray);
        System.out.println();
        shiftArray(shArray, -404);
        System.out.println();

    }

    private static void revertArray (int[]zeroOneInt) {
        for (int i = 0; i < zeroOneInt.length; i++) {
            if (zeroOneInt[i]==0) zeroOneInt[i]= 1;
            else zeroOneInt[i] = 0;
            System.out.print(zeroOneInt[i] + " ");
        }
    }

    private static void fillByArithmeticProgression (int[]arithProgression) {
        arithProgression[0] = 0;
        System.out.print(arithProgression[0] + " ");
        for (int i = 1; i < arithProgression.length; i++) {
            arithProgression[i] = arithProgression[i-1] + 3;
            System.out.print(arithProgression[i] + " ");
        }
    }
    private static void doubleLittleNumbers (int[]randomArray) {
        for (int i = 0; i < randomArray.length; i++) {
            if (randomArray[i]<6) randomArray[i] *= 2;
            System.out.print(randomArray[i] + " ");
        }
    }

    private static void fillSquareArray (int[][] squareArray) {
        for (int i = 0; i < squareArray.length; i++) {
            for (int j = 0; j < squareArray[i].length; j++) {
                if (i==j || i== squareArray[i].length-1-j) squareArray[i][j] = 1;
                else squareArray[i][j] = 0;
                System.out.print(squareArray[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private static void fillRandomArray (int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 300);
            System.out.print(array[i] + " ");
        }
    }

    private static void findMaxAndMin (int[] array) {
        int maxValue = array[0];
        int minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) maxValue = array[i];
            else if (array[i] < minValue) minValue = array[i];
        }
        System.out.println("Максимальное значение: " + maxValue);
        System.out.println("Минимальное значение: " + minValue);
    }

    private static boolean checkEquilSumFromtartAndFromEnd (int[] array) {
        int startSum = 0;
        int endSum = 0;
        for (int i = 0; i < array.length; i++) {
            startSum += array[i];
            endSum = 0;
            for (int j = array.length-1; j > i; j--) {
                endSum += array[j];
            }
            if (startSum==endSum) {
                System.out.println("Одинаковая сумма = " + startSum);
                System.out.println("Граница после элемента " + (i+1));
                return true;
            }
        }
        System.out.println("Граница для одинаковой суммы не найдена");
        return false;
    }

    private static void shiftArray (int[] array, int n) {

        if (n >= array.length || n <= -array.length) n %= array.length;
        if (n<0) n = array.length + n;
        if (n==0) {
            System.out.println("Нулевой сдвиг");
            return;
        }

        for (int i = 0; i < n; i++) {
            shiftToOne(array);
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    private static void shiftToOne (int [] arrayOne) {
        int p;
        int c;
        c = arrayOne [0];
        for (int i = 0; i < arrayOne.length; i++) {
            if (i == arrayOne.length - 1) {
                arrayOne[0] = c;
            }
            else {
                p = arrayOne[i+1];
                arrayOne[i+1] = c;
                c = p;
            }

        }
    }
}

