
public interface List<t> {
		
	public void insert(t info, int location);
	
	public void addToHead(t info);
	
	public void addToTail(t info);

	public t retrieve(int location);
	
	public void delete(int location);
	
	public void delete(t info);

	public void print();
	
	public void printReverse();

	
}
