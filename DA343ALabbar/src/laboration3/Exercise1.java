package laboration3;

import java.io.*;

public class Exercise1 {
	
	public void sum(String filename) throws IOException {
		try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
			int sum = 0;
			int n = dis.readInt();
			for(int i= 0; i<n; i++) {
				System.out.println(sum);
				sum += i;
			}
			System.out.println(sum);
		}	
	}
	
	public static void main(String[] args) throws IOException {
		Exercise1 e1 = new Exercise1();
		e1.sum("files/heltal.dat");
	}
}
