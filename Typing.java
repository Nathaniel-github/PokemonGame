
public class Typing {
	
	int wait = 50;
	
	public void setWaitTime(int time) {
		wait = time;
	}
	
	public void setMedWaitTime() {
		wait = 50;
	}
	
	public void setLongWaitTime() {
		wait = 80;
	}
	
	public void setShortWaitTime() {
		wait = 30;
	}
	
	public void typeMessage(String message) {
		String letters[] = message.split("");
		for (int i = 0; i < letters.length; i++) {
			System.out.print(letters[i]);
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
