package ua.external.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Integer MIN_DIAPASON = 0;
    private final Integer MAX_DIAPASON = 100;
    private Integer guessableNumber;
    private Integer diapasonFrom;
    private Integer diapasonTo;
    List<Integer> attempts;

    public Game() {
        guessableNumber = (int) (Math.random() * (MAX_DIAPASON - MIN_DIAPASON)) + MIN_DIAPASON;
        diapasonFrom = MIN_DIAPASON;
        diapasonTo = MAX_DIAPASON;
        attempts = new ArrayList<>();
    }

    public Integer getGuessableNumber() {
        return guessableNumber;
    }

    public int getDiapasonFrom() {
        return diapasonFrom;
    }

    public void setDiapasonFrom(Integer diapasonFrom) {
        this.diapasonFrom = diapasonFrom;
    }

    public int getDiapasonTo() {
        return diapasonTo;
    }

    public void setDiapasonTo(Integer diapasomTo) {
        this.diapasonTo = diapasomTo;
    }

    public List<Integer> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Integer> attempts) {
        this.attempts = attempts;
    }

    public void addAttempt(Integer attempt) {
        attempts.add(attempt);
    }
}
