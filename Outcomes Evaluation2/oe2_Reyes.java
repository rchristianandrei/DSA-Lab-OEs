import java.util.Scanner;
public class oe2_Reyes {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int size;
		int[] numbers;
		boolean goMenu = true;
		boolean invalid = true;
		int navigation;
		int temp;
		
		while(goMenu)
		{
			System.out.println("\nLet's sort your array!");
			System.out.print("Create your own array..."
					+ "\nHow many elements of whole numbers you want your array to have? ");
			
			size = in.nextInt();
			numbers = new int[size];
			invalid = true;
			
			System.out.println("\nPopulate your array:");
			for(int i = 0; i < numbers.length; i++)
			{
				System.out.print("Enter " + i + " of " + (numbers.length-1) + ": ");
				numbers[i] = in.nextInt();
			}
			
			while(invalid)
			{
				System.out.println("\nServices:");
				System.out.println("[1] Create new Array");
				System.out.println("[2] Bubble Sort");
				System.out.println("[3] Insertion Sort");
				System.out.println("[4] Show array");
				System.out.println("[5] Exit");
				System.out.print("\nWhat do you want to do? ");
				
				navigation = in.nextInt();
				
				invalid = true;
				goMenu = false;
				
				switch(navigation)
				{
					case 1:
						goMenu = true;
						invalid = false;
						break;
					case 2:
						System.out.println("\nDoing Bubble Sort...");
						if(numbers == null || numbers.length == 0)
						{
							System.out.println("Your array is empty! Create a new one.");
							goMenu = true;
							invalid = false;
						}
						else
						{
							boolean switched = true;
							while(switched)
							{
								switched = false;
								for(int i = 1; i < numbers.length; i++)
								{
									if(numbers[i-1] > numbers[i])
									{
										temp = numbers[i-1];
										numbers[i-1] = numbers[i];
										numbers[i] = temp;
										showArray(numbers);
										switched = true;
									}
								}
							}
						}
						System.out.println("Done sorting!");
						break;
					case 3:
						System.out.println("\nDoing Insertion Sort...");
						if(numbers == null || numbers.length == 0)
						{
							System.out.println("Your array is empty! Create a new one.");
							goMenu = true;
							invalid = false;
						}
						else
						{
							int i,j;
							for(i = 1; i < numbers.length; i++)
							{
								temp = numbers[i];
								for(j = i; j > 0; j--)
								{
									if(temp < numbers[j-1])
										numbers[j] = numbers[j-1];
									else
										break;
								}
								numbers[j] = temp;
								showArray(numbers);
							}
						}
						System.out.println("Done sorting!");
						break;
					case 4:
						showArray(numbers);
						break;
					case 5:
						System.out.println("Thank you and please come again!");
						goMenu = false;
						invalid = false;
						break;
					default:
						System.out.println("!!! Invalid input, please try again !!!");
						invalid = true;
				}
			}
		}
	}
	
	public static void showArray(int[] numbers)
	{
		System.out.println("\nHere is your array.");
		String statement = "Here are your numbers inside the array: [ ";
			
		for (int i = 0; i < numbers.length; i++) {
			statement += numbers[i] + ", ";
		}
		statement = statement.substring(0, statement.length()-2) + " ]";
		System.out.println(statement);
	}

}