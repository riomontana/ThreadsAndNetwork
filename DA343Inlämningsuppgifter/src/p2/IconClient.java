package p2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.Icon;

/**
 * Klass som ärver Thread. 
 * Tar emot Icon-objekt via ObjectInputStream
 * och skickar dem vidare till P2Viewer via Callback
 * @author Linus Forsberg
 *
 */
public class IconClient extends Thread {

	private Socket socket;
	private ObjectInputStream ois;
	private Icon icon;
	private Listener listener;
	private Action action;
	private ArrayList<Listener> list = new ArrayList<Listener>();

	public IconClient(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			ois = new ObjectInputStream(socket.getInputStream());
			action = new Action();
			action.start();
		}
		catch(IOException e) {
			System.err.print(e);
		}
	}

	/**
	 * Metod som lägger till lyssnare till arraylist
	 * @param listener
	 */
	public void addListener(Listener listener) {
		this.list.add(listener);
	}

	/**
	 * Inre klass som ärver Thread. 
	 * Skapar ny tråd läser in Icon-objekt från ObjectInputStream.
	 * Placerar Icon-objekt i arraylist.
	 * @author Linus Forsberg
	 *
	 */
	private class Action extends Thread {
		public void run() {
			try { 	
				while(true) {
					icon = (Icon)ois.readObject();
					for(Listener listener : list)
						listener.icon(icon);
				} 
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
