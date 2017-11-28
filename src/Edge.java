
public class Edge {

	protected Vertex start;
	protected Vertex end;
	protected int distance;
		
	public Edge(Vertex start, Vertex end, int distance)
	{
		this.start = start;
		this.end = end;
		this.distance = distance;		
	}
	
	public Vertex getStart()
	{
		return start;
	}
	
	public Vertex getEnd()
	{
		return end;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public String toString()
	{
		return "(" + start.getID() + "," + end.getID() + ")";
	}
}
