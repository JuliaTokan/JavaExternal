package game;

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

}
