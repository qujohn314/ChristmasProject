package backend;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import children.Action;
import children.Child;
import children.HashTable;
import children.Present;


public class SantaManager  {
	private static final Color SANTA_RED = new Color(89,5,5);
	private static final String TITLE = "Santa's List";
	private  JTextPane text;
	private JFrame frame;

	private ActionListenerHandler inputBox = new ActionListenerHandler(this);
	private JLabel label;
	private JPanel inputPanel, textPanel,leftPanel;
	public JTextField input;
	private Map<Child,ArrayList<Action>> niceList;
	private Map<Child,ArrayList<Action>> naughtyList;
	private ArrayList<Child> children;
	private HashTable<Present> presents;
	private PriorityQueue<Child> stops;
	
	
	public SantaManager() {
		init();
		initChildren();
		
		int input = -1;
		while(input != 5) {
			text.setText("");
		
			append(text,"Santa's Management Software v0.1.1\n\n",Color.ORANGE,27,true);
			append(text,"-Select an option-\n\n",Color.WHITE,20,false);
		
			append(text,"[0] View Children\n\n",Color.WHITE,20,false);
			append(text,"[1] Record Nice Action\n\n",Color.WHITE,20,false);
			append(text,"[2] Record Naughty Action\n\n",Color.WHITE,20,false);
			append(text,"[3] End the Week\n\n",Color.WHITE,20,false);
			append(text,"[4] View Current Christmas Route\n\n",Color.WHITE,20,false);
			append(text,"[5] Delivery Presents and Exit Program",Color.WHITE,20,false);
		
			try {
				input = inputBox.getInt();
				
				if(input < 0 || input > 5)
					throw new IndexOutOfBoundsException();
				
				text.setText("");
				if(input == 0) {
					textEffect(text,"-Select A Child to View\n\n",Color.ORANGE,true);
					append(text,getChildren(),Color.WHITE,15,false);
					int choice = inputBox.getInt();
					text.setText("");
					append(text,children.get(choice).toString() + "\n",Color.WHITE,15,false);
					append(text,"\nNaughty Actions: " + Arrays.toString(naughtyList.get(children.get(choice)).toArray()),Color.WHITE,20,false);
					append(text,"\n\nNice Actions: "+ Arrays.toString(niceList.get(children.get(choice)).toArray()) + "\n",Color.WHITE,20,false);
					append(text,"\nPresents: " + Arrays.toString(presents.getStack(children.get(choice).hashCode()).toArray()) + "\n",Color.WHITE,20,false);
					userWait(text);
				}
					
				if(input == 1)
					addNiceAction();
				if(input == 2)
					addBadAction();
				if(input == 3)
					weeklyCheck();
				if(input == 4)
					deliveryRoute();
				if(input == 5)
					textEffect(text,"\n\n\n\nMerry Christmas!",Color.ORANGE,true);
					
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("FATAL ERROR >.< Relaunch before Christmas is ruined!");
			} catch (IndexOutOfBoundsException e) {
				text.setText("");
				textEffect(text,"\n\n\nInvalid Input!",Color.RED,true);
				pause(2000);
			}
		}
		
		

	}
	
	public void addNiceAction() throws InterruptedException {
		text.setText("");
		textEffect(text,"Select a child to record a NICE action for:\n",Color.ORANGE,true);
		append(text,getChildren(),Color.WHITE,15,false);
		inputBox.reset();
	    int input = inputBox.getInt();
		
	    text.setText("");
	    try {
	    	append(text,"Record NICE action for " + children.get(input).getName() + "\n",Color.ORANGE,20,true);
	    	String niceAction = inputBox.getString();
	    	append(text,niceAction,Color.WHITE,20,false);
	    	append(text,"\n\nNice value of action: ",Color.MAGENTA,20,true);
	    	int ginput = inputBox.getInt();
			append(text,ginput+"\n",Color.WHITE,20,false);
			niceList.get(children.get(input)).add(new Action(niceAction,ginput));
	    }catch(IndexOutOfBoundsException e){
	    	append(text,"\nInvalid value!\n",Color.RED,20,true);
	    }
		userWait(text);
		
	}
	
