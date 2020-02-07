package game;


public class GameView {
    public void printGameResultDetails(Game game, String result) {
        System.out.println("Your attempts: " + game.getAttempts());
        System.out.println("The guessable number in [" + game.getDiapasonFrom() + " ; " + game.getDiapasonTo() + "]");
        System.out.println(result);
        System.out.println();
    }

    public void printGameDiapasonDetails(Game game) {
        System.out.println("Guess a number between " + game.getDiapasonFrom() + " to " + game.getDiapasonTo());
    }
}
