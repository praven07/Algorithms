
public class QuickUnionUF{
	
	public QuickUnionUF(int N){
		
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
		
		int idp = id[p];
		int idq = id[q];
		for(int i = 0; i < id.length; i++){
			if(id[i] == idp){
				id[i] = idq;
			}
		}
	}
	
	
	/**
	 * It checks to see if the given two sites are connected.
	 * @param p
	 * @param q
	 * @return boolean
	 */
	public boolean connected(int p,int q){
		
		if(id[p] == id[q]){
			return true;
		}
		return false;
	}
	
	//IVAR
	private int[] id;


	//Client
	public static void main(String[] args){
		
		QuickUnionUF uf = new QuickUnionUF(10);
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