/****************************************************************************
 *  Compilation:  javac PercolationVisualizer.java
 *  Execution:    java PercolationVisualizer input.txt
 *  Dependencies: Percolation.java StdDraw.java In.java
 *
 *  This program takes the name of a file as a command-line argument.
 *  From that file, it
 *
 *    - Reads the grid size N of the percolation system.
 *    - Creates an N-by-N grid of sites (intially all blocked)
 *    - Reads in a sequence of sites (row i, column j) to open.
 *
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black,
 *  with with site (1, 1) in the upper left-hand corner.
 *
 ****************************************************************************/

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

public class PercolationVisualizer {

    // draw N-by-N percolation system
    public static void draw(Percolation perc, int N) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.filledSquare(N/2.0, N/2.0, N/2.0);

        // draw N-by-N grid
        int opened = 0;
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (perc.isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }
                else if (perc.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                }
                else
                    StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col - 0.5, N - row + 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(.25*N, -N*.025, opened + " open sites");
        if (perc.percolates()) StdDraw.text(.75*N, -N*.025, "percolates");
        else                   StdDraw.text(.75*N, -N*.025, "does not percolate");

    }

    
    
    public static void main(String[] args) {

    	UserInput input = new UserInput();
    	int N = input.getN();         // N-by-N percolation system
        // turn on animation mode
        StdDraw.show(0);
        InitalizeList(N);
        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(N);
        draw(perc, N);
        
        while(!perc.percolates()){
	         openRandomSite(perc);
	         draw(perc, N);
	         StdDraw.show(0);  //pause
        }
       StdAudio.play("beep-7.wav");
    }
    
	private static void openRandomSite(Percolation perc) {

		int k = rgen.nextInt(list.size() - 1);
		Value v = list.get(k);
		list.remove(k);
		int i = v.i;
		int j = v.j;
		perc.open(i, j);
	}

	
	private static void InitalizeList(int N) {

		rgen = new Random();
		list = new ArrayList<Value>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Value index = new Value(i, j);
				list.add(index);
			}
		}
	}

	
	private static class Value {

		/**
		 * It stores an set of index i and j for grid system
		 * 
		 * @param i
		 * @param j
		 */

		public Value(int i, int j) {

			this.i = i;
			this.j = j;
		}

		// IVAR
		public int i, j;
	}

	
	
	private static ArrayList<Value> list;
	private static Random rgen;
}
