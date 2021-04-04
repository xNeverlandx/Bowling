package backend;

import java.util.ArrayList;
import java.util.List;

public class Frame {

	Frame next;
	Frame previous;
	List<Chance> chances;
	private int totalScore;
	ScoreType scoreType;
	boolean extraPointsAdded;
	boolean deservesExtraPoints;
	
	public Frame() {
		chances = new ArrayList<>();
		scoreType = ScoreType.ZERO;
		totalScore = 0;
		extraPointsAdded = false;
		deservesExtraPoints = false;
	}
	
	public ScoreType getScoreType() {
		return scoreType;
	}
	
	public void addScoreToLastChance(int additionalScore) {
		if(additionalScore < 0) {
			throw new IllegalArgumentException("Score must be greater than 0");
		}
		Chance lastChance = chances.get(chances.size() - 1);
		if(lastChance != null) {
			lastChance.setScore(lastChance.getScore() + additionalScore);
			
		}
	}

	public void addChance(Chance chance) {
		// check if its the first one and its score is 10
		if(chances.size() == 0) {
			if(chance.getScore() == 10) {
				scoreType = ScoreType.STRIKE;
				totalScore = 10;
				// if the previous is a strike frame add up till the 2nd predecessor strike
				updateBoth2PreviousStrikes(10);
				// if the previous is a spare frame add up only till the first predecessor
				updatePreviousSpare(10);
			}	
			else if(chance.getScore() < 10) {
				updatePreviousSpare(chance.getScore());
				updateOnly2PreviousStrikes(chance.getScore());
			}
		}	
		else if(chances.size() == 1) {
			if((getTotalScore() + chance.getScore()) == 10) {
				scoreType = ScoreType.SPARE;			
			}
			else {
				scoreType = ScoreType.OPEN_FRAME;
			}
			updatePreviousStrike(getTotalScore() + chance.getScore());
		}
		chances.add(chance);
	}
	
	private void updateOnly2PreviousStrikes(int firstScore) {
		if(previous != null && previous.getScoreType() == ScoreType.STRIKE) {
			if(previous.previous != null && previous.previous.getScoreType() == ScoreType.STRIKE) {
				previous.previous.addScoreToLastChance(firstScore);
			}
		}
	}

	private void updateBoth2PreviousStrikes(int firstScore) {
		if(previous != null && previous.getScoreType() == ScoreType.STRIKE) {
			previous.addScoreToLastChance(firstScore);
			if(previous.previous != null && previous.previous.getScoreType() == ScoreType.STRIKE) {
				previous.previous.addScoreToLastChance(firstScore);
			}
		}
	}
	
	private void updatePreviousSpare(int score) {
		if(previous != null && previous.getScoreType() == ScoreType.SPARE) {
			previous.addScoreToLastChance(score);
		}
	}

	private void updatePreviousStrike(int score) {
		// get the previous Frame and 
		// check if its eligible for adding 10
		if(previous != null && previous.getScoreType() == ScoreType.STRIKE) {
			previous.addScoreToLastChance(score);
		}
	}

	public int getTotalScore() {
		int tScores = 0;
		for(Chance c : chances) {
			tScores += c.getScore();
		}
		return tScores;
	}

	public Node<Integer> generateNode() {
		int size = chances.size();
		switch(size) {
			case 1: return new Node<>(chances.get(0).getPoint());
			case 2: return new Node<>(chances.get(0).getPoint(), chances.get(1).getPoint());
			case 3: return new Node<>(chances.get(0).getPoint(), chances.get(1).getPoint(), chances.get(2).getPoint());
			default: return new Node<>();
		}
	}
	
	
}
