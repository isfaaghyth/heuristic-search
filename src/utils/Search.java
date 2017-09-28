package utils;

import java.util.ArrayList;

/**
 * Kelas Search yang berisi method static untuk algoritma search. Yaitu
 * algoritma search BFS dan Astar.
 *
 * @author
 */
public class Search {
    
    /**
     * TODO: IMPLEMENTASIKAN Method untuk melakukan BFS dari suatu initial
     * state. Di dalamnya juga dicetak path dari awal menuju goal.
     *
     * @param initState initial state
     * @return jumlah node yang diexpand
     */
    public static int bfs(Board initState) {
        int cost = 100;
        int sum = 0;
        System.out.println("====== BFS ======");
        initState.printBoard();
        ArrayList<Board> bfsBoard = initState.getNextMove();
        for (Board b : bfsBoard) {
            if (b.getCost() >= cost) {
                cost = b.getCost();
            }
            sum++;
        }
        //recursive board terkecil
        return sum;
    }

    /**
     * TODO: IMPLEMENTASIKAN Method untuk melakukan Astar dari suatu initial
     * state. Di dalamnya juga dicetak path dari awal menuju goal.
     *
     * @param initState initial state
     * @return jumlah node yang diexpand
     */
    public static int astar(Board initState) {
        System.out.println("====== AStar ======");
        
        return 0;
    }

}
