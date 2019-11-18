package laboration7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Exercise3a {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://ddwap.mah.se/AE7689/linus.xml");
			try {
				InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
				BufferedReader br = new BufferedReader(isr); {
//					System.out.println(url.toString());

//					int c = 0;
//					while((c =br.read()) != -1) {
//						System.out.print((char)c);
//					}
//					System.out.println();

					String line = br.readLine();
					while(line != null) {
						System.out.println(line);
						line = br.readLine();
					}
				}
			}
			catch(IOException ex) {
//				System.err.println(ex);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		finally {}
	}
}

