import java.util.concurrent.ThreadLocalRandom;

public class PokemonMath {

	Translator myTranslator = new Translator();
	
	public double calculateDamage(int attack, int defense, int basePower, double modifier, boolean stabBoolean, int level) { // Based on parameters will calculate a number for the exact amount of damage dealt
		double answer = 1;
		double stab = 1;

		if (stabBoolean) {
			stab = 1.5;
		}

		answer = ((double)(level * 2 + 10) / 250) * ((double)attack / defense) * (double)basePower + 2;
		answer = answer * modifier * stab;
		answer /= 4.61538461538;
		answer = Math.round(answer * ThreadLocalRandom.current().nextDouble(0.85, 1.01));
		return answer;
	}

	public double calculateTypeAdvantage(String aType, String dType1, String dType2) { //Calculates the type advantage based on the input parameters
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
	
	public int calcBaseStat(int base, int iv, int ev, int level, String nature, String stat) {
		int answer = 0;
		
		if (base == 1 && stat.equalsIgnoreCase("hp")) { //Handles shedinja, since it is the only mon with 1 hp I can find it like this
			return 1;
		}
		
		if (stat.equalsIgnoreCase("hp")) {
			
			base = calcLeveledStat(base, level);
			
			answer = (int)Math.floor((2 * base + iv + ev) * level / 100 + level + 10);
			
			return answer;
		} else {
		
			base = calcLeveledStat(base, level);
		
			answer = (int)Math.floor(Math.floor((2 * base + iv + ev) * level / 100 + 5));
			answer = (int)(answer * calcNatureBoost(nature, base, stat));
		
			return answer;
		}
	}
	
	public int calcBaseStat(int base, String stat) {
		int answer = 0;
		int level = 100;
		String nature = "Serious";
		int ev = 85;
		int iv = 31;
		
		if (base == 1 && stat.equalsIgnoreCase("hp")) { //Handles shedinja, since it is the only mon with 1 hp I can find it like this
			return 1;
		}
		
		if (stat.equalsIgnoreCase("hp")) {
			
			base = calcLeveledStat(base, level);
			
			answer = (int)Math.floor((2 * base + iv + ev) * level / 100 + level + 10);
			
			return answer;
		} else {
		
			base = calcLeveledStat(base, level);
		
			answer = (int)Math.floor(Math.floor((2 * base + iv + ev) * level / 100 + 5));
			answer = (int)(answer * calcNatureBoost(nature, base, stat));
		
			return answer;
		}
	}
	
	private int calcLeveledStat(int base, int level) {
		int answer = 0;
		
		answer = (int)(base * Math.pow(1.02, (double)level));
		
		return answer;
	}
	
	private double calcNatureBoost(String nature, int base, String stat) {
		double answer = 1;
		
		if (nature.equals("Bashful") || nature.equals("Docile") || nature.equals("Hardy") || nature.equals("Quirky") || nature.equals("Serious")) {
			
			return answer;
		
		} else if (stat.equalsIgnoreCase("attack") && (nature.equals("Adamant") || nature.equals("Brave") || nature.equals("Lonely") || nature.equals("Naughty"))) {
			
			answer = 1.1;
			
		} else if (stat.equalsIgnoreCase("defense") && (nature.equals("Bold") || nature.equals("Impish") || nature.equals("Lax") || nature.equals("Relaxed"))) {
			
			answer = 1.1;
			
		} else if (stat.equalsIgnoreCase("spattack") && (nature.equals("Modest") || nature.equals("Mild") || nature.equals("Quiet") || nature.equals("Rash"))) {
			
			answer = 1.1;
			
		} else if (stat.equalsIgnoreCase("spdefense") && (nature.equals("Calm") || nature.equals("Careful") || nature.equals("Gentle") || nature.equals("Sassy"))) {
			
			answer = 1.1;
			
		} else if (stat.equalsIgnoreCase("speed") && (nature.equals("Hasty") || nature.equals("Jolly") || nature.equals("Naive") || nature.equals("Timid"))) {
			
			answer = 1.1;
			
		} else if (stat.equalsIgnoreCase("attack") && (nature.equals("Bold") || nature.equals("Modest") || nature.equals("Calm") || nature.equals("Timid"))) {
			
			answer = 0.9;
			
		} else if (stat.equalsIgnoreCase("defense") && (nature.equals("Lonely") || nature.equals("Mild") || nature.equals("Gentle") || nature.equals("Hasty"))) {
			
			answer = 0.9;
			
		} else if (stat.equalsIgnoreCase("spattack") && (nature.equals("Adamant") || nature.equals("Impish") || nature.equals("Careful") || nature.equals("Jolly"))) {
			
			answer = 0.9;
			
		} else if (stat.equalsIgnoreCase("spdefense") && (nature.equals("Naughty") || nature.equals("Lax") || nature.equals("Rash") || nature.equals("Naive"))) {
			
			answer = 0.9;
			
		} else if (stat.equalsIgnoreCase("speed") && (nature.equals("Brave") || nature.equals("Relaxed") || nature.equals("Quiet") || nature.equals("Sassy"))) {
			
			answer = 0.9;
			
		}
		
		return answer;
	}
	
}
