
public class PokemonMath {
	
	public double calculateDamage(int level, int attack, int defense, int basePower, int modifier, boolean stabBoolean) {
		double answer = 0;
		double stab = 1;
		
		if (stabBoolean) {
			stab = 1.5;
		}
		
		answer = (((2 * level) / 250) * (attack / defense) * basePower + 2) * modifier * stab;
		
		return answer;
	}
	
}
