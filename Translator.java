
public class Translator {
	String typings[] = { "Normal", "Fighting", "Flying", "Poison", "Ground", "Rock", "Bug", "Ghost",
	"Steel", "Fire", "Water", "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark", "Fairy" };
	double normal[] = { 1, 1, 1, 1, 1, .5, 1, 0, .5, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	double fighting[] = { 2, 1, .5, .5, 1, 2, .5, 0, 2, 1, 1, 1, 1, .5, 2, 1, 2, .5 };
	double flying[] = { 1, 2, 1, 1, 1, .5, 2, 1, .5, 1, 1, 2, .5, 1, 1, 1, 1, 1 };
	double poison[] = { 1, 1, 1, .5, .5, .5, 1, .5, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2 };
	double ground[] = { 1, 1, 0, 2, 1, 2, .5, 1, 2, 2, 1, .5, 2, 1, 1, 1, 1, 1 };
	double rock[] = { 1, .5, 2, 1, .5, 1, 2, 1, .5, 2, 1, 1, 1, 1, 2, 1, 1, 2, .5 };
	double bug[] = { 1, .5, .5, .5, 1, 1, 1, .5, .5, .5, 1, 2, 1, 2, 1, 1, 2, .5 };
	double ghost[] = { 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, .5, 1 };
	double steel[] = { 1, 1, 1, 1, 1, 2, 1, 1, .5, .5, .5, 1, .5, 1, 2, 1, 1, 2 };
	double fire[] = { 1, 1, 1, 1, 1, .5, 2, 1, 2, .5, .5, 2, 1, 1, 2, .5, 1, 1 };
	double water[] = { 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, .5, .5, 1, 1, 1, .5, 1, 1 };
	double grass[] = { 1, 1, .5, .5, 2, 2, .5, 1, .5, .5, 2, .5, 1, 1, 1, .5, 1, 1 };
	double electric[] = { 1, 1, 2, 1, 0, 1, 1, 1, 1, 1, 2, .5, .5, 1, 1, .5, 1, 1 };
	double psychic[] = { 1, 2, 1, 2, 1, 1, 1, 1, .5, 1, 1, 1, 1, .5, 1, 1, 0, 1 };
	double ice[] = { 1, 1, 2, 1, 2, 1, 1, 1, .5, .5, .5, 2, 1, 1, .5, 2, 1, 1 };
	double dragon[] = { 1, 1, 1, 1, 1, 1, 1, 1, .5, 1, 1, 1, 1, 1, 1, 2, 1, 0 };
	double dark[] = { 1, .5, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, .5, .5 };
	double fairy[] = { 1, 2, 1, .5, 1, 1, 1, 1, .5, .5, 1, 1, 1, 1, 1, 2, 2, 1 };
	double typingArray[][] = { normal, fighting, flying, poison, ground, rock, bug, ghost, steel, fire,
	water, grass, electric, psychic, ice, dragon, dark, fairy };
	String segments [] = { "dexNumber", "Name", "type1", "type2", "HP", "Attack", "Defense", "specialAttack", "specialDefense", "Speed", "move1", "movetype1", "basepower1", "move2", "movetype2", "basepower2", "move3", "movetype3", "basepower3", "move4", "movetype4", "basepower4" };
	ReadWrite kleb = new ReadWrite("Stats.txt");
	//All arrays that are needed for storing values and variables
	
	
	public int getIntForType(String type) { //Basically returns the index of the array which a value from the array belongs to so that you can access the array at that value
		int answer = 0;
		for (int i = 0; i < typings.length; i++) {
			if (type.equalsIgnoreCase(typings[i])) {
				answer = i;
				break;
			}
		}
		return answer;
	}

	public int getIntForSegment(String segment) { //Same as previous method except for a different array
		int answer = 0;
		for (int i = 0; i < segments.length; i++) {
			if (segment.equalsIgnoreCase(segments[i])) {
				answer = i;
				break;
			}
		}
		return answer;
	}
	
	public int getIntForDexNum(int num) { //Same as previous
		int answer = 0;
		String temp[] = kleb.getAllDexNums();
		for (int i = 0; i < temp.length; i++) {
			int t = 0;
			try {
				t = Integer.parseInt(temp[i]);
			} catch (Exception e) {
				return -1;
			}
			if (num == t) {
				answer = i;
				break;
			}
		}
		return answer;
	}
	
	public double getMod(int num1, int num2) { //Returns the modifier based on the 2 integers inputed which represent attacking and defending type
		double answer = 0;
		answer = typingArray[num1][num2];
		return answer;
	}
}
