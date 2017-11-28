import java.util.ArrayList;


public class UndirectedGraph extends Graph {

	
	
	public UndirectedGraph()
	{
		super();
	}
	
	public void addEdge(UndirectedEdge e)
	{
		super.addEdge(e);
		super.addEdge(new Edge(e.end,e.start,e.distance));
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
			else {
				if (hasVisitedEdge(v, adjacent.get(i),visitedEdges)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasVisitedEdge(Vertex start, Vertex end, ArrayList<Edge> visited)
	{
		for (int i=0; i < visited.size(); i++) {
			if (start.equals(visited.get(i).start) && end.equals(visited.get(i).end))
				return true;
			if (end.equals(visited.get(i).start) && start.equals(visited.get(i).end))
				return true;
		}
		return false;
	}
	
	
	
	public static void main(String [] args) 
	{
	
		UndirectedGraph ug = new UndirectedGraph();
		
		Vertex[] vertices = new Vertex[6];
		
		for (int i=0; i < vertices.length; i++) {
			vertices[i] = new Vertex(i);
			ug.addVertex(vertices[i]);
		}
		
		
		ug.addEdge(new UndirectedEdge(vertices[0],vertices[1]));
		ug.addEdge(new UndirectedEdge(vertices[1],vertices[2]));
		ug.addEdge(new UndirectedEdge(vertices[2],vertices[0]));		
		ug.addEdge(new UndirectedEdge(vertices[3],vertices[4]));
		ug.addEdge(new UndirectedEdge(vertices[4],vertices[5]));
		ug.addEdge(new UndirectedEdge(vertices[5],vertices[3]));
		

		ug.Union();
	}	
}
