import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Instantiate SinglyLinkedLists object
		SinglyLinkedLists numbers = new SinglyLinkedLists();
		Scanner in = new Scanner(System.in);
				
				
		boolean prompt = true;
		int num, answer;
		do 
		{
			System.out.print("\nEnter number: " );
			num = in.nextInt();
			numbers.addNode(num);
			
			System.out.print("\nAdd more number? [1 if yes / 2 if no]: " );
			answer = in.nextInt();
			if(answer == 2)
				prompt = false;
		}while(prompt);
				
		System.out.println("\n" + numbers.toString());
		
		do 
		{
			// Resetting value
			prompt = true;
			
			System.out.print("\nInput index you want to remove: " );
			num = in.nextInt();
			numbers.deleteNode(num);
			
			System.out.print("\nDelete more number? [1 if yes / 2 if no]: " );
			answer = in.nextInt();
			if(answer == 2)
				prompt = false;
		}while(prompt);
				
		System.out.println("\n" + numbers.toString());
	}

}
