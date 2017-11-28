
public class ListQueue<T> implements Queue<T> {

	DLList<T> list;
	
	public ListQueue()
	{
		list = new DLList<T>();
	}
	
	public void enqueue(T val)
	{
		list.addToTail(val);
	}
	
	public T dequeue()
	{
		T tmp = list.retrieve(0);
		list.delete(0);
		return tmp;
	}
	
	public T front()
	{
		return list.retrieve(0);
	}
	
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	public static void main(String args[]) 
	{
		Queue<String> q = new ListQueue<String>();
		
		q.enqueue("tom");
		q.enqueue("jerry");
		q.enqueue("pinky");
		q.enqueue("brain");
		
		System.out.println(q.front());
		
		System.out.println("Queue Content: ");
		
		while(!q.isEmpty()) {
			System.out.println(q.dequeue());
		}
	}
	
}
