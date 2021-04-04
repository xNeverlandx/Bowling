package backend;

public class Node<T> {
	private T hit1;
	private T hit2;
	private T hit3;
	
	public Node() {
		
	}
	public Node(T hit1) {
		this.hit1 = hit1;
	}
	
	public Node(T hit1, T hit2) {
		this.hit1 = hit1;
		this.hit2 = hit2;
	}
	public Node(T hit1, T hit2, T hit3) {
		this.hit1 = hit1;
		this.hit2 = hit2;
		this.hit3 = hit3;
	}
	
	public T getHit1() {
		return hit1;
	}

	public void setHit1(T hit1) {
		this.hit1 = hit1;
	}

	public T getHit2() {
		return hit2;
	}

	public void setHit2(T hit2) {
		this.hit2 = hit2;
	}
	
	private String xValueCheck(T value) {
		if(value.toString().contentEquals("10")) {
			return "X";
		}
		return value.toString();
	}
	
	private String printVariable(T value) {
		return value == null ? "null" : xValueCheck(value);
	}

	public String toString() {
		if(hit1 != null && hit2 == null && hit3 == null) {
			return "[  , " + printVariable(hit1) + "]";
		}
		else if(hit1 != null && hit2 != null && hit3 == null) {
			return "[" + printVariable(hit1) + ", " + printVariable(hit2) + "]";
		}
		else {
			return "[" + printVariable(hit1) + ", " + printVariable(hit2) 
				+ ", " + printVariable(hit3) + "]";
		}
	}
}
