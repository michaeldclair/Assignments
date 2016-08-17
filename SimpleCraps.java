package simpleCraps;

import java.util.Scanner;

import java.util.stream.IntStream;
 
public class SimpleCraps {
	
	public static boolean stillPlaying = true;
	public static boolean gameNotOver = true;
	public static long seed = (new java.util.Date()).getTime();
	public static long seed2 = (new java.util.Date()).getTime() +1;
	public static java.util.Random rnd = new java.util.Random(seed);
	public static java.util.Random rnd2 = new java.util.Random(seed2);
	public static int targetRoll;
	public static int firstDie;
	public static int secondDie;


	public static void main(String[] args) {
		while (stillPlaying == true) {
		SimpleCraps onegame = new SimpleCraps();
		onegame.firstRoll();
		while (gameNotOver == true) {
			System.out.println("Your first dice added up to " + targetRoll + " and your next rolls must match that " + targetRoll + " . Enter any key to roll again. ");
			Scanner scanner = new Scanner(System.in);
			String readString = scanner.nextLine();
			System.out.println(readString);
			if (!readString.equals("$%^#$%Ffdkfdjfkj457486ldffdfdf;'4;5doubtanyonetypesthis")) {
			onegame.secondRoll();
			}
		
		}
		System.out.println("Do you want to play again? Enter 'N' to quit, any other key to continue.");
		Scanner scanner = new Scanner(System.in);
		String playAgain = scanner.nextLine();
		System.out.println(playAgain);
		playAgain = playAgain.toUpperCase();
		if (playAgain.equals("N")) {
			stillPlaying = false;
		}
		else {
			gameNotOver = true;}
		}
	} 
	
	

	
	public void firstRoll() {
		firstDie = rnd.nextInt(6) + 1;
		secondDie = rnd2.nextInt(6) + 1;
		targetRoll = firstDie + secondDie;
			if (targetRoll == 7) 
			{
				System.out.println("Your roll is " + targetRoll + ". A winner is you.");
				gameNotOver = false;
			}
			if (targetRoll == 2 || targetRoll == 3 || targetRoll == 12) 
			{
				System.out.println("Your roll is " + targetRoll +". A loser is you.");
				gameNotOver = false;}
			}
	
	public void secondRoll() {
				while (gameNotOver) {
				firstDie = rnd.nextInt(6) + 1;
				secondDie = rnd.nextInt(6) + 1;
				int total = firstDie + secondDie;
				System.out.println("You rolled " + total);
				if (total == targetRoll) {
					System.out.println(targetRoll + " was your target, you win!");
					gameNotOver = false;
					break;
				}
				if (total == 7) {
					System.out.println("7 is a loss, you lose!");
					gameNotOver = false;
					break;
				}

				}		
			}
		
					
	}



	


