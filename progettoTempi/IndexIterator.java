import java.util.Iterator;

public class IndexIterator implements Iterator<Index> {
	
	// instance attributes
	private Index ind;
	
	// IndexIterator constructor
	public IndexIterator(IndexList il){
		ind = il.getHead();
	}
	
	// method to see if there is a next element
	public boolean hasNext(){
		return (ind != null);
	}
	
	// method to get the next element
	public Index next(){
		Index curInd = ind;
		ind = ind.next();
		return curInd;
	}
	
	// method remove NOT supported
	public void remove(){
	    throw new UnsupportedOperationException();
	}
	
}
