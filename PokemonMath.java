
public class PokemonMath {

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

	public double calculateDamage(int level, int attack, int defense, int basePower, double modifier, boolean stabBoolean) {
		double answer = 0;
		double stab = 1;

		if (stabBoolean) {
			stab = 1.5;
		}

		answer = (((2 * level) / 250) * (attack / defense) * basePower + 2) * modifier * stab;

		return answer;
	}

	public double calculateTypeAdvantage(String aType, String dType1, String dType2) {
		double answer = 1;
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		double mod1 = 1;
		double mod2 = 1;
		for (int i = 0; i < typings.length; i++) {
			if (aType.equals(typings[i])) {
				num1 = i;
			}
		}
		for (int i = 0; i < typings.length; i++) {
			if (dType1.equals(typings[i])) {
				num2 = i;
			}
		}
		if (!dType2.equalsIgnoreCase("na")) {
			for (int i = 0; i < typings.length; i++) {
				if (dType2.equals(typings[i])) {
					num3 = i;
				}
			}
			mod1 = typingArray[num1][num2];
			mod2 = typingArray[num1][num3];
		} else {
			mod1 = typingArray[num1][num2];
		}
		answer = mod1 * mod2;
		return answer;
	}

}
