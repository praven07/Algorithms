import java.util.ArrayList;
import java.util.Random;

public class PercolationTest {

	
	public static void main(String[] args) {

		int N = 1;
		p = new Percolation(N);
		InitalizeList(N);
		while (!p.percolates()) {
			openRandomSite();
		}
		StdOut.println("System Percolates");
	}


	private static void openRandomSite() {

		int k = rgen.nextInt(list.size() - 1);
		Value v = list.get(k);
		list.remove(k);
		int i = v.i;
		int j = v.j;
		p.open(i, j);
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
	private static Percolation p;
}