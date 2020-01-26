
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
	String segments [] = { "Name", "t1", "t2", "HP", "Attack", "Defense", "spA", "spD", "Speed", "m1", "mt1", "bp1", "m2", "mt2", "bp2", "m3", "mt3", "bp3", "m4", "mt4", "bp4" };
	String names [] = {"Venusaur", "Blastoise", "Charizard"};
	
	public int getIntForType(String type) {
		int answer = 0;
		for (int i = 0; i < typings.length; i++) {
			if (type.equalsIgnoreCase(typings[i])) {
				answer = i;
				break;
			}
		}
		return answer;
	}

	public int getIntForSegment(String segment) {
		int answer = 0;
		for (int i = 0; i < segments.length; i++) {
			if (segment.equalsIgnoreCase(segments[i])) {
				answer = i;
				break;
			}
		}
		return answer;
	}

	public int getDexNumForName(String name) {
		int answer = 0;
		for (int i = 0; i < names.length; i++) {
			if (name.equalsIgnoreCase(names[i])) {
				answer = i;
				break;
			}
		}
		return answer;
	}
	
	public double getMod(int num1, int num2) {
		double answer = 0;
		answer = typingArray[num1][num2];
		return answer;
	}
}
