package f5;

import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ControllerCallback implements ZipController {
    private ZipUI gui = new ZipUI(this);	
    
    public ControllerCallback() {
    	JFrame frame = new JFrame("Callback");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.add(gui);
    	frame.pack();
    	frame.setVisible(true);
    }
	
	public void zip(String filename) {
		gui.clear();
		ZipArchiveCallback zar = new ZipArchiveCallback(filename);
		zar.addProgressListener(new CallbackImpl());
//		zar.addProgressListener(new ProgressListenerConsole());
		zar.start();
	}

	public void unzip(String filename) {
		// TODO Auto-generated method stub		
	}

	private class CallbackImpl implements ProgressListener {
		public void update(Observable source, Object obj) {
			gui.append(obj.toString());
		}

		@Override
		public void progress(String message) {
			gui.append(message);
		}

		@Override
		public void ready(String archive) {
			gui.append("Zip file: " + archive);
		}

		@Override
		public void exception(String message) {
			gui.append("EXCEPTION: " + message);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ControllerCallback();
			}
		});		
	}
}
