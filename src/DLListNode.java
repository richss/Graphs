public class DLListNode<t>  {

	public t info;
	public DLListNode<t> next;
	public DLListNode<t> prev;
	

	public DLListNode(t info)
	{
		this.info = info;
		next = prev = null;
	}
	
	public DLListNode(t info, 
			          DLListNode<t> prev, 
			          DLListNode<t> next)
	{
		this(info);
		this.prev = prev;
		this.next = next;
	}
	
	public String toString()
	{
		return info.toString();
	}
	
	public void printChainFwd()
	{
		System.out.print(info);
		if (next != null) {
			System.out.print(" -> ");
			next.printChainFwd();
		}
		else {
			System.out.println();
		}
	}
	
	public void printChainRev()
	{
		System.out.print(info);
		if (prev != null) {
			System.out.print(" -> ");
			prev.printChainRev();
		}
		else {
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		
		DLListNode<String> a = new DLListNode<String>("Harry");
		DLListNode<String> b = new DLListNode<String>("Rick");
		DLListNode<String> c = new DLListNode<String>("Tom");
	
		//   a <-> b <-> c		
		
		a.next = b;
		b.prev = a;
		b.next = c;
		c.prev = b;
		
		a.printChainFwd();
		c.printChainRev();
		
	}

}