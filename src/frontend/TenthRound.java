package frontend;

public class TenthRound {

	public TenthRound(Player player) {
		// inform backend that a new round has started
		player.newRoundStarted();
		// tell the player to throw ball and get the result
		int numberOfPinsHit = player.throwBall(10);
		System.out.println("Round 10. First hit: " + numberOfPinsHit);
		System.out.println("Current frame score: " + player.getCurrentFrameScore());
		int numberOfPinsToHitNext = 
				(numberOfPinsHit < 10) ? 10 - numberOfPinsHit : 10;
		// tell the player to throw ball and get the result
		int secondNumberOfPinsHit = player.throwBall(numberOfPinsToHitNext);
		System.out.println("Round 10. Second hit: " + secondNumberOfPinsHit);
		System.out.println("Current frame score: " + player.getCurrentFrameScore());
		int combinedScore = numberOfPinsHit + secondNumberOfPinsHit;
		// if combined result is more than 10
		if(combinedScore == 10 || combinedScore == 20) {
			//tell the player to throw ball again
			int thirdNumberOfPinsHit = player.throwBall(10);
			System.out.println("Round 10. Third hit: " + thirdNumberOfPinsHit);
			System.out.println("Current frame score: " + player.getCurrentFrameScore());
		}
		else if(combinedScore > 10) {
			//tell the player to throw ball again
			int thirdNumberOfPinsHit = player.throwBall(10 - secondNumberOfPinsHit);
			System.out.println("Round 10. Third hit: " + thirdNumberOfPinsHit);
			System.out.println("Current frame score: " + player.getCurrentFrameScore());
		}
		System.out.println("Previous frame score: " + player.getPreviousFrameScore());	
	}
}
