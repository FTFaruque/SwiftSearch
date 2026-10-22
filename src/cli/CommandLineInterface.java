package cli;


import java.util.Scanner;
import swiftbot.*;
import java.awt.image.BufferedImage;

public class CommandLineInterface{

	boolean endProgram = false;
	public String chosenMode;
	SwiftBotAPI API;
	private String user = "";
	
	public CommandLineInterface(SwiftBotAPI api) {
		API = api;
	}
	
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
		
		API.enableButton(Button.X, () -> {
			System.out.println("ENDING PROGRAM...");
			System.exit(0);
		});
		
		API.enableButton(Button.Y, () -> { System.out.println("Invalid Input! Please press X on Swiftbot to terminate."); });
		API.enableButton(Button.A, () -> { System.out.println("Invalid Input! Please press X on Swiftbot to terminate."); });
		API.enableButton(Button.B, () -> { System.out.println("Invalid Input! Please press X on Swiftbot to terminate."); });
		
		CommandLineMessages.welcomeMessage();
		while(!endProgram) {
			switch(user) {
			case ("q"):
				CommandLineMessages.QRscanMsg();
				user = getQRmsg();
				break;
			
			case "Curious Swiftbot": case "Scaredy Swiftbot": case "Dubious Swiftbot":
				CommandLineMessages.displayMode(user);
				chosenMode = user;
				user = getValidInput(scanner, new String[] {"1", "2"});
				break;
			
			// Error after Scanning QR code.
			case "-1":
				CommandLineMessages.QRscanErrorMsg();
				user = getValidInput(scanner, new String[] {"1", "2"});
				user = user.equals("1")? "q" : "2"; // If 1, then rescan QR code, else, return to main menu
				break;
	
			//Exit terminal and proceed to Swiftbot mode
			case "1":
				endProgram = true;
				break;
			
			//Return to main menu
			case "2":
				user = ""; //some character not present in the switch case
				break;

			// Main menu
			default:
				CommandLineMessages.instructions();
				user = getValidInput(scanner, new String[] {"q"});
				break;
			}
		}
		scanner.close();
		API.disableAllButtons();

	}
	
	
	/**Takes user input and returns valid input, else, it infinitely loops and asks the user for valid input**/
	public String getValidInput(Scanner scanner, String[] conditions) {
		String input = "";
		while (true) {
			input = scanner.nextLine();
			if (input.equals("x")) {
				System.out.println("Please press the X button on Swiftbot!!");
				continue;
			}
			for (String condition : conditions) {
				if (input.equals(condition))
					return input;
			}
			System.out.println("Invalid input! Please try again."); 
		}
	}
	
	
	public String getQRmsg() {
		String decodedMsg;
		try {
			Thread.sleep(5000);
			BufferedImage img = API.getQRImage();
			decodedMsg = API.decodeQRImage(img);
		}catch(Exception e) {
			System.out.println("Image provided doesn't contain any data!!");
			decodedMsg = "-1";
			return decodedMsg;
		}
		switch(decodedMsg) {
		case "Curious Swiftbot": case "Scaredy Swiftbot": case "Dubious Swiftbot":
			return decodedMsg;
		default:
			return "-1";
		}	
	}
}
