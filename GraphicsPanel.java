import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class GraphicsPanel extends JFrame{

	JPanel buttonPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel thing = new JPanel();
	JPanel text = new JPanel();
	JPanel bottomPanel = new JPanel();
	JTextArea other = new JTextArea();
	JTextField response = new JTextField();
	TextInterface theText;
	Icon allIcons[][];

	public GraphicsPanel(String name){
		super(name);

		Container c = getContentPane();

		mainPanel.setLayout(new GridLayout());
		bottomPanel.setLayout(new GridLayout());
		
		thing.setBorder(new LineBorder(Color.BLACK, 3));
		thing.setLayout(new GridLayout(0, 2));
		
		
	    
//		thing.add(new JLabel("UNDER DEV."));
		
		mainPanel.add(thing);
		
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
		
		
		other.setWrapStyleWord(true);
		other.setLineWrap(true);

		other.setEditable(false);
		other.setBackground(Color.LIGHT_GRAY);
		JScrollPane otherPane = new JScrollPane(other, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		otherPane.setBorder(new LineBorder(Color.BLACK, 3));
		
		mainPanel.add(otherPane);
		
		c.add(mainPanel, BorderLayout.CENTER);
		
		c.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void writeToScreen(String writing) {
		String current = other.getText();
		other.setText(current + writing);
	}
	
	public void updateAll() {
		buttonPanel.updateUI();
		mainPanel.updateUI();
		thing.updateUI();
		text.updateUI();
		bottomPanel.updateUI();
		
	}
	
	public void setTextInterface(TextInterface text) {
		theText = text;
		response.addActionListener(theText.action);
	}
	
	public void drawMons(String name1, String name2) {
		
		thing.removeAll();
		
		ImageIcon secImage = new ImageIcon(this.getClass().getResource("SpritesFront/" + name2 + ".gif"));
		secImage = new ImageIcon(secImage.getImage().getScaledInstance((int)(secImage.getIconWidth() * 1.5), (int)(secImage.getIconHeight() * 1.5), Image.SCALE_DEFAULT));
		Icon icon = secImage;
		JLabel label = new JLabel(icon);
		
		ImageIcon firstImage = new ImageIcon(this.getClass().getResource("SpritesBack/" + name1 + "-back.gif"));
		firstImage = new ImageIcon(firstImage.getImage().getScaledInstance((int)(firstImage.getIconWidth() * 1.5), (int)(firstImage.getIconHeight() * 1.5), Image.SCALE_DEFAULT));
		Icon icon2 = firstImage;
		JLabel label2 = new JLabel(icon2);
		
		fillPortions(1);
		thing.add(label);
		thing.add(label2);
		fillPortions(1);
		
		updateAll();
	}
	
	public void fillPortions(int x) {
		JLabel [] labels = new JLabel[x];
		for (int i = 0; i < x; i ++) {
			labels[i] = new JLabel();
			thing.add(labels[i]);
		}
	}

}
