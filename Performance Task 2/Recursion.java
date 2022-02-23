import java.util.Scanner;
import java.util.Stack;

public class Recursion {

	static Scanner in = new Scanner(System.in);
	static Stack<Integer> source = new Stack<>();
	static Stack<Integer> destination = new Stack<>();
	static Stack<Integer> aux = new Stack<>();
	static int steps = 0;
	static int num;
	
	public static void main(String[] args) {
		
		int choice;
		
		do {
			System.out.println("APPLICATION OF RECURSION\n");
			System.out.println("[1] Fibonacci Series Number"
					+ "\n[2] Factorial of a Number"
					+ "\n[3] Reverse a Number"
					+ "\n[4] Reverse a Word"
					+ "\n[5] Tower of Hanoi"
					+ "\n[6] Odd or Even using Mutual Recursion"
					+ "\n[0] System Exit\n");
			System.out.print("What do you want to do?: ");
			try{
				choice = in.nextInt();
			}
			catch(Exception e)
			{
				choice = 7;
			}
			
			
			switch(choice) {
				case 1: 
					System.out.print("\nEnter a number to undergo Fibonacci [two or more digit numbers might take some time]: ");
					num = in.nextInt();
					System.out.println("\nYour number is " + num + " the result is " + fibonacci(num) + "."); 
					System.out.println("\n");
					break;
				case 2: 
					System.out.print("\nEnter a number to undergo Factorial [two or more digit numbers might take some time or fail]: ");
					num = in.nextInt();
					System.out.println("\nYour number is " + num + " the result is " + factorial(num) + ".");
					System.out.println("\n");
					break;
				case 3: 
					System.out.print("\nEnter a number to get reversed: ");
					num = in.nextInt();
					System.out.print("\nYour number is " + num + " the result is ");
					reverseNumber(num);
					System.out.println("\n\n");
					break;
				case 4: 
					System.out.print("\nEnter a word to get reversed: ");
					String word = in.next();
					System.out.print("\nThe word is " + word + " the reverse is ");
					reverseWord(word);
					System.out.println("\n\n");
					break;
				case 5:
					steps = 0;
					System.out.print("\nEnter a number to get Hanoi'ed XD: ");
					num = in.nextInt();
					for(int i = num; i >= 1; i--)
					{
						source.push(i);
					}
					towerOfHanoi(num, source, destination, aux);
					System.out.println("\nDone placing, it took " + steps + " steps to finish!\n");
					System.out.print("Destination from top to bottom: ");
					while(!destination.isEmpty())
					{
						System.out.print(destination.pop() + " ");
					}
					System.out.println(".");
					System.out.println("\n");
					break;
				case 6: 
					System.out.print("\nEnter a number to know if odd or even: ");
					num = in.nextInt();
					System.out.println("\nIs " + num + " odd?: " + isOdd(num) + ".");
					System.out.println("\nIs " + num + " even?: " + isEven(num) + ".");
					System.out.println("\n");
					break;
				default: 
					if(choice != 0)
						System.out.println("\nInvalid input!");
			}
			
		}while(choice != 0);
		
		System.out.println("\nThank you for your time!");
	}

	private static int fibonacci(int counter) {
		if (counter <= 1)
		       return counter;
		return fibonacci(counter-1) + fibonacci(counter-2);	
	}

	private static int factorial(int num) {
		if(num <= 1)
			return 1;
		return num * factorial(num-1);	
	}

	private static void reverseNumber(int num) {
		if(num < 10) {
			System.out.print(num);
			return;
		}
		System.out.print(num % 10);
		reverseNumber(num / 10);
	}

	private static void reverseWord(String word) {
		if(word.length() <= 1) {
			System.out.print(word);
			return;
		}
		System.out.print(word.substring(word.length()-1));
		reverseWord(word.substring(0, word.length()-1));
	}

	private static void towerOfHanoi(int num, Stack<Integer> src, Stack<Integer> dst, Stack<Integer> spare) {
		steps++;
		if(num <=1) {
			dst.push(src.pop());
			return;
		}
		towerOfHanoi(num - 1, src, spare, dst);
		dst.push(src.pop());
		towerOfHanoi(num-1, spare, dst, src);
	}
	
	private static boolean isOdd(int num) {
		if(num == 0)
			return false;
		else
			return isEven(num-1);
	}
	
	private static boolean isEven(int num) {
		if(num == 0)
			return true;
		else
			return isOdd(num-1);
	}
}
