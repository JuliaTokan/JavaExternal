package ua.external.mvc.view;


import ua.external.mvc.model.Game;

public class GameView {
    public static final String ATTEMPTS_MSG = "Your attempts: ";
    public static final String NUM_ATTEMPTS_MSG = "Your number of attempts is ";
    public static final String DIAPASON_MSG = "The guessable number in ";
    public static final String ENTER_NUM_MSG = "Guess a number between ";
    public static final String TO_MSG = " to ";
    public static final String BREAK_FIRST = "[";
    public static final String BREAK_LAST = "]";
    public static final String POINT = " ; ";
    public static final String RESULT_MORE_THAN = "Your number is more than the right guess number! Try again!";
    public static final String RESULT_LESS_THAN = "Your number is less than the right guess number! Try again!";
    public static final String RESULT_OUT_OF_BOUND = "Your number out of bounds";
    public static final String RESULT_WIN = "You won!\nThe guess number is ";

    public void printGameResultDetails(Game game, String result) {
        System.out.println(ATTEMPTS_MSG + game.getAttempts());
        System.out.println(NUM_ATTEMPTS_MSG + game.getAttempts().size());
        System.out.println(DIAPASON_MSG+ BREAK_FIRST + game.getDiapasonFrom() + POINT + game.getDiapasonTo() + BREAK_LAST);
        System.out.println(result);
        System.out.println();
    }

    public void printGameDiapasonDetails(Game game) {
        System.out.println(ENTER_NUM_MSG + game.getDiapasonFrom() + TO_MSG + game.getDiapasonTo());
    }
}
