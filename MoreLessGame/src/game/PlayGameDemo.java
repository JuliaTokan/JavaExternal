package game;

import java.util.Scanner;

public class PlayGameDemo {
    public static void main(String[] args) {
        Game game = new Game();
        GameView gameView = new GameView();
        GameController gameController = new GameController(game, gameView);

        playGame(gameController);
    }

    public static void playGame(GameController gameController) {
        String RESULT_MORE_THAN = "Yor number is more than the right guess number! Try again!";
        String RESULT_LESS_THAN = "Yor number is less than the right guess number! Try again!";
        String RESULT_OUT_OF_BOUND = "Yor number out of bounds";
        String RESULT_WIN = "You win!\nThe guess number is ";

        Scanner scanner = new Scanner(System.in);

        boolean win = false;
        while (!win) {
            Integer guessableNumber = gameController.getGameGuessableNumber();
            Integer diapasonFrom = gameController.getGameDiapasonFrom();
            Integer diapasonTo = gameController.getGameDiapasonTo();

            gameController.showGameDiapason();

            Integer number = scanner.nextInt();
            gameController.addGameAttempt(number);

            if (number < diapasonFrom || number > diapasonTo) {
                gameController.showGameResult(RESULT_OUT_OF_BOUND);
                continue;
            }

            if (number == guessableNumber) {
                gameController.showGameResult(RESULT_WIN + number);
                win = true;
            } else if (number < guessableNumber) {
                gameController.setGameDiapasonFrom(number);
                gameController.showGameResult(RESULT_LESS_THAN);
            } else {
                gameController.setGameDiapasonTo(number);
                gameController.showGameResult(RESULT_MORE_THAN);
            }
        }
    }
}
