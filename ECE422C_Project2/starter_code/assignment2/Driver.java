package assignment2;

import java.util.Scanner;

public class Driver {

    public void start(GameConfiguration config) {
        Scanner begin = new Scanner(System.in);
        System.out.println("Hello! Welcome to Wordle.");
        System.out.println("Do you want to play a new game? (y/n)");
        String yn = begin.nextLine();
        if(yn.equals("y")){
            Game game = new Game(config, begin);
            game.runGame();
        }
        if(yn.equals("n")){
            ;
        }
    }
    
    public void start_hardmode(GameConfiguration config) {
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        GameConfiguration config = new GameConfiguration(6, 10, false);
        driver.start(config);
    }
}
