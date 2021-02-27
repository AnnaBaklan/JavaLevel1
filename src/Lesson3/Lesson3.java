package Lesson3;

import java.util.Random;
import java.util.Scanner;
import java.util.Locale;

public class Lesson3 {

    private static boolean playGuessNumber = true;
    private static final int tryNumber = 3;
    private static Random random = new Random();
    private static Scanner scan = new Scanner(System.in).useLocale(Locale.US);

    private static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    public static void main(String[] args) {
        do {
            guessNumber(tryNumber);
        } while(playGuessNumber);

        guessWord();

        scan.close();
    }

    private static void guessWord() {
        int conceivedWordNum = random.nextInt(words.length);
        String conceivedWord = words[conceivedWordNum];
        String userAnswer;
        int minLength;
        System.out.println("Угадайте слово");
        while (true) {
            userAnswer = scan.nextLine();
            if (userAnswer.equals(conceivedWord)) {
                System.out.println("Поздравялем, Вы угадали!");
                break;
            } else {
                if (conceivedWord.length() < userAnswer.length()) {
                    minLength = conceivedWord.length();
                } else {
                    minLength = userAnswer.length();
                }
                for (int i = 0; i < minLength; i++) {
                    if (userAnswer.charAt(i)==conceivedWord.charAt(i)){
                        System.out.print(conceivedWord.charAt(i));
                    } else {
                        System.out.print("#");
                    }
                }
                for (int i = minLength; i < 15; i++) {
                    System.out.print("#");
                }
            }
            System.out.println();
        }

    }

    private static void guessNumber(int tryNumber) {
        if (tryNumber < 1) {
            return;
        }
        int userAnswer;
        int conceivedNum = random.nextInt(10);
        System.out.println("Угадайте число от 0 до 9");
        for (int i=0; i < tryNumber; i++) {
            userAnswer = scan.nextInt();
            if (userAnswer < 0 || userAnswer > 9) {
                System.out.println("Число должно быть в диапазоне от 0 до 9");
                i--;
            }
            else {
                if (userAnswer == conceivedNum) {
                    System.out.println("Поздравялем, Вы угадали!");
                    break;
                } else if (userAnswer < conceivedNum) {
                    System.out.println("Загаданное число больше введённого");
                } else {
                    System.out.println("Загаданное число меньше введённого");
                }
            }
        }
        System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
        userAnswer = scan.nextInt();
        while ((userAnswer != 0) && (userAnswer != 1)) {
            System.out.println("Введите 0 или 1");
            userAnswer = scan.nextInt();
        }
        if (userAnswer == 0) {
            playGuessNumber = false;
        } else {
            playGuessNumber = true;
        }
        scan.nextLine();
    }
}
