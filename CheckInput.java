/*
 Author: Nathaniel Thomas
 Date: 10/27/19
 Rev: 01
 */

import java.util.*;


public class CheckInput {
	TextInterface myobj;
	Typing printer;
	public CheckInput() {
		printer = new Typing();
		
	}
	
	public int getInt(String message) { //Method that checks to make sure that the input is an integer
		int output = 0;
		boolean check = false;
		printer.typeMessage(message);
		while (!check) { //Checks to make sure that the input is and integer and won't continue unless it is
			if (myobj.hasNextInt()) {
				output = myobj.nextInt();
				check = true;
			}
			else {
				printer.typeMessage("That wasn't a valid input");
				printer.typeMessage(message);
				printer.myPanel.writeToScreen(myobj.getBoxText());
				myobj.moveOn = false;
			}
		}
		return output;
	}
	
	public int checkIntRange(String message, int lowPar, int highPar) { //Method that checks to make sure that the input is an integer in a certain range
		int output = 0;
		boolean check = false;
		printer.typeMessage(message);
		while (!check) { //Checks to make sure that the input is in a certain range and won't continue unless it is
			if (myobj.hasNextInt()) {
				output = myobj.nextInt();
				if (output >= lowPar && output <= highPar) {
					check = true;
				}
				else {
					printer.typeMessage("That wasn't a valid input");
					printer.typeMessage(message);
				}
			}
			else {
				printer.typeMessage("That wasn't a valid input");
				printer.typeMessage(message);
				printer.myPanel.writeToScreen(myobj.getBoxText());
				myobj.moveOn = false;
				myobj.myTextField.setText("");
			}
		}
		return output;
	}
	
	public int checkIntRangeShort(int lowPar, int highPar) { //Method that checks to make sure that the input is an integer in a certain range but doesn't type any message
		int output = 0;
		boolean check = false;
		while (!check) { //Checks to make sure that the input is in a certain range and won't continue unless it is
			if (myobj.hasNextInt()) {
				output = myobj.nextInt();
				if (output >= lowPar && output <= highPar) {
					check = true;
				}
				else {
					printer.typeMessage("That wasn't a valid input");
					
				}
			}
			else {
				printer.typeMessage("That wasn't a valid input");
				
			}
		}
		return output;
	}
	
	public int getPosInt(String message) { //Method that checks to make sure that the input is a positive integer
		int output = 0;
		boolean check = false;
		printer.typeMessage(message);
		while (!check) { //Checks to make sure that the input is in a certain range and won't continue unless it is
			if (myobj.hasNextInt()) {
				output = myobj.nextInt();
				if (output >= 0) {
					check = true;
				}
				else {
					printer.typeMessage("That wasn't a valid input");
					printer.typeMessage(message);
					
				}
			}
			else {
				printer.typeMessage("That wasn't a valid input");
				printer.typeMessage(message);
				
			}
		}
		return output;
	}
	
	public boolean checkInt(String input) { //Method that checks to make sure that the input is an integer
		try {
			Integer.parseInt(input);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	public String getMessage(String message, String desiredOutput) {
		String output = "";
		String checker;
		String [] parts = desiredOutput.split(" ");
		boolean check = false;
		printer.typeMessage(message);
		while (!check) { //Checks to make sure that the input is a String and won't continue to the rest of the code unless it is
			checker = myobj.next();
			for (String element : parts) { //Checks the user input against all the possible outputs the code wanted
	            if (element.equalsIgnoreCase(checker)) { 
	                check = true; 
	                output = checker;
	                break; 
	            } 
	        }
			if (!check) {
				printer.typeMessage("That wasn't a valid input");
				printer.typeMessage(message);
			}
		}
		return output;
	}
	
	public String getYesNo(String message) {
		String output = "";
		String checker;
		boolean check = false;
		printer.typeMessage(message);
		while (!check) { //Checks to make sure that the input is a String and won't continue to the rest of the code unless it is
			checker = myobj.next();
			if (checker.equals("y") || checker.equals("yes") || checker.equals("Yes") || checker.equals("n") || checker.equals("no") || checker.equals("No")) {
				check = true;
				output = checker;
			}
			else {
				printer.typeMessage("That wasn't a valid input");
				printer.typeMessage(message);
			}
		}
		return output;
	}
	
	public boolean checkMessage(String userInput, String desiredOutputs) {
		boolean output = false;
		String [] parts = desiredOutputs.split(" ");
		for (String element : parts) { //Checks the user input against all the possible outputs the code wanted
            if (element.equals(userInput)) { 
                output = true;
                break; 
            } 
        }
		return output;
	}
	
	public String getString(String message) {
		String output = "";
		String checker = "";
		boolean check = false;
		boolean containsDigit = false;
		printer.typeMessage(message);
		while (!check) { //Checks to make sure that the input is a String and won't continue to the rest of the code unless it is
			checker = myobj.next();
			containsDigit = false;
			for (char c : checker.toCharArray()) {
				if (Character.isDigit(c)) {
					containsDigit = true;
				}
			}
			if (containsDigit) {
				printer.typeMessage("That wasn't a valid input");
				printer.typeMessage(message);
			}
			else {
				check = true;
				output = checker;
			}
		}
		
		return output;
	}
	
	public String next(String message) {
		printer.typeMessage(message);
		String output = myobj.next();
		return output;
	}
	
	public void setTyper (Typing type) {
		printer = type;
		myobj = new TextInterface (printer.myPanel.response);
		myobj.setPanel(printer.myPanel);
	}
	
	public TextInterface getTextInterface() {
		return myobj;
	}
}
