package tiralabra.logic;

import java.util.ArrayList;
import tiralabra.logic.structures.NodeQueue;

/**
 * Not implemented yet.
 * better version of current a star. is replaced with upgraded version.
 * @author hexvaara
 */
public class Astar2 {
    
    private Board board;
    private NodeQueue nominees;
    private Node goal;
    
    public Astar2(Board board)
    {
        nominees = new NodeQueue();
        
        this.board = board;
    }
    
    public void setStart(int x, int y)
    {
        nominees.add(new Node(x,y));
        board.setStart(x, y);
    }
    
    public void setGoal(int x, int y)
    {
        goal = new Node(x,y);
        board.setGoal(x, y);
    }
    
    private void drawRoute(Node goal)
    {
        while (goal.previous != null)
        {
            goal = goal.previous;
            board.getBoard()[goal.x][goal.y] = 4;
        }
    }
    
    private boolean inProgress = true;
    
    public Board iterate()
    {
        if (!inProgress) return board;
        
        Node current = nominees.popClosest(goal);
        if (current.color == 3) 
        {
            inProgress = false;
            drawRoute(current);
            System.out.println("GOAL!!!");
        }
        board.visit(current.x, current.y);
        ArrayList<Node> neighbours = getNeighbours(current);
        for (Node neighbour : neighbours) {
            nominees.add(neighbour);
        }
            
        /*    
        if (inProgress)
        {
            for (Node neighbour : neighbours) 
            {
                if (neighbour.color == 2) continue;
                else if (neighbour.color == 1) continue;
                else if (neighbour.color == 3)
                {
                    System.out.println("GOAL!!!");
                    drawRoute(neighbour);
                        inProgress = false;
                        break;
                }
                else if (neighbour.color == 0) 
                {
                        visited.add(neighbour);
                        board.getBoard()[neighbour.x][neighbour.y] = 2;
                }
            }
        }
        */
        return board;
    }
    
    private ArrayList<Node> getNeighbours(Node current)
    {
        ArrayList<Node> returnlist = new ArrayList<>();
        
        if (current.x > 0 && isFreeNeighbour(board.getBoard()[current.x-1][current.y])) 
        {
            returnlist.add(new Node(current.x-1, current.y, board.getBoard()[current.x-1][current.y], current, current.totalWeight+100));
        } //ylös
        
        
        
        if (current.y > 0 && isFreeNeighbour(board.getBoard()[current.x][current.y-1]))
        {
            returnlist.add(new Node(current.x, current.y-1, board.getBoard()[current.x][current.y-1], current, current.totalWeight+100));
        } // vasen
        
        
        if (current.y < 19 && isFreeNeighbour(board.getBoard()[current.x][current.y+1]))
        {
            returnlist.add(new Node(current.x, current.y+1, board.getBoard()[current.x][current.y+1], current, current.totalWeight+100));
        } //oikea
        
        
        if (current.x < 19 && isFreeNeighbour(board.getBoard()[current.x+1][current.y]))
        {
            returnlist.add(new Node(current.x+1, current.y, board.getBoard()[current.x+1][current.y], current, current.totalWeight+100));
        } // alas
        
        
        if (current.x > 0 && current.y > 0 && isFreeNeighbour(board.getBoard()[current.x-1][current.y-1]))
        {
            returnlist.add(new Node(current.x-1, current.y-1, board.getBoard()[current.x-1][current.y-1], current, current.totalWeight+141));
        }  // vasen ylä
        
        
        if (current.x > 0 && current.y < 19 && isFreeNeighbour(board.getBoard()[current.x-1][current.y+1]))
        {
            returnlist.add(new Node(current.x-1, current.y+1, board.getBoard()[current.x-1][current.y+1], current, current.totalWeight+141));
        } // oikea ylä
        
        
        if (current.x < 19 && current.y > 0 && isFreeNeighbour(board.getBoard()[current.x+1][current.y-1]))
        {
            returnlist.add(new Node(current.x+1, current.y-1, board.getBoard()[current.x+1][current.y-1], current, current.totalWeight+141));
        } // vasen ala
        
        
        if (current.x < 19 && current.y < 19 && isFreeNeighbour(board.getBoard()[current.x+1][current.y+1]))
        {
            returnlist.add(new Node(current.x+1, current.y+1, board.getBoard()[current.x+1][current.y+1], current, current.totalWeight+141));
        } // oikea ala
        
        return returnlist;
    }
    private boolean isFreeNeighbour(int color)
    {
        return color == 0 || color == 3 || color == 4;
    }
}
