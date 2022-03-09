package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int n;
    private final boolean[][] sites;
    private final int topNode;
    private final int bottomNode;
    private WeightedQuickUnionUF wquAll;
    private WeightedQuickUnionUF wquNoBottomNode;
    private int numOfOpenSites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.n = N;
        topNode = N * N;
        bottomNode = N * N + 1; // the value of topNode and bottomNode is indifferent
        sites = new boolean[N][N];

        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                sites[i][j] = false;
            }
        }
        wquAll = new WeightedQuickUnionUF(N * N + 2);
        wquNoBottomNode = new WeightedQuickUnionUF(N * N + 1);
        numOfOpenSites = 0;
    }

    //row is x and col is y
    private int xyToNum(int x, int y) {
        return n * x + y;
    }

    private boolean validate(int row, int col) {
        return (row >= 0 && row <= n - 1) && (col >= 0 && col <= n - 1);
    }

    private void updateUnion(int row, int col) {
        if (row == 0) {
            wquAll.union(topNode, xyToNum(row, col));
            wquNoBottomNode.union(topNode, xyToNum(row, col));
        }
        if (row == n - 1) {
            wquAll.union(bottomNode, xyToNum(row, col));
        }
        if (validate(row + 1, col) && isOpen(row + 1, col)) {
            wquAll.union(xyToNum(row, col), xyToNum(row + 1, col));
            wquNoBottomNode.union(xyToNum(row, col), xyToNum(row + 1, col));
        }
        if (validate(row - 1, col) && isOpen(row - 1, col)) {
            wquAll.union(xyToNum(row, col), xyToNum(row - 1, col));
            wquNoBottomNode.union(xyToNum(row, col), xyToNum(row - 1, col));
        }
        if (validate(row, col + 1) && isOpen(row, col + 1)) {
            wquAll.union(xyToNum(row, col), xyToNum(row, col + 1));
            wquNoBottomNode.union(xyToNum(row, col), xyToNum(row, col + 1));
        }
        if (validate(row, col - 1) && isOpen(row, col - 1)) {
            wquAll.union(xyToNum(row, col), xyToNum(row, col - 1));
            wquNoBottomNode.union(xyToNum(row, col), xyToNum(row, col - 1));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validate(row, col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!sites[row][col]) {
            numOfOpenSites += 1;
        }
        sites[row][col] = true;
        updateUnion(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validate(row, col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validate(row, col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return wquNoBottomNode.connected(topNode, xyToNum(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquAll.connected(topNode, bottomNode);
    }

    public static void main(String[] args) {

    }


}
