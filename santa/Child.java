import java.util.ArrayList;
import java.util.Map;

public class Child {
	private String address;
	private String name;
	private int age;
	private int niceScore;
	
	public Child(String n,int a,String ad) {
		name = n;
		age = a;
		address = ad;
		niceScore = 0;
	}
	
	public int hashCode() {
		return 0;
		
	}
	
	public void updateNice(Map<Child,ArrayList<Action>> nice, Map<Child,ArrayList<Action>> naughty) {
		niceScore = 0;
		
		nice.get(this).forEach(n -> {
			niceScore += n.getScore();
		});
		
		naughty.get(this).forEach(n -> {
			niceScore -= n.getScore();
		});
	}

	public boolean isNice() {
		return niceScore > 0 ? true : false;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getNiceScore() {
		return niceScore;
	}

	public void setNiceScore(int niceScore) {
		this.niceScore = niceScore;
	}
	
	public String toString() {
		return name + "(" + age + " years old) is nice = " + niceScore + ", " + address;
	}
}
