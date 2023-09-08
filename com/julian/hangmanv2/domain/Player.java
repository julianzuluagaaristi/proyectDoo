package com.julian.hangmanv2.domain;

public class Player {
    private String name;
    private String word;
    private int attempts;
    private int failures;
    private boolean winner;

    public Player(String name) {
        this.name = name;
        attempts = 0;
        failures = 0;
        winner = false;
    }

    public String getName() {
        return name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getFailures() {
        return failures;
    }

    public void setFailures(int failures) {
        this.failures = failures;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}