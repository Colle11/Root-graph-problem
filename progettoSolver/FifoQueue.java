
public class FifoQueue {
	
	// instance constructor
	private final int[] queue;
	private int enqueue; // points to the next empty cell
	private int dequeue; // points to the first index added
	
	// FifoQueue constructor
	public FifoQueue(int size){
		queue = new int[size];
		enqueue = 0;
		dequeue = 0;
	}
	
	// method to enqueue index
	public void enqueueIndex(int ind){
		queue[enqueue] = ind;
		enqueue++;
	}
	
	// method to dequeue index
	public int dequeueIndex(){
		int ind = queue[dequeue];
		dequeue++;
		return ind;
	}
	
	// method to check if it is empty
	public boolean isEmpty(){
		return (dequeue == enqueue);
	}
	
	// method to get a String object representing the value of the FifoQueue Object
	public String toString(){
		String s = "";
		for(int i=dequeue; i<enqueue; i++){
			s += queue[i] + " ";
		}
		return s;
	}
	
}
