public class Percolation {

	public Percolation(int n) {

		N = n;
		initSystem();
	}

	private void initSystem() {

		grid = new boolean[N][N];
		index = new int[N][N];
		connection = new WeightedQuickUnionUF(N * N);
		int value = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = false;
				index[i][j] = value++;
			}
		}
	}

	public void open(int i, int j) {

		if ((i - 1 < 0) || (i - 1 > N - 1) || (j - 1 < 0) || (j - 1 > N - 1))
			throw new IndexOutOfBoundsException();

		grid[i - 1][j - 1] = true;
		checkNeibour(i - 1, j - 1);
	}

	private void checkNeibour(int i, int j) {

		// left
		if ((i - 1 >= 0) && isOpen(i, j + 1)) {
			connection.union(index[i][j], index[i - 1][j]);
		}

		// right
		if ((i + 1 < N) && isOpen(i + 2, j + 1)) {
			connection.union(index[i][j], index[i + 1][j]);
		}

		// top
		if ((j - 1 >= 0) && isOpen(i + 1, j)) {
			connection.union(index[i][j], index[i][j - 1]);
		}

		// bottom
		if ((j + 1 < N) && isOpen(i + 1, j + 2)) {
			connection.union(index[i][j], index[i][j + 1]);
		}
	}

	public boolean isOpen(int i, int j) {

		if ((i - 1 < 0) || (i - 1 > N - 1) || (j - 1 < 0) || (j - 1 > N - 1))
			throw new IndexOutOfBoundsException();
		return grid[i - 1][j - 1];
	}

	public boolean isFull(int i, int j) {

		if ((i - 1 < 0) || (i - 1 > N - 1) || (j - 1 < 0) || (j - 1 > N - 1))
			throw new IndexOutOfBoundsException();

		int currentPosition = index[i - 1][j - 1];
		for (int k = 1; k <= N; k++) {
			if (isOpen(1, k))
				if (connection.connected(index[0][k - 1], currentPosition))
					return true;
		}
		return false;
	}

	public boolean percolates() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int top = index[0][i];
				int bottom = index[N - 1][j];
				if (connection.connected(top, bottom))
					return true;
			}
		}
		return false;
	}

	private int N;
	private boolean[][] grid;
	private int[][] index;
	private WeightedQuickUnionUF connection;
}