package ua.external.mvc.controller;

import ua.external.mvc.model.Game;
import ua.external.mvc.view.GameView;

import java.util.Scanner;

public class GameController {
    private Game game;
    private GameView gameView;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public Integer getGameGuessableNumber() {
        return game.getGuessableNumber();
    }

    public void setGameDiapasonFrom(Integer diapasonFrom) {
        game.setDiapasonFrom(diapasonFrom);
    }

    public Integer getGameDiapasonFrom() {
        return game.getDiapasonFrom();
    }

    public void setGameDiapasonTo(Integer diapasonTo) {
        game.setDiapasonTo(diapasonTo);
    }

    public Integer getGameDiapasonTo() {
        return game.getDiapasonTo();
    }

    public void addGameAttempt(Integer attempt) {
        game.addAttempt(attempt);
    }

    public void showGameResult(String result) {
        gameView.printGameResultDetails(game, result);
    }

    public void showGameDiapason() {
        gameView.printGameDiapasonDetails(game);
    }

    public void playGame() {
        String RESULT_MORE_THAN = "Yor number is more than the right guess number! Try again!";
        String RESULT_LESS_THAN = "Yor number is less than the right guess number! Try again!";
        String RESULT_OUT_OF_BOUND = "Yor number out of bounds";
        String RESULT_WIN = "You win!\nThe guess number is ";

        Scanner scanner = new Scanner(System.in);

        boolean win = false;
        while (!win) {
            Integer guessableNumber = getGameGuessableNumber();
            Integer diapasonFrom = getGameDiapasonFrom();
            Integer diapasonTo = getGameDiapasonTo();

            showGameDiapason();

            Integer number = scanner.nextInt();
            addGameAttempt(number);

            if (number < diapasonFrom || number > diapasonTo) {
                showGameResult(RESULT_OUT_OF_BOUND);
                continue;
            }

            if (number == guessableNumber) {
                showGameResult(RESULT_WIN + number);
                win = true;
            } else if (number < guessableNumber) {
                setGameDiapasonFrom(number);
                showGameResult(RESULT_LESS_THAN);
            } else {
                setGameDiapasonTo(number);
                showGameResult(RESULT_MORE_THAN);
            }
        }
    }

}
