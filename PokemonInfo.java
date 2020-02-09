
public class PokemonInfo {
	Translator myTranslator = new Translator();
	ReadWrite kleb = new ReadWrite("PokemonStats.txt");
	String [][][] allPlayerInfo = {};
	//Fields
	
	public String getInfo(String segment, int dex) { //Takes the user interface inputs and uses methods from translator class to turn them into numbers that can interact with the methods in the code and in the end returns the stat requested
		String answer = "";
		int ndex = myTranslator.getIntForDexNum(dex);
		int num = myTranslator.getIntForSegment(segment);
		answer = kleb.getPokemonInfo(num, ndex);
		return answer;
	}
	
	public void storeInfo(String [][][] allInfo) { //Takes the info passed into the method and stores locally so it can be used by this class's methods
		allPlayerInfo = allInfo;
	}
	
	public String getPokemonInfo(int segment, int numOfMon, int pNum) { //Uses previous array that was stored and accesses the value requested by the parameters
		String answer = "";
		answer = allPlayerInfo[pNum][segment][numOfMon];
		return answer;
	}
	
	public String [] getAllNames(int pNum) { //Returns all the names of the pokemon on a team based on player number
		String [] answer = new String[6];
		for (int i = 0; i < 6; i++) {
			answer [i] = allPlayerInfo[pNum][1][i];
		}
		return answer;
	}
	
	public String [] getAllMoves(int pNum, int ndex) { //Enter the player number and the number of the pokemon (1-6) based on where it is in the player's set
		String [] answer = new String[4];
		for (int i = 0; i < 4; i++) {
			answer [i] = allPlayerInfo[pNum][10 + (3 * i)][ndex];
		}
		return answer;
	}
	
	public int getDamageForMove(int pNum, int num, int ndex) {
		int answer = 0;
		answer = Integer.parseInt(allPlayerInfo[pNum][9 + (3 * num)][ndex]);
		return answer;
	}
	
	public int [][] getAllHP() {
		int answer [][]  = new int [3][6];
		for (int k = 1; k <= 2; k++) {
			for (int i = 0; i < 6; i++) {
				answer[k][i] = Integer.parseInt(allPlayerInfo[k][4][i]);
			}
		}
		return answer;
	}
	
	public int [][] getAllAttack() {
		int answer [][]  = new int [3][6];
		for (int k = 1; k <= 2; k++) {
			for (int i = 0; i < 6; i++) {
				answer[k][i] = Integer.parseInt(allPlayerInfo[k][5][i]);
			}
		}
		return answer;
	}
	
	public int [][] getAllDefense() {
		int answer [][]  = new int [3][6];
		for (int k = 1; k <= 2; k++) {
			for (int i = 0; i < 6; i++) {
				answer[k][i] = Integer.parseInt(allPlayerInfo[k][6][i]);
			}
		}
		return answer;
	}
	
	public int [][] getAllSPAttack() {
		int answer [][]  = new int [3][6];
		for (int k = 1; k <= 2; k++) {
			for (int i = 0; i < 6; i++) {
				answer[k][i] = Integer.parseInt(allPlayerInfo[k][7][i]);
			}
		}
		return answer;
	}
	
	public int [][] getAllSPDefense() {
		int answer [][]  = new int [3][6];
		for (int k = 1; k <= 2; k++) {
			for (int i = 0; i < 6; i++) {
				answer[k][i] = Integer.parseInt(allPlayerInfo[k][8][i]);
			}
		}
		return answer;
	}
	
	public int [][] getAllSpeed() {
		int answer [][]  = new int [3][6];
		for (int k = 1; k <= 2; k++) {
			for (int i = 0; i < 6; i++) {
				answer[k][i] = Integer.parseInt(allPlayerInfo[k][9][i]);
			}
		}
		return answer;
	}
	
	public boolean checkStab(int monOut, int moveSelected, int pNum) {
		boolean answer = false;
		String moveType = allPlayerInfo[pNum][8 + (moveSelected*3)][monOut];
		String typings [] = new String [2];
		for (int i = 0; i < 2; i++) {
			typings[i] = allPlayerInfo[pNum][i + 2][monOut];
		}
		for (String element : typings) {
			if (element.equalsIgnoreCase(moveType)) {
				answer = true;
			}
		}
		return answer;
	}
	
	public String getTypeForMove(int moveSelected, int pNum, int monOut) {
		String answer = "";
		answer = allPlayerInfo[pNum][8 + (moveSelected*3)][monOut];
		return answer;
	}
	
	public String getDefendType1(int monOut, int pNum) {
		String answer = "";
		answer = allPlayerInfo[pNum][2][monOut];
		return answer;
	}
	
	public String getDefendType2(int monOut, int pNum) {
		String answer = "";
		answer = allPlayerInfo[pNum][3][monOut];
		return answer;
	}
	

	public double calculateHealing(int pNum, int monOut, double healing) {
		double answer = 0;
		int health = Integer.parseInt(allPlayerInfo[pNum][4][monOut]);
		healing /= -100.0;
		answer = health * healing;
		return answer;
	}
	
	public boolean checkWinner(int allHP [][], int pNum) {
		boolean answer = false;
		for(int i = 0; i < 6; i++) {
			if (allHP[pNum][i] != 0) {
				answer = true;
				break;
			}
		}
		return answer;
	}
	
	public int firstMove(int monOut[]) {
		int answer = 2;
		int [] speed = new int [3];
		for(int i = 1; i <= 2; i++) {
			speed [i] = Integer.parseInt(allPlayerInfo[i][9][monOut[i]]);
		}
		if (speed[1] > speed[2]) {
			answer = 1;
		}
		return answer;
	}
}
