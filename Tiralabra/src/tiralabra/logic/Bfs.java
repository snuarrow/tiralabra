package tiralabra.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS search algorithm.
 * modifies board
 * @author hexvaara
 */
public class Bfs {
    
    private Board board;
    private Queue<Node> visited;
    
    
    public Bfs(Board board)
    {
        this.board = board;
        visited = new LinkedList<>();
    }
    
    public void setStart(int x, int y)
    {
        visited.add(new Node(x,y,2, null, 0));
    }
    
    // 0 not visited
    // 1 obstacle
    // 2 visited
    // 3 goal
    // 4 start
    
    boolean inProgress = true;
    
    
    public Board iterate()
    {
        // todo, empty queue
        if (inProgress)
        {
            Node current = visited.poll();
            ArrayList<Node> neighbours = getNeighbours(current);
        
            if (inProgress)
            {
                for (Node neighbour : neighbours) {
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
        }
        return board;
    }
    
    private void drawRoute(Node goal)
    {
        while (goal.previous != null)
        {
            goal = goal.previous;
            board.getBoard()[goal.x][goal.y] = 4;
        }
    }
    
    
    
    private ArrayList<Node> getNeighbours(Node current)
    {
        ArrayList<Node> returnlist = new ArrayList<>();
        
        if (current.x > 0) returnlist.add(new Node(current.x-1, current.y, board.getBoard()[current.x-1][current.y], current, 0)); //ylös
        if (current.y > 0) returnlist.add(new Node(current.x, current.y-1, board.getBoard()[current.x][current.y-1], current, 0)); // vasen
        if (current.y < 19) returnlist.add(new Node(current.x, current.y+1, board.getBoard()[current.x][current.y+1], current, 0)); //oikea
        if (current.x < 19) returnlist.add(new Node(current.x+1, current.y, board.getBoard()[current.x+1][current.y], current, 0)); // alas
        if (current.x > 0 && current.y > 0) returnlist.add(new Node(current.x-1, current.y-1, board.getBoard()[current.x-1][current.y-1], current, 0));  // vasen ylä
        if (current.x > 0 && current.y < 19) returnlist.add(new Node(current.x-1, current.y+1, board.getBoard()[current.x-1][current.y+1], current, 0)); // oikea ylä
        if (current.x < 19 && current.y > 0) returnlist.add(new Node(current.x+1, current.y-1, board.getBoard()[current.x+1][current.y-1], current, 0)); // vasen ala
        if (current.x < 19 && current.y < 19) returnlist.add(new Node(current.x+1, current.y+1, board.getBoard()[current.x+1][current.y+1], current, 0)); // oikea ala
        
        return returnlist;
    }
}
