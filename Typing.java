import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Typing {
	
	int wait = 2;
	String currentMessage = "";
	int index = 0;
	String letters[] = {};
	
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
		currentMessage = message;
		letters = currentMessage.split("");
		for (int i = 0; i < letters.length; i++) {
			System.out.print(letters[i]);
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
	}
	
}
