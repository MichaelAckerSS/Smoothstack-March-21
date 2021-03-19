package console;

public class LoginDialogue {
	int userClass;
	
	public LoginDialogue(int userClass) {
		this.userClass = userClass;
	}
	
	public void launch() {
		System.out.println("Enter your user ID:");
	}

}
