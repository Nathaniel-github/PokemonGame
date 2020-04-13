import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;

public class TextInterface {

	JTextField myTextField;
	boolean moveOn = false;
	GraphicsPanel myPanel;
	Action action = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        moveOn = true;
	    }
	};
	
	public TextInterface(JTextField getThisText) {
		myTextField = getThisText;
	}
	
	public String next() {
		while(!moveOn) {
			moveOn = getMoveOn();
		}
		String answer = myTextField.getText();
		myPanel.writeToScreen(answer + "\n");
		myTextField.setText("");
		moveOn = false;
		return answer;
	}
	
	public String nextLine() {
		while(!moveOn) {
			moveOn = getMoveOn();
		}
		
		moveOn = false;
		
		String answer = myTextField.getText();
		
		myPanel.writeToScreen(answer + "\n");
		
		myTextField.setText("");
		
		return answer;
	}
	
	public String getBoxText() {
		return myTextField.getText();
	}
	
	public int nextInt() {
		while(!moveOn) {
			moveOn = getMoveOn();
		}
		
		moveOn = false;

		String answer = myTextField.getText();
		
		myPanel.writeToScreen(answer + "\n");
		myTextField.setText("");
		
		return Integer.parseInt(answer);
	}
	
	public boolean hasNextInt() {
		while(!moveOn) {
			moveOn = getMoveOn();
		}
		
		boolean answer = true;
		
		try {
			Integer.parseInt(myTextField.getText());
		} catch(Exception e ) {
			answer = false;
		}
		
		return answer;
	}
	
	public boolean getMoveOn() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return moveOn;
	}
	
	public void setPanel(GraphicsPanel thePanel) {
		myPanel = thePanel;
	}
	
}
