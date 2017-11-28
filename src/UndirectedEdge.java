
public class UndirectedEdge 
	extends Edge {
	
	public UndirectedEdge(Vertex start, Vertex end)
	{
		this(start, end, 1);
	}
	
	public UndirectedEdge(Vertex start, Vertex end, int distance)
	{
		super(start, end, distance);
		
		start.addAdjacent(end,distance,this);
		end.addAdjacent(start,distance,this);		
	}	
	

}
