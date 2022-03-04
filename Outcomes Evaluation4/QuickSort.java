import java.util.Scanner;

public class QuickSort {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int size;
		int[] numbers;
		
		System.out.print("How big do you want your array?: ");
		size = in.nextInt();
		
		numbers = new int[size];
		
		//	Populate array
		for(int i = 0; i < size; i++) {
			
			System.out.print("Insert " + i + " of " + (size-1) + ": ");
			numbers[i] = in.nextInt();
			System.out.println();
		}
		
		// Quick Sort
		quickSort(numbers, 0, numbers.length-1);
		
		// Iterate the array
		System.out.println("Your numbers are:");
		for(int number : numbers){
			System.out.println(number);
		}
		
		in.close();
		
	}

	private static void quickSort(int[] numbers, int start, int end) {

		if(end <= start) return;
		
		int pivot = numbers[end];
		int j = start;
		int i = start-1;
		int temp;
		
		for(; j <= end; j++) {
			if(numbers[j] <= pivot) {
				i++;
				temp = numbers[j];
				numbers[j] = numbers[i];
				numbers[i] = temp;
			}
		}
		
		quickSort(numbers, start, (i-1));
		quickSort(numbers, (i+1), end);
	}

}