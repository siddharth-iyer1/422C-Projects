package assignment2;

import java.util.*;

public class GameData{
    ArrayList<String> guessHistory;
    ArrayList<String> responseHistory;

    public GameData(){
        guessHistory = new ArrayList<String>();
        responseHistory = new ArrayList<String>();
    }

    public void newGuess(String guess, String feedback){
        guessHistory.add(guess);
        responseHistory.add(feedback);
    }

    public void printHistory(){
        for(int i = 0; i < guessHistory.size(); i++){
            System.out.println(guessHistory.get(i) + "->" + responseHistory.get(i));
        }
    }
}





