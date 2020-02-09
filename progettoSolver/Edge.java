
public class Edge {
	
	// instance attributes
	private final int vertexFrom;
	private final int vertexTo;
	
	// Edge constructor
	public Edge(int vFrom, int vTo){
		vertexFrom = vFrom;
		vertexTo = vTo;
	}
	
	// method to get vertexFrom
	public int getVertexFrom(){
		return vertexFrom;
	}
	
	// method to get vertexTo
	public int getVertexTo(){
		return vertexTo;
	}
	
	// method to get a String object representing the value of the Edge Object
	public String toString(){
		return "(" + vertexFrom + ", " + vertexTo + ")";
	}
	
}
