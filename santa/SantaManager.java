package quincy.projects.santa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class SantaManager extends JPanel {
	private static final Color SANTA_RED = new Color(96,8,5);
	private JTextPane text =  new JTextPane();
	ActionHandler actionHandler = new ActionHandler(this);
	
	public SantaManager() {
		init();
	}
	
	public void init() {
		setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		setBackground(SANTA_RED);
		setVisible(true);
		setDoubleBuffered(true);
		setPreferredSize(Window.SCREEN_SIZE);
		
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		text.setPreferredSize(new Dimension(750,640));
		text.setMaximumSize(new Dimension(750,640));
		text.setEditable(false);
		
		add(text);
		
		input = new JTextField();
        input.addActionListener(actionHandler);
        input.setBorder(null);
        input.setPreferredSize(new Dimension(1270,35));
        input.setBackground(Color.black);
        input.setForeground(Color.white);
        input.setFont(new Font("Monospaced", Font.PLAIN, 20));
        input.grabFocus();
	}
}
