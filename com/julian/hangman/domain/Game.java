package com.julian.hangman.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Scanner scanner=new Scanner(System.in);
    private String secretWord;
    private char[] secretWordHyphens;
    private List<String> listAllLetters=new ArrayList<>();

    public Game() {
        for (char letra = 'a'; letra <= 'z'; letra++) {
            this.listAllLetters.add(String.valueOf(letra));
        }
    }
    public char[] getWordInHyphens(Player player){
        this.secretWord= player.getWord();
        int lengthSecretWord = secretWord.length();
        secretWordHyphens = new char[lengthSecretWord];
        for (int i = 0; i < secretWordHyphens.length; i++) {
            this.secretWordHyphens[i] = '.';
        }
        System.out.println(secretWordHyphens);
        return secretWordHyphens;
    }
    public void chooseWord(Player player,String wordToGuess){
        player.setWord(wordToGuess);
        System.out.println(player.getWord());
    }
    public void inicializeList(Player player){
        char[] list=getWordInHyphens(player);
    }
    public byte verificationLetters(Player playerGuesser, Player playerQuestioner,char guesserLetter ) {
        String wordToGuess;
        byte auxiliar=0;
        int guessPlayer;
        boolean flag = false;
        if (String.valueOf(secretWordHyphens).equals(secretWord)) {
            playerGuesser.setWinner(true);
            return 2;
        } else {
            if (verificateLetter(guesserLetter)) {
                playerGuesser.setLetter(guesserLetter);
                playerGuesser.setAttempts(playerGuesser.getAttempts()+1);
                for (int i = 0; i < secretWord.length(); i++) {
                    if (playerGuesser.getLetter() == secretWord.charAt(i)) {
                        secretWordHyphens[i] = playerGuesser.getLetter();
                        System.out.println("This is correct");
                        flag = true;
                        auxiliar=1;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("The letter is not in the word");
                    playerGuesser.setFailures(playerGuesser.getFailures()+1);
                    auxiliar=0;
                }
            } else {
                System.out.println("you already write this word");
                auxiliar=-1;
            }
            if (String.valueOf(secretWordHyphens).equals(secretWord)) {
                playerGuesser.setWinner(true);
                return 2;
            }
        }
        return auxiliar;
    }


    public void play(Player player1, Player player2,String guessPlayer,String wordToGuess){
        if (guessPlayer.equals("1")){
            player1.setGuesser(true);
            chooseWord(player2,wordToGuess);
            getWordInHyphens(player2);
            inicializeList(player2);
        }else if (guessPlayer.equals("2")){
            player2.setGuesser(true);
            chooseWord(player1,wordToGuess);
            getWordInHyphens(player1);
            inicializeList(player1);
        }
    }

    public String showHyphensWord(Player playerGuesser){
        String wordHyphenathed="";
        for (char letter:this.secretWordHyphens) {
            wordHyphenathed+=letter;
        }
        return (playerGuesser.getName()+" the word is: "+wordHyphenathed+" write a letter");
    }

    public boolean verificateLetter(char letter){
        String newLetter= String.valueOf(letter);
        if(this.listAllLetters.contains(newLetter)){
            this.listAllLetters.remove(newLetter);
            return true;
        }else {
            return false;
        }
    }

    public char[] getSecretWordHyphens() {
        return secretWordHyphens;
    }

}

