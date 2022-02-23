/**
 * Module Description: View module for game 2048. 
 * Date: Apr 12,2021
 * Author: Mark Procopio
 * @brief View module that prints to terminal state of game board 
 */

package src;

public class View {
    
    /**
     * @brief showBoard method to clear and print new view of game model
     * @param game model variable 
     */
    public static void showBoard(GameState game){
        clearScreen();
        System.out.println(String.format("%20d", 2048));
        System.out.println(String.format("%s%-2d", "Score: ", game.score));

        for(int row = 3; row > -1; row--){
            for(int i = 0; i< 4; i++){
                System.out.print(String.format("%-4s%-4d ", "|", game.b.board[row][i].get_value()));
            }
            System.out.println("");
        }
    }
    

    /**
     * @brief clearScreen uses java specific ASCII code to clear terminal screen 
     */
    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
