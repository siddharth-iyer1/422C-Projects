package assignment2;

import java.util.Scanner;

public class Game {
    int numGuesses;
    GameConfiguration config;
    boolean win = false;
    Scanner scanner;

    public Game(GameConfiguration config, Scanner scanner){
        this.numGuesses = config.numGuesses;
        this.config = config;
        this.scanner = scanner;
    }

    public void runGame(){
        GameMechanics mech = new GameMechanics(config);
        GameData data = new GameData();
        Scanner user_input =this.scanner;
        while(this.numGuesses > 0){
            if(this.win){
                this.numGuesses = 0;
                break;
            }
            System.out.println("Enter your guess:");
            String input = user_input.nextLine();
            if(input.equalsIgnoreCase("[history]")){
                // Run print history method
                data.printHistory();
                System.out.println("--------");
            }
            else{
                mech.processGuess(this, data, input);
            }
        }
        if(this.numGuesses == 0){
            System.out.println("Do you want to play a new game? (y/n)");
            String yn = user_input.nextLine();
            if(yn.equals("y")){
                Game game = new Game(config, this.scanner);
                game.runGame();
            }
            if(yn.equals("n")){
                ;
            }
        }
    }
}
