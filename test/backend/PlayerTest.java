package backend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import frontend.Player;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlayerTest {
	static Player player;
	
	@BeforeAll
	public static void setup() {
		player = new Player();
		
	}
	
	int[] playNormalRound() {
		// inform backend that a new round has started
		player.newRoundStarted();
		// tell the player to throw ball and get the result
		int numberOfPinsHit = player.throwBall(10);
		// if result is less than 10
		if(numberOfPinsHit < 10) {
			//tell the player to throw ball again
			int secondHit = player.throwBall(10 - numberOfPinsHit);
			return new int[] {numberOfPinsHit, secondHit};
		}
		return new int[] {numberOfPinsHit};
	}
	
	//It must be possible to add points scored by the player.
	@Test
	@Order(1)
	void testCurrentFrameScoreIsSameNumberOfHitsRecorded() {
		int[] hitsRound1 = playNormalRound();
		
		assertEquals(player.getCurrentFrameScore(), Arrays.stream(hitsRound1).sum());
		
	}
	// Add points scored by the player considering strike and spare.
	@Test
	@Order(2)
	void testPointsAreAddedToPreviousFrameStrikeOrSpare() {
		//Testing strike hit
		FrameDoublyLinkedList record = player.getRecord();
		record.newRoundStarted();
		Chance chance = new Chance(10);
		record.setScoreForCurrentChance(chance);
		
		// Starting new round that hits an open range
		record.newRoundStarted();
		chance = new Chance(5);
		record.setScoreForCurrentChance(chance);
		chance = new Chance(4);
		record.setScoreForCurrentChance(chance);
		
		assertEquals(player.getPreviousFrameScore(), 19);
		
		//Testing spare hit
		record.newRoundStarted();
		chance = new Chance(5);
		record.setScoreForCurrentChance(chance);
		chance = new Chance(5);
		record.setScoreForCurrentChance(chance);
				
		// Starting new round that hits a strike
		record.newRoundStarted();
		chance = new Chance(10);
		record.setScoreForCurrentChance(chance);
				
		assertEquals(player.getPreviousFrameScore(), 20);		
	}
	
	// It must be possible to ask each player for the score currently in play.
	@Test
	@Order(3)
	void testTotalScoresGreaterThan0AndScoresAccumulate() {
		FrameDoublyLinkedList record = player.getRecord();
		// Starting new round that hits an open range
		record.newRoundStarted();
		Chance chance = new Chance(5);
		record.setScoreForCurrentChance(chance);
		chance = new Chance(4);
		record.setScoreForCurrentChance(chance);
		
		assertTrue(player.getTotalScores() > 0);
		
		int previousScores = player.getTotalScores();
		
		// Starting new round that hits an open range
		record.newRoundStarted();
		chance = new Chance(3);
		record.setScoreForCurrentChance(chance);
		chance = new Chance(4);
		record.setScoreForCurrentChance(chance);
		
		int currentRoundScores = 7;
		
		assertEquals(player.getTotalScores(), (previousScores + currentRoundScores));	
	}

	//It must be possible to ask for the player's full points table.
	@Test
	@Order(4)
	void testPointsTableSizeEquals7AndFirstHitOfLastFrameIs3() {
		LinkedList<Node<Integer>> pointsTable = player.getFullPointsTable();
		assertEquals(pointsTable.size(), 7);
		
		assertEquals(pointsTable.getLast().getHit1(), 3);
	}
}
