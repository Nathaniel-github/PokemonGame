
public class ShowdownImportExport {
	
	public String makeShowdownTeamText(String [] names, String [] move1, String [] move2, String[] move3, String[] move4) {
		
		String answer = "";
		
		for (int i = 0; i < names.length; i ++) {
			
			String temp = "";
			temp = makeShowdownText(names[i], move1[i], move2[i], move3[i], move4[i]);
			if (!temp.trim().isEmpty()) {
				temp += "\n\n";
			}
			answer += temp;
			
		}
		
		return answer;
		
	}
	
	public String makeShowdownText(String name, String move1, String move2, String move3, String move4) {
		
		String answer = "";
		
		String [] allMoves = {move1, move2, move3, move4};
		
		for (int i = 0; i < 4; i ++) {
			
			if (!allMoves[i].trim().isEmpty()) {
				
				allMoves[i] = "\n- " + allMoves[i].trim();
				
			}
			
		}
		
		answer = name + allMoves[0] + allMoves[1] + allMoves[2] + allMoves[3];
		
		return answer;
		
	}
	
	public String[] decodeTeamText(String text) {

		String temp[] = text.split("\n\n");

		String[] answer = new String[temp.length];

		for (int i = 0; i < answer.length; i++) {

			answer[i] = decodePokemonText(temp[i]);

		}

		return answer;

	}
	
	public String decodePokemonText(String text) {
		String answer = "";
		String name = "";
		String move1 = " ";
		String move2 = " ";
		String move3 = " ";
		String move4 = " ";
		boolean dumb = false;
		
		String [] allLines = text.split("\n");
		
		for (int i = 0; i < allLines.length; i ++) {
			if (i == 0) {
				
				String [] temp = allLines[i].trim().split(" @ ");
				temp[0] = temp[0].trim().replaceAll(" \\(?.*?\\)", "");
				
				name = temp[0];
				
			} else if (allLines[i].contains("-")) {
				
				if (move1.trim().isEmpty()) {
					move1 = allLines[i].trim().replace("- ", "");
				} else if (move2.trim().isEmpty()) {
					move2 = allLines[i].trim().replace("- ", "");
				} else if (move3.trim().isEmpty()) {
					move3 = allLines[i].trim().replace("- ", "");
				} else if (move4.trim().isEmpty()) {
					move4 = allLines[i].trim().replace("- ", "");
				}
				
			}
		}
		
		answer = name + "\n" + move1 + "\n" + move2 + "\n" + move3 + "\n" + move4;
	
		return answer;
	}
	
	
}
