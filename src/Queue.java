
public interface Queue<T> {

	public void enqueue(T val);
	
	public T dequeue();
	
	public T front();
	
	public boolean isEmpty();	
}
