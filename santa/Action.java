
public class Action {
	private String desc;
	private int score;
	
	public Action(int s, String d) {
		score = s;
		desc = d;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
