
public class Node {
	
	// node's color
	public enum Color {
	    WHITE, GRAY, BLACK
	}
	
	// instance attributes
	private final String label;
	private final int index;
	private int oldIndex;
	private Color color;
	private IndexList inEdges;
	private IndexList outEdges;
	private int start;
	private int end;
	private int lowStart;
	private int distance;
	private int parent;
	
	// Node constructor
	public Node(String lbl, int ind){
		label = lbl;
		index = ind;
		oldIndex = -1;
		color = Color.WHITE;
		inEdges = new IndexList();
		outEdges = new IndexList();
		start = -1;
		end = -1;
		lowStart = -1;
		distance = Integer.MAX_VALUE;
		parent = -1;
	}
	
	// method to get label
	public String getLabel(){
		return label;
	}
	
	// method to get index
	public int getIndex(){
		return index;
	}
	
	// method to set oldIndex
	public void setOldIndex(int ind){
		oldIndex = ind;
	}
	
	// method to get oldIndex
	public int getOldIndex(){
		return oldIndex;
	}
	
	// method to set color
	public void setColor(Color clr){
		color = clr;
	}
	
	//method to get color
	public Color getColor(){
		return color;
	}
	
	// method to get inEdges
	public IndexList getInEdges(){
		return inEdges;
	}
	
	// method to get outEdges
	public IndexList getOutEdges(){
		return outEdges;
	}
	
	// method to get start
	public int getStart(){
		return start;
	}
	
	// method to set start
	public void setStart(int s){
		start = s;
	}
	
	// method to get end
	public int getEnd(){
		return end;
	}
	
	// method to set end
	public void setEnd(int e){
		end = e;
	}
	
	// method to get lowStart
	public int getLowStart(){
		return lowStart;
	}
	
	// method to set lowStart
	public void setLowStart(int lowS){
		lowStart = lowS;
	}
	
	// method to get distance
	public int getDistance(){
		return distance;
	}
	
	// method to set distance
	public void setDistance(int dist){
		distance = dist;
	}
	
	// method to get parent
	public int getParent(){
		return parent;
	}
	
	// method to set parent
	public void setParent(int par){
		parent = par;
	}
	
	// method to get a String object representing the value of the Node Object
	public String toString(){
		return index + " - " + label + " -> out: " + outEdges.toString() + " || in: " + inEdges.toString();
	}
	
}