	public void addBadAction() throws InterruptedException {
		text.setText("");
		textEffect(text,"Select a child to record a NAUGHTY action for:\n",Color.ORANGE,true);
		append(text,getChildren(),Color.WHITE,15,false);
		inputBox.reset();
	    int input = inputBox.getInt();
		try {
			text.setText("");
			append(text,"Record NAUGHTY action for " + children.get(input).getName() + "\n",Color.ORANGE,20,true);
			String badAction = inputBox.getString();
			append(text,badAction,Color.WHITE,20,false);
			append(text,"\n\nNAUGHTY value of action: ",Color.RED,20,true);
			int binput = inputBox.getInt();
			append(text,binput+"\n",Color.WHITE,20,false);
			naughtyList.get(children.get(input)).add(new Action(badAction,binput));
		}catch(IndexOutOfBoundsException e){
	    	append(text,"\nInvalid value!\n",Color.RED,20,true);
	    }
		
		
		userWait(text);
	}
	
	public void weeklyCheck() throws InterruptedException {
		ArrayList<Child> goodWeek = new ArrayList<Child>();
		ArrayList<Child> badWeek = new ArrayList<Child>();
		
		for(int i = 0;i<children.size();i++) {
			children.get(i).updateNice(niceList, naughtyList);
			if(!presents.getStack(i).empty())
				if(children.get(i).isNice() && !(presents.getStack(i).peek().getType().equals("Coal")))
					presents.add(new Present(i));
				else if(children.get(i).isNice() && presents.getStack(i).peek().getType().equals("Coal"))
					presents.getStack(i).pop();
				else if(!children.get(i).isNice() && !(presents.getStack(i).peek().getType().equals("Coal")))
					presents.getStack(i).pop();
				else
					presents.add(new Present(i,"Coal"));
			else if(children.get(i).isNice())
				presents.add(new Present(i));
			else
				presents.add(new Present(i,"Coal"));
			
		}
		for(Child child : children) 
			if(child.getNiceScore() > 0)
				goodWeek.add(child);
			else
				badWeek.add(child);
		
		
		
		textEffect(text,"~ALL lists have been updated~\n",Color.ORANGE,true);
		userWait(text);
		append(text,"-Weekly Report-\n\n",Color.ORANGE,20,true);
		append(text,"Nice Children: \n",Color.GREEN,20,true);
		
		for(Child child : goodWeek) 
			append(text,"   "+child.getName()+"\n",Color.WHITE,20,false);
		
		append(text,"Naughty Children: \n",Color.RED,20,false);
		
		for(Child child : badWeek) 
			append(text,"   "+child.getName()+"\n",Color.WHITE,20,false);
		
		
		
		userWait(text);
		
		
	}
	
	public void deliveryRoute() throws InterruptedException {
		for(Child child : children)
			stops.add(child);
		textEffect(text,"~Santa's Delivery Route~\n",Color.ORANGE,true);
		int count = 0;
		while(!stops.isEmpty()) {
			Child c = stops.poll();
			append(text,count+". "+c.getAddress()+"\n >"+Arrays.toString(presents.getStack(c.hashCode()).toArray())+"\n",Color.WHITE,18,true);
			count++;
		}
		
		userWait(text);
		
	}
	
