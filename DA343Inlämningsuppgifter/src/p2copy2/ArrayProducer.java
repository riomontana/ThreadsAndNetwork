package p2copy2;

import javax.swing.Icon;

/**
 * Klass som implementerar interfacet IconProducer.
 * Klassen tar emot en Icon array och parametrarna delay och times 
 * som beskriver hur lång tid och hur många gånger bilderna ska visas.
 * @author Linus Forsberg
 */

public class ArrayProducer implements IconProducer {
	private Icon[] icons;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;
	
	/**
	 * Konstruktor som tar emot en Icon-array och två int som parametrar.
	 * @param icons - Icon array
	 * @param delay - int
	 * @param times - int
	 */
	public ArrayProducer(Icon[] icons, int delay, int times) {
		this.delay = delay;
		this.times = times;
		this.icons = icons;
	}
	
	/**
	 * Metod som returnerar fördröjningstiden.
	 * @return - int fördröjningstid
	 */
	@Override
	public int delay() {
		return delay;
	}

	/**
	 * Returnerar antalet gånger.
	 * @return - int antal gånger
	 */
	@Override
	public int times() {
		return times;
	}

	/**
	 * Om icons array är tom returneras null. 
	 * Annars returneras storleken på icons-arrayen.
	 * @return - int storlek på arrayen.
	 */
	@Override
	public int size() {
		return (icons==null) ? 0 : icons.length;
	}

	/**
	 * Returnerar nästa Icon-objekt i Icons arrayen.
	 * @return - Icon objekt
	 */
	@Override
	public Icon nextIcon() {
		if(icons==null || icons.length==0)
		    return null;
		currentIndex = (currentIndex+1) % icons.length;
		return icons[currentIndex];
	}
}
