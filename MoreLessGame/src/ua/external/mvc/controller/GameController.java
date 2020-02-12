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
        Scanner scanner = new Scanner(System.in);

        boolean win = false;
        while (!win) {
            showGameDiapason();

            Integer number = scanner.nextInt();
            addGameAttempt(number);

            if (game.checkOutOfBound(number)) {
                showGameResult(GameView.RESULT_OUT_OF_BOUND);
                continue;
            }

            if (game.compareGuessableNumber(number) == 0) {
                showGameResult(GameView.RESULT_WIN + number);
                win = true;
            } else if (game.compareGuessableNumber(number) == 1) {
                setGameDiapasonFrom(number);
                showGameResult(GameView.RESULT_LESS_THAN);
            } else {
                setGameDiapasonTo(number);
                showGameResult(GameView.RESULT_MORE_THAN);
            }
        }
    }

}
