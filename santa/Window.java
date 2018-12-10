package quincy.projects.santa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;



public class Window extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static final Dimension SCREEN_SIZE = new Dimension(800, 700);

	private static final String TITLE = "Santa's List";
	public static final Color BACKGROUND_COLOR = Color.BLACK;
	private static FlowLayout layout;
	public static boolean isRunning = false;
	
	
	public Window() {
		init();
	}
	
	
	
	public void init(){
		
	    setResizable(false);
	 
		setSize(SCREEN_SIZE);
		setTitle(TITLE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(BACKGROUND_COLOR);
	
		add(new SantaManager());
		pack();
	
		setVisible(true);
		revalidate();
	}
}
