
public class Graph {
	
	// instance attributes
	private int lastIndex;
	private Node[] graph;
	private int root;
	private String graphName;
	private int addedEdges;
	private int numEdges;
	
	// Graph constructor
	public Graph(int size){
		graph = new Node[size];
		lastIndex = 0;
		root = -1;
		graphName = "";
		addedEdges = 0;
		numEdges = 0;
	}
	
	// method to get numEdges
	public int getNumEdges(){
		return numEdges;
	}
	
	// method to get lastIndex
	public int getLastIndex(){
		return lastIndex;
	}
	
	// method to set lastIndex
	public void setLastIndex(int ind){
		lastIndex = ind;
	}
	
	// method to set root
	public void setRoot(int r){
		root = r;
	}
	
	// method to get root
	public int getRoot(){
		return root;
	}
	
	// method to get i-th Node
	public Node getIthNode(int ith){
		return graph[ith];
	}
	
	// method to set i-th Node
	public void setIthNode(int ith, Node n){
		graph[ith] = n;
	}
	
	// method to get graphName
	public String getGraphName(){
		return graphName;
	}
	
	// method to set graphName
	public void setGraphName(String gn){
		graphName = gn;
	}
	
	// method to get addedEdges
	public int getAddedEdges(){
		return addedEdges;
	}
	
	// method to get the index of the node
	public int getIndexNode(String label){
		int i = 0;
		while(i<graph.length && (graph[i]==null || !graph[i].getLabel().equals(label))){
			i++;
		}
		if(i >= graph.length){
			return -1;
		}else{
			return graph[i].getIndex();
		}
	}
	
	// method to add a node
	public int addNode(String label){
		int ind = getIndexNode(label);
		if(ind == -1){
			graph[lastIndex] = new Node(label, lastIndex);
			return lastIndex++;
		}else{
			return ind;
		}
	}
	
	// method to add an edge
	public void addEdge(int indFrom, int indTo){
		graph[indFrom].getOutEdges().addIndex(indTo);
		graph[indTo].getInEdges().addIndex(indFrom);
		numEdges++;
	}
	
	// method to compute strongly connected components
	public Graph doTarjanSCC(){
		Graph gSCCDirty = new Graph(graph.length);
		for(Node v:graph){
			v.setColor(Node.Color.WHITE);
			v.setStart(-1);
			v.setEnd(-1);
			v.setLowStart(-1);
		}
		int time = 0;
		IntStack indStack = new IntStack(graph.length);
		EdgeList edges = new EdgeList();
		int[] lowstartIndMap = new int[graph.length];
		for(int i=0; i<lowstartIndMap.length; i++){
			lowstartIndMap[i] = -1;
		}
		
		for(Node v:graph){
			if(v.getColor() == Node.Color.WHITE){
				time = doStrongConnect(gSCCDirty, v, time, indStack, edges, lowstartIndMap);
			}
		}
		
		while(!edges.isEmpty()){
			Edge e = edges.getHead();
			int vFrom = e.getVertexFrom();
			int vTo = e.getVertexTo();
			int lowStartFrom = graph[vFrom].getLowStart();
			int lowStartTo = graph[vTo].getLowStart();
			if(lowStartFrom != lowStartTo){
				int newVertexFrom = lowstartIndMap[lowStartFrom];
				int newVertexTo = lowstartIndMap[lowStartTo];
				gSCCDirty.addEdge(newVertexFrom, newVertexTo);
			}			
			edges.holdTail();
		}
		
		Graph gSCC = gSCCDirty.getCleanCopy();
		return gSCC;
		
	}
	
	// auxiliary method for strongly connected components
	private int doStrongConnect(Graph gSCCDirty, Node v, int time, IntStack indStack, EdgeList edges, int[] lowstartIndMap){
		v.setColor(Node.Color.GRAY);
		indStack.pushInt(v.getIndex());
		v.setStart(time);
		v.setLowStart(time);
		time = time + 1;
		
		for(Index i:v.getOutEdges()){
			Node w = graph[i.getValue()];
			if(w.getColor() == Node.Color.WHITE){
				time = doStrongConnect(gSCCDirty, w, time, indStack, edges, lowstartIndMap);
				if(w.getLowStart() < v.getLowStart()){
					v.setLowStart(w.getLowStart());
				}
			}else if(w.getColor() == Node.Color.GRAY){
				if(w.getStart() < v.getLowStart()){
					v.setLowStart(w.getStart());
				}
			}
			
			if(w.getColor() == Node.Color.BLACK){
				edges.addEdge(v.getIndex(), w.getIndex());
			}
		}
		
		if(v.getLowStart() == v.getStart()){
			int newIndex = gSCCDirty.addNode(v.getLabel());
			Node newNode = gSCCDirty.getIthNode(newIndex);
			int oldInd = v.getIndex();
			newNode.setOldIndex(oldInd);
			lowstartIndMap[v.getLowStart()] = newIndex;
			
			int indVer = -1;
			do{
				indVer = indStack.popInt();
				graph[indVer].setLowStart(v.getLowStart());
				graph[indVer].setColor(Node.Color.BLACK);
			}while(v.getIndex() != indVer);
		}
		
		v.setEnd(time);
		return time;		
	}
	
