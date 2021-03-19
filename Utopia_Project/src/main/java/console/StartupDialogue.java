package console;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StartupDialogue {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.printf("Welcome to Utopia Airlines!%nWhat type of user are you? Please select:%n1. Customer%n2. Employee%n3. Administrator%n4. Guest%n");
		Boolean valid = false;
		int response = 0;
		while(!valid) {
			try {
			response = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.printf("Input must be an integer! Please select:%n1. Customer%n2. Employee%n3. Administrator%n4. Guest%n");
				in.next();
				continue;
			}
			switch(response) {
				case 1: 
					System.out.println("Proceeding to customer login...");
					valid = true;
					break;
				case 2: 
					System.out.println("Proceeding to employee login...");
					valid = true;
					break;
				case 3: 
					System.out.println("Proceeding to administrator login...");
					valid = true;
					break;
				case 4: 
					System.out.println("Proceeding as guest...");
					valid = true;
					break;
				default: 
					System.out.printf("Invalid choice! Please select:%n1. Customer%n2. Employee%n3. Administrator%n4. Guest%n");
					break;
			}
		}
		in.close();
		LoginDialogue login = new LoginDialogue(response);
		login.launch();
	}

}
