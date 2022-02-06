import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Deck {

	public Cards[] deck = new Cards[52];
	public List<Cards> shuffledDeck;
	
	//Stack<Cards> Deck = new Stack<Cards>();
	
	public Deck ()
	{
		//Populates Deck
		int counter = 1;
		
		for(int i = 0; i < deck.length; i++)
		{
			String shape;
			String name = null;
			
			//Decide which suit a card is
			if(i < 13)
			{
				shape = "Spades";
				
			}
			else if(i < 26)
			{
				shape = "Hearts";
			} 
			else if(i < 39) 
			{
				shape = "Diamonds";
			}
			else 
			{
				shape = "Clubs";
			}
			
			switch(counter)
			{
				case 1:
					name = "Ace";
					break;
				case 2:
					name = "2";
					break;
				case 3:
					name = "3";
					break;
				case 4:
					name = "4";
					break;
				case 5:
					name = "5";
					break;
				case 6:
					name = "6";
					break;
				case 7:
					name = "7";
					break;
				case 8:
					name = "8";
					break;
				case 9:
					name = "9";
					break;
				case 10:
					name = "10";
					break;
				case 11:
					name = "Jack";
					break;
				case 12:
					name = "Queen";
					break;
				case 13:
					name = "King";
					break;
			}
			
			counter++;
			
			if(counter > 13)
			{
				counter = 1;
			}
			
			deck[i] = new Cards(name, shape);
		}
		
		shuffledDeck = Arrays.asList(deck);
		
		Collections.shuffle(shuffledDeck);
	}
}
