package cli;

public class CommandLineMessages {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	
	public static void welcomeMessage() {
		
		System.out.println(ANSI_CYAN+"*************************************************************"+ANSI_RESET);
		System.out.println(ANSI_CYAN+"*"+ANSI_YELLOW+"                   -------------------                     "+ANSI_CYAN+"* "+ANSI_RESET);
		System.out.println(ANSI_CYAN+"*"+ANSI_YELLOW+"                   | Object Detected!|                     "+ANSI_CYAN+"* "+ANSI_RESET);
		System.out.println(ANSI_CYAN+"*"+ANSI_YELLOW+"                   -------------------                     "+ANSI_CYAN+"* "+ANSI_RESET);
		System.out.println(ANSI_CYAN+"*"+ANSI_YELLOW+"                   |/                                      "+ANSI_CYAN+"* "+ANSI_RESET);
		System.out.println(ANSI_CYAN+"*"+ANSI_YELLOW+"              ______                                       "+ANSI_CYAN+"* "+ANSI_RESET);
		System.out.println(ANSI_CYAN+"*"+ANSI_YELLOW+"             |      |>  []                                 "+ANSI_CYAN+"* "+ANSI_RESET);
		System.out.println(ANSI_CYAN+"*"+ANSI_YELLOW+"              -o--O-                                       "+ANSI_CYAN+"* "+ANSI_RESET);
		System.out.println(ANSI_CYAN+"*"+ANSI_YELLOW+"__        __   _                            _              "+ANSI_CYAN+"*\n"+ANSI_RESET
				+ ANSI_CYAN+"*"+ANSI_YELLOW+"\\ \\      / /__| | ___ ___  _ __ ___   ___  | |_ ___        "+ANSI_CYAN+"*\n"+ANSI_RESET
				+ ANSI_CYAN+"*"+ANSI_YELLOW+" \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\       "+ANSI_CYAN+"*\n"+ANSI_RESET
				+ ANSI_CYAN+"*"+ANSI_YELLOW+"  \\ V  V /  __/ | (_| (_) | | | | | |  __/ | || (_) |      "+ANSI_CYAN+"*\n"+ANSI_RESET
				+ ANSI_CYAN+"*"+ANSI_YELLOW+" __\\_/\\_/ \\___|_|\\___\\___/|_|_|_| |_|\\___|  \\__\\___/ _     "+ANSI_CYAN+"*\n"+ANSI_RESET
				+ ANSI_CYAN+"*"+ANSI_YELLOW+"/ ___|_      _(_)/ _| |_  / ___|  ___  __ _ _ __ ___| |__  "+ANSI_CYAN+"*\n"+ANSI_RESET
				+ ANSI_CYAN+"*"+ANSI_YELLOW+"\\___ \\ \\ /\\ / / | |_| __| \\___ \\ / _ \\/ _` | '__/ __| '_ \\ "+ANSI_CYAN+"*\n"+ANSI_RESET
				+ ANSI_CYAN+"*"+ANSI_YELLOW+" ___) \\ V  V /| |  _| |_   ___) |  __/ (_| | | | (__| | | |"+ANSI_CYAN+"*\n"+ANSI_RESET
				+ ANSI_CYAN+"*"+ANSI_YELLOW+"|____/ \\_/\\_/ |_|_|  \\__| |____/ \\___|\\__,_|_|  \\___|_| |_|"+ANSI_CYAN+"*\n"+ANSI_RESET
				+ ANSI_CYAN+"*************************************************************"+ANSI_RESET);
	
	}
	
	public static void instructions() {
	        
	        System.out.println(ANSI_CYAN+"\n#################"+ANSI_RESET
	                         + "Swiftbot Modes"
	                         + ANSI_CYAN+"#################"+ANSI_RESET
	                         + "\n   - In this program, you will get to navigate in three modes:"      
	                         + "\n\t• Curious Swiftbot: Swiftbot is intrigued when it sees an object." 
	                         + "\n\t• Scaredy Swiftbot: Swiftbot is scared when it sees an object."    
	                         + "\n\t• Dubious Swiftbot: You never know what Swiftbot will do next!!\n");
	        
	        System.out.println(ANSI_CYAN+"\n#################"+ANSI_RESET
	                + "\nUSER INSTRUCTIONS"
	                + ANSI_CYAN+"\n#################"+ANSI_RESET
	                + "\n   - To initiate a mode, you need to create a QR code that decodes to any one of the aforementioned modes.\n       For example, if you want to run in Dubious Swiftbot mode, your QR code should exactly decode to 'Dubious Swiftbot'."
	                + "\n   - Once the QR code is ready, press 'Q' on your keyboard to scan it using the Swiftbot camera.\n       Note that, you have 10 seconds to scan the QR code after Q is pressed."
	                + "\n   - Press 'X' on the Swiftbot to terminate any time during the execution.");
	} 
	public static void QRscanMsg() {
		System.out.println("~Turning on camera~");
		System.out.println("Please hold the QR code in front of the camera\n");
	}
	public static void QRscanErrorMsg() {
		System.out.println("Error occurred while scanning QR code, would you like to try again? \n" 
				         + "> Press 1 on your keyboard for Yes and \n" 
				         + "> Press 2 on your keyboard for No (returns to Welcome Page)\n");
	}
	
	public static void displayMode(String mode) {
		System.out.printf("You have selected %s.\n" +
				          "Do you wish to continue?\n" +
				          "> Press 1 on your keyboard for Yes and \n" +
				          "> Press 2 on your keyboard for No (returns to Welcome Page)\n\n", mode);
	}
}
