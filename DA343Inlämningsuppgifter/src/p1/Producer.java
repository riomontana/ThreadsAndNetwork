package p1;

import javax.swing.Icon;

/**
 * Klass som hämtar en IconProducer-implementering ur IconProducer buffer och flyttar till Icon buffer
 * @author Linus Forsberg
 *
 */
public class Producer {

	private Buffer<IconProducer> prodBuffer;
	private Buffer<Icon> iconBuffer;
	private NewThread thread = new NewThread();

	
	/**
	 * Konstruktor som tar emot en instans av Buffer<IconProducer> 
	 * och en instans av Buffer<Icon> som parametrar.
	 * @param prodBuffer - Buffer<IconProducer> 
	 * @param iconBuffer - Buffer<Icon>
	 */
	public Producer(Buffer<IconProducer> prodBuffer, Buffer<Icon> iconBuffer) {
		this.prodBuffer = prodBuffer;
		this.iconBuffer = iconBuffer;
	}

	/**
	 * Metod som startar tråden i den inre klassen.
	 */
	public void start() {
		thread.start();
	}
	
	/**
	 * Inre klass som skapar ny tråd. Ärver klassen Thread.
	 * Metoden run() flyttar ett IconProducer objekt från
	 * en IconProducer buffer till en Icon buffer med en förutbestämd paus.
	 * @author Linus Forsberg
	 */
	private class NewThread extends Thread {
		public void run() {
			try {
				while(true) {
					IconProducer iconproducer = prodBuffer.get();
					for(int i = 0; i < iconproducer.times() * iconproducer.size(); i++) {
						iconBuffer.put(iconproducer.nextIcon());
						Thread.sleep(iconproducer.delay());
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}



