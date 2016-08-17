package theDiceGame;

import java.util.Scanner;

import java.util.stream.IntStream;
 
public class SimpleDice {
	
	public static int counter = 0;
	public static boolean isNotOne = true;
	public static boolean isNotSeven = true;
	public static long seed = (new java.util.Date()).getTime();
	public static long seed2 = (new java.util.Date()).getTime() +1;
	public static java.util.Random rnd = new java.util.Random(seed);
	public static java.util.Random rnd2 = new java.util.Random(seed2);

	public static void main(String[] args) {
		SimpleDice onegame = new SimpleDice();
		System.out.println("Would you like to play with 1 die or 2 (Enter 1 or 2)?");
		Scanner in= new Scanner(System.in);
		int answer = in.nextInt();
		if (answer == 1) {
		onegame.OneDie();}
		if (answer == 2) {
		onegame.TwoDice(); }
		if (answer != 1 && answer != 2) {
			System.out.println("I don't have that many dice, Run program again");
		}
	
	}
		
		
	public void OneDie() {
		while (isNotOne) {
			int random = rnd.nextInt(6) + 1;
			if (random == 1) {
				System.out.println("Random is finally equal to 1");
				isNotOne = false;
				break; }
					counter = counter + random;
					System.out.println("Random is " + random + " Counter is currently " + counter);
			}
			
		
		System.out.println("Total is " + counter);

	}
	
	public void TwoDice() {
		while (isNotSeven) {
			int random = rnd.nextInt(6) + 1;
			int random2 = rnd2.nextInt(6) + 1;
			if (random + random2 == 7) {
				System.out.println("Finally hit a seven as Random 1 is " + random + " and Random 2 is " +random2);
				isNotSeven = false;
				break; }
					counter = counter + random + random2;
					System.out.println("Random 1 is " + random + ", Random 2 is " + random2 + ", Counter is " + counter);
			}
			
		
		System.out.println("Total is " + counter);
	}

}
