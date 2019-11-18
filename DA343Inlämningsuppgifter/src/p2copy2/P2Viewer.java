package p2copy2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class P2Viewer extends JPanel {
	private JLabel lblIcon = new JLabel();
	private IconClient iconClient;

	public P2Viewer(int width, int height) { // konstruktor 
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width,height));
	}

	public P2Viewer(IconClient iconClient, int width, int height) { // konstruktor
		this(width,height);
		this.iconClient = iconClient;
		iconClient.addListener(new IconListener());
	}

	/**
	 * metod som lägger in icon objekt i fönstret
	 * @param icon
	 */
	public void setIcon(Icon icon) {
		lblIcon.setIcon(icon);
	}

	/**
	 * Lyssnar på ifall det finns objekt i iconmanager och kör isåfall metoden setIcon
	 */
	public void update(Observable o, Object arg) {
		if(arg instanceof Icon) { 
			setIcon((Icon)arg);
		}
	}

	private class IconListener implements Listener {
		public void icon(Icon icon) {
			setIcon(icon);			
		}
	}
}



