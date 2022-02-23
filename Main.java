/**
 * Author: Mark Procopio
 * Revised: Apr 12, 2021
 * @brief Main module that initializes 2048 game.
 */

package src;

public class Main {
    public static void main(String[] args) {       
        
        System.out.println("Welcome to 2048");
        System.out.println("Use numerical directional keypad only! Enjoy :)");      

        Controller controller = new Controller();
        controller.runGame();
    }        
}

