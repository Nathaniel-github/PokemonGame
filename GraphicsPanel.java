import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class GraphicsPanel extends JFrame implements ActionListener, CaretListener{

	JPanel buttonPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel animationPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JPanel text = new JPanel();
	
	PokemonLearnsets info = new PokemonLearnsets();
	
	HealthBar healthBar1 = new HealthBar();
	HealthBar healthBar2 = new HealthBar();
	
	JTextArea textArea = new JTextArea();
	JTextField response = new JTextField();
	
	int health1 = 100;
	int total1 = 100;
	int health2 = 100;
	int total2 = 100;
	int startOfRect = 1;
	int p1MonNum = -1;
	int waitTime = 20;
	int p2MonNum = -1;
	
	TextInterface theText;
	
	Icon allIcons[][];
	JLabel p1Gif;
	JLabel p2Gif;
	
	JPanel mainBuilderPanel = new JPanel();
	JPanel builderPanel = new JPanel();
	JPanel builderMessagePanel = new JPanel();
	
	JPanel monPanels [] = new JPanel[6];
	JPanel imagePanels[] = new JPanel[6];
	JPanel namePanels[] = new JPanel[6];
	JPanel movePanels[] = new JPanel[6];
	
	JPanel allMoves[][] = new JPanel[6][4];
	
	JTextField names[] = new JTextField[6];
	
	JTextField moves[][] = new JTextField[6][4];
	
	JButton validate = new JButton("Validate");
	
	JLabel tempImages[] = new JLabel[6];
	JLabel emptyLabels[] = new JLabel[6];
	
	AutoSuggestor [] nameSuggestions = new AutoSuggestor[6];
	
	AutoSuggestor [][] moveSuggestions = new AutoSuggestor[6][4];
	

	public GraphicsPanel(String name){

		super(name);
		
		addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				
				for (int i = 0; i < 6; i ++) {
					
					nameSuggestions[i].hideSuggestionWindow();
					
					for (int k = 0; k < 4; k ++) {
						
						moveSuggestions[i][k].hideSuggestionWindow();
						
					}
					
				}
				
			}
			
		});
		
		setupTeamBuilderPanel();
		
