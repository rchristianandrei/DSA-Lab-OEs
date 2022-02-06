import java.util.Scanner;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class War {
	
	//	Initialize needed objects
	public static int size = 0;
	public static int round = 1;
	
	public static boolean isWon = true;
	public static boolean isPlaying = true;
	public static boolean isWar = false;
	
	public static Scanner in = new Scanner(System.in);
	public static Deck deck = new Deck();
	
	public static Stack<Cards> player = new Stack<Cards>();
	public static Stack<Cards> enemy = new Stack<Cards>();
	
	public static Cards[] onHand = new Cards[3];
	public static Cards[] onPlay = new Cards[2];
	
	public static Queue<Cards> playerSide = new LinkedList<Cards>();
	public static Queue<Cards> enemySide = new LinkedList<Cards>();
	public static Queue<Cards> warSide = new LinkedList<Cards>();
		
	public static void main(String[] args) {
		
		//	Introduction
		System.out.println("Welcome to War Card Game!\n");
		
		System.out.println("The objective of the game is to win all of the cards.\n");
		
		System.out.println("Mechanics of the game:");
		
		System.out.println("\tThe Deck of Poker will be shuffled then divided into two evenly.\n");
		System.out.println("\tThe player draws 3 cards from the deck. then will choose 1 from the 3 "
				+ "\n\tto play. Draw 1 card after. The cards in play will be compared, the player"
				+ "\n\twith the higher card takes both of the cards played and moves them to "
				+ "\n\ttheir side for now.");
		
		System.out.println("\n\tIf the two cards played are of equal value, then there is a \"war\"."
				+ "\n\tBoth players place the next two cards face down and then another card face-up");
		System.out.println("\tproceed to compare the two cards. If the cards are equal again, repeat the process.");
		
		System.out.println("\n\tIn a case where a player run out of cards, shuffle the cards you obtained then use "
				+ "\n\tit as your deck. If you have no cards obtained from earlier battles, "
				+ "\n\tthen you lose the game.");
		
		System.out.print("\nReady? Enter the \"Start\" keyword: ");
		in.next();
		
		System.out.println("\nStart!\n");  

		
		//	Preparing Decks
		prepareCards();
		
		
		
//	-------------	Start of actual game	-----------------
		
		//	Drawing 3 Cards
		System.out.println("Drawing 3 cards...");
		for(int i = 0; i < onHand.length; i++)
		{
			onHand[i] = player.pop();
		} 
		
//	-------------	Start of loop	-----------------
		
		while(isPlaying) //	Will loop if game is going on
		{
			//	Show current round
			System.out.println("\n#-----[Round: " + round + " | Cards on Deck: " + player.size() + " | Cards on Side: " + playerSide.size() + "]-----#\n");
				
			//	Choosing card to play
			handToPlay();
				
			// Draw 1 card
			sortHand();
				
			//	Compare on Play cards
			compareCards();
				
			//	War is going on
			while(isWar)
			{
				// Check the size of enemy deck
				if(enemy.size() < 2)
				{
					populateEnemyDeck();
				}
					
				//	Dropping 2 cards each
				System.out.println("\nDropping 2 cards from deck each player...\n");
					
				for(int i = 0; i < 2; i++)
				{
					// check first
					handToPlay();
					warSide.add(onPlay[0]);
					warSide.add(enemy.pop());
				}
					
				//	Display no. of cards to get
				System.out.println("Number of cards to win: " + warSide.size() + "\n");
				
				//	Show current round
				System.out.println("\n#-----[Round: " + round + " | Cards on Deck: " + player.size() + " | Cards on Side: " + playerSide.size() + "]-----#\n");
						
				//	Choosing card to play
				handToPlay();
						
				// Draw 1 card
				sortHand();
						
				//	Compare on Play cards
				compareCards();
					
				//	Check enemy deck
				if(enemy.empty())
				{
					populateEnemyDeck();
				}
			}
			
				//	Check enemy deck
			if(enemy.empty())
			{
				populateEnemyDeck();
			}
			
			//	Get your prizes!
			if(isWon)
			{
				while(!warSide.isEmpty())
				{
					playerSide.add(warSide.remove());
				}
			}
			else
			{
				while(!warSide.isEmpty())
				{
					enemySide.add(warSide.remove());
				}
			}		
			
			// Will check if game will continue or not
			if(player.empty() && playerSide.isEmpty() && size == 0)
			{
				System.out.println("\nYou Lose the game!");
				isPlaying = false;
			}
			else if(enemy.isEmpty() && enemySide.isEmpty())
			{
				System.out.println("\nYou Win the game!");
				isPlaying = false;
			}
			else
			{
				//	Increment round
				round++;
				
				//	Prompt
				System.out.print("\nReady for next round? [Enter anything except nothing]:");
				in.next();
				isPlaying = true;
			}
			
		}	//	-------------	End of loop	-----------------

	}	//	End of main method
	
	private static void populateEnemyDeck()
	{
		if(enemy.size() < 1)
		{
			System.out.println("Enemy run out of cards on Deck! Populating deck with cards enemy won earlier");
			while(!enemySide.isEmpty())
			{
				enemy.push(enemySide.remove());
			}
		}
	}

	private static void compareCards() 
	{
		int playerCard = 0;
		int enemyCard = 0;
		
		System.out.println("\nYour Card on Play: " + onPlay[0]);
		System.out.println("Enemy Card on Play: " + onPlay[1] + "\n");
		
		//	Evaluates player card on play
		switch(onPlay[0].name)
		{
			case "King":	playerCard = 13; break;
			case "Queen":	playerCard = 12; break;
			case "Jack":	playerCard = 11; break;
			case "10":		playerCard = 10; break;
			case "9":		playerCard = 9; break;
			case "8":		playerCard = 8; break;
			case "7":		playerCard = 7; break;
			case "6":		playerCard = 6; break;
			case "5":		playerCard = 6; break;
			case "4":		playerCard = 4; break;
			case "3":		playerCard = 3; break;
			case "2":		playerCard = 2; break;
			default: 		playerCard = 1;
		}
		
		//Evaluates enemy card on play
		switch(onPlay[1].name)
		{
			case "King":	enemyCard = 13; break;
			case "Queen":	enemyCard = 12; break;
			case "Jack":	enemyCard = 11; break;
			case "10":		enemyCard = 10; break;
			case "9":		enemyCard = 9; break;
			case "8":		enemyCard = 8; break;
			case "7":		enemyCard = 7; break;
			case "6":		enemyCard = 6; break;
			case "5":		enemyCard = 6; break;
			case "4":		enemyCard = 4; break;
			case "3":		enemyCard = 3; break;
			case "2":		enemyCard = 2; break;
			default: 		enemyCard = 1;
		}
		
		//	Deciding factor
		if(playerCard > enemyCard)
		{
			System.out.println("You win this round! You got all the cards on play.");
			
			isWon = true;
			isWar = false;
		}
		else if (playerCard < enemyCard)
		{
			System.out.println("You lose this round! You lose all the cards on play.");
			
			isWon = false;
			isWar = false;
		}
		else
		{
			System.out.println("It's a tie! There will be war!");
			isWar = true;
		}
	}

	private static void handToPlay() 
	{
		boolean notValid = true;
		int chosenCard = -1;
		
		while(notValid == true)
		{
			notValid = false;
			System.out.print("Your Cards on Hand\n");
			
			for(int i = 0; i < onHand.length && onHand[i] != null; i++)
			{
				System.out.println("Card "+ i + ": " + onHand[i].toString());
			}
			
			System.out.print("\nChoose 1 card to play: ");
			
			chosenCard = in.nextInt();
			
			//	Player card on play
			switch(chosenCard)
			{
				case 0:
					onPlay[0] = onHand[0];
					onHand[0] = null;
					warSide.add(onPlay[0]);
					break;
				case 1:
					onPlay[0] = onHand[1];
					onHand[1] = null;
					warSide.add(onPlay[0]);
					break;
				case 2:
					onPlay[0] = onHand[2];
					onHand[2] = null;
					warSide.add(onPlay[0]);
					break;
				default:
					System.out.println("\nInvalid input");
					notValid = true;
			}	
		}
			
		//	Enemy card on play
		onPlay[1] = enemy.pop();
		warSide.add(onPlay[1]);
		
		//	Populating cards to win
	}
	
	private static void sortHand()
	{
		size = 0;
		//	Checking how many cards on Hand
		for(int i = 0; i < onHand.length; i++)
		{
			if(onHand[i] != null)
			{
				size++;
			}
		}
		
		for(int i = 0; i < size; i++)
		{
			if(onHand[i] == null)
			{
				while(i < size)
				{
					if(onHand[i+1] != null)
						onHand[i] = onHand[i+1];
					i++;
				}
			}
		}

		populatePlayerDeck();

		if(!player.empty())
		{
			System.out.println("\nDrawing 1 card..");
			onHand[size] = player.pop();
		}		
	}
	
	private static void populatePlayerDeck()
	{
		if(player.empty() && !playerSide.isEmpty())
		{
			System.out.println("You run our of cards on deck! Populating using cards you won earlier...");
			
			while(!playerSide.isEmpty())
			{
				player.push(playerSide.remove());
			}
		}
	}
	
	private static void prepareCards()
	{
		for(int i = 0; i < 26; i++)
		{
			player.push(deck.shuffledDeck.get(i));
		}
		for(int i = 26; i < 52; i++)
		{
			enemy.push(deck.shuffledDeck.get(i));
		}
	}

}	//	End of class