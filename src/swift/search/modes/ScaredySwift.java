package swift.search.modes;

import java.awt.image.BufferedImage;
import swiftbot.*;
import javax.imageio.ImageIO;
import java.io.File;

public class ScaredySwift extends SuperclassSwiftSearch {
	String mode = "Scaredy Swiftbot";
	

	/**This is the Scaredy Swift mode
	 * @param wanderSpeed the speed at which Swiftbot wanders around
	 * @param api takes object of type SwiftBotAPI**/	
	public ScaredySwift(int wanderSpeed, SwiftBotAPI api) {
		super(wanderSpeed, api);
	}
	
	/**Starts the process for Scaredy Swift**/
	@Override
	public void start() {
		// Checking Button press for Code terminations
		System.out.println("Scaredy Swiftbot has began...");
		
		startTime = System.currentTimeMillis();
		API.enableButton(Button.X, ()->{
			API.disableUnderlights();
			API.disableButton(Button.X);
			terminationLog(mode); //Termination log
			System.exit(0);
		});
		
		
		System.out.println("\n\nBeginning Action log!!");
		try {
			while(true) {
				API.fillUnderlights(Blue);
				wanderAndSearch(100);
				API.fillUnderlights(Red);
				resetObjectDistance();
				System.out.println("Swiftbot has detected an object");
				
				try {
					System.out.println("Swiftbot is taking a picture of the object");
					photoCount++;
					detectedObjects++;
					BufferedImage img = API.takeStill(ImageSize.SQUARE_144x144);
					ImageIO.write(img, "jpg", new File("/home/pi/Documents/" + "Image_"+Integer.toString(photoCount)+".jpg"));
				}catch(Exception e) {}
				
				for (int i=0; i < 2; i++) {
					API.disableUnderlights();
					Thread.sleep(200);
					API.fillUnderlights(Red);
					Thread.sleep(200);
				}
				
				System.out.println("Swiftbot is running away in the opposite direction!!");
				turnAroundRunAway();
				
				double timer = System.currentTimeMillis() + 5000;
				System.out.println("Swiftbot will catch a breath for 5 seconds after running away!");
				while(System.currentTimeMillis() <= timer) {
					objectDistance = (int) API.useUltrasound();
					if (objectDistance < 100 && objectDistance > 0) {
						turnAroundRunAway();
						timer = System.currentTimeMillis() + 5000;
						System.out.println("Swiftbot will catch a breath for 5 seconds after running away!");
					}
				}
				API.move(100, 0, 2000);
				
				
			}
		}catch(Exception e) {}
	}
	

	public void turnAroundRunAway() {
		API.move(-wanderSpeed, -wanderSpeed, 1000);
		API.move(100, 0, 1800); // turn 180 degree
		API.move(wanderSpeed, wanderSpeed, 3000);
	}
}
