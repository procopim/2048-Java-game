/**
 * Module Description: Model of the game board, for game 2048. 
 * Date: Apr 8,2021
 * Author: Mark Procopio
 * @brief GameState Abstract Object for our game board 
 * @details utilizes CellStatus and DirectionEnum types
 */

package src;

import java.util.ArrayList;

public class GameState{

   int score = 0;        // keeps track of player's score 
   boolean gameOver = false;
   BoardT b;

   /**
    * @brief Constructor for GameState, the model in our game 
    * @details An abstract object representing a gameboard for 2048 game 
    */
   GameState(){
      this.b = new BoardT();
      this.score = 0;
      this.gameOver = false;
   }   

   /**
    * @brief move method that translates user input into action on game board 
    * @details updates game board, adds a new random cell and checks whether game can continue or is won/lost
    * @param d Direction enum type 
    */
   public void move(Direction d){

      if (d == Direction.UP){
         updateN();
         if (avail_cell_check()){
            add_random_cell();
            this.b.avail_cells -= 1;
         }
         else{
            System.out.println("YOU LOSE - GAME OVER");
         }

         if (game_over_check()){
            //print final view
            System.out.println("YOU WIN! GAME OVER :) ");
            System.exit(0);
         }
      }

      if (d == Direction.DOWN){
         updateS();
         if (avail_cell_check()){
            add_random_cell();
            this.b.avail_cells -= 1;
         }
         else{
            System.out.println("YOU LOSE - GAME OVER");
         }

         if (game_over_check()){
            //print final view
            System.out.println("YOU WIN! GAME OVER :) ");
            System.exit(0);
         }
      }

      if (d == Direction.LEFT){
         updateW();
         if (avail_cell_check()){
            add_random_cell();
            this.b.avail_cells -= 1;
         }
         else{
            System.out.println("YOU LOSE - GAME OVER");
         }

         if (game_over_check()){
            //print final view
            System.out.println("YOU WIN! GAME OVER :) ");
            System.exit(0);
         }
      }

      if (d == Direction.RIGHT){
         updateE();
         if (avail_cell_check()){
            add_random_cell();
            this.b.avail_cells -= 1;
         }
         else{
            System.out.println("YOU LOSE - GAME OVER");
         }

         if (game_over_check()){
            //print final view
            System.out.println("YOU WIN! GAME OVER :) ");
            System.exit(0);
         }
      }
   }

   /**
    * @brief Checks is there are any Free cells on the board
    * @return boolean representing whether free cells exist on board 
    */
   private boolean avail_cell_check(){
      return this.b.avail_cells > 0;
   }

   /**
    * @brief Updates a random CellT on the board 
    * @details reflects random addition of an occupied cell on game board 
    */
   private void add_random_cell(){
      int[] values = gen_ran_vals();

      while (b.board[values[1]][values[0]].get_status() == CellStatus.OCCUPIED){
         values = gen_ran_vals();
      }
      
      b.board[values[1]][values[0]].set_value(values[2]);
      b.board[values[1]][values[0]].set_status_OCC();
   }


   /**
    * @brief generates random x,y coordinates and random value 
    * @returns array of integers 
    */
   private int[] gen_ran_vals(){
      int rand_val = BoardT.get_rand_cell_val();
      int x_rand_coords = BoardT.get_rand_coords();
      int y_rand_coords = BoardT.get_rand_coords();
      int[] result = new int[] {x_rand_coords, y_rand_coords, rand_val};
      return result; 
   }

   /**
    * @brief sets game over flag to true parameter value is 2048
    * @param number representing value of CellT 
    */
   private void game_over_flag(int number){
      if (number == 2048){
      gameOver = true;
      }
   }

   /**
    * @brief checks if game is over 
    * @return gameOver variable which is a boolean 
    */
   private boolean game_over_check(){
      return this.gameOver;
   }

   /**
    * @brief Updates game board in north direction 
    * @details updates game board if user input represents UP direction 
    * @details updates score, avail_cells and checks if new CellT.value is 2048
    */
   private void updateN(){
      int[] r = {3,2,1,0};
      int[] i = {0,1,2,3};
      ArrayList<CellT> collapsedN = new ArrayList<CellT>();//will hold previously collapsed CellT objects, so that we write their values only once in update

      for (int j = 0; j < 4; j++){

         for (int x: i){
            for (int y : r){
               if (b.board[y][x].get_status() == CellStatus.FREE){  //if im FREE, pull up whats under me
                  if (b.ValidPosition(x, y-1)){

                     b.board[y][x].set_value(b.board[y-1][x].get_value());
                     b.board[y][x].set_status(b.board[y-1][x].get_status());

                     b.board[y-1][x].set_status_FREE(); //sets value to zero as well 
                  }
               }
               else{ //CellStatus == OCCUPIED 
                  if (b.ValidPosition(x, y-1)){

                     if(b.board[y][x].get_value() == b.board[y-1][x].get_value()){ //collapse cells 
                        if (!(collapsedN.contains(b.board[y-1][x])) | !(collapsedN.contains(b.board[y][x]))) {
                           int new_cell_val = b.board[y][x].get_value() * 2;
                           score += new_cell_val;
                           game_over_flag(new_cell_val);
   
                           b.board[y][x].set_value(new_cell_val);
                           b.board[y][x].set_status_OCC();
                           b.board[y-1][x].set_status_FREE();
                           b.avail_cells += 1;
                           collapsedN.add(b.board[y][x]);
                           collapsedN.add(b.board[y-1][x]);                        
                        }
                     }
                  }
               }
            }
         }
      }
   }
   
