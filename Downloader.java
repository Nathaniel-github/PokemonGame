import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.Arrays;

public class Downloader {

	public void downloadImage (String url, String path, String name, String tag) {
		try(InputStream in = new URL(url + name + tag).openStream()){
		    Files.copy(in, Paths.get(path + "/" + name + "-back" + tag));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void uncompressImage (String name, boolean back, String path) {
		
		String b = "";	
		
		name = name.toLowerCase().replace("-", "").replace(" ", "").replace(".", "").replace("'", "").replace(":", "");
		
		if (back) {
			
			b = "-back";
			
		}
		
		ProcessBuilder pb = new ProcessBuilder();
		
		String[] args = new String[] {"/usr/local/Cellar/imagemagick/7.0.10-0/bin/convert" , name + b + ".gif", "-coalesce", name + b + ".gif"};
		
		try {
			pb.inheritIO();
			pb.directory(new File(path));
            pb.command(args);
            Process process = pb.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		// Example code for this method
		
//		for (String element : allNames) {
//
//			myDownloader.uncompressImage(element, false, "/Users/nathaniel/Downloads/SpritesFront");
//
//		}
//
//		for (String element : allNames) {
//
//			myDownloader.uncompressImage(element, true, "/Users/nathaniel/Downloads/SpritesBack");
//
//		}
	
	}
	
}
