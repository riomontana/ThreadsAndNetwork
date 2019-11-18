package p1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klass som implementerar interfacet Observer som används för att lyssna på IconManager
 * 
 * @author Linus Forsberg
 */

public class Viewer extends JPanel implements Observer {
	private JLabel lblIcon = new JLabel();
	private IconManager iconmanager;
	
	public Viewer(int width, int height) { // konstruktor 
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width,height));
	}
	
	public Viewer(IconManager iconmanager, int width, int height) { // konstruktor
		this(width,height);
		this.iconmanager = iconmanager;
		iconmanager.addObserver(this); // lägger till observer
	
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
}
