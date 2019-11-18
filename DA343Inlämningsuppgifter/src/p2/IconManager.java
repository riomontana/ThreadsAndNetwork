package p2;

import java.util.Observable;

import javax.swing.Icon;

/**
 * Klass som ärver Observable
 * Tar emot en Buffer<Icon> instans och skapar en instans av en inre klass som
 * skickar meddelanden när något händer i <Icon>Buffer instansen.
 * @author Linus Forsberg
 */

public class IconManager extends Observable {

	private Buffer<Icon> iconbuffer;
	private Thread thread;

	/**
	 * Konstruktor som tar emot en instans av Buffer<Icon>
	 * och skapar en ny instans av den inre klassen.
	 * @param iconbuffer - Buffer<Icon>
	 */
	public IconManager(Buffer<Icon> iconbuffer) {
		thread = new Activity();
		this.iconbuffer = iconbuffer;
	}

	/*
	 * Metod som startar tråden i den inre klassen.
	 */
	public void start() {
		thread.start();
	}

	/**
	 * Inre klass som ärver Thread
	 * Innehåller metoden run som notifierar när den har hämtat ett objekt ur <Icon>Buffer instansen.
	 * Dessa meddelanden lyssnar sedan Viewer-klassen på.
	 * @author Linus Forsberg
	 *
	 */
	private class Activity extends Thread {
		public void run() {
			while(iconbuffer != null) {
				try {
					Icon icon = iconbuffer.get();
					setChanged();
					notifyObservers(icon);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