   /**
   * @brief Updates game board in south direction 
   * @details updates game board if user input represents DOWN direction 
   * @details updates score, avail_cells and checks if new CellT.value is 2048
   */
   private void updateS(){
      int[] r = {0,1,2,3};
      int[] i = {0,1,2,3};
      ArrayList<CellT> collapsedS = new ArrayList<CellT>();//will hold previously collapsed CellT objects, so that we write their values only once in update

      for (int j = 0; j < 4; j++){

         for (int x: i){
            for (int y : r){
               if (b.board[y][x].get_status() == CellStatus.FREE){  //if im FREE, pull down whats above me
                  if (b.ValidPosition(x, y+1)){

                     b.board[y][x].set_value(b.board[y+1][x].get_value());
                     b.board[y][x].set_status(b.board[y+1][x].get_status());

                     b.board[y+1][x].set_status_FREE(); //sets value to zero as well 
                  }
               }
               else{ //CellStatus == OCCUPIED 
                  if (b.ValidPosition(x, y+1)){

                     if(b.board[y][x].get_value() == b.board[y+1][x].get_value()){ //collapse cells 
                        if (!(collapsedS.contains(b.board[y+1][x])) | !(collapsedS.contains(b.board[y][x]))){

                        int new_cell_val = b.board[y][x].get_value() * 2;
                        score += new_cell_val;
                        game_over_flag(new_cell_val);

                        b.board[y][x].set_value(new_cell_val);
                        b.board[y][x].set_status_OCC();
                        b.board[y+1][x].set_status_FREE();
                        b.avail_cells += 1;
                        collapsedS.add(b.board[y][x]);
                        collapsedS.add(b.board[y+1][x]);
                        }
                     }
                  }
               }
            }
         }
      }
   }

      /**
   * @brief Updates game board in east direction 
   * @details updates game board if user input represents RIGHT direction 
   * @details updates score, avail_cells and checks if new CellT.value is 2048
   */
   private void updateE(){
      int[] r = {0,1,2,3};
      int[] i = {3,2,1,0};
      ArrayList<CellT> collapsedE = new ArrayList<CellT>();//will hold previously collapsed CellT objects, so that we write their values only once in update

      for (int j = 0; j < 4; j++){

         for (int y: r){
            for (int x : i){
               if (b.board[y][x].get_status() == CellStatus.FREE){  //if im FREE, pull down whats under me
                  if (b.ValidPosition(x-1, y)){

                     b.board[y][x].set_value(b.board[y][x-1].get_value());
                     b.board[y][x].set_status(b.board[y][x-1].get_status());

                     b.board[y][x-1].set_status_FREE(); //sets value to zero as well 
                  }
               }
               else{ //CellStatus == OCCUPIED 
                  if (b.ValidPosition(x-1, y)){

                     if(b.board[y][x].get_value() == b.board[y][x-1].get_value()){ //collapse cells 
                        if (!(collapsedE.contains(b.board[y][x-1])) | !(collapsedE.contains(b.board[y][x]))){
                           int new_cell_val = b.board[y][x].get_value() * 2;
                           score += new_cell_val;
                           game_over_flag(new_cell_val);
   
                           b.board[y][x].set_value(new_cell_val);
                           b.board[y][x].set_status_OCC();
                           b.board[y][x-1].set_status_FREE();
                           b.avail_cells += 1;
                           collapsedE.add(b.board[y][x]);
                           collapsedE.add(b.board[y][x-1]);
                        }

                        
                     }
                  }
               }
            }
         }
      }
   }

      /**
   * @brief Updates game board in west direction 
   * @details updates game board if user input represents LEFT direction 
   * @details updates score, avail_cells and checks if new CellT.value is 2048
   */
   private void updateW(){
      int[] r = {0,1,2,3};
      int[] i = {0,1,2,3};
      ArrayList<CellT> collapsedW = new ArrayList<CellT>();//will hold previously collapsed CellT objects, so that we write their values only once in update

      for (int j = 0; j < 4; j++){

         for (int y: r){
            for (int x : i){
               if (b.board[y][x].get_status() == CellStatus.FREE){  //if im FREE, pull down whats under me
                  if (b.ValidPosition(x+1, y)){

                     b.board[y][x].set_value(b.board[y][x+1].get_value());
                     b.board[y][x].set_status(b.board[y][x+1].get_status());

                     b.board[y][x+1].set_status_FREE(); //sets value to zero as well 
                  }
               }
               else{ //CellStatus == OCCUPIED 
                  if (b.ValidPosition(x+1, y)){

                     if(b.board[y][x].get_value() == b.board[y][x+1].get_value()){ //collapse cells 
                        if (!(collapsedW.contains(b.board[y][x+1])) | !(collapsedW.contains(b.board[y][x]))){

                           int new_cell_val = b.board[y][x].get_value() * 2;
                           score += new_cell_val;
                           game_over_flag(new_cell_val);
   
                           b.board[y][x].set_value(new_cell_val);
                           b.board[y][x].set_status_OCC();
                           b.board[y][x+1].set_status_FREE();
                           b.avail_cells += 1;
                           collapsedW.add(b.board[y][x]);
                           collapsedW.add(b.board[y][x+1]);
                        }
                     }
                  }
               }
            }
         }
      }
   }
}

