package backend;

public class Chance {

	private int point;
	private int score;
	
	public Chance() {
		
	}
	
	public Chance(int score) {
		this.point = score;
		this.score = score;
	}
	

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPoint() {
		return point;
	}
	
	
}
