/**
 * Author: Mark Procopio
 * Revised: Apr 10, 2021
 * @brief CellT ADT that represents the cell of the 2048 board game 
 * @details utilizes CellStatus Enum types 
 */

package src;

public class CellT {

    int value; 
    CellStatus status; 

    /**
     * @brief Constructor for CellT 
     * @details always initializes to 0 and set to FREE status 
     */
    CellT(){
        this.value = 0;
        this.status = CellStatus.FREE;

        if (this.value == 0 && this.status == CellStatus.OCCUPIED){
            throw new IllegalArgumentException("Occupied cells cannot be zero");
        }
    }

    /**
     * @brief Setter method for CellT value 
     * @param num
     */
    public void set_value(int num){
        if (num > 2048){
            throw new IllegalArgumentException("larger than max allowable");
        }
        this.value = num;
    }

    /**
     * @brief Getter method for CellT value 
     * @return int 
     */
    public int get_value(){
        return this.value; 
    }

    /**
     * @brief 
     * @return CellStatus enum type 
     */
    public CellStatus get_status(){
        return this.status;
    }

    /**
     * @brief sets status of CellT
     * @param status is CellStatus enum type
     */
    public void set_status(CellStatus status){
        this.status = status;
    }

    /**
     * @brief sets CellT.status to CellStatus.OCCUPIED
     */
    public void set_status_OCC(){
        this.status = CellStatus.OCCUPIED;
    }
    
    /**
    * @brief sets CellT.status to CellStatus.FREE
    */
    public void set_status_FREE(){
        this.status = CellStatus.FREE;
        this.set_value(0);
    }
}
