package ssa;

import java.util.*;

public class RPNCalculator {
	static String operandOne;
	static String operandTwo;
	static String operator;
	static String[] problem;
	static Stack<Integer> st;
	static ArrayList<String[]> formulas = new ArrayList<String[]>();
	static boolean moreFormulas = true;
	static int i=0;
	static String reply;

	public static void main(String[] args) {
		RPNCalculator calculation = new RPNCalculator();
		while (moreFormulas == true) {
		calculation.enterString();
	  	problem = new String[] {operandOne, operandTwo, operator};
	  	System.out.println("o1 is " + operandOne + " o2 is " + operandTwo + " oper is " + operator);
	  	formulas.add(problem);
		System.out.println("Do you want to enter another formula? Hit Y for yes, any other key for no");
		Scanner in= new Scanner(System.in);
		reply = in.next();
		System.out.println(reply);
		if (!reply.equals("Y")) {
				moreFormulas = false;
			}
		}
		
		//Cycles through arrays in ArrayList formulas to calculate
		for (String[] formula: formulas){
 			calculation.calculate(formula);
 
		}
		}
 	
	  //This method pushes the operands onto the stack and then calculates a result based
	  // on the operator
 	  public void calculate(String[] algorithm){
  		System.out.println("Calculating " + algorithm[0] + "," + algorithm[1] + "," + algorithm[2]);
  		st = new Stack<Integer>();
  		st.push(Integer.parseInt(algorithm[0]));
  		st.push(Integer.parseInt(algorithm[1]));
  		switch (algorithm[2]) {
  			case "+": st.push(st.pop() + st.pop());
  	 		System.out.println("Answer is " + st.pop());
  			break;
  			case "-": st.push((0 - st.pop() + st.pop()));
  	 		System.out.println("Answer is " + st.pop());
  			break;
  			case "*": st.push(st.pop() * st.pop());
  	 		System.out.println("Answer is " + st.pop());
  			break;
  			case "/": double firstPop = (1/(double)(st.pop()));
  						double secondPop = (double)st.pop();
  						double doubleResult = firstPop * secondPop;
  						int intResult = (int)doubleResult;
  						st.push(intResult);
  				 		System.out.println("Answer is " + st.pop());
  			break;
  			
	}

	}
  		//This method asks for a formula and parses it into its Operands and Operators.
  		public void enterString() {
  			System.out.println("Please enter a mathematical formula in the form OPERAND OPERAND OPERATOR (e.g. 1 2 +)");
  			Scanner in= new Scanner(System.in);
  			operandOne = in.next();
  			operandTwo = in.next();
  			operator = in.next();
  
  		}


		
	
	}


