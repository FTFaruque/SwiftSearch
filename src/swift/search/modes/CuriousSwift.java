package swift.search.modes;

import java.awt.image.BufferedImage;
import swiftbot.*;
import javax.imageio.ImageIO;
import java.io.File;

public class CuriousSwift extends SuperclassSwiftSearch {
	String mode = "Curious Swiftbot";
	
	
	/**This is the Curious Swift mode
	 * @param wanderSpeed the speed at which Swiftbot wanders around
	 * @param api takes object of type SwiftBotAPI**/	
	public CuriousSwift(int wanderSpeed, SwiftBotAPI api) {
		super(wanderSpeed, api);
	}

	/**Starts the process for Curious Swift**/
	@Override
	public void start() {
		// Checking Button press for Code terminations
		System.out.println("Curious Swiftbot has began...");
		
		startTime = System.currentTimeMillis();
		API.enableButton(Button.X, ()->{
			API.disableUnderlights();
			API.disableButton(Button.X);
			terminationLog(mode); //Termination long
			System.exit(0);
		});
		
		
		System.out.println("\n\nBeginning Action log!!");
		try {
			while(true) {
				API.fillUnderlights(Blue);
				wanderAndSearch(50);
				API.fillUnderlights(Green);
				
				
				if (objectDistance == 15) {
					for (int i=0; i < 2; i++) {
						API.disableUnderlights();
						Thread.sleep(200);
						API.fillUnderlights(Green);
						Thread.sleep(200);
					}					
				}
				
				maintainBufferDistance();
				resetObjectDistance();
				
				try {
					System.out.println("Swiftbot is taking picture!!");
					photoCount++;
					detectedObjects++;
					BufferedImage img = API.takeStill(ImageSize.SQUARE_144x144);
					ImageIO.write(img, "jpg", new File("/home/pi/Documents/" + "Image_"+Integer.toString(photoCount)+".jpg"));
				}catch(Exception e) {}
				
				double timer = System.currentTimeMillis() + 5000;
				System.out.println("Swiftbot will analyse the object for 5 seconds");
				while(System.currentTimeMillis() <= timer) {
					objectDistance = (int)API.useUltrasound();
					Thread.sleep(2000);
					
					if ((int)API.useUltrasound() != objectDistance) {
						timer = System.currentTimeMillis() + 5000;
						System.out.println("Swiftbot has detected a change, Swiftbot will analyse for 5 more seconds");
						detectedObjects++;
						resetObjectDistance();
						maintainBufferDistance();
					}
				}
				API.move(100, 0, 2000);

			}
		}catch (Exception e) {}
	}
	

	/**Maintains a distance between the Swiftbot and the object
	 * @param api takes object of type SwiftBotAPI**/
    public void maintainBufferDistance() {
    	Thread movement = new Thread(() ->{
            while(true) {                
                if (objectDistance > 15 ) //objectDistance > BUFFERZONE 
                    API.move(44, 49, 100);
                else if (objectDistance < 15 ) //objectDistance < BUFFERZONE
                    API.move(-44, -49, 100);
                else
                    return;
            }
        });
        //Start the operations for maintaining distance
        movement.start();
        while(true) {
            try{
               objectDistance = (int)API.useUltrasound();
               Thread.sleep(200);
            }catch (Exception e) {}
            if (!movement.isAlive()) {
            	return;
            }
        }
    }

}
