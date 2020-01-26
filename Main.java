
public class Main {

	public static void main(String[] args) {
		CheckInput myobj = new CheckInput();
		Translator myTranslator = new Translator();
		PokemonInfo coolName = new PokemonInfo();
		boolean invalid = true;
		int dex = 0;
		while (invalid) {
			dex = myobj.checkIntRange("Enter the dex number of the pokemon you want (Only fully evolved pokemon are accepted): ", 1, 890);
			int ndex = myTranslator.getIntForDexNum(dex);
			if (ndex == -1) { // -1 is the return value if the input value is not an accepted value
				System.out.println("That was not a valid dex number");
			} else {
				invalid = false;
			}
		}
		String segment = myobj.getMessage("Enter the stat you want: ", "dexNum Name t1 t2 HP Attack Defense spA spD Speed m1 mt1 bp1 m2 mt2 bp2 m3 mt3 bp3 m4 mt4 bp4");
		String x = coolName.getInfo(segment, dex);
		System.out.println(x);
//		PokemonMath calculator = new PokemonMath();
//		String aType = myobj.getMessage("Enter the attack type:",
//				"Normal Fighting Flying Poison Ground Rock Bug Ghost Steel Fire Water Grass Electric Psychic Ice Dragon Dark Fairy");
//		String dType1 = myobj.getMessage("Enter defending type #1:",
//				"Normal Fighting Flying Poison Ground Rock Bug Ghost Steel Fire Water Grass Electric Psychic Ice Dragon Dark Fairy");
//		String dType2 = myobj.getMessage("Enter defending type #2 (type NA if the pokemon is monotype):",
//				"Normal Fighting Flying Poison Ground Rock Bug Ghost Steel Fire Water Grass Electric Psychic Ice Dragon Dark Fairy NA");
//		double x = calculator.calculateTypeAdvantage(aType, dType1, dType2);
//		System.out.println(x);
	}

}
