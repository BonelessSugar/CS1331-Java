import java.util.Scanner;
public class Calculator {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("List of operations: ");
		System.out.print("add ");
		System.out.print("subtract ");
		System.out.print("multiply ");
		System.out.print("divide ");
		System.out.println("alphabetize");
		System.out.println("Enter an operation: ");
		
		String operation = input.next();
		operation = operation.toLowerCase();
		
		if (operation.equals("add")) {
			System.out.println("Enter two integers:");
			int numOne = input.nextInt();
			int numTwo = input.nextInt();
			System.out.println("Answer: " + (numOne + numTwo));
		}
		else if (operation.equals("subtract")) {
			System.out.println("Enter two integers:");
			int numOne = input.nextInt();
			int numTwo = input.nextInt();
			System.out.println("Answer: " + (numOne - numTwo));
		}
		else if (operation.equals("multiply")) {
			System.out.println("Enter two doubles:");
			double numOne = input.nextDouble();
			double numTwo = input.nextDouble();
			System.out.println("Answer: " + (numOne * numTwo));
		}
		else if (operation.equals("divide")) {
			System.out.println("Enter two doubles:");
			double numOne = input.nextDouble();
			double numTwo = input.nextDouble();
			System.out.println("Answer: " + (numOne / numTwo));
		}
		else if (operation.equals("alphabetize")) {
			System.out.println("Enter two words:");
			String wordOne = input.next();
			String wordTwo = input.next();
			int result = wordOne.compareTo(wordTwo);
			System.out.print("Answer: ");
			if (result < 0) {
				System.out.print(wordOne + " comes before " + wordTwo);
				System.out.println(" alphabetically.");
			}
			else if (result > 0) {
				System.out.print(wordTwo + " comes before " + wordOne);
				System.out.println(" alphabetically.");
			}
			else {
				System.out.println("Chicken or Egg.");
			}
		}
		else {
			System.out.println("Invalid input entered. Terminating...");
		}
	}
}
