package frontend;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import backend.Chance;
import backend.FrameDoublyLinkedList;
import backend.Node;

public class Player {

	FrameDoublyLinkedList record;
	Random random;
	
	public Player() {
		record = new FrameDoublyLinkedList();
		random = new Random();
		setOf10 = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,
						  10,10,10,10,9,9,8,8,10,9,8);
		setOf9 = Arrays.asList(0,1,2,3,4,5,6,7,8,9,
				  		 9,9,9,9,8,8,8,7,7);
		setOf8 = Arrays.asList(0,1,2,3,4,5,6,7,8,
				         8,8,8,7,7,7,6,6,8);
		setOf7 = Arrays.asList(0,1,2,3,4,5,6,7,
		                 7,7,7,6,6,6,7,7);
		setOf6 = Arrays.asList(0,1,2,3,4,5,6,
		                 6,6,6,5,5,5,6);
		setOf5 = Arrays.asList(0,1,2,3,4,5,
                		 5,5,5,4,4,5);
		setOf4 = Arrays.asList(0,1,2,3,4,
                		 4,4,3,3,4);
		setOf3 = Arrays.asList(0,1,2,3,
       		 			 3,3,2,3);
		setOf2 = Arrays.asList(0,1,2,
       		 			 2,2,1);
		setOf1 = Arrays.asList(0,1,1);
	}
	
	public int throwBall(int numberOfPins) {
		List<Integer> pinSet = null;
		switch(numberOfPins) {
			case 10 :  pinSet = setOf10;
						break;
			case 9 :   pinSet = setOf9;
						break;
			case 8 :   pinSet = setOf8;
						break;
			case 7 :   pinSet = setOf7;
						break;
			case 6 :   pinSet = setOf6;
						break;
			case 5 :  pinSet = setOf5;
						break;
			case 4 :   pinSet = setOf4;
						break;
			case 3 :   pinSet = setOf3;
						break;
			case 2 :   pinSet = setOf2;
						break;
			case 1 :   pinSet = setOf1;
						break;
			default :  pinSet = setOf10;			
		}
		// shuffle the list
		Collections.shuffle(Arrays.asList(pinSet));
		int randomPosition = random.nextInt(pinSet.size());
		int numberOfPinsHit = pinSet.get(randomPosition);
		Chance chance = new Chance(numberOfPinsHit);
		record.setScoreForCurrentChance(chance);
		return numberOfPinsHit;
	}
	
	// request record for maximumRangeValueForNextChance
	// randomization that favors the max number
	// List<Integer> setOf10  0 1 2 3 4 5 6 7 8 9 10
	//                                  10%  10%   40%
	
	
	List<Integer> setOf10;
	List<Integer> setOf9;
	List<Integer> setOf8;
	List<Integer> setOf7;
	List<Integer> setOf6;
	List<Integer> setOf5;
	List<Integer> setOf4;
	List<Integer> setOf3;
	List<Integer> setOf2;
	List<Integer> setOf1;

	public void newRoundStarted() {
		record.newRoundStarted();
		
	}
	
	public int getCurrentFrameScore() {
		return record.getCurrentFrameScore();
	}
	
	public int getTotalScores() {
		return record.getTotalScores();
	}

	public int getPreviousFrameScore() {
		return record.getPreviousFrameScore();
	}
	
	public LinkedList<Node<Integer>> getFullPointsTable(){
		return record.fullPointsTable();
	}

	public FrameDoublyLinkedList getRecord() {
		return record;
	}
	
	
}
