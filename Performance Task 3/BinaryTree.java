import java.util.Scanner;

public class BinaryTree {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Tree tree = new Tree();
		
		boolean ongoing = true;
		int choice;
		int dataInput;
		
		while(ongoing) {
			
			
			System.out.println("Choices:");
			System.out.println("[1] Insert Node");
			System.out.println("[2] Preorder Traversal");
			System.out.println("[3] Inorder Traversal");
			System.out.println("[4] Postorder Traversal");
			System.out.println("[5] Search");
			System.out.println("[6] Delete");
			System.out.println("[7] Exit");
			System.out.println();
			System.out.print("What do you want to do? ");
			
			choice = in.nextInt();
			
			switch(choice) {
			
				case 1: 
					
					System.out.print("Input Data: ");
					dataInput = in.nextInt();
					
					tree.Insert(new Node(dataInput));
					break;
				case 2: 
					System.out.print("Pre-Order: ");
					tree.PreOrder(tree.GetRoot());
					System.out.println();
					System.out.println();
					break;
				case 3: 
					System.out.print("In-Order: ");
					tree.InOrder(tree.GetRoot());
					System.out.println();
					System.out.println();
					break;
				case 4: 
					System.out.print("Post-Order: ");
					tree.PostOrder(tree.GetRoot());
					System.out.println();
					System.out.println();
					break;
				case 5: 
					
					System.out.print("Input target to find: ");
					dataInput = in.nextInt();
					
					System.out.println("Is your number in the Tree?: " + tree.Search(dataInput, tree.GetRoot()));
					System.out.println();
					break;
				case 6: 
					
					System.out.print("Input target to delete: ");
					dataInput = in.nextInt();
					
					System.out.println("Is your number deleted?: " + tree.Delete(dataInput, tree.GetRoot()));
					System.out.println();
					break;
				case 7: ongoing = false; break;
				default: System.out.println("Invalid input");
			}
		}
		
		System.out.println("Thank you and come again!");
		in.close();
	}

}

class Node{
	
	Integer data;
	Node leftNode;
	Node rightNode;
	
	Node(){
		this.data = null;
		leftNode = null;
		rightNode = null;
	}
	Node(int data){
		this.data = data;
		leftNode = null;
		rightNode = null;
	}
}

class Tree{
	
	private Node root = null;
	
	public Node GetRoot() {
		return root;
	}
	
	public void Insert(Node node){
		
		if(this.root == null)
			this.root = node;
		else if(this.root.data == null)
			this.root.data = node.data;
		else {
			
			Node current = this.root;
			boolean occupied = true;
			
			while(occupied) {
				
				occupied = false;
				
				if(node.data > current.data) {
					
					if(current.rightNode == null)
						current.rightNode = node;
					else if(current.rightNode.data == null)
						current.rightNode.data = node.data;
					else {
						
						current = current.rightNode;
						occupied = true;
					}
				}else {
					
					if(current.leftNode == null)
						current.leftNode = node;
					else if(current.leftNode.data == null)
						current.leftNode.data = node.data;
					else {
						
						current = current.leftNode;
						occupied = true;
					}
				}
			}
		}
		
		System.out.println();
	}
	
	public void InOrder(Node current) {
		
		if(current != null) {
			
			if(current.data == null)
				return;
			
			if(current.leftNode != null)
				InOrder(current.leftNode);
					
			System.out.print(current.data + " ");
			
			if(current.rightNode != null)
				InOrder(current.rightNode);
			
		}
		else
			System.out.print("Root is null.");
	}
	
	public void PreOrder(Node current){
		
		if(current != null) {
			
			if(current.data == null)
				return;
			
			System.out.print(current.data + " ");
			
			if(current.leftNode != null)
				PreOrder(current.leftNode);
			
			if(current.rightNode != null)
				PreOrder(current.rightNode);
		}
		else
			System.out.print("Root is null.");
	}
	
	public void PostOrder(Node current) {
		
		if(current != null) {
			
			if(current.data == null)
				return;
			
			if(current.leftNode != null)
				PostOrder(current.leftNode);
			
			if(current.rightNode != null)
				PostOrder(current.rightNode);
			
			System.out.print(current.data + " ");
		}
		else
			System.out.print("Root is null.");
	}
	
	public boolean Search(int target, Node current) {
		
		if(current != null) {
			
			if(target == current.data)
				return true;
			else if(target > this.root.data)
				if(current.rightNode != null)
					return Search(target, current.rightNode);
				else
					return false;
			else
				if(current.leftNode != null)
					return Search(target, current.leftNode);
				else
					return false;
		}
		else
			return false;
	}
	
	public boolean Delete(int target, Node current) {
		
		if(current != null) {
			
			if(target == current.data) {
				
				if(current.rightNode != null) {
					
					current.data = current.rightNode.data;
					if(current.rightNode.rightNode != null)
						return Delete(current.rightNode.data, current.rightNode);
					else if(current.rightNode.leftNode != null)
						return Delete(current.rightNode.data, current.leftNode);
					else
						current.rightNode = new Node();
				}
				else if(current.leftNode != null) {
					
					current.data = current.leftNode.data;
					if(current.leftNode.rightNode != null)
						return Delete(current.leftNode.data, current.rightNode);
					else if(current.leftNode.leftNode != null)
						return Delete(current.leftNode.data, current.leftNode);
					else
						current.leftNode = new Node();
				}
				else
					current.data = null;
				
				return true;
			}
			else if(target > current.data)
				return Delete(target, current.rightNode);
			else
				return Delete(target, current.leftNode);
		}
		else
			return false;
	}
}