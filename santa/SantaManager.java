

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class SantaManager extends JPanel {
	private static final Color SANTA_RED = new Color(89,5,5);
	private JTextPane text =  new JTextPane();
	private ActionHandler action = new ActionHandler(this);
	private JLabel label;
	private JPanel inputPanel, textPanel,leftPanel;
	public JTextField input;
	
	public SantaManager() {
		init();
	}
	
	private static void append(JTextPane p, String n, Color c,int size, boolean bold) {
    	
    	StyledDocument doc = p.getStyledDocument();

    	SimpleAttributeSet keyWord = new SimpleAttributeSet();
    	StyleConstants.setForeground(keyWord,c);
    	StyleConstants.setBold(keyWord, bold);
    	if(size != 0)
    		StyleConstants.setFontSize(keyWord, size);
    		try
    		{
    			doc.insertString(doc.getLength(),n, keyWord);
    		} catch(Exception e) { System.out.println(e);}
    }
	
	private static TitledBorder genBorder(String n,int orient) {
		TitledBorder border3 = new TitledBorder(n);
        border3.setTitleColor(Color.WHITE);
        border3.setTitleFont(new Font("Monospaced", Font.BOLD, 18));
        if(orient == 0)
        	border3.setTitleJustification(TitledBorder.LEFT);
        else
        	border3.setTitleJustification(TitledBorder.CENTER);
        border3.setTitlePosition(TitledBorder.TOP);
        return border3;
	}
	
	
	
	
	
	
	
	
	private void init() {
		 textPanel = new JPanel(true);
		 	
		 leftPanel = new JPanel(true);
		 
		 
		 	setBackground(SANTA_RED);
	        inputPanel = new JPanel();
	        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));
	        inputPanel.setBorder(genBorder("INPUT",0));
	        inputPanel.setBackground(SANTA_RED);
	        
	        
	        text = new JTextPane();
	        text.setEditable(false);
	        textPanel.add(text);
	        text.setPreferredSize(new Dimension(600,600));
	        text.setBackground(Color.BLACK);
	        text.setForeground(Color.WHITE);
	        text.setFont(new Font("Monospaced", Font.PLAIN, 20));
	        
	        
	        input = new JTextField();
	        input.addActionListener(action);
	        input.setBorder(null);
	        input.setPreferredSize(new Dimension(600,25));
	        input.setBackground(SANTA_RED);
	        input.setForeground(Color.WHITE);
	        input.setFont(new Font("Monospaced", Font.PLAIN, 20));
	        input.grabFocus();

	        add(inputPanel);
	        add(textPanel);
	        
	
	       
	        setVisible(true);

	        label = new JLabel();
	        label.setText(">>");
	        label.setBackground(SANTA_RED);
	        label.setForeground(Color.WHITE);
	        label.setOpaque(true);
	   
	        label.setPreferredSize(new Dimension(35,25));
	        label.setMinimumSize(new Dimension(35, 25));
	        label.setMaximumSize(new Dimension(35, 25));
	        label.setFont(new Font("Serif", Font.PLAIN, 20));
	        
	        textPanel.add(text);
	        inputPanel.add(label);
	        inputPanel.add(input);

	        input.grabFocus();

	}
}
