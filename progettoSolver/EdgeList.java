
public class EdgeList {

	// instance attributes
	private final IndexList fromEdges;
	private final IndexList toEdges;
	private int numEdges;
	
	// EdgeList constructor
	public EdgeList(){
		fromEdges = new IndexList();
		toEdges = new IndexList();
		numEdges = 0;
	}
	
	// method to add an edge
	public void addEdge(int indFrom, int indTo){
		fromEdges.addIndex(indFrom);
		toEdges.addIndex(indTo);
		numEdges++;
	}
	
	// method to get numEdges
	public int getNumEdges(){
		return numEdges;
	}
	
	// method to check if it is empty
	public boolean isEmpty(){
		return fromEdges.isEmpty();
	}
	
	// method to get head
	public Edge getHead(){
		int vFrom = fromEdges.getHead().getValue();
		int vTo = toEdges.getHead().getValue();
		Edge e = new Edge(vFrom, vTo);
		return e;
	}
	
	// method to hold tail
	public void holdTail(){
		fromEdges.holdTail();
		toEdges.holdTail();
		numEdges--;
	}
	
}