	public String getChildren() {
		String output = "";
		for(int i = 0;i<children.size();i++) 
			output += i + " " + children.get(i) + "\n\n";
		return output;
	}
	
	
	
	
	
	
	private void initChildren() {
		 String[] names = {"Henry","Avery","Tyrone","Isha","Kelly","Melanie","Jack","James","Dennis","Quincy","Josh","Matthew","Linda","Lindy","Marissa","Chelsea","Catherine","Zack","Yusef","Yaazan","Al","Allen","Quinton","Hash","Jun","Jing","Lee","Peter","Jacob","Anthony","Luna","Samantha","John","Neil","Alice","Java","Bean","Sue","Billy","Lisa","Elise","Erik","Eric","Austin","Ryan","Christian","Justin","Karl"};
		 String[] lastName = {"Johnson","Krul","Gul","Jones","Hundson","Krause","Haws","Hawkinson","Black","Snape","Vanes","Donners","Hundley","Abbey","Barrot","Jenkins","Hanes","Holmes","Seebs","Emerson","Stein","Marx","Himms","Hughes","Ford","Dimble","Lee","Peters","Peterson","Rickard","Wechsler","Caesar","Moon","Bargs","Biggs","Cruz","Clause","Rickard","Hans","Ritz"};
		 String[] address = {"Midnight","Moonlight","Seeker","Mini","China","Septic","Hectic","Paladino","Search","Dirt","Discovery","Paso Fino","Pandesa","Kneely","Sweet","Merry","Cream","Caroline","Main","Deppy","Rim","Wheel","Bueno","Sunset","Placent","Star","Shiny","Pencil","Arctic","Cyclical","Leather","Slaughter","Juniper","Ash","Moist","Acryllic","Electric","Flappy","Whiskey","Gamer","Erotic","ABC","Pacific"};
		 String[] addMod = {"Blvd","Street","Cv","Rd","Dr","Circle","Lane","Route","Ave"};
		 for(int i = 0;i<6;i++) {
			children.add(new Child(names[(int)(Math.random()*names.length)] + " " + lastName[(int)(Math.random()*lastName.length)],3+(int)(Math.random()*9),100+(int)(Math.random() * 800) + " " + address[(int)(Math.random()*address.length)] + " " + addMod[(int)(Math.random()*addMod.length)]));
		}
		 
		 for(Child x : children) {
				niceList.put(x, new ArrayList<Action>());
				naughtyList.put(x, new ArrayList<Action>());
		}
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
	

	private static void textEffect(JTextPane p,String n,Color c,boolean b){
	    for(int i = 0;i<n.length();i++)
	    {
	      append(p,n.substring(i,i+1),c,20,b);
	      pause(10);
	    }
	}
	
	
	 private static void pause(int t) {
	        try {
	            Thread.sleep(t);
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	    }
	 
	  private void userWait(JTextPane p) throws InterruptedException{
	        inputBox.reset();
	        append(p,"\n'Press Enter to Continue'\n",Color.WHITE,20,false);
	        inputBox.getString();
	        p.setText("");
	    }
	
	private void init() {
		
			frame = new JFrame(TITLE);
			FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 0, 0);
			layout.setVgap(0);
	        frame.setLayout(layout);
	        frame.setResizable(false);
		 
	        ImageIcon icopic = new ImageIcon("santa/pics/tree.png");
	  	  	frame.setIconImage(icopic.getImage());
			
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setBackground(SANTA_RED);
		

			stops = new PriorityQueue<Child>();
			presents = new HashTable<Present>();
			children = new ArrayList<Child>();
			niceList = new HashMap<Child,ArrayList<Action>>();
			naughtyList = new HashMap<Child,ArrayList<Action>>();
		
			textPanel = new JPanel(true);
			leftPanel = new JPanel(true);
			text = new JTextPane();
		 
		 	
			leftPanel.setPreferredSize(new Dimension(600,600));
			leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
			
			JPanel picPanel = new JPanel();
			picPanel.setBackground(SANTA_RED);
			picPanel.setPreferredSize(new Dimension(600,500));
			
			JLabel pic = new JLabel(new ImageIcon("santa/pics/Bonsall0.png"));
			picPanel.add(pic);
			
			
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
	        text.setFocusable(false);
	        
	        
	        input = new JTextField();
	        input.addActionListener(inputBox);
	        input.setBorder(null);
	        input.setPreferredSize(new Dimension(600,25));
	        input.setBackground(SANTA_RED);
	        input.setForeground(Color.WHITE);
	        input.setFont(new Font("Monospaced", Font.PLAIN, 20));
	        input.grabFocus();

	        leftPanel.add(picPanel);
	        leftPanel.add(inputPanel);
	        
	        frame.add(leftPanel);
	        frame.add(textPanel);
	        
	       
	        

	        label = new JLabel();
	        label.setText(">>");
	        label.setBackground(SANTA_RED);
	        label.setForeground(Color.WHITE);
	        label.setOpaque(true);
	   
	        label.setPreferredSize(new Dimension(35,25));
	        label.setMinimumSize(new Dimension(35, 25));
	        label.setMaximumSize(new Dimension(35, 25));
	        label.setFont(new Font("Serif", Font.PLAIN, 25));
	        
	        textPanel.add(text);
	        inputPanel.add(label);
	        inputPanel.add(input);

	        input.grabFocus();
	        frame.pack();
	        frame.setVisible(true);
	}
}
