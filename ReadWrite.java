/*
© Khicken
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadWrite {
	public String url;
	File f;
	String [] dexNums;
	String[][] file;
	
	public ReadWrite(String yooareel) {
		url = yooareel;
		f = new File(url);
		dexNums = new String[151];
		file = breakApart();
		for(int i = 0; i < 151; i++) {
			dexNums[i] = file[0][i];
		}
	}
	
	protected String[][] breakApart() {
		try {
			file = new String[22][151];
			Scanner sc = new Scanner(f);
			String[] currentSegments;
			int dex = 0;
			while(sc.hasNextLine()) {
				currentSegments = sc.nextLine().split(";");
				for(int segment = 0; segment < currentSegments.length; segment++) {
					file[segment][dex] = currentSegments[segment];
				}
				dex++;
			}
			
			sc.close();
			
			return file;
		} catch(FileNotFoundException ee) {
			ee.printStackTrace();
			return null;
		}
	}
	
	
	
	public String getPokemonInfo(int segment, int dex) {
		return file[segment][dex];
	}
	
	public String[] getAllDexNums() {
		return dexNums;
	}
}
