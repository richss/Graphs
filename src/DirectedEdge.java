
public class DirectedEdge 
	extends Edge {

	public DirectedEdge(Vertex start, Vertex end)
	{
		this(start, end, 1);
	}
	
	public DirectedEdge(Vertex start, Vertex end, int distance)
	{
		super(start, end, distance);
		
		start.addAdjacent(end,distance,this);
	}	
}
