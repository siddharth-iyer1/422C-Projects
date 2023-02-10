package assignment2;

import java.util.*;

public class GameMechanics {
    String toGuess;
    int length = 4; // Default
    Dictionary dict;

    public GameMechanics(GameConfiguration config){
        this.dict = new Dictionary("4_letter_words.txt");

        if(config.wordLength == 5){
            this.dict = new Dictionary("5_letter_words.txt");
            this.length = 5;
        }
        else if(config.wordLength == 6){
            this.dict = new Dictionary("6_letter_words.txt");
            this.length = 6;
        }

        this.toGuess = dict.getRandomWord();
    }

    public void processGuess(Game validity, GameData toUpdate, String userInput){
            // Now take in input
            String guess = userInput;
            // Check length validity
            if(guess.length() != this.toGuess.length()){
                System.out.println("This word has an incorrect length. Please try again.");
            }
            // Check dictionary validity
            else if(!dict.containsWord(guess)){
                System.out.println("This word is not in the dictionary. Please try again.");
            }
            else{
                String feedback = "";
                char[] feedback_array = new char[this.toGuess.length()];
                char[] toGuess_array = new char[this.toGuess.length()];
                String guess2 = "";
                for(int i = 0; i < guess.length(); i++){
                    if(guess.charAt(i) == this.toGuess.charAt(i)){
                        feedback_array[i] = 'G';
                        toGuess_array[i] = '&';
                        guess2 = guess2 + "*";
                    }
                    else{
                        guess2 = guess2 + guess.charAt(i);
                        toGuess_array[i] = this.toGuess.charAt(i);
                    }
                }
                // All correct letters are accounted for, check if game is won
                boolean flag = true;
                for(int i = 0; i < this.toGuess.length(); i++){
                    if(feedback_array[i] != 'G'){
                        flag = false;
                    }
                }
                // Otherwise, calculate "Y"s and "_"s
                char[] seen = new char[6];
                int seenIndex = 0;
                for(int i = 0; i < this.toGuess.length(); i++){
                    boolean flag2 = false;
                    for(int j = 0; j < this.toGuess.length(); j++){
                        if(guess2.charAt(i) == toGuess_array[j]){
                            flag2 = true;
                            break;
                        }
                    }
                    if(guess2.charAt(i) != '*' && flag2){
                        boolean isSeen = false;
                        for(int k = 0; k < seen.length; k++){
                            if(guess2.charAt(i) == seen[k]){
                                isSeen = true;
                            }
                        }
                        if(!isSeen){
                            feedback_array[i] = 'Y';
                            seen[seenIndex] = guess2.charAt(i);
                            seenIndex++;
                        }
                        else{
                            feedback_array[i] = '_';
                        }
                    }
                    else if(guess2.charAt(i) != '*' && !flag2){
                        feedback_array[i] = '_';
                    }
                }
                for(int i = 0; i < this.toGuess.length(); i++){
                    feedback = feedback + feedback_array[i];
                }

                toUpdate.newGuess(guess, feedback);
                if(guess.length() == 4){
                    if(flag){
                        System.out.println(feedback);
                        System.out.println("Congratulations! You have guessed the word correctly.");
                        validity.win = true;
                    }
                    else{
                        System.out.println(feedback);
                        validity.numGuesses--;
                        if(validity.numGuesses == 0){
                            System.out.println("You have run out of guesses.");
                            System.out.println("The correct word was " + "\"" + this.toGuess + "\"" + ".");
                        }
                        else{
                            System.out.println("You have " + (validity.numGuesses) + " guess(es) remaining.");
                        }
                    }
                }
                else if(guess.length() == 5){
                    if(flag){
                        System.out.println(feedback);
                        System.out.println("Congratulations! You have guessed the word correctly.");
                        validity.win = true;
                    }
                    else{
                        System.out.println(feedback);
                        validity.numGuesses--;
                        if(validity.numGuesses == 0){
                            System.out.println("You have run out of guesses.");
                            System.out.println("The correct word was " + "\"" + this.toGuess + "\"" + ".");
                        }
                        else{
                            System.out.println("You have " + (validity.numGuesses) + " guess(es) remaining.");
                        }
                    }
                }
                else if(guess.length() == 6){
                    if(flag){
                        System.out.println(feedback);
                        System.out.println("Congratulations! You have guessed the word correctly.");
                        validity.win = true;
                    }
                    else{
                        System.out.println(feedback);
                        validity.numGuesses--;
                        if(validity.numGuesses == 0){
                            System.out.println("You have run out of guesses.");
                            System.out.println("The correct word was " + "\"" + this.toGuess + "\"" + ".");
                        }
                        else{
                            System.out.println("You have " + (validity.numGuesses) + " guess(es) remaining.");
                        }
                    }
                }
            }
        }
    }