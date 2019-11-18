package laboration4;

public class Exercise2c {
	private String[] strings;
	private long pause;
	private int index = 0;
	private InnerClass thread;

	public Exercise2c(String[] strings) {
		this(strings, 500);
	}

	public Exercise2c(String[] strings, long pause) {
		this.strings = strings.clone();
		this.pause = pause;
	}
	
	public void start() {
		thread.run();
	}

	private class InnerClass extends Thread {
		@Override
		public void run() {
			while(index < strings.length) {
				System.out.println(strings[index]);
				index++;
				try {
					Thread.sleep(pause);
				}
				catch(InterruptedException e) {
				}
			}
		}	
	}

	public static void main(String[] args) {
		String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"}; 
		String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag",
				"Lördag", "Söndag"};
		Thread t2a = new Thread(new Exercise2b(strings1)); 
		Thread t2b = new Thread(new Exercise2b(strings2));
		t2a.start(); t2b.start();

	}

}

