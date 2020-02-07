package ua.external.mvc;

import ua.external.mvc.controller.GameController;
import ua.external.mvc.model.Game;
import ua.external.mvc.view.GameView;

public class PlayGameDemo {
    public static void main(String[] args) {
        Game game = new Game();
        GameView gameView = new GameView();
        GameController gameController = new GameController(game, gameView);

        gameController.playGame();
    }
}
