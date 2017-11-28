import java.util.*;

public class Vertex {

	protected int vertexID;
	protected ArrayList<Vertex> adjacent;
	protected ArrayList<Integer> adjacentDistances;
	protected ArrayList<Edge> edges;
	protected boolean visited;
	protected int curDistance;
	protected Vertex predecessor;
	protected boolean allAdjacentVisited;
	protected Vertex root; 
	protected Vertex next;
	protected int length;
	
	
	public Vertex(int id) 
	{
		vertexID = id;
		adjacent = new ArrayList<Vertex>();
		adjacentDistances = new ArrayList<Integer>();
		edges = new ArrayList<Edge>();
		visited = false;
		root = next =this;
		length = 1;
	}
	
	public int getID()
	{
		return vertexID;		
	}
	
	public ArrayList<Vertex> getAdjacent()
	{
		return adjacent;
	}
	
	public ArrayList<Integer> getAdjacentDistances()
	{
		return adjacentDistances;
	}
	
	public ArrayList<Edge> getEdges()
	{
		return edges;
	}
	
	public void addAdjacent(Vertex v, int distance, Edge e)
	{
		adjacent.add(v);
		adjacentDistances.add(distance);
		edges.add(e);
	}

		
	public String toString() 
	{
		String out = "";
		
		out += vertexID + " => ";
		
		Iterator<Vertex> i = adjacent.iterator();
		while (i.hasNext()) {
			out += i.next().getID();
			if (i.hasNext()) out += ", ";
		}		
		return out;
	}
	
	public void clear()
	{
		visited = false;
		curDistance = Integer.MAX_VALUE;
		predecessor = null;
		allAdjacentVisited = false;		
		root = next =this;
		length = 1;
				
	}

	public void setVisited()
	{
		visited = true;
	}
	
	public boolean isVisited()
	{
		return visited;
	}

	public Vertex getPredecessor()
	{
		return predecessor;
	}
	
	public void setPredecessor(Vertex v)
	{
		predecessor = v;
	}
	
	public int getCurDistance()
	{
		return curDistance;
	}
	
	public void setCurDistance(int dist)
	{
		curDistance = dist;
	}

	public boolean isAllAdjacentVisited()
	{
		return allAdjacentVisited;
	}
	
	public void setAllAdjacentVisited()
	{
		allAdjacentVisited = true;
	}	
}
