package src;

import org.junit.*;
import static org.junit.Assert.*;

public class Test {

    @Test //test collapsing of cells Left
    public void testCellCollapseL(){
        GameState game = new GameState();
        int expected_value = 16;    
        game.b.board[0][0].set_value(8);    
        game.b.board[0][1].set_value(8);
        game.move(Direction.LEFT);
            
        assertEquals(expected_value, game.b.board[0][0].get_value());
    }

    @Test //test collapsing of cells Right
    public void testCellCollapseR(){
        GameState game = new GameState();
        int expected_value = 16;    
        game.b.board[0][3].set_value(8);    
        game.b.board[0][2].set_value(8);
        game.move(Direction.RIGHT);
            
        assertEquals(expected_value, game.b.board[0][3].get_value());
    }

    @Test //test collapsing of cells Up
    public void testCellCollapseU(){
        GameState game = new GameState();
        int expected_value = 16;    
        game.b.board[0][0].set_value(8);    
        game.b.board[1][0].set_value(8);
        game.move(Direction.UP);
            
        assertEquals(expected_value, game.b.board[3][0].get_value());
    }

    @Test //test collapsing of cells Down
    public void testCellCollapseD(){
        GameState game = new GameState();
        int expected_value = 16;    
        game.b.board[0][0].set_value(8);    
        game.b.board[1][0].set_value(8);
        game.move(Direction.DOWN);
            
        assertEquals(expected_value, game.b.board[0][0].get_value());
    }

    @Test //test whether score updates appropriately 
    public void testScore(){
        GameState game = new GameState();
        int expected_value = 16;    
        game.b.board[0][0].set_value(8);    
        game.b.board[1][0].set_value(8);
        game.move(Direction.UP);

        assertEquals(expected_value, game.score);
    }
}
