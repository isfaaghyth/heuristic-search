/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristic.search;

import java.util.Scanner;
import utils.Board;
import utils.Search;

/**
 *
 * @author isfaaghyth
 */
public class HeuristicSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Membaca ukuran baris dan kolom
        String[] read = scan.nextLine().split(" ");
        int rows = Integer.parseInt(read[0]);
        int cols = Integer.parseInt(read[1]);

        // Membaca board masukan
        Board board = new Board(rows, cols);
        for (int i = 0; i < rows; i++) {
            read = scan.nextLine().split(" ");
            for (int j = 0; j < cols; j++) {
                board.setBoard(i, j, Integer.parseInt(read[j]));
            }
        }

        // Melakukan algoritma search
        int bfsExpand = Search.bfs(board);
        int astarExpand = Search.astar(board);
        System.out.println("Expand BFS: " + bfsExpand);
        System.out.println("Expand Astar: " + astarExpand);
        scan.close();
    }

}
