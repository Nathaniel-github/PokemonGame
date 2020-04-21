
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class ReadWriteShowdown {
	public String url;
	File f;
	String file;
	FileWriter myWriter;
	ReadWrite kleb = new ReadWrite("Stats.txt");
	String [] allNames = kleb.getAllNames();
	String [] allMoves = new String[397];
	HashMap <String, String> allDamage = new HashMap <String, String>();
	HashMap <String, String> allMoveTypes = new HashMap <String, String>();
	HashMap <String, String[]> taggedLearnsets = new HashMap <String, String[]>();
	HashMap <String, String> taggedStats = new HashMap <String, String>();
	
	public ReadWriteShowdown(String readURL, String writeURL, boolean overwrite) {
		url = readURL;
		
		f = new File(url);
		try {
			myWriter = new FileWriter(new File(writeURL), !overwrite);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ReadWriteShowdown() {
		
		setAllMoves();
		setAllLearnsets();
		setAllStats();
		
	}
	
	public String [] getAllMoves() {
		
		return allMoves;
		
	}
	
	public String getPokemonDataLine(String text) {
		
		String moves = "";
		String temp[] = text.split("\n");
		int i = 0;
		
		try {
			
			for (i = 1; i < 5; i ++) {
				
				moves += temp[i] + ";" + allMoveTypes.get(temp[i]) + ";" + allDamage.get(temp[i]) + ";";
				
			}
			
		} catch(ArrayIndexOutOfBoundsException e) {
			
			while (i < 5) {
				
				for (int k = 0; k < 3; k ++) {
					
					moves += "NA;";
					
				}
				
			}
			
		}
		
		return taggedStats.get(temp[0]) + moves;
		
	}
	
	public HashMap <String, String[]> getAllTaggedLearnsets() {
		
		return taggedLearnsets;
		
	}
	
	private void setAllStats() {
		
		try {

			Scanner myobj = new Scanner(new File("StatTemplate.txt"));
			String line = "";

			while (myobj.hasNextLine()) {
				
				line = myobj.nextLine();
				String temp[] = line.split(";");

				taggedStats.put(temp[1], line);
				
			}
			
			myobj.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}
		
	}
	
	public void writeAllMoves() {
		
			
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("AllMoves.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < allMoves.length; i++) {

			writer.println(allMoves[i] + ";" + allMoveTypes.get(allMoves[i]) + ";" + allDamage.get(allMoves[i]) + ";");

		}
		
		writer.close();
		
	}
	
	public void writeAllLearnsets() {
			
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("Learnsets.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int k = 0; k < taggedLearnsets.size(); k++) {

			writer.print(allNames[k] + ";");

			for (int i = 0; i < taggedLearnsets.get(allNames[k]).length; i++) {

				if (taggedLearnsets.get(allNames[k])[i] == null) {

					break;

				}
				writer.print(taggedLearnsets.get(allNames[k])[i] + ";");
			}

			writer.println();;

		}
		
		writer.close();
		
	}
	
	private void setAllLearnsets() {
		
		try {

			Scanner myobj = new Scanner(new File("Learnsets.txt"));
			String line = "";

			while (myobj.hasNextLine()) {
				line = myobj.nextLine();
				String temp[] = new String[line.split(";").length - 1];
				String temp2[] = line.split(";");

				for (int i = 1; i < temp2.length; i++) {

					temp[i - 1] = temp2[i];

				}

				taggedLearnsets.put(line.split(";")[0], temp);
			}
			
			myobj.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

	}
	
	private void setAllMoves() {
		
		try {

			Scanner myobj = new Scanner(new File("AllMoves.txt"));
			String line = "";
			int count = 0;

			while (myobj.hasNextLine()) {
				line = myobj.nextLine();
				String temp[] = line.split(";");

				allMoves[count] = temp[0];
				allMoveTypes.put(allMoves[count], temp[1]);
				allDamage.put(allMoves[count], temp[2]);

				count++;

			}
			
			myobj.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}
		
	}
	
	protected String getMoveStats(String moveName) {
		String answer = "";
		
		try {
			
			Scanner myobj = new Scanner(new File("ShowdownMovesets.txt"));
			String line;
			
			while (myobj.hasNextLine()) {
				line = myobj.nextLine();
				String basePower = "";
				String type = "";
				String name = "";
				
				if (line.contains("\"" + moveName + "\": ")) {
					
					line = myobj.nextLine();
					
					while(!line.contains("\": {")) {
						
						if (line.contains("basePower: ")) {
							basePower = line.trim().replace("basePower:", "").replace(",", "").trim();
						} else if (line.contains("name: ")) {
							name = line.trim().replace("name:", "").replace("\"", "").replace(",", "").trim();
						} else if (line.contains("type: \"")) {
							type = line.trim().replace("type:", "").replace("\"", "").replace(",", "").trim();
						}
						
						line = myobj.nextLine();
						
					}
					
					answer = name + ";" + type + ";" + basePower + ";";
					
					myobj.close();
					
					break;
					
				}
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		return answer;
	}
	
	protected String getMovesets(String name, String [] moveNames) {
		String answer = "";
		String [] moves = new String [4];
		name = name.toLowerCase();
		int moveCount = 0;
		
		try {
			
			Scanner myobj = new Scanner(new File("ShowdownLearnsets.txt"));
			String line;
			
			while(myobj.hasNextLine()) {
				line = myobj.nextLine();
				
				if (line.contains(name + ":")) {
					
					line = myobj.nextLine();
					
					while(!line.contains("}},")) {
							
						String moveName = line.trim().replace(":", "").replace(",", "").trim();
						
						moveName = moveName.trim().replaceAll(" \\(?.*?\\)", "");
						
						String tempAnswer = getMoveStats(moveName);
						String [] temp = tempAnswer.split(";");
						
						try {
							for (String element : moveNames) {
								if (moveName.equalsIgnoreCase(element)) {
									if (!temp[2].equals("0")) {
										if (moveCount < 4) {
											moves[moveCount] = tempAnswer;
											moveCount++;
										}
									}
									else {
										return element + "(InvalidMove)";
									}
								}
							}
						} catch (Exception eee) {
							System.out.println(tempAnswer);
						}
						
						line = myobj.nextLine();
						
					}
					
					for (int i = 0; i < 4; i ++) {
						answer += moves[i];
					}
					
					if (moveCount < 4) {
						for (int i = 0; i < (3 * (4 - moveCount)); i ++) {
							answer += "NA;";
						}
					}
					
					
					
					myobj.close();
					
					break;
					
				}
				
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		return answer;
	}
	
	
	
	protected String getMovesets(String name) {
		String answer = "";
		String [] moves = new String [4];
		name = name.toLowerCase();
		int moveCount = 0;
		
		try {
			
			Scanner myobj = new Scanner(new File("ShowdownLearnsets.txt"));
			String line;
			
			while(myobj.hasNextLine()) {
				line = myobj.nextLine();
				
				if (line.contains(name + ":")) {
					
					line = myobj.nextLine();
					
					while(!line.contains("}},")) {
							
						String moveName = line.trim().replace(":", "").replace(",", "").trim();
						
						moveName = moveName.trim().replaceAll(" \\(?.*?\\)", "");
						
						String tempAnswer = getMoveStats(moveName);
						String [] temp = tempAnswer.split(";");
						
						try {
							if (!temp[2].equals("0")) {
								if (moveCount < 4) {
									moves[moveCount] = tempAnswer;
									moveCount++;
								} else {
									if ((ThreadLocalRandom.current().nextInt(0, 3) == 1 && Integer.parseInt(temp[2]) >= 70) || Integer.parseInt(temp[2]) >= 100) {
										int tempInt = ThreadLocalRandom.current().nextInt(0, 4);
										moves[tempInt] = tempAnswer;
									}
									int tempCount = 0;
									if (Integer.parseInt(temp[2]) >= 70) {
										for (String element : moves) {
											int damage = Integer.parseInt(element.split(";")[2]);
											if (damage <= 50) {
												moves[tempCount] = tempAnswer;
												break;
											}
											tempCount ++;
										}
									}
								}
							}
						} catch (Exception eee) {
							System.out.println(tempAnswer);
						}
						
						line = myobj.nextLine();
						
					}
					
					for (int i = 0; i < 4; i ++) {
						if (moves[i] != null) {
							answer += moves[i];
						}
					}
					
					if (moveCount < 4) {
						for (int i = 0; i < (3 * (4 - moveCount)); i ++) {
							answer += "NA;";
						}
					}
					
					
					
					myobj.close();
					
					break;
					
				}
				
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		return answer;
	}
	
	public void writeAllStats() {
		try {
			Scanner sc = new Scanner(f);
			String currentLine;
			String name = "notPossibleString";
			
			while(sc.hasNextLine()) {
				currentLine = sc.nextLine();
				
				String [] temp = currentLine.split(";");
				
				name = temp[1];
				
				name = name.replace("-", "").replace(" ", "").replace(".", "").toLowerCase();
				
				currentLine = "";
				
				for (int i = 0; i < temp.length; i ++) {
					currentLine += temp[i] + ";";
				}
				
				currentLine += getMovesets(name);
				
				try {
					myWriter.write(currentLine + "\n");
					myWriter.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
				
//				String [] temp = currentLine.split(";");
//				
//				if (currentLine.contains(name)) {
//					
//				} else {
//					name = temp[1];
//					try {
//						myWriter.write(currentLine + "\n");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
				
				
				
//				if (currentLine.contains("num:")) {
//					file += currentLine.trim().replace("num: ", "").replaceAll(",", "") + ";";
//				} else if (currentLine.contains("species:")) {
//					file += currentLine.trim().replace("species: ", "").replaceAll("\"", "").replaceAll(",", "") + ";";
//				} else if (currentLine.contains("types:")) {
//					String [] temp = currentLine.trim().replace("types: ", "").replaceAll("\"", "").replaceAll(",", "").replace("[", "").replace("]", "").trim().split(" ");
//					if (temp.length == 1) {
//						file += temp[0] + ";NA;";
//					} else {
//						file += temp[0] + ";" +temp[1] + ";";
//					}
//				} else if (currentLine.contains("baseStats:")) {
//					String [] temp = currentLine.trim().replace("baseStats: ", "").replace("{", "").replace("}", "").replaceAll(",", "").replace("hp: ", "").replace("atk: ", "").replace("def: ", "").replace("spa: ", "").replace("spd: ", "").replace("spe: ", "").trim().split(" ");
//					for (int i = 0; i < temp.length; i ++) {
//						file += temp[i] + ";";
//					}
//				} else if (currentLine.contains("ENDLINE")) {
//					file += "\n";
//				}
				
			}
			
			sc.close();
			
		} catch(FileNotFoundException ee) {
			ee.printStackTrace();
		}
	}
	
}
