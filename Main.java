
public class Main {

	public static void main(String[] args) {
		CheckInput myobj = new CheckInput();
		PokemonInfo coolName = new PokemonInfo();
		String name = myobj.getMessage("Enter the name of the pokemon: ", "Charizard Venusaur Blastoise");
		String segment = myobj.getMessage("Enter the stat you want: ", "Name t1 t2 HP Attack Defense spA spD Speed m1 mt1 bp1 m2 mt2 bp2 m3 mt3 bp3 m4 mt4 bp4");
		String x = coolName.getInfo(segment, name);
		System.out.println(x);
//		PokemonMath calculator = new PokemonMath();
//		String aType = myobj.getMessage("Enter the attack type:",
//				"Normal Fighting Flying Poison Ground Rock Bug Ghost Steel Fire Water Grass Electric Psychic Ice Dragon Dark Fairy");
//		String dType1 = myobj.getMessage("Enter defending type #1:",
//				"Normal Fighting Flying Poison Ground Rock Bug Ghost Steel Fire Water Grass Electric Psychic Ice Dragon Dark Fairy");
//		String dType2 = myobj.getMessage("Enter defending type #2 (type NA if the pokemon in monotype):",
//				"Normal Fighting Flying Poison Ground Rock Bug Ghost Steel Fire Water Grass Electric Psychic Ice Dragon Dark Fairy NA");
//		double x = calculator.calculateTypeAdvantage(aType, dType1, dType2);
//		System.out.println(x);
	}

}