	// method to get minimum number of edges to add and root
	public RootAndEdges getRootAndMinEdges(){
		EdgeList minimumEdges = new EdgeList();
		boolean foundRoot = false;
		int rt = -1;
		int indTo = -1;
				
		for(Node v:graph){
			if(!foundRoot && v.getInEdges().isEmpty()){
				foundRoot = true;
				rt = v.getOldIndex();
			}else if(v.getInEdges().isEmpty()){
				indTo = v.getOldIndex();
				minimumEdges.addEdge(rt, indTo);
			}
		}
		
		RootAndEdges rae = new RootAndEdges(rt, minimumEdges);		
		return rae;
	}
	
	// method to add minimum number of edges and root
	public void addRootAndMinEdges(RootAndEdges rae){
		root = rae.getRoot();
		EdgeList el = rae.getEdges();
		
		while(!el.isEmpty()){
			Edge e = el.getHead();
			int verFrom = e.getVertexFrom();
			int verTo = e.getVertexTo();
			addEdge(verFrom, verTo);
			addedEdges++;
			Index vT = graph[verFrom].getOutEdges().getLast();
			vT.setColor(Index.Color.RED);
			Index vF = graph[verTo].getInEdges().getLast();
			vF.setColor(Index.Color.RED);
			el.holdTail();
		}
	}
	
	// method to do Breadth-first search
	public void doBFS(){
		if(root == -1){
			throw new IllegalArgumentException("Root not initialized!");
		}else{
			for(Node v:graph){
				v.setColor(Node.Color.WHITE);
				v.setDistance(Integer.MAX_VALUE);
				v.setParent(-1);
			}
			
			Node s = graph[root];
			s.setColor(Node.Color.GRAY);
			s.setDistance(0);
			FifoQueue q = new FifoQueue(graph.length);
			q.enqueueIndex(root);
			int current = -1;
			
			while(!q.isEmpty()){
				current = q.dequeueIndex();
				Node v = graph[current];
				
				for(Index i:v.getOutEdges()){
					Node w = graph[i.getValue()];
					if(w.getColor() == Node.Color.WHITE){
						w.setColor(Node.Color.GRAY);
						w.setDistance(v.getDistance() + 1);
						w.setParent(v.getIndex());
						i.setStyle(Index.Style.DASHED);
						q.enqueueIndex(w.getIndex());
					}
				}
				
				v.setColor(Node.Color.BLACK);
			}
		}
	}
	
	// method to get a String formatted in DOT (graph description language)
	public String toDotString(){
		String s = "digraph out_" + getGraphName() + " {" + "\n";
		String rtLbl = "";
		String noRtLbl = "";
		String fromLbl = "";
		String toLbl = "";
		
		for(Node v:graph){
			if(root == -1){
				noRtLbl = v.getLabel();
				s += noRtLbl + ";" + "\n";
			}else{
				rtLbl = graph[root].getLabel();
				if(v.getIndex() == root){
					s += rtLbl + " [label=\"root = " + rtLbl + " ; |E'|-|E| = " + addedEdges + "\"];" + "\n";
				}else{
					noRtLbl = v.getLabel();
					s += noRtLbl + " [label=\"d(" + rtLbl + "," + noRtLbl + ")=" + v.getDistance() + "\"];" + "\n";
				}
			}
			
			for(Index i:v.getOutEdges()){
				fromLbl = v.getLabel();
				toLbl = graph[i.getValue()].getLabel();
				s += fromLbl + " -> " + toLbl;
				
				if(i.getStyle()==Index.Style.DASHED && i.getColor()==Index.Color.RED){
					s += "[style=dashed,color=red]";
				}else if(i.getStyle()==Index.Style.DASHED){
					s += "[style=dashed]";
				}else if(i.getColor()==Index.Color.RED){
					s += "[color=red]";
				}
				
				s += ";" + "\n";
			}
		}
		s += "}";
		return s;
	}
	
	// method to get a String object representing the value of the Graph Object
	public String toString(){
		String s = "";
		for(Node v:graph){
			s += v.toString() + "\n";
		}
		return s;
	}
	
	// method to get a clean copy of graph
	public Graph getCleanCopy(){
		int sizeGraph = this.getLastIndex();
		Graph g = new Graph(sizeGraph);
		g.setLastIndex(sizeGraph);
		for(int i=0; i<sizeGraph; i++){
			Node n = this.getIthNode(i);
			g.setIthNode(i, n);
		}
		return g;
	}
	
	// method to clone graph
	public Graph cloneGraph(){
		Graph newGraph = new Graph(this.graph.length);
		
		for(Node v:this.graph){
			newGraph.addNode(v.getLabel());
		}
		
		for(Node v:this.graph){
			int indV = v.getIndex();
			for(Index i:v.getOutEdges()){
				int indTo = i.getValue();
				newGraph.addEdge(indV, indTo);
			}
		}
		
		return newGraph;
	}
	
}
