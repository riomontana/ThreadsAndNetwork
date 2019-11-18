package laboration7;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Exercise3b {
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("https://ddwap.mah.se/AE7689/Gubbe.jpg");
		ImageIcon icon = new ImageIcon(url);
		JOptionPane.showMessageDialog(null, icon);
	}
}
