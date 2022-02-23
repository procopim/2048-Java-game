/**
 * Module Description: Model of the game board, for game 2048. 
 * Date: Apr 11,2021
 * Author: Mark Procopio
 * @brief BoardT Abstract Data Type representing game board for game 2048 
 */
package src;
import java.util.Random;

public class BoardT {

   
    int row_size;         // board game array size - seq [4] x [4] 
    int avail_cells;  // to be initialized at 16
    CellT[][] board;

    
    /**
    * @brief Constructor for BoardT ADT 
    * @details initializes BoardT object representing board game of 4 x 4
    * @details also randomly chooses two board cells to randomly set to either 2 or 4
    */
    public BoardT(){
        
        this.row_size = 4;
        this.avail_cells = 16;

        int[] rand_vals = new int[2];
        rand_vals[0] = get_rand_cell_val();
        rand_vals[1] = get_rand_cell_val();

        int[] x_rand_coords = new int[2];
        x_rand_coords[0] = get_rand_coords();
        x_rand_coords[1] = get_rand_coords();

        int[] y_rand_coords = new int[2];
        y_rand_coords[0] = get_rand_coords();
        y_rand_coords[1] = get_rand_coords();
    
        this.board = new CellT[4][4]; 
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board[i][j] = new CellT();
            }
        }
        
        this.board[x_rand_coords[0]][y_rand_coords[0]].set_value(rand_vals[0]);
        this.board[x_rand_coords[0]][y_rand_coords[0]].set_status_OCC();
        this.board[x_rand_coords[1]][y_rand_coords[1]].set_value(rand_vals[1]);
        this.board[x_rand_coords[1]][y_rand_coords[1]].set_status_OCC();
        
        this.avail_cells -= 2;

    }

    /**
     * @brief determines if coordinates are valid 
     * @param x represents horizontal position on board
     * @param y represents vertical position on board
     * @return boolean if coordinates are on board 
     */
    public boolean ValidPosition(int x, int y){
        return (x >= 0 && x < row_size) && (y >= 0 && y < row_size);
    }

    /**
     * @brief provides a random int 
     * @return int within 4x4 board grid 
     */
    public static int get_rand_coords(){

        Random random = new Random();

        int coord = random.nextInt(4);   
        return coord;

    }

    /**
     * @brief provides random int 4 or int 2, reflecting new cells on board
     * @return int 
     */
    public static int get_rand_cell_val(){
        //10% probability that the value is 4, 90% probabliity it is 2
        Random random = new Random();
        int val = random.nextInt(10);

        if (val == 0){
            val = 4;
        }
        else{
            val = 2;
        }
        return val;
    }

}
