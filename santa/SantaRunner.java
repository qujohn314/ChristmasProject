package quincy.projects.santa;


//import javax.swing.JApplet;

public class SantaRunner {
	
	public SantaRunner() {
		new Window();
	}
	
	public static void main(String args[]) {
	    java.awt.EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            new SantaRunner();
	        }
	    });
	}
	

	
}
