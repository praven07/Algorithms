public class WeightedQuickUnionUF{
	
	
	
	public WeightedQuickUnionUF(int N){
		
		id = new int[N];
		size = new int[N];
		for(int i = 0; i < id.length; i++){
			id[i] = i;
			size[i] = 1;
		}
	}
	
	
	
	/**
	 * It connects or union the given two sites.
	 * @param p
	 * @param q
	 */
	public void union(int p,int q){
		
		int i = root(p);
		int j = root(q);
		if(size[i] <= size[j]){
			id[i] = id[j];
			size[j] += size[i];
		}else{
			id[j] = id[i];
			size[i] += size[j];
		}
	}
	
	
	
	/**
	 * It checks to see if the given two sites are connected.
	 * @param p
	 * @param q
	 * @return boolean
	 */
	public boolean connected(int p,int q){
		
		if(root(p) == root(q)){
			return true;
		}
		return false;
	}
	
	
	
	/*
	 * Finds the root of the given site.
	 */
	public int root(int p){
		
		while(id[p] != p){
			p = id[p];
		}
		return p;
	}
	
	
	private int[] id,size;
	
	
	
	//Client
	public static void main(String[] args){
		
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
		StdOut.println(uf.connected(5, 9));
		StdOut.println(uf.connected(1, 3));
		uf.union(5, 9);
		uf.union(9, 1);
		StdOut.println(uf.connected(5, 9));
		StdOut.println(uf.connected(1, 9));
		StdOut.println(uf.connected(5, 1));
		StdOut.println(uf.connected(2, 3));
		StdOut.println(uf.root(1));
	}
}