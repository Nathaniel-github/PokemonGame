
public class PokemonInfo {
	Translator myTranslator = new Translator();
	ReadWrite kleb = new ReadWrite("PokemonStats");
	
	public String getInfo(String segment, int dex) {
		String answer = "";
		int ndex = myTranslator.getIntForDexNum(dex);
		if (ndex == -1) { //-1 is the return value if the input value is not an accepted value
			return "That was not a valid dex number";
		}
		int num = myTranslator.getIntForSegment(segment);
		answer = kleb.getPokemonInfo(num, ndex);
		return answer;
	}
	
}
