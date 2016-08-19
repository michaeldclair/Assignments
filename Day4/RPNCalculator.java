package ssa;

import java.util.*;

public class RPNCalculator {

	public static void main(String[] args) {
		char[] problem = new char[] {'1','2','+','3','+','2','*','4','/'};
		Stack<Integer> st = new Stack<Integer>();
		System.out.printf("Calculating ");
		for (int i=0;i<problem.length-1;i++) {
			System.out.printf(problem[i] + ","); }
		System.out.printf(problem[problem.length - 1] + ". ");
		
		for (char ch : problem) {
				if (ch != '+' && ch != '-' && ch!= '*' && ch != '/') 	{
					st.push(Character.getNumericValue(ch));				}
				else 								{
					int p1 = (int)st.pop();
					int p2 = (int)st.pop();
				switch (ch) 					{
					case '+': st.push(p2 + p1);
					break;
					case '-': st.push(p2 - p1);
					break;
					case '*': st.push(p2 * p1);
					break;
					case '/': st.push(p2 / p1);
					break;						}
													}
					}
		System.out.println("Answer is " + (int)st.pop());
		
	
	
	}
}

