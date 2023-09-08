package com.julian.hangmanv2.domain;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private String secretWord;
    private char[] secretWordHyphens;
    private List<Character> guessedLetters;
    private int failures;
    private boolean gameOver;

    public Game(String wordToGuess) {
        secretWord = wordToGuess;
        secretWordHyphens = new char[secretWord.length()];
        guessedLetters = new ArrayList<>();
        failures = 0;
        gameOver = false;
        initializeHyphens();
    }

    private void initializeHyphens() {
        for (int i = 0; i < secretWord.length(); i++) {
            secretWordHyphens[i] = '.';
        }
    }

    public void play(Player guesser, Player questioner) {
        JOptionPane.showMessageDialog(null,guesser.getName() + ", start guessing!");
    }

    public byte verifyLetter(char letter) {
        if (guessedLetters.contains(letter)) {
            return -1; // Letter already guessed
        }

        guessedLetters.add(letter);
        if (secretWord.contains(String.valueOf(letter))) {
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == letter) {
                    secretWordHyphens[i] = letter;
                }
            }
            return 1; // Correct guess
        } else {
            failures++;
            return 0; // Wrong guess
        }
    }

    public boolean isGameOver() {
        gameOver = (failures >= 6) || String.valueOf(secretWordHyphens).equals(secretWord);
        return gameOver;
    }

    public String getWordStatus() {
        return String.valueOf(secretWordHyphens);
    }
}