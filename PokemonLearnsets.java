import java.util.HashMap;

public class PokemonLearnsets {

	String [] allNames;
	ReadWrite kleb;
	ReadWriteShowdown moves;
	String [] allMoves;
	HashMap <String, String[]> allLearnsets;
	
	public PokemonLearnsets() {
		kleb = new ReadWrite("Stats.txt");
		moves = new ReadWriteShowdown();
		allNames = kleb.getAllNames();
		allLearnsets = moves.getAllTaggedLearnsets();
		allMoves = moves.getAllMoves();
	}
	
	public String [] getAllNames() {
		
		return allNames;
		
	}
	
	public String [] getAllMoves() {
		
		return allMoves;
		
	}
	
	public String [] getInfo(String text) {
		
		return moves.getPokemonDataLine(text).split(";");
		
	}
	
	public boolean noMoves(String name) {
		
		try {
			
			String test = allLearnsets.get(name)[0];
			
			return false;
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
			return true;
			
		}
		
	}
	
	public String getTrueName(String name) {

		for (String element : allNames) {

			if (element.equalsIgnoreCase(name)) {

				return element;

			}

		}

		return "Not a name";

	}
	
	public String [] getLearnset(String name) {
		
		return allLearnsets.get(name);
		
	}

	public boolean validMove(String move, String name) {

		boolean answer = false;

		if (move.isEmpty()) {

			return true;

		}

		for (String element : allLearnsets.get(name)) {

			if (element == null) {

				break;

			}

			if (element.equalsIgnoreCase(move)) {

				answer = true;

				break;

			}

		}

		return answer;

	}

	public boolean validPokemon(String name) {

		boolean answer = false;

		name = name.trim();

		if (name.isEmpty() || name.length() <= 2) {

			return answer;

		}

		for (String element : allNames) {

			if (element.equalsIgnoreCase(name)) {

				answer = true;
				break;

			}

		}

		return answer;

	}

}
