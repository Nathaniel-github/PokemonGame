
public class PokemonInfo {
	Translator myTranslator = new Translator();
	ReadWrite kleb = new ReadWrite("PokemonStats");
	
	public String getInfo(String segment, int dex) {
		String answer = "";
		int ndex = myTranslator.getIntForDexNum(dex);
		int num = myTranslator.getIntForSegment(segment);
		answer = kleb.getPokemonInfo(num, ndex);
		return answer;
	}
}
