
public class DLList<t> implements List<t> {

	DLListNode<t> head;
	DLListNode<t> tail;
	

	public DLList()
	{
		head = null;
		tail = null;
	}
	

	public void addToHead(t info) {
		head = new DLListNode<t>(info,null,head);
	
		if (head.next == null) {
			tail = head;
		}
		else {
			(head.next).prev = head;
		}		
	}

	
	public void addToTail(t info) {
		
		tail = new DLListNode<t>(info, tail, null);
		
		if (tail.prev == null) {
			head = tail;
		}
		else {
			(tail.prev).next = tail;
		}		
	}


	public void insert(t info, int location) {
	
		DLListNode<t> cur=head;
		int pos = 0;
			
		while(cur != null && pos != location) {				
			cur = cur.next;
			pos++;
		}		
		
		if (cur == null) {
			addToTail(info);
		}
		else {		
			DLListNode<t> tmp = new DLListNode<t>(info,cur,cur.next);			
			(cur.next).prev = tmp;
			cur.next = tmp;			
		}		
	}
	
	
	public void delete(int location) {

		DLListNode<t> cur=head;
		int pos = 0;
			
		while(cur != null && pos != location) {			
			cur = cur.next;
			pos++;
		}
		deleteNode(cur);
	}


	public void delete(t info) {
		if (head == null) return;
		
		DLListNode<t> cur=head;		
		
		while(cur != null && !cur.info.equals(info)) {
			cur = cur.next;			
		}
		deleteNode(cur);
	}

	private void deleteNode(DLListNode<t> cur)
	{
		if (cur == null) return;
		
		DLListNode<t> next = cur.next;
		DLListNode<t> prev = cur.prev;
		
		//Deleting Last Item
		if (prev == null && next == null) {
			head = tail = null;
		}
		else if (prev == null) {
			head = cur.next;
			head.prev = null;
		}
		else if (next == null) {
			tail = cur.prev;
			tail.next = null;
		}
		else {
			prev.next = cur.next;
			(cur.next).prev = prev;			
		}
	}

	public boolean isEmpty()
	{
		if (head == null)
			return true;
		else
			return false;
	}
	
	public void print() {
		DLListNode<t> cur = head;
		
		while (cur != null) {
			System.out.println(cur);
			cur = cur.next;
		}		
	}

	
	public void printReverse() {
		DLListNode<t> cur = tail;
		
		while (cur != null) {
			System.out.println(cur);
			cur = cur.prev;
		}
	}

	
	public t retrieve(int location) {
		if (head == null) return null;
		
		DLListNode<t> cur=head;
		int pos = 0;
			
		while(cur != null && pos != location) {			
			cur = cur.next;
			pos++;
		}
		if (cur==null) return null;
		else return cur.info;
	}
	

	public static void main(String [] args)
	{
		DLList<String> list = new DLList<String>();
		
		list.delete(0);
		
		list.insert("Bob",0);
		list.insert("Tom",1);
		list.insert("Chuck",2);
		
		list.delete(2);
		
		list.insert("Rock",3);
		
		list.print();
		System.out.println();
		list.printReverse();
		
	}

}