//		setupBattlePanel();
	}
	
	private void setupTeamBuilderPanel() {
	
		Container c = getContentPane();
	
		mainBuilderPanel.setLayout(new BorderLayout());
		mainBuilderPanel.setBorder(new LineBorder(Color.BLACK, 2));
		builderPanel.setLayout(new GridLayout(1, 0));
		builderPanel.setBorder(new LineBorder(Color.BLACK, 1));
		builderMessagePanel.setBorder(new LineBorder(Color.BLACK, 2));
		
		for (int i = 0; i < monPanels.length; i ++) {
			
			monPanels[i] = new JPanel();
			imagePanels[i] = new JPanel();
			namePanels[i] = new JPanel();
			movePanels[i] = new JPanel();
			
			names[i] = new JTextField();
			
			names[i].addCaretListener(this);
			
			emptyLabels[i] = new JLabel();
			tempImages[i] = emptyLabels[i];
			
			monPanels[i].setBorder(new LineBorder(Color.BLACK, 3));
		    imagePanels[i].setBorder(new LineBorder(Color.BLACK, 3));
			movePanels[i].setBorder(new LineBorder(Color.BLACK, 3));
			
			monPanels[i].setLayout(new GridLayout(0 ,1));
			namePanels[i].setLayout(new GridLayout(1, 0));
			movePanels[i].setLayout(new GridLayout(0, 1));
			imagePanels[i].setLayout(new BorderLayout());
			
			namePanels[i].add(new JLabel("      Name:"));
			namePanels[i].add(names[i]);
			imagePanels[i].add(tempImages[i]);
			imagePanels[i].add(namePanels[i], BorderLayout.SOUTH);
			monPanels[i].add(imagePanels[i]);
			
			for (int k = 0; k < moves[i].length; k ++) {
				
				moves[i][k] = new JTextField();
				
				allMoves[i][k] = new JPanel();
				
				allMoves[i][k].setLayout(new GridLayout(0, 1));
				
				allMoves[i][k].add(new JLabel(" Move " + Integer.toString(k + 1) + ":"));
				allMoves[i][k].add(moves[i][k]);
				allMoves[i][k].add(new JLabel());
				allMoves[i][k].add(new JLabel());
				
				movePanels[i].add(allMoves[i][k]);
				
			}
			
			monPanels[i].add(movePanels[i]);
			
			builderPanel.add(monPanels[i]);
			
		}
		
		String welcomeMessage = "";
		
		for (int i = 0; i < 5; i ++) {
			
			welcomeMessage += " ";
			
		}
		
		welcomeMessage += "Welcome to the Teambuilder!";
		
		setupSuggestions();
		
		validate.addActionListener(this);
		
		builderMessagePanel.add(new JLabel(welcomeMessage));
		mainBuilderPanel.add(builderMessagePanel, BorderLayout.NORTH);
		mainBuilderPanel.add(validate, BorderLayout.SOUTH);
		mainBuilderPanel.add(builderPanel);
		
		c.add(mainBuilderPanel);
	}
	
	private void setupBattlePanel() {
		
		Container c = getContentPane();

		mainPanel.setLayout(new GridLayout());
		bottomPanel.setLayout(new GridLayout());
		
		animationPanel.setBorder(new LineBorder(Color.BLACK, 3));
		animationPanel.setLayout(new GridLayout(0, 2));
		
		mainPanel.add(animationPanel);
		
		buttonPanel.setBorder(new LineBorder(Color.BLACK, 3));
		buttonPanel.setLayout(new FlowLayout(5));
		buttonPanel.setBackground(Color.GREEN);
//		buttonPanel.add(new JLabel("                 "));
//		buttonPanel.add(new JButton("Testing"));
//		buttonPanel.add(new JButton("Testing"));
//		buttonPanel.add(new JButton("Testing"));
//		buttonPanel.add(new JButton("Testing"));
		
		bottomPanel.add(buttonPanel);
		
		text.setLayout(new GridLayout());
		text.setBorder(new LineBorder(Color.BLACK, 3));
		text.add(response);
		
		bottomPanel.add(text);
		
		
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);

		textArea.setEditable(false);
		textArea.setBackground(Color.LIGHT_GRAY);
		JScrollPane textAreaPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		textAreaPane.setBorder(new LineBorder(Color.BLACK, 3));
		
		mainPanel.add(textAreaPane);
		
		c.add(mainPanel, BorderLayout.CENTER);
		
		c.add(bottomPanel, BorderLayout.SOUTH);
		
	}
	
	private void setupSuggestions() {

		ArrayList<String> allPossibleMoves = new ArrayList<String>();
		ArrayList<String> allNames = new ArrayList<String>();
		
		Collections.addAll(allPossibleMoves, info.getAllMoves());
		Collections.addAll(allNames, info.getAllNames());
		
		for (int i = 0; i < 6; i ++) {
			
			nameSuggestions[i] = new AutoSuggestor(names[i], allNames, Color.WHITE.brighter(), Color.BLACK, Color.BLACK, 0.75f);
			
			for (int k = 0; k < 4; k ++) {
				
				moveSuggestions[i][k] = new AutoSuggestor(moves[i][k], allPossibleMoves, Color.WHITE.brighter(), Color.BLACK, Color.BLACK, 0.75f);
				
			}
			
		}
		
	}
	
	public void writeToScreen(String writing) {
		String current = textArea.getText();
		textArea.setText(current + writing);
	}
	
	public void updateAll() {
		
		mainPanel.updateUI();
		bottomPanel.updateUI();
		mainBuilderPanel.updateUI();
		
	}
	
	public void setTextInterface(TextInterface text) {
		theText = text;
		response.addActionListener(theText.action);
	}
	
	public void drawMons(String name1, String name2) {
		
		animationPanel.removeAll();
		
		ImageIcon secImage = new ImageIcon(this.getClass().getResource("SpritesFront/" + name2 + ".gif"));
		secImage = new ImageIcon(secImage.getImage().getScaledInstance((int)(secImage.getIconWidth() * 1.5), (int)(secImage.getIconHeight() * 1.5), Image.SCALE_DEFAULT));
		Icon icon = secImage;
		p2Gif = new JLabel(icon);
	
		ImageIcon firstImage = new ImageIcon(this.getClass().getResource("SpritesBack/" + name1 + "-back.gif"));
		firstImage = new ImageIcon(firstImage.getImage().getScaledInstance((int)(firstImage.getIconWidth() * 1.5), (int)(firstImage.getIconHeight() * 1.5), Image.SCALE_DEFAULT));
		Icon icon2 = firstImage;
		p1Gif = new JLabel(icon2);
		
		animationPanel.add(healthBar2);
		animationPanel.add(p2Gif);
		animationPanel.add(p1Gif);
		animationPanel.add(healthBar1);

		updateAll();
	}
	
	public void fillPortions(int x) {
		JLabel [] labels = new JLabel[x];
		for (int i = 0; i < x; i ++) {
			labels[i] = new JLabel();
			animationPanel.add(labels[i]);
		}
	}
	
	public void refreshHealthBar(int health, int total, int pNum, int mon) {
		
		if (pNum == 1) {
			if (p1MonNum != mon && p1MonNum != -1) {
				p1MonNum = mon;
				
				health1 = health;
				total1 = total;
				
				healthBar1.setHealth(health);
				healthBar1.setTotal(total);
				
				redoHealthPanel();
			}
			else {
				p1MonNum = mon;
				healthBar1.setTotal(total);;
				while (health < health1) {
					
					healthBar1.setHealth(health1);
					
					redoHealthPanel();
					
					health1--;
				}
			
				while (health > health1) {
					
					healthBar1.setHealth(health1);
					
					redoHealthPanel();
					
					health1++;
				}
			}
		}
		else {
			if (p2MonNum != mon && p2MonNum != -1) {
				p2MonNum = mon;
				
				health2 = health;
				total2 = total;
				
				healthBar2.setHealth(health);
				healthBar2.setTotal(total);
				
				redoHealthPanel();
				
				updateAll();
			}
			else {
				p2MonNum = mon;
				healthBar2.setTotal(total);
				while (health < health2) {
					
					healthBar2.setHealth(health2);
					
					redoHealthPanel();
					
					health2--;
				}
			
				while (health > health2) {
					
					healthBar2.setHealth(health2);
					
					redoHealthPanel();
					
					health2++;
				}
			}
		}
		
	}
	
	private void redoHealthPanel () {
		animationPanel.removeAll();
		
		animationPanel.add(healthBar2);
		animationPanel.add(p2Gif);
		animationPanel.add(p1Gif);
		animationPanel.add(healthBar1);
		
		updateAll();
		
		try {
			TimeUnit.MILLISECONDS.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private String validatePokemon() {
		
		String answer = "";
		
		String tempMonNames [] = new String[6];

		boolean invalidMove = false;
		
		for (int i = 0; i < monPanels.length; i ++) {
			
			String currentName = names[i].getText();
			
			if (currentName.isEmpty()) {
				
				answer = "You must have 6 Pokemon on your team but you can have less than 4 moves";
				return answer;
				
			}
			
			for (String element : tempMonNames) {
				
				if (element == null) {
					
					continue;
					
				} else if (currentName.equals(element)) {
					
					answer = "You have duplicate Pokemon on your team";
					
					return answer;
					
				}
			}
			
			if (!info.validPokemon(currentName)) {
				
				answer += "Pokemon #" + (i + 1) + " is invalid\n";
				
			} else {
				
				tempMonNames[i] = currentName;
				
			}

			int count = 0;
			
			for (int k = 0; k < moves[i].length; k++) {
				
				if (moves[i][k].getText().isEmpty()) {
					
					count++;
					
				}
				
				if (!info.validMove(moves[i][k].getText().replace(" ", "").toLowerCase(), currentName)) {
					
					answer += "Pokemon #" + (i + 1) + ", move #" + (k + 1) + " is invalid\n";
					invalidMove = true;
					
				}
				
				if (count >= 4) {
					
					answer += "Pokemon #" + (i + 1) + " has no moves\n";
					break;
					
				}
				
			}
			
		}
		
		if (invalidMove) {
			
			answer += "***Please keep in mind only damaging moves without recoil are allowed, if there aren't enough for 4 moves, leave fields blank***";
			
		}
		
		return answer;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (validatePokemon().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Confirmed" , "", JOptionPane.INFORMATION_MESSAGE);
			
		} else {
			
			JOptionPane.showMessageDialog(null, validatePokemon() , "", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	
	@Override
	public void caretUpdate(CaretEvent e) {
		
		for (int i = 0; i < names.length; i ++) {
		
			if (info.validPokemon(names[i].getText()) && tempImages[i].getParent() == null) {
				
				ImageIcon temp = new ImageIcon(this.getClass().getResource("SpritesFront/" + names[i].getText().replace(" ", "").replace(":", "").replace("'", "").replace(".", "").replace("-", "").toLowerCase() + ".gif"));
				temp = new ImageIcon(temp.getImage().getScaledInstance((int)(temp.getIconWidth() * 1.5), (int)(temp.getIconHeight() * 1.5), Image.SCALE_DEFAULT));
				Icon icon = temp;
				
				imagePanels[i].remove(tempImages[i]);
				
				tempImages[i] = new JLabel(icon);
				
				imagePanels[i].add(tempImages[i]);
				
				updateAll();
				
			} else if (!info.validPokemon(names[i].getText())){
				
				imagePanels[i].remove(tempImages[i]);
				
				updateAll();
				
			}
			
		}
	}

}
