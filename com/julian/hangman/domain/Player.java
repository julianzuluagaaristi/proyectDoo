package com.julian.hangman.domain;

public class Player {
    private boolean guesser;
    private int failures;
    private String name;
    private String word;
    private int attempts;
    private char letter;
    private boolean winner;

    public Player( String name) {
        this.failures = 0;
        this.name = name;
        this.attempts = 0;
        this.guesser=false;
        this.winner=false;
    }

    public boolean isGuesser() {
        return guesser;
    }

    public int getFailures() {
        return failures;
    }

    public String getName() {
        return name;
    }

    public String getWord() {
        return word;
    }

    public int getAttempts() {
        return attempts;
    }

    public char getLetter() {
        return letter;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setGuesser(boolean guesser) {
        this.guesser = guesser;
    }

    public void setFailures(int failures) {
        this.failures = failures;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}

