package backend;

import java.util.LinkedList;

public class FrameDoublyLinkedList {
	Frame head;

	private Frame getLastCurrentFrame() {	
		if(head == null) {
			head = new Frame();
			return head;
		}
		Frame current = head;
		while(current.next != null) {
			current = current.next;
		}
		return current;
	}
	
	private Frame addNewFrame() {
		Frame last = getLastCurrentFrame();
		Frame newFrame = new Frame();
		newFrame.previous = last;
		last.next = newFrame;
		return newFrame;
	}
	// It must be possible to add points scored by the player.
	// considering strike and spare.
	public void setScoreForCurrentChance(Chance chance) {
		Frame last = getLastCurrentFrame();
		last.addChance(chance);
	}
	
	//It must be possible to ask for the player's full points table.
	public int getTotalScores() {
		int totalScores = 0;
		if(head == null) {
			return 0;
		}
		else {
			totalScores += head.getTotalScore();
		}
		Frame current = head;
		while(current.next != null) {
			current = current.next;
			totalScores += current.getTotalScore();
		}
		return totalScores;
	}
	
	//It must be possible to ask each player for the score currently in play.
	public int getCurrentFrameScore() {
		Frame last = getLastCurrentFrame();
		return last.getTotalScore();
	}

	public void newRoundStarted() {
		addNewFrame();
		
	}

	public int getPreviousFrameScore() {
		Frame last = getLastCurrentFrame();
		if(last.previous != null) {
			return last.previous.getTotalScore();
		}
		return -1;
	}
	
	//- It must be possible to ask for the player's full points table.
	public LinkedList<Node<Integer>> fullPointsTable() {	
		LinkedList<Node<Integer>> table = new LinkedList<>();
		if(head == null) {
			return table;
		}
		else {
			Node<Integer> n = head.generateNode();
			table.add(n);
		}
		Frame current = head;
		while(current.next != null) {
			current = current.next;
			Node<Integer> n = current.generateNode();
			table.add(n);
		}
		table.remove();
		return table;
	}
	
}
