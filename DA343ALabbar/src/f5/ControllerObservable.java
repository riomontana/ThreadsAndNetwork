package f5;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ControllerObservable implements ZipController {
    private ZipUI gui = new ZipUI(this);	
    
    public ControllerObservable() {
    	JFrame frame = new JFrame("Observer");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.add(gui);
    	frame.pack();
    	frame.setVisible(true);
    }
	
	public void zip(String filename) {
		gui.clear();
		ZipArchiveObservable zip = new ZipArchiveObservable(filename);
		zip.addObserver(new ObserverImpl());
//		zip.addObserver(new ProgressListenerConsole2());
		zip.start();
	}

	public void unzip(String filename) {
		// TODO Auto-generated method stub		
	}

	private class ObserverImpl implements Observer {
		public void update(Observable source, Object obj) {
			gui.append(obj.toString());
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ControllerObservable();
			}
		});		
	}
}
