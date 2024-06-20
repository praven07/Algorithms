public class QuickFindUF{
	
	public QuickFindUF(int N){
		
		id = new int[N];
		for(int i = 0; i < id.length; i++){
			id[i] = i;
		}
	}
	
	
	/**
	 * It connects or union the given two sites.
	 * @param p
	 * @param q
	 */
	public void union(int p,int q){
		
		id[root(p)] = root(q);
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
	 * It finds the root of the given site or id.
	 */
	private int root(int p){
		
		while(id[p] != p){
			p = id[p];
		}
		return p;
	}
	
	
	private int[] id;
	
	
	
	//Client
	public static void main(String[] args){
		
		QuickFindUF uf = new QuickFindUF(10);
		StdOut.println(uf.connected(5, 9));
		StdOut.println(uf.connected(1, 3));
		uf.union(5, 9);
		uf.union(1, 9);
		StdOut.println(uf.connected(5, 9));
		StdOut.println(uf.connected(1, 9));
		StdOut.println(uf.connected(5, 1));
		StdOut.println(uf.connected(2, 3));
	}
}