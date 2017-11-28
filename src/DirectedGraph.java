import java.util.ArrayList;


public class DirectedGraph extends Graph {

	
	
	public DirectedGraph()
	{
		super();
	}
	
	public void addEdge(DirectedEdge e)
	{
		super.addEdge(e);
	}
	
	public boolean cycleDetection()
	{
		clearVertices();
		ArrayList<Edge> visitedEdges = new ArrayList<Edge>();
		for (int i=0; i < vertices.size(); i++) {
			if (!vertices.get(i).isVisited()) {
				if (cycleDetection(vertices.get(i), visitedEdges))
					return true;
			}
		}		
		return false;
	}
	
	public boolean cycleDetection(Vertex v, ArrayList<Edge> visitedEdges)
	{
		v.setVisited();
		
		
		ArrayList<Vertex> adjacent = v.getAdjacent();
		ArrayList<Edge> edges = v.getEdges();
			
		for (int i=0; i < adjacent.size(); i++) {
			if (!adjacent.get(i).isVisited()) {
				visitedEdges.add(edges.get(i));
				if (cycleDetection(adjacent.get(i), visitedEdges))
					return true;
			}
			else if (!adjacent.get(i).isAllAdjacentVisited()) {
				return true;				
			}
		}
		v.setAllAdjacentVisited();
		return false;
	}
	
	
		
	
	public static void main(String [] args) 
	{
	
		DirectedGraph dg = new DirectedGraph();
		
		Vertex[] vertices = new Vertex[7];
		
		for (int i=0; i < 7; i++) {
			vertices[i] = new Vertex(i);
			dg.addVertex(vertices[i]);
		}
		
		
		dg.addEdge(new DirectedEdge(vertices[0],vertices[1],6));
		dg.addEdge(new DirectedEdge(vertices[0],vertices[2],5));
		dg.addEdge(new DirectedEdge(vertices[1],vertices[2],9));
		dg.addEdge(new DirectedEdge(vertices[2],vertices[3],16));
		dg.addEdge(new DirectedEdge(vertices[1],vertices[4],13));
		dg.addEdge(new DirectedEdge(vertices[2],vertices[5],12));
		dg.addEdge(new DirectedEdge(vertices[3],vertices[4],15));
		dg.addEdge(new DirectedEdge(vertices[4],vertices[6],8));
		dg.addEdge(new DirectedEdge(vertices[5],vertices[6],3));
		dg.addEdge(new DirectedEdge(vertices[3],vertices[5],7));
		
		
		dg.KruskalsAlgorithm();
		
		
	}	
}
