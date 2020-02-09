
public class Vertex {
	
	// instance attributes
	private final String label;
	private Vertex next;
	
	// Vertex constructor
	public Vertex(String lbl){
		label = lbl;
		next = null;
	}
	
	// method to get label
	public String getLabel(){
		return label;
	}
	
	// method to see if there is a next element
	public boolean hasNext(){
		return (next != null);
	}
	
	// method to get the next element
	public Vertex next(){
		return next;
	}
	
	// method to set next
	public void setNext(Vertex vtx){
		next = vtx;
	}
	
	// method to get a String object representing the value of the Vertex Object
	public String toString(){
		return getLabel();
	}
	
}
