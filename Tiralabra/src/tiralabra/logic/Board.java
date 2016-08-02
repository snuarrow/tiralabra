/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import java.util.Random;

/**
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
    
    private void flush()
    {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = (byte) 0;
            }
        }
    }
    
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
