package p1;


/** 
 * Klassen innehåller metoden addIconProducer som vid anrop 
 * får tillgång till en instans av en klass vilken implementerar IconProducer. 
 * Detta objekt placeras sedan i en buffert av typen Buffer<IconProducer>
 * @author Linus Forsberg
 */
public class IconProducerManager {
	Buffer<IconProducer> buffer = new Buffer<IconProducer>();
	
	/** 
	 * Konstruktor som tar emot en Buffer<IconProducer> som parameter
	 * @param buffer - Buffer<IconProducer>
	 */
	public IconProducerManager(Buffer<IconProducer> buffer) { 
		this.buffer = buffer;
	}
	
	
	/**
	 * Metod som tar emot ett IconProducer-objekt
	 * och placerar objektet i en buffer.
	 * @param iconproducer - IconProducer-objekt
	 */
	public void addIconProducer(IconProducer iconproducer) { 
		buffer.put(iconproducer);
	}
}
