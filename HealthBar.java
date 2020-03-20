import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class HealthBar extends JPanel{

	int startOfRectX = 1;
	int lengthOfHealth;
	int startOfRectY;
	int widthOfRect = 10;
	int lengthOfRect;
	int health = 100;
	int total = 100;
	
	
	public void paintComponent(Graphics g) {
		if((double)health/total <= .5) {
			g.setColor(Color.YELLOW);
			 if ((double)health/total <= .25) {
				g.setColor(Color.RED);
			 }
		} else {
			g.setColor(Color.GREEN);
		}
		
		lengthOfRect = this.getWidth() - 1;
		lengthOfHealth = (int)((double)health/total * lengthOfRect);
		startOfRectY = this.getHeight()/2 - 5;
		
		
		if (health > 1) {
			g.fillRoundRect(startOfRectX, startOfRectY, lengthOfHealth, widthOfRect, widthOfRect, widthOfRect);
		}
		g.setColor(Color.BLACK);
		g.drawRoundRect(startOfRectX, startOfRectY, lengthOfRect, widthOfRect, widthOfRect, widthOfRect);
	}
	
	public void setHealth (int myHealth) {
		health = myHealth;
		repaint();
	}
	
	public void setTotal (int myTotal) {
		total = myTotal;
		repaint();
	}
	
}
