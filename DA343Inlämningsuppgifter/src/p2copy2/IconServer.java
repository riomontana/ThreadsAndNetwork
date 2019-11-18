package p2copy2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;

public class IconServer implements Observer {

//	private ObjectOutputStream oos;
	private IconManager iconManager;
	private ServerSocket serverSocket;
	private Icon icon;
	private ClientHandler clientHandler;

	public IconServer(IconManager iconManager, int port) throws IOException {
		this.iconManager = iconManager;
		serverSocket = new ServerSocket(port);
		clientHandler = new ClientHandler();
		clientHandler.start();
		iconManager.addObserver(this);
		}
	
	public void update(Observable o, Object arg) {
		if(arg instanceof Icon) { 
			this.icon = (Icon)arg;	
		}
	}
	
	private class Action extends Thread {
		private Socket socket;

		public Action(Socket socket) {
			this.socket = socket;
		}

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

