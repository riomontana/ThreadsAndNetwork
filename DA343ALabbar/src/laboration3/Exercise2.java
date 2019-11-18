package laboration3;

import java.io.*;

public class Exercise2 {
	
	public void members(String filename) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "ISO-8859-1"))) {
			int count = 0; int women = 0;
			String name = br.readLine();
			while(name != null) {
				System.out.println(name);
				count++;
				if(name.endsWith("K")) {
					name = br.readLine();
					women++;
					if(name != null) {
						System.out.println(", ");
					}
					System.out.print("\nAntal medlemmar: "+ count + " , därav " + women + " kvinnor.\n");
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Exercise2 e2 = new Exercise2();
		e2.members("files/medlemmar.txt");
	}
}
