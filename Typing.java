import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;

public class Typing implements ActionListener {
	
	int wait = 4;
	Timer clock = new Timer(wait, this);
	Queue <String> currentMessage = new LinkedList <String>();
	int index = 0;
	String letters[] = {};
	int currentPosition = 0;
	GraphicsPanel myPanel;
	int numOfMessage = 0;
	int numOn = 0;
	
	public void setWaitTime(int time) { //Useless method
		wait = time;
	}
	
	public void setMedWaitTime() { //Useless method
		wait = 50;
	}
	
	public void setLongWaitTime() { //Useless method
		wait = 80;
	}
	
	public void setShortWaitTime() { //Useless method
		wait = 30;
	}
	
	public void typeMessage(String message) { //Types a message with a cool effect (a pause in between characters printed)
		currentMessage.add(message);
		clock.start();
	}
	
	public void setPanel(GraphicsPanel thePanel) {
		myPanel = thePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		letters = currentMessage.peek().split("");
		if (currentPosition < letters.length) {
			myPanel.writeToScreen(letters[currentPosition]);
			currentPosition++;
		}
		else {
			myPanel.writeToScreen("\n");
			currentPosition = 0;
			currentMessage.remove();
		}
		} catch(NullPointerException e1) {
			clock.stop();
		}
	}
	
}
