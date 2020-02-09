import java.util.Iterator;

public class VertexIterator implements Iterator<String> {
	
	// instance attributes
	private Vertex ver;
	
	// VertexIterator constructor
	public VertexIterator(VertexList vl){
		ver = vl.getHead();
	}
	
	// method to see if there is a next element
	public boolean hasNext(){
		return (ver != null);
	}
	
	// method to get the next element
	public String next(){
		String curLbl = ver.getLabel();
		ver = ver.next();
		return curLbl;
	}
	
	// method remove NOT supported
	public void remove(){
		throw new UnsupportedOperationException();
	}
	
}
