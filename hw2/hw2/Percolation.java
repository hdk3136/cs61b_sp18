package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int n;
    private final boolean[][] sites;
    private WeightedQuickUnionUF wqu;
    private int numOfOpenSites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.n = N;
        sites = new boolean[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                sites[i][j] = false;
            }
        }
        wqu = new WeightedQuickUnionUF(N * N);
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
        if (validate(row + 1, col) && isOpen(row + 1, col)) {
            wqu.union(xyToNum(row, col), xyToNum(row + 1, col));
        }
        if (validate(row - 1, col) && isOpen(row - 1, col)) {
            wqu.union(xyToNum(row, col), xyToNum(row - 1, col));
        }
        if (validate(row, col + 1) && isOpen(row, col + 1)) {
            wqu.union(xyToNum(row, col), xyToNum(row, col + 1));
        }
        if (validate(row, col - 1) && isOpen(row, col - 1)) {
            wqu.union(xyToNum(row, col), xyToNum(row, col - 1));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validate(row, col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        sites[row][col] = true;
        numOfOpenSites += 1;
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
        if (row == 0) {
            return isOpen(row, col);
        } else {
            for (int i = 0; i < n; i += 1) {
                if (wqu.connected(xyToNum(0, i), xyToNum(row, col))) {
                    return true;
                }
            }
        }

        return false;

    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < n; i += 1) {
            if (isFull(n - 1, i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }


}
