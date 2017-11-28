

import java.util.ArrayList;

public abstract class Graph {

	ArrayList<Vertex> vertices;
	ArrayList<Edge> edges;
	ArrayList<Edge> spanTree;
	
	public Graph()
	{
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}
	
	public void addEdge(Edge e)
	{
		edges.add(e);
	}
	
	public void addVertex(Vertex v)
	{
		vertices.add(v);
	}
	
	public void clearVertices()
	{		
		for (int i=0; i < vertices.size(); i++) {
			vertices.get(i).clear();
		}
	}
	
	public void DepthFirstTraversal()
	{
		clearVertices();
		for (int i=0; i < vertices.size(); i++) {
			if (!vertices.get(i).isVisited()) {
				DepthFirstTraversal(vertices.get(i));
			}
		}		
	}
	
	public void DepthFirstTraversal(Vertex v)
	{
		v.setVisited();
		System.out.println("Vertex: " + v.getID());
		
		ArrayList<Vertex> adjacent = v.getAdjacent();
			
		for (int i=0; i < adjacent.size(); i++) {
			if (!adjacent.get(i).isVisited()) {
				DepthFirstTraversal(adjacent.get(i));
			}
		}
	}	
	
	
	
	
	public void dijkstraShortestPath(Vertex start)
	{
		ArrayList<Vertex> toBeChecked = new ArrayList<Vertex>();
		Vertex tmp;
		int tmpDist;
		for (int i=0; i < vertices.size(); i++) {
			tmp = vertices.get(i);
			tmp.clear();
			toBeChecked.add(tmp);
		}
		
		start.setCurDistance(0);
		
		while(!toBeChecked.isEmpty()) {
			Vertex cur = getMinimumVertex(toBeChecked);
			
			ArrayList<Vertex> adjacent = cur.getAdjacent();
			ArrayList<Integer> adjacentDistances = cur.getAdjacentDistances();
			for (int i=0; i < adjacent.size(); i++) {
				
				tmp = adjacent.get(i);
				tmpDist = adjacentDistances.get(i);
				
				if (tmp.getCurDistance() > ((long) cur.getCurDistance() + tmpDist)) {
					tmp.setCurDistance(cur.getCurDistance() + tmpDist);
					tmp.setPredecessor(cur);
				}		
			}
		}
		
		printShortestPaths();
	}
	
	public void fordShortestPath(Vertex start)
	{
		clearVertices();
		start.setCurDistance(0);
	
		boolean proceed = true;
		
		start.setCurDistance(0);
		while (proceed) {
			proceed = false;
			
			for (int i=0; i < edges.size();  i++) {
				
				Edge e = edges.get(i);
				Vertex v = e.getStart();
				Vertex u = e.getEnd();
				
				//Compare the current distance to u versus the distane to u via v
				//note the "long" is there because our max distance is equal to maximum integer...
				//if we did not set it to long, then we would have our integer "wrap" around to a 
				//negative which would cause lots of fun confusion.
				if (u.getCurDistance() > ((long) v.getCurDistance() + e.getDistance())) {
					u.setCurDistance((v.getCurDistance() + e.getDistance()));
					u.setPredecessor(v);
					proceed = true;
				}				
			}			
		}	
		printShortestPaths();
	}
	
	public abstract boolean cycleDetection();
	
	private void printShortestPaths()
	{
		Vertex cur;
		for (int i=0; i < vertices.size(); i++) {
			cur = vertices.get(i);
			System.out.print("Vertex-" + cur.getID() + " ");
			
			if (cur.getCurDistance() == Integer.MAX_VALUE) 
				System.out.println("Dist=INF");				
		    else {
					System.out.print("Dist=" + cur.getCurDistance() + "::");
					printPath(cur);					
					System.out.println();		
			}
		}		
	}
	
	public void printPath(Vertex cur)
	{
		if (cur == null)
			return;
		printPath(cur.getPredecessor());
		System.out.print(cur.getID() + " ");
	}
	
	private Vertex getMinimumVertex(ArrayList<Vertex> list)
	{
		int smallestIndex = 0;
		Vertex smallestVertex = list.get(0);
		Vertex tmp;
		
		for (int i=1;i < list.size(); i++) {
			tmp = list.get(i);
			if (tmp.getCurDistance() < smallestVertex.getCurDistance()) {
				smallestIndex = i;
				smallestVertex = tmp;
			}
		}
		list.remove(smallestIndex);
		return smallestVertex;
	}
	
	
	public void BreadthFirstTraversal()
	{
		clearVertices();
		
		Queue<Vertex> q = new ListQueue<Vertex>();
		Vertex cur;
		ArrayList<Vertex> adjacent;
		
		for (int i=0; i < vertices.size(); i++) {
			q.enqueue(vertices.get(i));
			
			while (!q.isEmpty()) {
				cur = q.dequeue();
				
				//If not visited, visit
				if (!cur.isVisited()) {
					
					cur.setVisited();
					System.out.println("Vertex:" + cur.getID());
				
				
					//Now enqueue its adjacent nodes
					adjacent = cur.getAdjacent();					
					for (int j=0; j < adjacent.size(); j++) {		
						
						if (!adjacent.get(j).isVisited()) {
							q.enqueue(adjacent.get(j));
						}
					}
				}
			}
			
		}		
	}	
	
	public void Union()
	{
		clearVertices();
		spanTree = new ArrayList<Edge>();
		for (int i=0; i < edges.size(); i++)
			Union(edges.get(i));
		
		System.out.println("Span Tree Edges");
		for (int i=0; i < spanTree.size(); i++)
			System.out.println(spanTree.get(i));
	}
	
	public void Union(Edge e)
	{
		Vertex v = e.getStart();
		Vertex u = e.getEnd();
		
		if (v.root == u.root) 
			return;
		
		else if (v.root.length < u.root.length) {
			Vertex rt = v.root;
			u.root.length += v.root.length;
			rt.root = u.root;
			
			for (Vertex tmp=rt.next; tmp !=rt; tmp = tmp.next) {
				tmp.root = u.root;
			}
			
			//swap nexts between rt and u
			Vertex tmp = rt.next;
			rt.next = u.next;
			u.next = tmp;			
		}
		else {
			Vertex rt = u.root;
			v.root.length += u.root.length;
			rt.root = v.root;
			
			for (Vertex tmp=rt.next; tmp !=rt; tmp = tmp.next) {
				tmp.root = v.root;
			}
			
			//swap nexts between rt and u
			Vertex tmp = rt.next;
			rt.next = v.next;
			v.next = tmp;			
		}		
		
		spanTree.add(e);
	}
	
	
	public void KruskalsAlgorithm()
	{
		clearVertices();
		spanTree = new ArrayList<Edge>();
		
		Edge[] edgeArray = edges.toArray(new Edge[0]);		
		insertionSort(edgeArray);
		
		for (int i=0; (i < edgeArray.length); i++) {
			Union(edgeArray[i]);
		}
		
		System.out.println("Span Tree Edges");
		for (int i=0; i < spanTree.size(); i++)
			System.out.println(spanTree.get(i));
	}
	
	/**
	 * Insertion sort algorithm
	 * @param data - data to sort
	 * 
	 */
	private void insertionSort(Edge [] data)
	{
		Edge tmp;
		
		for (int i=1; i < data.length; i++){

		    //Data to insert
		   tmp = data[i];

		    //Find Insert Point and Shift data over
		    int j;
		    
		    for (j=i; j > 0 && tmp.distance < data[j-1].distance;  j--)
		       data[j] = data[j-1];

		    //Insert
		    data[j] = tmp;
		}	
	}
	
}
