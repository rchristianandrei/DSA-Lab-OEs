
public class SinglyLinkedLists {
	
	// Creating Node class
	class Node{
		public int object;
		public Node nextNode;	
		
		public Node(int object)
		{
			this.object = object;
		}
		
		public void addNode(Node node)
		{
			this.nextNode = node;
		}
	}
	
	public Node head;
	public Node tail;
	
	public SinglyLinkedLists()
	{
		
	}
	
	public SinglyLinkedLists(int object)
	{
		this.head = new Node(object);
		this.tail = new Node(object);
	}
	
	public void addNode (int object)
	{
		Node node = new Node(object);
		
		if(this.head == null) {
			this.head = node;
			this.tail = node;
		}
		
		this.tail.addNode(node);
		this.tail = node;
	}
	
	public void deleteNode(int index)
	{
		if(index == 0)
			this.head = head.nextNode;	
		
		Node currentNode = this.head;
		for(int i = 0; i <= index && currentNode.nextNode != null; i++)
		{
			if (i == index-1)
			{		
				Node prevNode = currentNode;
				currentNode = currentNode.nextNode;
				prevNode.nextNode = currentNode.nextNode;
				currentNode.nextNode = null;	
				break;	
			}
			currentNode = currentNode.nextNode;
		}
	}
	
	public String toString()
	{
		String display = "[ " + this.head.object;
		Node current = head;
		while(current.nextNode != null)
		{
			current = current.nextNode;
			display += ", " + current.object;
		}
		
		display += " ]";
		return display;
	}
}
