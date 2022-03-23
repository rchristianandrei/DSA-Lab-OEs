import java.io.Console;
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
			System.out.println("[5] Exit");
			System.out.println();
			System.out.print("What do you want to do? ");
			
			choice = in.nextInt();
			
			switch(choice) {
			
				case 1: 
					
					System.out.print("Input Data: ");
					dataInput = in.nextInt();
					
					tree.Insert(new Node(dataInput));
					break;
				case 2: tree.PreOrder(); break;
				case 3: tree.InOrder(); break;
				case 4: tree.PostOrder(); break;
				case 5: ongoing = false; break;
				default: System.out.println("Invalid input");
			}
		}
		
		System.out.println("Thank you and come again!");
		in.close();
	}

}

class Node{
	
	int data;
	Node leftNode = null;
	Node rightNode = null;
	
	Node(int data){
		this.data = data;
	}
}

class Tree{
	
	Node root = null;
	
	public void Insert(Node node){
		
		if(this.root == null) {
			
			this.root = node;
		}else {
			
			Node current = this.root;
			boolean occupied = true;
			
			while(occupied) {
				
				occupied = false;
				
				if(node.data > current.data) {
					
					if(current.rightNode == null)
						current.rightNode = node;
					
					else {
						
						current = current.rightNode;
						occupied = true;
					}
				}else {
					
					if(current.leftNode == null)
						current.leftNode = node;
					
					else {
						
						current = current.leftNode;
						occupied = true;
					}
				}
			}
		}
		
		System.out.println();
	}
	
	public void InOrder() {
		
		if(this.root != null) {
			
			System.out.print("In-Order: ");
			
			if(this.root.leftNode != null)
				InOrder(this.root.leftNode);
			
			System.out.print(root.data + " ");
			
			if(this.root.rightNode != null)
				InOrder(this.root.rightNode);
			
		}
		else
			System.out.print("Root is null.");
		
		System.out.println();
		System.out.println();
	}
	
	public void InOrder(Node parent) {
		
		if(parent.leftNode != null)
			InOrder(parent.leftNode);
				
		System.out.print(parent.data + " ");
		
		if(parent.rightNode != null)
			InOrder(parent.rightNode);
	}
	
	public void PreOrder(){
		
		if(this.root != null) {
			
			System.out.print("Pre-Order: " + this.root.data);
			
			if(this.root.leftNode != null)
				PreOrder(this.root.leftNode);
			
			if(this.root.rightNode != null)
				PreOrder(this.root.rightNode);
		}
		else
			System.out.print("Root is null.");
		
		System.out.println();
		System.out.println();
	}
	
	public void PreOrder(Node parent){
		
		System.out.print(" " + parent.data);
		
		if(parent.leftNode != null)
			PreOrder(parent.leftNode);
		
		if(parent.rightNode != null)
			PreOrder(parent.rightNode);
	}
	
	public void PostOrder() {
if(this.root != null) {
			
			System.out.print("Post-Order: ");
			
			if(this.root.leftNode != null)
				PostOrder(root.leftNode);
			
			if(this.root.rightNode != null)
				PostOrder(root.rightNode);
			
			System.out.print(root.data + " ");
		}
		else
			System.out.print("Root is null.");
		
		System.out.println();
		System.out.println();
	}
	
	public void PostOrder(Node parent) {
		
		if(parent.leftNode != null)
			PostOrder(parent.leftNode);
		
		if(parent.rightNode != null)
			PostOrder(parent.rightNode);
		
		System.out.print(parent.data + " ");
	}
}