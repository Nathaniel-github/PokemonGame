import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;

public class TextInterface{

	JTextField myTextField;
	GraphicsPanel myPanel;
	Object sync = new Object();
	AbstractAction action = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			synchronized (sync) {
				sync.notify();
			}
		}
	};
	boolean hasNextInt = false;
	
	public TextInterface(JTextField getThisText) {
		myTextField = getThisText;
	}
	
	public String next() {
		synchronized (sync) {
			try {
				sync.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String answer = myTextField.getText();
		myPanel.writeToScreen(answer + "\n");
		myTextField.setText("");
		return answer;
	}
	
	public String nextLine() {
		synchronized (sync) {
			try {
				sync.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String answer = myTextField.getText();
		
		myPanel.writeToScreen(answer + "\n");
		
		myTextField.setText("");
		
		return answer;
	}
	
	public String getBoxText() {
		return myTextField.getText();
	}
	
	public int nextInt() {
		
		if (!hasNextInt) {
			synchronized (sync) {
				try {
					sync.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		hasNextInt = false;
		
		String answer = myTextField.getText();
		
		myPanel.writeToScreen(answer + "\n");
		myTextField.setText("");
		
		return Integer.parseInt(answer);
	}
	
	public boolean hasNextInt() {
		
		synchronized (sync) {
			try {
				sync.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		hasNextInt = true;
		
		boolean answer = true;
		
		try {
			Integer.parseInt(myTextField.getText());
		} catch(Exception e ) {
			answer = false;
		}
		
		return answer;
	}
	
	public void setPanel(GraphicsPanel thePanel) {
		myPanel = thePanel;
	}

}
