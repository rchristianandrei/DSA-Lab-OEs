import java.util.Scanner;


public class Prelim {
	
	public static int n, choice;
	public static Scanner in = new Scanner(System.in);
	public static int[] array;

	public static void main(String[] args) {
		
		boolean isActive = true;
		//	User prompt
		System.out.print("How many elements you want your array of numbers to hold?: ");
		n = in.nextInt();
		
		array = new int[n];
		
		//	Populating Array
		System.out.println("Enter your number one at a time.");
		for(int i = 0; i < array.length; i++)
		{
			System.out.print("Index " + i + " of " + (array.length - 1) + ": ");
			array[i] = in.nextInt();
		}
		
		//	Loop
		do {
			//	User available functions
			System.out.println("\nFunctions:");
			System.out.println("[0] Show array");
			System.out.println("[1] Search an element");
			System.out.println("[2] Insert an element");
			System.out.println("[3] Delete an element");
			System.out.println("[4] Exit");
			System.out.print("\nWhat do you want to do next?: ");
			choice = in.nextInt();
			
			switch(choice)
			{
				case 0: showArray(); break;
				case 1: search(); break;
				case 2:	insert(); break;
				case 3: delete(); break;
				case 4: isActive = false; break;
				default: System.out.println("Invalid Input");
			}
		}while(isActive);
		
	}

	private static void showArray() {
		
		String statement = "Here are your numbers inside the array: [ ";
		
		for (int i = 0; i < array.length; i++) {
			statement += array[i] + ", ";
		}
		statement = statement.substring(0, statement.length()-2) + " ]";
		System.out.println(statement);
	}

	private static void delete() {
		
		System.out.print("Index of element you want to delete (0 to " + (array.length-1) + "): ");
		int delete = in.nextInt();
		
		for (int i = delete; i < (array.length-1); i++) {
			array[i] = array[i+1];
		}
		
		int[] newArray = array;
		array = new int[newArray.length-1];
		
		for(int i = 0; i < array.length; i++) {
			array[i] = newArray[i];
		}
	}

	private static void insert() {
		
		System.out.print("What number you want to insert?: ");
		int insert = in.nextInt();
		
		System.out.print("Where do you want to insert?[0 to " + array.length + "]: ");
		int position = in.nextInt();
		
		int[] temp = array;
		array = new int [temp.length+1];
		
		for(int i = 0; i < temp.length; i++)
		{
			array[i] = temp[i];
		}
		
		for(int i = array.length-1; i > position; i--) {
			array[i] = array[i-1];
		}
		array[position] = insert;
	}

	private static void search() {
		
		System.out.print("What number you want to search?: ");
		int target = in.nextInt();		
		
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == target)
			{
				System.out.println("The number you're looking for is at index " + i + ".\n");
				break;
			}
			
			if(i == (array.length - 1) && array[i] != target)
			{
				System.out.println("The number you're looking for is not in the array. \n");
			}
		}
	}
}
