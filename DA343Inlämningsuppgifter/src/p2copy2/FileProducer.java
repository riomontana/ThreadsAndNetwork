package p2copy2;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Klass som implementerar interfacet IconProducer.
 * Tar ut info ur textfil och sparar som int-variabler.
 * Antalet rader med filnamn i textfilen avgör hur många 
 * icon-implementeringar det är i sekvensen.
 * Lägger sedan in bilderna i en Icon-array
 * @author Linus Forsberg
 */
public class FileProducer implements IconProducer {

	private Icon[] icons;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;
	
	/**
	 * Konstruktor som skapar en ny instans av BufferedReader
	 * och initialiserar en ArrayList som håller informationen som läses in.
	 * Konstruktorn tar emot ett filnamn som parameter.
	 * @param filename - String
	 */
	public FileProducer(String filename) { // konstruktor som tar emot en sträng med ett filnamn som parameter
		String  thisLine; // buffert som håller senast lästa rad
		ArrayList<String> list = new ArrayList<String>(); // definierar och initialiserar arraylist
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"))) { // skapar instans av bufferedreader
			this.times = Integer.parseInt(br.readLine()); // konverterar rad från textfilen till integer och för över till instansvariabeln
			this.delay = Integer.parseInt(br.readLine()); // konverterar rad från textfilen till integer och för över till instansvariabeln

			while ((thisLine = br.readLine()) != null) { // läser varje rad i filen till filen tar slut
				list.add(thisLine); // lägger till rad för rad i arraylisten
			}      
			br.close();
			icons = new Icon[list.size()]; // skapar icon-array lika stor som arraylist
			for(int i = 0; i < list.size(); i++) { // loopar igenom alla element i listan
				icons[i] = new ImageIcon(list.get(i)); // lägger till imageicons i icon-arrayen
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * returnerar delay-tiden från filen
	 * @return delay - int
	 */
	public int delay() {
		return delay;
	}

	/**
	 * returnerar antalet gånger Icon-sekvensen ska placeras i bufferten
	 * @return times - int
	 */
	public int times() {
		return times;
	}


	/**
	 * Om icons array är tom returneras null. annars returneras storleken på icons-arrayen
	 * @return size - int storlek på icons-arrayen
	 */
	public int size() { 
		return (icons==null) ? 0 : icons.length;
	}

	/**
	 * Returnerar Icon-implementering en i taget.
	 * @return nextIcon - nästa Icon i icons-arrayen
	 */
	public Icon nextIcon() { 
		if(icons==null || icons.length==0)
		    return null;
		currentIndex = (currentIndex+1) % icons.length;
		return icons[currentIndex];
	}
}
