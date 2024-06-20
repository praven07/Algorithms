import java.util.ArrayList;
import java.util.Random;

public class PercolationStats {

	public static void main(String[] args) {

		 In in = new In(args[0]); // input file
		 int n = in.readInt();
		 int t = in.readInt();
//		int n = 1;
//		int t = 1;
		StdOut.println(n);
		PercolationStats stats = new PercolationStats(n, t);
		double mean = stats.mean();
		double stddev = stats.stddev();
		double a = mean - (1.96 * stddev / Math.sqrt(t));
		double b = mean + (1.96 * stddev / Math.sqrt(t));
		StdOut.print("mean = ");
		StdOut.println(mean);
		StdOut.print("stddev = ");
		StdOut.println(stddev);
		StdOut.print("95% confidence interval = ");
		StdOut.println(a + " , " + b);
	}

	public PercolationStats(int n, int t) {

		if ((n <= 0) || (t <= 0))
			throw new IllegalArgumentException();
		N = n;
		T = t;
		for (int i = 0; i < t; i++) {
			sitesOpenCount = 0;
			Percolation perc = new Percolation(N);
			while (!perc.percolates()) {
				openRandomSite(perc);
				sitesOpenCount++;
			}
			meanList.add(sitesOpenCount / (double) (N * N));
		}
	}

	public double mean() {

		double m = 0;
		for (int i = 0; i < meanList.size(); i++) {
			m += (double) meanList.get(i);
		}
		return (double) m / T;
	}

	public double stddev() {

		double result = 0;
		double mean = mean();
		double sum = 0;
		for (int i = 0; i < meanList.size(); i++) {
			sum += Math.pow(meanList.get(i) - mean, 2);
		}
		result = (double) Math.sqrt(sum / (T - 1));
		return result;
	}

	private void openRandomSite(Percolation perc) {

		initalizeList(N);
		int k = rgen.nextInt(list.size() - 1);
		Value v = list.get(k);
		list.remove(k);
		int i = v.i;
		int j = v.j;
		perc.open(i, j);
	}

	private static void initalizeList(int N) {

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
	private static Random rgen = new Random();
	private int N;
	private int T, sitesOpenCount;
	private ArrayList<Double> meanList = new ArrayList<Double>();
}