package theDiceGame;

import java.util.stream.IntStream;

public class TwoDice {
	
	public static int counter = 0;
	public static boolean isNotSeven = true;
	public static long seed = (new java.util.Date()).getTime();
	public static long seed2 = (new java.util.Date()).getTime() +1;
	public static java.util.Random rnd = new java.util.Random(seed);
	public static java.util.Random rnd2 = new java.util.Random(seed2);

	public static void main(String[] args) {
		
		while (isNotSeven) {
			int random = rnd.nextInt(7) + 1;
			int random2 = rnd2.nextInt(7) + 1;
			if (random == 7 || random2 == 7) {
				System.out.println("Finally hit a seven as Random 1 is " + random + " and Random 2 is " +random2);
				isNotSeven = false;
				break; }
					counter = counter + random + random2;
					System.out.println("Random 1 is " + random + ", Random 2 is " + random2 + ", Counter is " + counter);
			}
			
		
		System.out.println("Total is " + counter);

	}

}
