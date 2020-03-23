import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Downloader {

	public void downloadImage (String url, String path, String name, String tag) {
		try(InputStream in = new URL(url + name + tag).openStream()){
		    Files.copy(in, Paths.get(path + "/" + name + "-back" + tag));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
