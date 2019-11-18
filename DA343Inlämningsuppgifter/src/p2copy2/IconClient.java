package p2copy2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.Icon;

public class IconClient extends Thread {

	private Socket socket;
	private ObjectInputStream ois;
	private Icon icon;
	private Listener listener;
	private Action action;
	private ArrayList<Listener> list = new ArrayList<Listener>();

	public IconClient(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		ois = new ObjectInputStream(socket.getInputStream());
		action = new Action();
		action.start();
	}

	public void addListener(Listener listener) {
		this.list.add(listener);
	}

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
