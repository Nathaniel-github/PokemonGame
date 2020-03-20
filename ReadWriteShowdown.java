
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class ReadWriteShowdown {
	public String url;
	File f;
	String file;
	FileWriter myWriter;
	
	public ReadWriteShowdown(String readURL, String writeURL, boolean overwrite) {
		url = readURL;
		
		f = new File(url);
		try {
			myWriter = new FileWriter(new File(writeURL), !overwrite);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	protected String getMoveStats(String moveName) {
		String answer = "";
		
		try {
			
			Scanner myobj = new Scanner(new File("ShowdownMovesets"));
			String line;
			
			while (myobj.hasNextLine()) {
				line = myobj.nextLine();
				String basePower = "";
				String type = "";
				String name = "";
				
				if (line.contains("\"" + moveName + "\": ")) {
					
					line = myobj.nextLine();
					
					while(!line.contains("\": {")) {
						
						if (line.contains("basePower:")) {
							basePower = line.trim().replace("basePower:", "").replace(",", "").trim();
						} else if (line.contains("name:")) {
							name = line.trim().replace("name:", "").replace("\"", "").replace(",", "").trim();
						} else if (line.contains("type:")) {
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
			
			Scanner myobj = new Scanner(new File("ShowdownLearnsets"));
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
			
			Scanner myobj = new Scanner(new File("ShowdownLearnsets"));
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
				
				for (int i = 0; i < 10; i ++) {
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
	
	public String makeMoveset(String [] names, String [] moveNames) {
		String answer = "AllGood";
		
		String currentLine;
		Scanner sc;
		int count = 0;
		try {
			sc = new Scanner(f);
			
			while(sc.hasNextLine()) {

				currentLine = sc.nextLine();

				if (currentLine.contains(names[count])) {

					names[count] = names[count].replaceAll("-", "").replaceAll(" ", "").replaceAll(".", "").toLowerCase();

					currentLine += getMovesets(names[count], moveNames);

					if (currentLine.contains("InvalidMove")) {
						return currentLine;
					}
					
					try {
						myWriter.write(currentLine + "\n");
						myWriter.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					count++;
					
					if (count >= names.length) {
						break;
					}
					
				}
			
			}
			sc.close();
			return answer;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return null;
		}
		
	}
	
}
