
public class PokemonInfo {
	Translator myTranslator = new Translator();
	ReadWrite kleb = new ReadWrite("PokemonStats");
	
	public String getInfo(String segment, String name) {
		String answer = "";
		int num1 = myTranslator.getDexNumForName(name);
		int num2 = myTranslator.getIntForSegment(segment);
		answer = kleb.getPokemonInfo(num2, num1);
		return answer;
	}
	
}
