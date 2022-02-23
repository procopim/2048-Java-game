/**
 * Author: Mark Procopio
 * Revised: Apr 12, 2021
 * @brief Controller an Abstract Object that controls GameState model and View for the 2048 board game 
 * @details reads user input from keyboard environment variable 
 */

package src;

import java.util.Scanner;

public class Controller {
    
    //state variables
    GameState game;
    int dir;

    //environment variable
    Scanner keyboard = new Scanner(System.in);

    
    Controller(){
        this.game = new GameState();
        View.showBoard(this.game);
    }

    public void runGame(){    
    while (!(this.game.gameOver)){
        dir = keyboard.nextInt();

        if (dir == 2){
            this.game.move(Direction.DOWN);
            View.showBoard(this.game);
        }
        if (dir == 4){
            this.game.move(Direction.LEFT);
            View.showBoard(this.game);
        }
        if (dir == 8){
            this.game.move(Direction.UP);
            View.showBoard(this.game);
        }
        if (dir == 6){
            this.game.move(Direction.RIGHT);
            View.showBoard(this.game);
        }
        }
    }
}