package frontend;

public class Game {

	public static void main(String[] args) {
		Player player = new Player();
		int roundCount = 1;
		new NormalRound(player, roundCount++);
		
		System.out.println("Total score: " + player.getTotalScores());
		new NormalRound(player, roundCount++);

		System.out.println("Total score: " + player.getTotalScores());
		new NormalRound(player, roundCount++);

		System.out.println("Total score: " + player.getTotalScores());
		new NormalRound(player, roundCount++);

		System.out.println("Total score: " + player.getTotalScores());
		new NormalRound(player, roundCount++);

		System.out.println("Total score: " + player.getTotalScores());
		new NormalRound(player, roundCount++);

		System.out.println("Total score: " + player.getTotalScores());
		new NormalRound(player, roundCount++);

		System.out.println("Total score: " + player.getTotalScores());
		new NormalRound(player, roundCount++);

		System.out.println("Total score: " + player.getTotalScores());
		new NormalRound(player, roundCount);

		System.out.println("Total score: " + player.getTotalScores());
		new TenthRound(player);

		System.out.println("Total score: " + player.getTotalScores());
		
		System.out.println(player.getFullPointsTable());
	}
}
