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
	
	public ReadWrite(String yooareel) {
		url = yooareel;
		f = new File(url);
		dexNums = new String[890];
		for(int i = 0; i < 890; i++) {
			dexNums[i] = breakApart()[0][i];
		}
	}
	
	protected String[][] breakApart() {
		try {
			String[][] file = new String[22][890];
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
		return breakApart()[segment][dex];
	}
	
	public String[] getAllDexNums() {
		return dexNums;
	}
}
