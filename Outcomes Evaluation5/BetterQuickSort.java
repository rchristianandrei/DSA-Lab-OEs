import java.util.Random;
import java.util.Scanner;

public class BetterQuickSort {

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
		
		System.out.println("Minimum value " + numbers[0]);
        System.out.println("Maximum value "+ numbers[numbers.length-1]);
		
		in.close();
		
	}

	private static void quickSort(int[] numbers, int start, int end) {

		if(end <= start) return;
		int pivot = random(numbers, start, end);
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
	
	private static int random(int[] numbers, int start, int end) {
		
		Random random = new Random();
		int pivot = random.nextInt(start, end+1);
		
		//	Place the random pivot to the end
		int temp = numbers[pivot];
		numbers[pivot] = numbers[end];
		numbers[end] = temp;
		
		System.out.println("Random Pivots " + numbers[end]);
		
		return numbers[end];
	}

}