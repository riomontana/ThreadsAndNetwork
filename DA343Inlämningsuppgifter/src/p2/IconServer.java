package p2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;

/**
 * 
 * Klass som implementerar Observer.
 * Lyssnar på om det finns Icon-implementeringar att hämta i klassen IconManager.
 * Skickar sedan vidare objekten till IconClient via ObjectOutputStream.
 * @author Linus Forsberg
 *
 */
public class IconServer implements Observer {

	private IconManager iconManager;
	private ServerSocket serverSocket;
	private Icon icon;
	private ClientHandler clientHandler;

	public IconServer(IconManager iconManager, int port) {
		try {
			this.iconManager = iconManager;
			serverSocket = new ServerSocket(port);
			clientHandler = new ClientHandler();
			clientHandler.start();
			iconManager.addObserver(this);
		}
		catch(IOException e) {
			System.err.print(e);
		}
	}


	/**
	 * Metod som används för att lyssna på om det finns några Icon-objekt att hämta i IconManager.
	 * @param o the observable object. 
	 * @param arg an argument passed to the notifyObservers method.
	 */
	public void update(Observable o, Object arg) {
		if(arg instanceof Icon) { 
			this.icon = (Icon)arg;	
		}
	}

	/**
	 * Inre klass skriver icon-objekt till en ObjectOutputStream.
	 * @author Linus Forsberg
	 *
	 */
	private class Action extends Thread {
		private Socket socket;

		public Action(Socket socket) {
			this.socket = socket;
		}

		/**
		 * run metod som skapar ny tråd
		 */
		public void run() {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				while(true) {
					try {
						oos.writeObject(icon);
						oos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} 
			catch(Exception e) {
				System.err.print(e);
			}
		}
	}

	/**
	 * Inre klass som skapar ny tråd för varje klient.
	 * @author Linus Forsberg
	 *
	 */
	private class ClientHandler extends Thread {

		public void run() {
			try {
				while(true) {
					Socket socket = serverSocket.accept();
					new Action(socket).start();
				}
			}
			catch(Exception e) {
				System.err.print(e);
			}
		}
	}
}

