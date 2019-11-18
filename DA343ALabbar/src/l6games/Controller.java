package l6games;

public class Controller {
	private Buffer<Game> buffer;
	
	public Controller(Buffer<Game> buffer) {
		this.buffer = buffer;
//		Thread thread = new Thread();
		// Skapa instans av tråd och starta tråden 
	}
	
	// Inre klass vilken vilken hämtar Game-objekt ur buffer och visar objekten
	// i TextWindow. Använd println-metoden i TextWindow.
	private class InnerClass extends Thread {
		public void run() {
			buffer.get();
			
		}
	}
}
