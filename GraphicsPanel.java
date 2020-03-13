import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class GraphicsPanel extends JFrame{

	JPanel buttonPanel = new JPanel();
	int startOfRect = 1;
	int p1MonNum = -1;
	int waitTime = 20;
	int p2MonNum = -1;
	JPanel mainPanel = new JPanel();
	JPanel animationPanel = new JPanel();
	JPanel text = new JPanel();
	HealthBar healthBar1 = new HealthBar();
	HealthBar healthBar2 = new HealthBar();
	JPanel bottomPanel = new JPanel();
	JTextArea textArea = new JTextArea();
	JTextField response = new JTextField();
	int health1 = 100;
	int total1 = 100;
	int health2 = 100;
	int total2 = 100;
	TextInterface theText;
	Icon allIcons[][];
	JLabel p1Gif;
	JLabel p2Gif;

	public GraphicsPanel(String name){
		super(name);

		Container c = getContentPane();

		mainPanel.setLayout(new GridLayout());
		bottomPanel.setLayout(new GridLayout());
		
		animationPanel.setBorder(new LineBorder(Color.BLACK, 3));
		animationPanel.setLayout(new GridLayout(0, 2));
		
		mainPanel.add(animationPanel);
		
		buttonPanel.setBorder(new LineBorder(Color.BLACK, 3));
		buttonPanel.setLayout(new FlowLayout(5));
		buttonPanel.setBackground(Color.GREEN);
		buttonPanel.add(new JLabel("                 "));
		buttonPanel.add(new JButton("Testing"));
		buttonPanel.add(new JButton("Testing"));
		buttonPanel.add(new JButton("Testing"));
		buttonPanel.add(new JButton("Testing"));
		
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
	
	public void writeToScreen(String writing) {
		String current = textArea.getText();
		textArea.setText(current + writing);
	}
	
	public void updateAll() {
		buttonPanel.updateUI();
		mainPanel.updateUI();
		animationPanel.updateUI();
		text.updateUI();
		bottomPanel.updateUI();
		
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
	
	public void redoHealthPanel () {
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

}
