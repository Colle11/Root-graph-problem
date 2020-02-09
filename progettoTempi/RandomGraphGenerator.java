
public class RandomGraphGenerator {
	
	// method to generate a random graph
	public static Graph getRandomGraph(int sizeGraph){
		long casual = System.currentTimeMillis();
		double seed = (casual * sizeGraph) % Integer.MAX_VALUE;
		PseudoRandomNumberGenerator prng = new PseudoRandomNumberGenerator(seed);
		Graph graph = new Graph(sizeGraph);
		
		for(int i=0; i<sizeGraph; i++){
			String label = "v" + i;
			graph.addNode(label);
		}
		
		double p = prng.getRanNum();
		double random;
		for(int i=0; i<sizeGraph; i++){
			for(int j=0; j<sizeGraph; j++){
				random = prng.getRanNum();
				if(random < p){
					graph.addEdge(i, j);
				}
			}
		}
		
		return graph;
	}
	
}
