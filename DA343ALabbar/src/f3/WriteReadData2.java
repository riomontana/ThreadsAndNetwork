package f3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class WriteReadData2 {
	private String filename = "files/div.dat";
	private String archivename = "files/div.zip";

	public void writeNumbers() throws IOException {
		ZipOutputStream zos = null;
		DataOutputStream dos = null;
		try { 
			zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(archivename)));
			dos = new DataOutputStream(zos);
			int[] numbers = new int[10000];

			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = (int) (Math.random() * 900) + 100;
				if (i % 100 == 0) {
					if (i % 2000 == 0) {
						System.out.println();
					}
					System.out.print(numbers[i] + " ");
				}
			}

			zos.putNextEntry(new ZipEntry(filename));

			dos.writeInt(10000); // antal int i datafilen
			for (int i = 0; i < numbers.length; i++) {
				dos.writeInt(numbers[i]);
			}
			dos.flush();
			zos.closeEntry();
		} catch(IOException e) {
			System.err.println(e);
		} finally {
			WriteReadData2.close(dos);
		}
	}

	public void readNumbers() throws IOException {
		ZipEntry zipEntry;
		try ( ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(archivename)));
				DataInputStream dis = new DataInputStream(zis) ) {
			while((zipEntry = zis.getNextEntry())!=null) {
				if(zipEntry.getName().equals(filename)) {
					int count = dis.readInt(); // antal att läsa
					int[] numbers = new int[count];
					for (int i = 0; i < numbers.length; i++) {
						numbers[i] = dis.readInt();
					}
					for (int i = 0; i < numbers.length; i++) {
						if (i % 100 == 0) {
							if (i % 2000 == 0) {
								System.out.println();
							}
							System.out.print(numbers[i] + " ");
						}
					}
				}
			}
		} 
	}

	public static void close(Closeable c) {
		try {
			c.close();
		} catch(Exception e) {}
	}

	public static void main(String args[]) throws IOException {
		WriteReadData2 dt = new WriteReadData2();
        dt.writeNumbers();
		System.out.println(
				"\n--------------------------------------------------------");
		dt.readNumbers();
	}
}
