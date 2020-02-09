
public class RootAndEdges {
	
	// instance attributes
	private final int root;
	private final EdgeList edges;
	
	// RootAndEdges constructor
	public RootAndEdges(int r, EdgeList e){
		root = r;
		edges = e;
	}
	
	// method to get root
	public int getRoot(){
		return root;
	}
	
	// method to get edges
	public EdgeList getEdges(){
		return edges;
	}
	
}
