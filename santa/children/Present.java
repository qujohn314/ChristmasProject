package children;

public class Present {
	private String type;
	private static final String[] presentOptions = {"Ball","Poker Set","Paddle","Lotion","Money","Ruby","Video Game","Bike","Skateboard","Computer","Bread","Shirt","Socks","Underwear","Car","Deoderant","LEGO set","Book","Pencils","Markers","Cookies","Toy Truck","Pillow","Pants","Minecraft Account","Phone","Chess Set"}; 
	private int hashVal;
	
	public Present(int childID) {
		type = presentOptions[(int)(Math.random() * presentOptions.length)];
		hashVal = childID;
	}
	
	public Present(int childID,String present) {
		type = present;
		hashVal = childID;
	}
	
	public int hashCode() {
		return hashVal;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
	
	
}
