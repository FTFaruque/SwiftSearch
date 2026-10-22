package swift.search.modes;

import swiftbot.*;


public abstract class SuperclassSwiftSearch{
	int wanderSpeed;
	long startTime = 0;
	int[] Red = {255, 0, 0}, Blue = {0, 255, 0}, Green = {0, 0, 255}; 
	SwiftBotAPI API;
	private volatile String terminateProgram = "";
	int objectDistance = 0;
	int photoCount;
	int detectedObjects;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";
	

	
	public SuperclassSwiftSearch(int wanderspeed, SwiftBotAPI api) {
		wanderSpeed = wanderspeed;
		API = api;
	}

	public abstract void start();
	
	// IMPORTANT: Reset object distance once wanderAndSearch or maintainBufferDistance is done
	public void resetObjectDistance() {
    	objectDistance = 0;
    }
	

	public void wanderAndSearch(int distance) {
		while(true) {
			objectDistance = (int)API.useUltrasound();
			if (objectDistance < distance && objectDistance > 0) {
				return;
			}
			API.move(wanderSpeed, wanderSpeed, 1000);
			int moveDirection = (int)(Math.random() * (2 - 1 + 1) + 1);
			if(moveDirection % 2 == 0) {
				API.move(0, 100, 1000);
			}else {
				API.move(100, 0, 1000);
			}
			objectDistance = (int)API.useUltrasound();
			if (objectDistance < distance && objectDistance > 0) {
				return;
			}
		}	
	}
	

	/**Shows termination log on Y button press and doesn't show the log on X button press on Swiftbot.
	 * Termination log shows total duration ran, pictures taken and the Mode that was selected 
	 * @param api takes object of type SwiftBotAPI**/
    public void terminationLog(String mode) {
		System.out.println("\nDo you wish to see the termination log? On your Swiftbot, press Y for Yes and X for No");
		API.enableButton(Button.X, () -> { terminateProgram = "x"; });
		API.enableButton(Button.Y, () -> { terminateProgram = "y"; });
		
		while(true) { 
			if (terminateProgram.equals("y")) {
				long duration_ran = System.currentTimeMillis() - startTime;
				System.out.printf("Pictures taken: %d\n", photoCount);
				System.out.printf("Objects detected: %d\n", detectedObjects);
				System.out.println("Duration ran: " + hmsFormat(duration_ran));
				System.out.println("The mode ran: " + mode);
				break;
			}else if (terminateProgram.equals("x"))
				break;
		}
		API.disableAllButtons();
		System.out.println("ENDING PROGRAM....\n");
		System.out.println(ANSI_YELLOW+"Successfully terminated. Have a nice day ^_^\n"+ANSI_RESET
				+ ANSI_CYAN+"*********************************************"+ANSI_RESET);
		
	}
	
	/**Converts milliseconds into Hours : minutes : seconds format format
	 * @param millis takes milliseconds of type long
	 * @return Returns String containing duration in hh : mm : ss format**/
	public String hmsFormat(long millis) {
		double seconds = millis / 1000.0;
        
        double hours = seconds / 3600.0;
        seconds = seconds % 3600.0;
        double minutes = seconds / 60.0;
        seconds = seconds % 60.0;
        
        return String.format("%02d : %02d : %02f", (int)hours, (int)minutes, seconds);
	}	
}
