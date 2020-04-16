import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class Main {
	
	public static void main(String[] args){
		CheckInput myobj = new CheckInput(); //My version of the Scanner class which is much better as it doesn't return errors
		Typing myPrinter = new Typing(); //Class that prints messages with a cool effect
		Translator myTranslator = new Translator(); //Class that holds most data, most arrays are stored there and are accessed through its methods and get returns of integers which are a "translation" of the input
		PokemonInfo info = new PokemonInfo(); //Holds all current information such as names of selected pokemon, stats, and other values
		PokemonMath calculator = new PokemonMath(); //Does the math calculations for battle such as attacking and type advantages
		ReadWrite kleb = new ReadWrite("Stats.txt");
		Downloader myDownloader = new Downloader();
		
		GraphicsPanel window = new GraphicsPanel("Pokemon");
		window.setBounds(0, 0, 1440, 830);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		myPrinter.setPanel(window);
		myobj.setTyper(myPrinter);
		window.setTextInterface(myobj.getTextInterface());
		
		int [][] allDexNums = new int[3][6];
		int [][] allMonHP = new int [3][6];
		int [][] allMonAttack = new int [3][6];
		int [][] allMonDefense = new int [3][6];
		int [][] allMonSPAttack = new int [3][6];
		int [][] allMonSPDefense = new int [3][6];
		int [][] allMonSpeed = new int [3][6];
		boolean first = true;
		String [] monOut = new String[3];
		int [] monOutNum = new int[3];
		boolean battling = true;
		int otherP = 0;
		String allNames [] = kleb.getAllNames();
		
		String [][][] allInfo = new String[3][22][6];
		
		int option [] = new int[3];
		int currentDamage [] = new int[3];
		String names [][] = new String[3][6];
		int moveSelected [] = new int[3];
		
		String segments [] = { "dexNumber", "Name", "type1", "type2", "HP", "Attack", "Defense", "specialAttack", "specialDefense", "Speed", "move1", "movetype1", "basepower1", "move2", "movetype2", "basepower2", "move3", "movetype3", "basepower3", "move4", "movetype4", "basepower4" };
		//Arrays that hold temporary values
		
		
		for (int k = 1; k <= 2; k++) { 
			for (int i = 0; i < 6; i++) {
				boolean invalid = true;
				int dex = 0;
				int dexn = 0;
				while (invalid) { //Tests to make sure the user inputs a dex number that is available and won't exit otherwise
					if (k == 1 && first) {
						myPrinter.typeMessage("Player 1: ");
						myPrinter.typeMessage("Enter the national dex number of the pokemon you want (Generations 1-7 accepted):");
						first = false;
					}
					else if (k == 2 && first){
						myPrinter.typeMessage("Player 2: ");
						myPrinter.typeMessage("Enter the national dex number of the pokemon you want (Generations 1-7 accepted):");
						first = false;
					}
					
					dex = myobj.checkIntRange((i + 1) + ": ", 1, 802);
					dexn = myTranslator.getIntForDexNum(dex);
					int validMon = info.checkValidMon(dexn);
					
					if (validMon == -1) {
						myPrinter.typeMessage("That Pokemon does not have any attacking moves and this program does not currently handle status moves.\nPlease choose another Pokemon.");
						invalid = true;
						continue;
					}
					if (dexn == -1) { // -1 is the return value if the input value is not an accepted value
						myPrinter.typeMessage("That was not a valid dex number");
					} else {
						invalid = false;
					}
					for (int element : allDexNums[k]) {
						if (element == dex) {
							myPrinter.typeMessage("That pokemon was already chosen by you");
							invalid = true;
						}
					}
					if (!invalid) {
						myPrinter.typeMessage("You chose: " + info.getInfo(segments[1], dex) + "!");
					}
				}
				allDexNums[k][i] = dex;
			}
			first = true;
		}
		for (int l = 1; l <= 2; l++) { //Stores all the stats from the pokemon that were selected based on dex number
			for (int k = 0; k < segments.length; k++) {
				for (int i = 0; i < 6; i++) {
					allInfo[l][k][i] = info.getInfo(segments[k], allDexNums[l][i]);
				}
			}
		}
		info.storeInfo(allInfo); //Passes the info into the other class so methods from said class can access values from it at ease
		allMonHP = info.getAllHP();
		allMonAttack = info.getAllAttack();
		allMonDefense = info.getAllDefense();
		allMonSPAttack = info.getAllSPAttack();
		allMonSPDefense = info.getAllSPDefense();
		allMonSpeed = info.getAllSpeed();
		
//		int [][][] allMonStats = {allMonHP, allMonAttack, allMonDefense, allMonSPAttack, allMonSPDefense, allMonSpeed};
//		String [] stats = {"HP", "Attack", "Defense", "SPAttack", "SPDefense", "Speed"};
//		
//		for (int i = 1; i <= 2; i ++) {
//			for (int k = 0; k < allMonStats.length; k++) {
//				for (int l = 0; l < stats.length; l ++) {
//					for (int n = 0; n < 6; n ++) {
//						allMonStats[k][i][n] = calculator.calcBaseStat(allMonStats[k][i][n], stats[l]);
//					}
//				}
//			}
//		}
		
		final int [][] startingMonHP = info.getAllHP();
				
		for (int i = 1; i <= 2; i++) {
			myPrinter.typeMessage("Player " + i + " which pokemon would you like to send out?");
			names[i] = info.getAllNames(i);
			for (int k = 0; k < 6; k++) {
				myPrinter.typeMessage((k + 1) + ") " + names[i][k]);
			}
			monOutNum[i] = (myobj.checkIntRange("", 1, 6) - 1);
			monOut[i] = names[i][monOutNum[i]];
		}
		
		window.drawMons(monOut[1], monOut[2]);
		
		for (int i = 1; i <= 2; i++) {
			myPrinter.typeMessage("Player " + i + " sent out " + monOut[i] + "!");
		}
		while (battling) {
			for (int i = 1; i <= 2; i++) {
				myPrinter.typeMessage("Player " + i + " what would you like to do?");
				myPrinter.typeMessage("1) Fight");
				myPrinter.typeMessage("2) Switch Out");
				option[i] = myobj.checkIntRangeShort(1, 2);
				if (option[i] == 1) {
					boolean valid = false;
					while (!valid) {
						valid = true;
						myPrinter.typeMessage("Player " + i + " please select a move: ");
						for (int k = 0; k < 4; k++) {
							myPrinter.typeMessage((k + 1) + ") " + info.getAllMoves(i, monOutNum[i])[k]);
						}
						int [] naMoves = info.getAllNAMoves(i, monOutNum[i]);
						moveSelected[i] = myobj.checkIntRange("", 1, 4);
						for (int element : naMoves) {
							if (element == moveSelected[i]) {
								valid = false;
								myPrinter.typeMessage("The move selected does not exist, please choose again.");
							}
						}
					}
				}
				if (option[i] == 2) {
					myPrinter.typeMessage("Player " + i + " which pokemon would you like to send out instead?");
					boolean valid = false;
					while (!valid) {
						for (int k = 0; k < 6; k++) {
							String fainted = "";
							if (allMonHP[i][k] <= 0) {
								fainted = " ***";
							}
							myPrinter.typeMessage((k + 1) + ") " + names[i][k] + fainted);
						}
						monOutNum[i] = (myobj.checkIntRange("", 1, 6) - 1);
						if (allMonHP[i][monOutNum[i]] > 0) {
							valid = true;
						} else {
							myPrinter.typeMessage("That pokemon has fainted please select another one");
						}
					}
					monOut[i] = names[i][monOutNum[i]];
					window.drawMons(monOut[1], monOut[2]);
					myPrinter.typeMessage("Player " + i + " sent out " + monOut[i] + "!");
					window.refreshHealthBar(allMonHP[i][monOutNum[i]], startingMonHP[i][monOutNum[i]], i, monOutNum[i]);
				}
			}
			boolean f = true;
			for (int i = 1; i <= 2; i++) {
				int mover = info.firstMove(monOutNum);
				if (mover == 1) {
					otherP = 2;
				}
				else {
					otherP = 1;
				}
				if (f) {	
					f = false;
				}
				else {
					int temp = otherP;
					otherP = mover;
					mover = temp;
				}
				if (option[mover] == 1) {
					currentDamage[mover] = info.getDamageForMove(mover, moveSelected[mover], monOutNum[mover]);
					if (currentDamage[mover] >= 0) {
						boolean stab = info.checkStab(monOutNum[mover], moveSelected[mover], mover);
						String aT = info.getTypeForMove(moveSelected[mover], mover, monOutNum[mover]);
						String dT1 = info.getDefendType1(monOutNum[otherP], mover);
						String dT2 = info.getDefendType2(monOutNum[otherP], mover);
						double mod = calculator.calculateTypeAdvantage(aT, dT1, dT2);
						double damage = calculator.calculateDamage(allMonAttack[mover][monOutNum[mover]], allMonDefense[mover][monOutNum[otherP]], currentDamage[mover], mod, stab, 100);
						myPrinter.typeMessage(monOut[mover] + " used " + info.getNameForMove(moveSelected[mover], mover, monOutNum[mover]) + "!");
						if (ThreadLocalRandom.current().nextInt(0, 24) == 0) {
							damage *= 2;
							myPrinter.typeMessage("A critical hit!!");
						}
						allMonHP[otherP][monOutNum[otherP]] -= damage;
						if (allMonHP[otherP][monOutNum[otherP]] <= 0) {
							allMonHP[otherP][monOutNum[otherP]] = 0;
						}
						window.refreshHealthBar(allMonHP[otherP][monOutNum[otherP]], startingMonHP[otherP][monOutNum[otherP]], otherP, monOutNum[otherP]);
						if (allMonHP[otherP][monOutNum[otherP]] <= 0) {
							myPrinter.typeMessage(names[otherP][monOutNum[otherP]] + " has fainted");
							break;
						}
						else {
							myPrinter.typeMessage(names[otherP][monOutNum[otherP]] + " has " + allMonHP[otherP][monOutNum[otherP]] + " HP left");
						}
					}
					else {
						double healing = info.calculateHealing(mover, monOutNum[mover], currentDamage[mover]);
						myPrinter.typeMessage(monOut[mover] + " used " + info.getNameForMove(moveSelected[mover], mover, monOutNum[mover]) + "!");
						allMonHP[mover][monOutNum[mover]] += healing;
						if (startingMonHP[mover][monOutNum[mover]] < allMonHP[mover][monOutNum[mover]]) {
							allMonHP[mover][monOutNum[mover]] = startingMonHP[mover][monOutNum[mover]];
						}
						window.refreshHealthBar(allMonHP[mover][monOutNum[mover]], startingMonHP[mover][monOutNum[mover]], mover, monOutNum[mover]);
						myPrinter.typeMessage(names[mover][monOutNum[mover]] + " has " + allMonHP[mover][monOutNum[mover]] + " HP left");
					}
				} 
			}
			for(int i = 1; i <= 2; i++) {
				if (allMonHP[i][monOutNum[i]] <= 0) {
					boolean valid = false;
					if (!info.checkWinner(allMonHP, i)) {
						break;
					}
					while (!valid) {
						myPrinter.typeMessage("Player " + i + " which pokemon would you like to send out instead?");
						for (int k = 0; k < 6; k++) {
							String fainted = "";
							if (allMonHP[i][k] <= 0) {
								fainted = " ***";
							}
							myPrinter.typeMessage((k + 1) + ") " + names[i][k] + fainted);
						}
						monOutNum[i] = (myobj.checkIntRange("", 1, 6) - 1);
						if (allMonHP[i][monOutNum[i]] > 0) {
							valid = true;
						}
						else {
							myPrinter.typeMessage("That pokemon has fainted please select another one");
						}
					}
					monOut[i] = names[i][monOutNum[i]];
					window.drawMons(monOut[1], monOut[2]);
					myPrinter.typeMessage("Player " + i + " sent out " + monOut[i] + "!");
					window.refreshHealthBar(allMonHP[i][monOutNum[i]], startingMonHP[i][monOutNum[i]], i, monOutNum[i]);
				}
			}
			for(int i = 1; i <= 2; i++) {
				if (i == 1) {
					otherP = 2;
				}
				else {
					otherP = 1;
				}
				battling = info.checkWinner(allMonHP, i);
				if (!battling) {
					myPrinter.typeMessage("Player " + otherP + " has won !!");
					break;
				}
			}
		}
//		for (int i = 1; i <= 2; i++) {
//			if (option[i] == 1) {
//				myPrinter.typeMessage("Player " + i + " please select a move: ");
//				for (int k = 0; k < 4; k++) {
//					myPrinter.typeMessage((k + 1) + ") " + info.getAllMoves(i, monOutDex[i])[k]);
//				}
//				moveSelected[i] = myobj.checkIntRange("", 1, 4);
//			}
//			if (option[i] == 2) {
//				myPrinter.typeMessage("Player " + i + " which pokemon would you like to send out instead?");
//				for (int k = 0; k < 6; k++) {
//					myPrinter.typeMessage((k + 1) + ") " + names[k]);
//				}
//				monOutDex[i] = (myobj.checkIntRange("", 1, 6) - 1);
//				monOut[i] = names[monOutDex[i]];
//			}
//		}
		
		
//		String str = "";
//		do {
//		boolean invalid = true;
//		int dex = 0;
//		while (invalid) {
//			dex = myobj.checkIntRange("Enter the dex number of the pokemon you want (Only fully evolved pokemon from gen 1 are accepted): ", 1, 890);
//			int dexn = myTranslator.getIntForDexNum(dex);
//			if (dexn == -1) { // -1 is the return value if the input value is not an accepted value
//				myPrinter.typeMessage("That was not a valid dex number");
//			} else {
//				invalid = false;
//			}
//		}
//		
//		String segment = myobj.getMessage("Enter the stat you want: ", "dexNumber Name type1 type2 HP Attack Defense specialAttack specialDefense Speed move1 movetype1 basepower1 move2 movetype2 basepower2 move3 movetype3 basepower3 move4 movetype4 basepower4");
//		String x = info.getInfo(segment, dex);
//		myPrinter.typeMessage(x);
//		str = myobj.getString("Enter 'quit' to quit and anything else to continue: ");
//		}while (!str.equalsIgnoreCase("quit"));
		
		
//		String aType = myobj.getMessage("Enter the attack type:",
//				"Normal Fighting Flying Poison Ground Rock Bug Ghost Steel Fire Water Grass Electric Psychic Ice Dragon Dark Fairy");
//		String dType1 = myobj.getMessage("Enter defending type #1:",
//				"Normal Fighting Flying Poison Ground Rock Bug Ghost Steel Fire Water Grass Electric Psychic Ice Dragon Dark Fairy");
//		String dType2 = myobj.getMessage("Enter defending type #2 (type NA if the pokemon is monotype):",
//				"Normal Fighting Flying Poison Ground Rock Bug Ghost Steel Fire Water Grass Electric Psychic Ice Dragon Dark Fairy NA");
//		double x = calculator.calculateTypeAdvantage(aType, dType1, dType2);
//		myPrinter.typeMessage(x);
	}

}
