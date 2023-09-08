package com.julian.hangmanv2.app;

import com.julian.hangmanv2.domain.Game;
import com.julian.hangmanv2.domain.Player;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HangmanApp {
    private static final ImageIcon APP_ICON = new ImageIcon(HangmanApp.class.getResource("juego.png"));
    private static final String APP_TITLE = "Hangman";

    public static void main(String[] args) {
        showMessageDialog("Welcome to the Hangman game");
        Player player1 = createPlayer("Player 1");
        Player player2 = createPlayer("Player 2");

        String guesserNumber = inputDialog("Enter 1 if Player 1 is the guesser, or 2 otherwise");
        Player guesser = (guesserNumber.equals("1")) ? player1 : player2;
        Player questioner = (guesser == player1) ? player2 : player1;

        String wordToGuess = inputDialog(questioner.getName() + ", enter the word to guess");
        playHangman(guesser, questioner, wordToGuess);
    }

    public static Player createPlayer(String playerName) {
        String name = inputDialog(playerName + ", write your name");
        return new Player(name);
    }

    public static String inputDialog(String message) {
        return (String) JOptionPane.showInputDialog(null, message, APP_TITLE, 1, APP_ICON, null, null);
    }

    public static void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, APP_TITLE, 0, APP_ICON);
    }

    public static void playHangman(Player guesser, Player questioner, String wordToGuess) {
        Game game = new Game(wordToGuess);
        game.play(guesser, questioner);
        while (!game.isGameOver()) {
            char letter = guessLetter();
            byte result = game.verifyLetter(letter);
            showMessageDialog(getMessageForResult(result));
        }
        showResults(guesser, questioner, game);
    }

    public static char guessLetter() {
        return inputDialog("Guess a letter").charAt(0);
    }

    public static String getMessageForResult(byte result) {
        switch (result) {
            case 1:
                return "That's correct!";
            case 0:
                return "That's wrong!";
            case -1:
                return "You already guessed this letter!";
            default:
                return "";
        }
    }

    public static void showResults(Player guesser, Player questioner, Game game) {
        String message = (guesser.isWinner()) ?
                guesser.getName() + " Congratulations, you finished the game.\nThe word was " +
                        questioner.getWord() + "\nYour stats are:\nAttempts = " +
                        guesser.getAttempts() + "\nFailures = " + guesser.getFailures() :
                guesser.getName() + " you lose the game.\nThe word was " +
                        questioner.getWord() + "\nYour stats are:\nAttempts = " +
                        guesser.getAttempts() + "\nFailures = " + guesser.getFailures();
        showMessageDialog(message);
    }
}
