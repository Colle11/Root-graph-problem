
public class Main {
	
	public static void main(String[] args){		
		Parser p = new Parser();
		Graph g = p.createGraph();
		Graph gSCC = g.doTarjanSCC();
		RootAndEdges re = gSCC.getRootAndMinEdges();
		g.addRootAndMinEdges(re);
		g.doBFS();
		System.out.println(g.toDotString());
	}
	
}
