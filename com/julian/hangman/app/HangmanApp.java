package com.julian.hangman.app;

import com.julian.hangman.domain.Game;
import com.julian.hangman.domain.Player;

import javax.swing.*;

public class HangmanApp {
    private static final ImageIcon APP_ICON = new ImageIcon(com.julian.hangman.app.HangmanApp.class.getResource("juego.png"));
    private static final String APP_TITTLE = "Hangman";
    public static void main(String[] args) {
        showMessageDialog("Welcome to the hangman game");
        Player player1 = new Player(inputDialogJoptionPane("Player1 write your name"));
        Player player2 = new Player(inputDialogJoptionPane("Player2 write your name"));
        String numberPlayerGuesser= inputDialogJoptionPane("Write 1 if the guesser by the player 1 " +
                "or 2 if be if it's the other way around");
        if (numberPlayerGuesser.equals("1")){
            String wordPlayerQuestioner=inputDialogJoptionPane(player2.getName()+" Write the word");
            playHangmanWithJoptionPane(player1,player2,numberPlayerGuesser,wordPlayerQuestioner);
        }else {
            String wordPlayerQuestioner=inputDialogJoptionPane(player1.getName()+" Write the word");
            playHangmanWithJoptionPane(player2,player1,numberPlayerGuesser,wordPlayerQuestioner);
        }
    }
    public static String inputDialogJoptionPane (String message){
        return (String) JOptionPane.showInputDialog(null, message,
                APP_TITTLE, 1, APP_ICON, null, null);
    }
    public static String showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, APP_TITTLE, 0, APP_ICON);
        return "2";
    }
    public static String joptionPaneHanghman(int failures,String message){
        String failuresString= String.valueOf(failures);
        ImageIcon HANGMAN = new ImageIcon(com.julian.hangman.app.HangmanApp.class.getResource("ahorcado"+failuresString+".png"));
        return (String) JOptionPane.showInputDialog(null, message,
                APP_TITTLE, 1,HANGMAN , null, null);
    }
    public static void showResults(Player playerGuesser, Player playerQuestioner){
        if (playerGuesser.isWinner()){
            showMessageDialog(playerGuesser.getName() + " Congratulations you finished the game\nthe word was "
                    + playerQuestioner.getWord() + "\nyour stats are:\n" +
                    "attempts = " + playerGuesser.getAttempts() + "\nfailures = " + playerGuesser.getFailures());
        }else{
            showMessageDialog(playerGuesser.getName()+" you lose the game\nthe word was "
                    + playerQuestioner.getWord() + "\nyour stats are:\n" +
                    "attempts = " + playerGuesser.getAttempts() + "\nfailures = " +
                    "" + playerGuesser.getFailures());
        }
    }
    public static void playHangmanWithJoptionPane(Player playerGuesser,Player playerQuestioner
            ,String numberPlayerGuesser,String wordPlayerQuestioner){
        Game game = new Game();
        byte auxiliar;
        playerQuestioner.setWord(wordPlayerQuestioner);
        game.play(playerGuesser, playerQuestioner,numberPlayerGuesser,wordPlayerQuestioner);
        do {
            auxiliar=(game.verificationLetters(playerGuesser, playerQuestioner
                    , (joptionPaneHanghman(playerGuesser.getFailures(), game.showHyphensWord(playerGuesser)).charAt(0))));
            if (auxiliar==1){
                showMessageDialog("that´s Correct");
            }else if (auxiliar==0){
                showMessageDialog("That´s wrong");
            }else {
                showMessageDialog("You already write this word");
            }
        } while (playerGuesser.getFailures()<6 && !playerGuesser.isWinner()&& auxiliar!=2);
        showResults(playerGuesser,playerQuestioner);
    }
}


