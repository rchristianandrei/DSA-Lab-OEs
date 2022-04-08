import java.util.Scanner;
import java.util.ArrayList;

public class HashTable {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Hash hash;
		
		boolean ongoing = true;
		int choice, key, value;
		
		System.out.print("Enter Hash Table Size: ");
		value = in.nextInt();
		hash = new Hash(value);
		System.out.println();
		
		while(ongoing) {
			
			System.out.println("Choices:");
			System.out.println("[1] Insert to Map");
			System.out.println("[2] Search");
			System.out.println("[3] Print Table");
			System.out.println("[4] Delete Item");
			System.out.println("[5] Update Item");
			System.out.println("[6] Exit");
			System.out.println();
			System.out.print("What do you want to do? ");
			
			choice = in.nextInt();
			
			switch(choice) {

				case 1: 
					System.out.print("Enter Key: ");
					key = in.nextInt();
					System.out.print("Enter Value: ");
					value = in.nextInt();
					hash.Insert(new Item(key, value));
					System.out.println();
					break;
				case 2: 
					System.out.print("Enter Key to find: ");
					key = in.nextInt();
					hash.Search(key);
					break;
				case 3: 
					hash.Print();
					break;
				case 4:
					System.out.print("Enter Key to delete: ");
					key = in.nextInt();
					hash.Delete(key);
					System.out.println();
					break;
				case 5:
					System.out.print("Enter Existing Key: ");
					key = in.nextInt();
					System.out.print("Enter New Value: ");
					value = in.nextInt();
					hash.Update(key, value);
					System.out.println();
					break;
				case 6: 
					ongoing = false;
					break;
				default: System.out.println("Invalid input");
			}
		}
		
		System.out.println("Thank you and come again!");
		in.close();

	}

}

class Item{
	int key;
	int value;
	
	Item(int key, int value){
		
		this.key = key;
		this.value = value;
	}
}

class Hash{
	
	private Item[][] array;
	private ArrayList<Integer> keys;
	
	public Hash(int size) {
		
		this.array = new Item[size][1];
		this.keys = new ArrayList<>();
	}
	
	public Hash() {
		
		this.array = new Item[10][];
		this.keys = new ArrayList<>();
	}
	
	public void Insert(Item entry) {
		
		for(int i = 0; i < keys.size(); i++) {
			
			if(entry.key == keys.get(i)) {
				System.out.println("Key already taken");
				return;
			}
		}
		
		int index = entry.key % this.array.length;
		
		for(int i = 0; i < array.length; i++) {
			
			if(array[index][0] == null) {
				
				array[index][0] = entry;
				keys.add(entry.key);
				return;
			}
				
			if(index >= array.length-1)
				index = 0;
			else
				index++;
		}
		
		Item[] temp = array[index];
		array[index] = new Item[array[index].length+1];
		
		for(int i = 0; i < temp.length; i++) {
			
			array[index][i] = temp[i];
		}
		
		array[index][array[index].length-1] = entry;
		keys.add(entry.key);
	}
	
	public void Search(int key) {
		
		boolean found = false;
		
		for(int i = 0; i < keys.size(); i++) {
			
			if(key == keys.get(i)) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			
			System.out.println("Key not found\n");
			return;
		}
		
		int index = key % array.length;
		
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[index].length; j++) {
				
				if(key == array[i][j].key) {
					System.out.println("Key: " + key + ", Value: " + array[index][j].value + " at (" + index + "," + j + ")\n");
					return;
				}
			}
			
			if(index >= array.length)
				index = 0;
		}
	}
	
	public void Print() {
		
		for(int i = 0; i < array.length; i++) {
			System.out.print(i + " {  ");
			for(int j = 0; j < array[i].length; j++) {
				if(array[i][j] != null)
					System.out.print(array[i][j].key + ":" + array[i][j].value + "  ");
			}
			System.out.print("}\n");
		}
		
		System.out.println();
	}
	
	public void Delete(int key) {
		
		boolean keyFound = false;
		
		for(int i = 0; i < keys.size(); i++) {
			if(key == keys.get(i)) {
				keyFound = true;
			}
		}
		
		if(keyFound) {
			for(int i = 0; i < array.length; i++) {
				for(int j = 0; j < array[i].length; j++) {
					if(array[i][j] != null && key == array[i][j].key) {
						array[i][j] = null;
						for(int k = 0; k < keys.size(); k++){
							if(key == keys.get(k)) {
								keys.remove(k);
							}
						}
						System.out.println("Item Deleted.");
						break;
					}
				}
			}
		}
		else {
			System.out.println("Item not found.");
		}
	}
	
	public void Update(int key, int value) {
		
		boolean keyFound = false;
		
		for(int i = 0; i < keys.size(); i++) {
			if(key == keys.get(i)) {
				keyFound = true;
			}
		}
		
		if(keyFound) {
			for(int i = 0; i < array.length; i++) {
				for(int j = 0; j < array[i].length; j++) {
					if(array[i][j] != null && key == array[i][j].key) {
						array[i][j].value = value;
						System.out.println("Item Updated.");
						break;
					}
				}
			}
		}
		else {
			System.out.println("Item not found.");
		}
	}
}