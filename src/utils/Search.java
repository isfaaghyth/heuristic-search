package utils;

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
        System.out.println("====== BFS ======");
        int h = initState.heuristic();
        return h;

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
        int g = initState.getCost();
        int h = initState.heuristic();
        return g + h;
    }

}
