import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {
	
	// class attributes
	public static final String NAME_PATTERN = "digraph\\s*(\\w*)\\s*\\{";
	public static final String NODE_PATTERN = "((\\w+)\\s*->)?\\s*(\\w+)\\s*;";

	// instance attributes
	private final String input;
	private final Pattern namePattern;
	private final Pattern nodePattern;
	private int graphSize;
	private Graph graph;
	
	// Parser constructor
	public Parser(){
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\\Z");
		input = scanner.next();
		scanner.close();
		namePattern = Pattern.compile(NAME_PATTERN);
		nodePattern = Pattern.compile(NODE_PATTERN);
		graphSize = -1;
		graph = null;
	}
	
	// method to do the first pass of input to get the number of vertices
	private void doFirstPass(){
		Scanner scanner = new Scanner(input);
		String line;
		Matcher matcher;
		VertexList vertices = new VertexList();
		String ver = "";
		String verFrom = "";
		String verTo = "";
		
		while(scanner.hasNextLine()){
			line = scanner.nextLine();
			matcher = nodePattern.matcher(line);
			
			while(matcher.find()){
				if(matcher.group(1) == null){
					ver = matcher.group(3);
					if(!vertices.isVertex(ver)){
						vertices.addVertex(ver);
					}
				}else{
					verFrom = matcher.group(2);
					if(!vertices.isVertex(verFrom)){
						vertices.addVertex(verFrom);
					}
					verTo = matcher.group(3);
					if(!vertices.isVertex(verTo)){
						vertices.addVertex(verTo);
					}
				}
			}
		}
		
		scanner.close();
		graphSize = vertices.getNumVertex();
	}
	
	// method to do the second pass of input to create graph
	private void doSecondPass(){
		Scanner scanner = new Scanner(input);
		graph = new Graph(graphSize);
		String line;
		Matcher matcher;
		int indFrom;
		int indTo;
		String graphName = "";
		String ver = "";
		String verFrom = "";
		String verTo = "";
		
		while(scanner.hasNextLine()){
			line = scanner.nextLine();
			
			matcher = namePattern.matcher(line);
			if(matcher.find()){
				graphName = matcher.group(1);
				graph.setGraphName(graphName);
			}
			
			matcher = nodePattern.matcher(line);
			while(matcher.find()){
				if(matcher.group(1) == null){
					ver = matcher.group(3);
					graph.addNode(ver);
				}else{
					verFrom = matcher.group(2);
					verTo = matcher.group(3);
					indFrom = graph.addNode(verFrom);
					indTo = graph.addNode(verTo);
					graph.addEdge(indFrom, indTo);
				}
			}
		}
		
		scanner.close();
	}
	
	// method to create graph from input
	public Graph createGraph(){
		doFirstPass();
		doSecondPass();
		return graph;
	}
	
}
