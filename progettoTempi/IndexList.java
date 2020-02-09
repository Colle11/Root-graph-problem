import java.util.Iterator;

public class IndexList implements Iterable<Index> {
	
	// instance attributes
	private Index head;
	private Index last;
	
	// IndexList constructor
	public IndexList(){
		head = null;
		last = null;
	}
	
	// method to add an index
	public void addIndex(int val){
		Index ind = new Index(val);
		if(isEmpty()){
			head = ind;
			last = ind;
		}else{
			last.setNext(ind);
			last = ind;
		}
	}
	
	// method to get head
	public Index getHead(){
		return head;
	}
	
	// method to get last
	public Index getLast(){
		return last;
	}
	
	// method to hold tail
	public void holdTail(){
		head = head.next();
	}
	
	// method to check if it is empty
	public boolean isEmpty(){
		return (head == null);
	}
	
	// method to get a String object representing the value of the IndexList Object
	public String toString(){
		String s = "";
		for(Index i:this){
			s += i.toString() + " ";
		}
		return s;
	}
	
	// method to get iterator
	public Iterator<Index> iterator() {
		return new IndexIterator(this);
	}
	
}
