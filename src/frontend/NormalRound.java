package frontend;

public class NormalRound {
	
	public NormalRound(Player player, int roundNumber) {
		// inform backend that a new round has started
		player.newRoundStarted();
		// tell the player to throw ball and get the result
		int numberOfPinsHit = player.throwBall(10);
		System.out.println("Round " + roundNumber + " First hit: " + numberOfPinsHit);
		System.out.println("Current frame score: " + player.getCurrentFrameScore());
		// if result is less than 10
		if(numberOfPinsHit < 10) {
			//tell the player to throw ball again
			int secondHit = player.throwBall(10 - numberOfPinsHit);
			System.out.println("Round " + roundNumber + " Second hit: " + secondHit);
			System.out.println("Current frame score: " + player.getCurrentFrameScore());
		}

		System.out.println("Previous frame score: " + player.getPreviousFrameScore());
	}

}
