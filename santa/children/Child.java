package children;
import java.util.ArrayList;
import java.util.Map;

public class Child implements Comparable<Child>{
	private String address;
	private String name;
	private int age;
	private int niceScore;
	private int ID;
	private static int IDcount = 0;
	public Child(String n,int a,String ad) {
		name = n;
		age = a;
		address = ad;
		niceScore = 0;
		ID = IDcount++;
	}
	
	public int hashCode() {
		return ID;
		
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

	@Override
	public int compareTo(Child c) {
		if(this.niceScore > c.getNiceScore())
			return -1;
		else if(this.niceScore < c.getNiceScore())
			return 1;
		return 0;
	}
}
