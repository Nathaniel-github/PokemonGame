import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class HealthBar extends JPanel{

	int startOfRect = 1;
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
		if (health > 0) {
			g.fillRoundRect(startOfRect, this.getHeight()/2 - 5, (int)((double)health/total * (this.getWidth() - 1)), 10, 10, 10);
		}
		g.setColor(Color.BLACK);
		g.drawRoundRect(startOfRect, this.getHeight()/2 - 5, this.getWidth() - 1, 10, 10, 10);
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
