package simpleCraps;

import java.util.Scanner;
 
public class OneHundredCraps {
	
	//I know declaring all these variables up here isn't ideal, but I needed them in multiple methods and was having...
	//...trouble trying to 'publicize' them. Please let me know how this could be improved. Thanks.
	
	public static boolean stillPlaying = true;
	public static boolean gameNotOver = true;
	public static long seed = (new java.util.Date()).getTime();
	public static long seed2 = (new java.util.Date()).getTime() +1;
	public static java.util.Random rnd = new java.util.Random(seed);
	public static java.util.Random rnd2 = new java.util.Random(seed2);
	public static int targetRoll;
	public static int firstDie;
	public static int secondDie;
	public static int winCounter;
	public static int lossCounter;


	public static void main(String[] args) {
	
		OneHundredCraps hundredCraps = new OneHundredCraps();
		//User is asked how many times to play, their answer is then stored as the number of loops to perform
		System.out.println("How many times would you like to play? Answer must be greater than 100.");
		Scanner scanner = new Scanner(System.in);
		int howManyTimes = scanner.nextInt();
		if (howManyTimes < 101) {
			System.out.println("Answer needs to be greater than 100, try running program again");}
		else {
		for (int totalGames=0; totalGames<howManyTimes; totalGames++) {
				hundredCraps.originalCraps();} }
		System.out.println("You had " + winCounter + " wins and " + lossCounter + " losses.");
	}
	
	public static void originalCraps() {
		//Calls a method to roll dice once, then, if 2,3,7,or 12 are not rolled, calls a second method to roll dice again.
		OneHundredCraps onegame = new OneHundredCraps();
		onegame.firstRoll();
		while (gameNotOver == true) {
			System.out.println("Your first dice added up to " + targetRoll + " and your next rolls must match that to win. This craps game rolls for you. ");
			onegame.secondRoll();
			}
			gameNotOver = true;}
		
	public void firstRoll() {
		//Rolls dice one time to set a target 'point' and ends game if user gets a 7 (win) or 2, 3, or 12 (loss)
		firstDie = rnd.nextInt(6) + 1;
		secondDie = rnd2.nextInt(6) + 1;
		targetRoll = firstDie + secondDie;
			if (targetRoll == 7) 
			{
				System.out.println("Your first roll is " + targetRoll + ". A winner is you.");
				gameNotOver = false;
				winCounter++;
			}
			if (targetRoll == 2 || targetRoll == 3 || targetRoll == 12) 
			{
				System.out.println("Your first roll is " + targetRoll +". A loser is you.");
				gameNotOver = false;
				lossCounter++;}
			}
	
	public void secondRoll() {
		//Rolls dice as many times as necessary to reach target 'point' or hit 7, which is a loss
				while (gameNotOver) {
				firstDie = rnd.nextInt(6) + 1;
				secondDie = rnd.nextInt(6) + 1;
				int total = firstDie + secondDie;
				System.out.println("You rolled " + total);
				if (total == targetRoll) {
					System.out.println(targetRoll + " was your target, you win!");
					gameNotOver = false;
					winCounter++;
					break;
				}
				if (total == 7) {
					System.out.println("7 is a loss, you lose!");
					gameNotOver = false;
					lossCounter++;
					break;
				}

				}		
			}
		
					
	}



	


