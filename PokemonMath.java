
public class PokemonMath {

	Translator myTranslator = new Translator();
	
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
		double answer = 0;
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		double mod1 = 1;
		double mod2 = 1;
		num1 = myTranslator.getIntForType(aType);
		num2 = myTranslator.getIntForType(dType1);
		if (!dType2.equalsIgnoreCase("na")) {
			num3 = myTranslator.getIntForType(dType2);
			mod1 = myTranslator.getMod(num1, num2);
			mod2 = myTranslator.getMod(num1, num3);
		} else {
			mod1 = myTranslator.getMod(num1, num2);
		}
		answer = mod1 * mod2;
		return answer;
	}

}
