
public class IntStack {
	
	// instance attributes
	private int size;
	private int[] stack;
	private int top;
	
	// IntStack constructor
	public IntStack(int s){
		size = s;
		stack = new int[size];
		top = -1;
	}
	
	// method to push an int
	public void pushInt(int ind){
		top++;
		stack[top] = ind;
	}
	
	// method to pop an int
	public int popInt(){
		int ind = stack[top];
		top--;
		return ind;
	}
	
	// method to check if it is empty
	public boolean isEmpty(){
		return (top == -1);
	}
	
	// method to check if it is full
	public boolean isFull(){
		return (top == (size - 1));
	}
	
}
