package swift.search.modes;

import java.lang.Math;

public class DubiousSwift {
	public CuriousSwift curiousSwift;
	public ScaredySwift scaredySwift;
	long startTime;
	
	/**This is the Dubious mode
	 * @param curiousSwift takes of object of type CuriousSwift
	 * @param scaredySwift takes of object of type ScaredySwift**/
	public DubiousSwift(CuriousSwift curriousSwift, ScaredySwift scaredySwift) {
		this.curiousSwift = curriousSwift;
		this.scaredySwift = scaredySwift;
	}
	
	/**Starts the process for Dubious Swift. Chooeses between Curious or Scaredy Swift**/
	public void start() {
		System.out.println("Dubious Swiftbot has begun...");
		
		int choice =  (int)(Math.random() * (11 - 1 + 1) + 1);
		
		if (choice % 2 == 0) {
			System.out.println("Swiftbot is feeling ~curious~ today...");
			curiousSwift.start();
		}else {
			System.out.println("Swiftbot is feeling ~scared~ today...");
			scaredySwift.start();
		}
	}
}
