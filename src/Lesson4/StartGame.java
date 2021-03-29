package Lesson4;

public class StartGame {

    private static int fieldSize = 3;
    private static char humanDot = 'X';

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        ParameterWindow parameterWindow = new ParameterWindow(game);
    }


}
