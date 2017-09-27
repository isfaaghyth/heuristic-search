package utils;

import java.util.ArrayList;

/**
 * Kelas Board merepresentasikan suatu board dalam 8-puzzle. Implements
 * Comparable agar bisa diurutkan berdasarkan dari cost terendah ke tertinggi
 * saat proses Astar
 *
 * @author
 */
public class Board implements Comparable<Board> {

    /**
     * Banyaknya baris pada Board
     */
    private int rows;

    /**
     * Banyaknya kolom pada Board
     */
    private int cols;

    /**
     * Mengetahui letak baris yang berisi nilai 0 (kosong)
     */
    private int xZero = 0;

    /**
     * Mengetahui letak kolom yang berisi nilai 0 (kosong)
     */
    private int yZero = 0;

    /**
     * Array integer untuk board
     */
    private int[][] board;

    /**
     * Digunakan untuk mengeset nilai g(n) pada proses Astar
     */
    private int cost;

    /**
     * Constructor default untuk menginisialisasi board dengan ukuran baris dan
     * kolom tertentu
     *
     * @param rows banyaknya baris board yang ingin dibuat
     * @param cols banyaknya koom board yang ingin dibuat
     */
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new int[rows][cols];
    }

    /**
     * Constructor yang menerima board untuk melakukan pengkopian board pada
     * saat mengenerate successor function
     *
     * @param board board yang akan dikopi
     */
    public Board(int[][] board) {
        this.rows = board.length;
        this.cols = board[0].length;
        // Copy board
        this.board = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j] = board[i][j];
            }
        }

    }

    /**
     * Fungsi isGoal untuk mengecek apakah suatu Board merupakan goal State
     * Suatu board merupakan goal jika setiap baris dan kolom terurut kecuali
     * baris terakhir yang harus angka 0.
     *
     * @return true jika goal; false otherwise
     */
    public boolean isGoal() {
        int count = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Cek posisi terakhir merupakan 0
                if (i == rows - 1 && j == cols - 1 && board[i][j] == 0) {
                    return true;
                }
                // Jika tidak terurut langsung return salah
                if (board[i][j] != count) {
                    return false;
                }
                count++;
            }
        }

        return true;
    }

    /**
     * Fungsi untuk mengeset nilai pada board
     *
     * @param x baris
     * @param y kolom
     * @param value nilai yang ingin dimasukkan
     */
    public void setBoard(int x, int y, int value) {
        board[x][y] = value;
        // Jika yang dimasukkan 0 maka nilai xZero dan yZero diupdate
        if (value == 0) {
            xZero = x;
            yZero = y;
        }
    }

    /**
     * Fungsi untuk mengecek apakah suatu koordinat keluar dari board
     *
     * @param x baris yang ingin dicek
     * @param y kolom yang ingin dicek
     * @return apakah x,y keluar dari board
     */
    public boolean isOutOfBound(int x, int y) {
        return x < 0 || y < 0 || x >= rows || y >= cols;
    }

    /**
     * Successor Function dari suatu board. Successor didapat dengan menukar
     * angka 0 dengan angka di atas, bawah, kanan, dan kiri (jika bisa).
     *
     * @return Successor states dari Board ini
     */
    public ArrayList<Board> getNextMove() {
        ArrayList<Board> res = new ArrayList<Board>();

        Board b;
        // Tukar atas
        if (!isOutOfBound(xZero - 1, yZero)) {
            b = new Board(board);
            b.setBoard(xZero, yZero, board[xZero - 1][yZero]);
            b.setBoard(xZero - 1, yZero, 0);
            res.add(b);
        }
        // Tukar kanan
        if (!isOutOfBound(xZero, yZero + 1)) {
            b = new Board(board);
            b.setBoard(xZero, yZero, board[xZero][yZero + 1]);
            b.setBoard(xZero, yZero + 1, 0);
            res.add(b);
        }
        // Tukar bawah
        if (!isOutOfBound(xZero + 1, yZero)) {
            b = new Board(board);
            b.setBoard(xZero, yZero, board[xZero + 1][yZero]);
            b.setBoard(xZero + 1, yZero, 0);
            res.add(b);
        }
        // Tukar kiri
        if (!isOutOfBound(xZero, yZero - 1)) {
            b = new Board(board);
            b.setBoard(xZero, yZero, board[xZero][yZero - 1]);
            b.setBoard(xZero, yZero - 1, 0);
            res.add(b);
        }

        return res;
    }

    /**
     * Method untuk menghitung heuristic dari suatu Board
     *
     * @return nilai heuristik
     */
    public int heuristic() {
        int manhattanDistanceSum = 0;
        // TODO: Implementasikan! Tidak boleh heuristik = 0 atau fungsi konstan (ref: 12526792)
        for (int x = 0; x < rows; x++) { // x-dimension, traversing rows (i)
            for (int y = 0; y < cols; y++) { // y-dimension, traversing cols (j)
                int value = board[x][y]; // tiles array contains board elements
                if (value != 0) { // we don't compute MD for element 0
                    int targetX = (value - 1) / rows; // expected x-coordinate (row)
                    int targetY = (value - 1) % rows; // expected y-coordinate (col)
                    int dx = x - targetX; // x-distance to expected coordinate
                    int dy = y - targetY; // y-distance to expected coordinate
                    manhattanDistanceSum += Math.abs(dx) + Math.abs(dy); 
                } 
            }
        }
        return manhattanDistanceSum;
    }

    /**
     * Accessor untuk variabel cost
     *
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Mutator untuk variabel cost
     *
     * @param cost cost yang ingin diset
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    /**
     * Membandingkan dua buah board dengan melihat f(n) = g(n) + h(n) dari dua
     * buah board. Nilai akan terurut secara naik.
     */
    public int compareTo(Board b) {
        return (cost + heuristic()) - (b.cost + b.heuristic());
    }

    /**
     * Method untuk mencetak board dengan rapih
     */
    public void printBoard() {
        String widthStr = ((rows * cols) - 1) + "";
        int width = widthStr.length();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out
                        .print(String.format("%" + width + "s ", board[i][j]));
            }
            System.out.println();
        }
    }

}
