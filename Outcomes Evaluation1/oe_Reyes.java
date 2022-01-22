package package1;

import java.util.*;

public class oe_Reyes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		boolean repeatAll = true;
		boolean repeat = true;
		int size = 0;
		int[] numbers;
		int choice = 0;
	
		System.out.println("Welcome to my program!");
		System.out.println("Create your class first!");
		
		while (repeatAll){	
			System.out.print("How big you want your array? ");
			size = scan.nextInt();
			numbers = new int[size];
			
			System.out.print("\nPopulate your array");
			for (int i = 0; i < size; i++) {
				System.out.print("\nElement " + (i+1) + " of " + size + ": ");
				numbers[i] = scan.nextInt();
			}
			
			repeatAll = true;
			repeat = true;
			
			while(repeat) {
				System.out.println("\n[1] Create class");
				System.out.println("[2] Insert in array");
				System.out.println("[3] Delete an element in array");
				System.out.println("[4] Show Array");
				System.out.println("[5] Search in Array");
				System.out.println("[0] Exit");
				System.out.print("\nWhat do you want to do next? ");
				choice = scan.nextInt();
					
				switch (choice) {
					case 0:
						System.out.print("Come again!");
						repeatAll = false;
						repeat = false;
						break;
					case 1:
						repeatAll = true;
						repeat = false;
						break;
					case 2:
						int num = -1, index = -1;
						boolean tryAgain = false;
						
						{
							System.out.print("Number you want to insert: ");
							num = scan.nextInt();
							
							System.out.print("\nIndex you want to insert (0 to " + (numbers.length) + "): ");
							index = scan.nextInt();
								
							if(index >= 0 && index <= numbers.length) {
								int[] temporary = numbers;
								numbers = new int[temporary.length+1];
									
								for(int i = 0; i < temporary.length; i++) {
									numbers[i] = temporary[i];
								}
									
								for(int i = numbers.length-1; i > index; i--) {
									numbers[i] = numbers[i-1];
								}
									
								numbers[index] = num;
							}
							else {
								System.out.println("!! Invalid input !!");
								tryAgain = true;
							}
						}while (tryAgain);
								
						break;
					case 3:
						int delete = -1;
						boolean tryAgain3 = false;
						{
							System.out.print("Index of element you want to delete (0 to " + (numbers.length-1) + "): ");
							delete = scan.nextInt();
							
							if(delete >= 0) {
								for (int i = delete; i < (numbers.length-1); i++) {
									numbers[i] = numbers[i+1];
								}
								
								int[] newArray = numbers;
								numbers = new int[newArray.length-1];
								
								for(int i = 0; i < numbers.length; i++) {
									numbers[i] = newArray[i];
								}
							}
							else {
								System.out.println("Invalid index");
								tryAgain3 = true;
							}
						}while(tryAgain3);
						
						break;
					case 4:
						System.out.println("\nHere is your array.");
						String statement = "Here are your numbers inside the array: [ ";
							
						for (int i = 0; i < numbers.length; i++) {
							statement += numbers[i] + ", ";
						}
						statement = statement.substring(0, statement.length()-2) + " ]";
						System.out.println(statement);
							
						break;
					case 5:
						System.out.print("\nElement you want to find in array: ");
						int element = scan.nextInt();
						
						int result = linearSearch(element, numbers);
						if(result != -1) {
							System.out.print("The number " + element + " is at index " + result + ".\n");
						}
						else {
							System.out.print("The number " + element + " is not found.\n");
						}
						break;
					default:
						System.out.println("\n!! Invalid Input !!");
						repeat = true;
				}
			}
		}
	}
	public static int linearSearch(int num, int[] numbers) {
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == num) {
				return i;
			}
		}
		return -1;
	}
}
