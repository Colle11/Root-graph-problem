import java.util.Iterator;

public class VertexList implements Iterable<String> {

	// instance attributes
	private Vertex head;
	private Vertex last;
	private int numVertex;
	
	// VertexList constructor
	public VertexList(){
		head = null;
		last = null;
		numVertex = 0;
	}
	
	// method to add a vertex
	public void addVertex(String lbl){
		Vertex ver = new Vertex(lbl);
		if(isEmpty()){
			head = ver;
			last = ver;
		}else{
			last.setNext(ver);
			last = ver;
		}
		numVertex++;
	}
	
	// method to get head
	public Vertex getHead(){
		return head;
	}
	
	// method to check if it is empty
	public boolean isEmpty(){
		return (head == null);
	}
	
	// method to get numVertex
	public int getNumVertex(){
		return numVertex;
	}
	
	// method to check if there is the vertex
	public boolean isVertex(String vtx){
		for(String v:this){
			if(v.equals(vtx)){
				return true;
			}
		}
		return false;
	}
	
	// method to get a String object representing the value of the VertexList Object
	public String toString(){
		String s = "";
		for(String v:this){
			s += v + " ";
		}
		return s;
	}
	
	// method to get iterator
	public Iterator<String> iterator() {
		return new VertexIterator(this);
	}
	
}
