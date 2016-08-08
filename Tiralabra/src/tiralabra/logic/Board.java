package tiralabra.logic;

import java.util.Random;

/**
 * Byte map for handling current status
 * 
 * @author hexvaara
 */
public class Board {
    private byte[][] board;
    private byte x, y;
    
    
    public byte[][] getBoard()
    {
        return board;
    }
    
    public Board(byte x, byte y)
    {
        this.x = x;
        this.y = y;
        board = new byte[x][y];
        flush();
    }
    
    /**
     * writes 0 for each unit
     */
    private void flush()
    {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = (byte) 0;
            }
        }
    }
    
    /**
     * create randomly distributed barriers
     * @param density in percents
     */
    public void randomizeObjects(int density)
    {
        Random r = new Random();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (r.nextInt(100) < density) board[i][j] = 1;
            }
        }
        
    }
    
    public void setStart(int x, int y)
    {
        board[x][y] = 4;
    }
    
    public void setGoal(int x, int y)
    {
        board[x][y] = 3;
    }
    
    @Override
    public String toString()
    {
        String returnvalue = "";
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                returnvalue += board[i][j];
            }
            returnvalue += "\n";
        }
        return returnvalue;
    }
}
