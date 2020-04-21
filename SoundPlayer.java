import java.io.*;

import javax.sound.sampled.*;

public class SoundPlayer {
	
	AudioInputStream myobj;
	Clip clip;
	String fileName;
	
	public SoundPlayer(String FileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {	
		fileName = FileName;
		
		myobj = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
		
		clip = AudioSystem.getClip();
		
		clip.open(myobj);
	}
	
	public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException  {
		clip.start();
	}
	
	public void loop() throws UnsupportedAudioFileException, IOException, LineUnavailableException   {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException  {
		clip.stop();
		clip.close();
	}
	
	public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException  { 
		clip.stop(); 
		clip.close(); 
		resetAudioStream();
		clip.setMicrosecondPosition(0);
	} 
	
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException  { 
		myobj = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile()); 		
		clip.open(myobj); 
	} 
}
