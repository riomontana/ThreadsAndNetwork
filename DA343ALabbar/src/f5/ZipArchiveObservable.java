package f5;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchiveObservable extends Observable {
	private File file;
	private String archive;
	
	public ZipArchiveObservable(String fileOrDir) {
		this(new File(fileOrDir));
	}
	
	public ZipArchiveObservable(File file) {
		this.file = file;
	}
	
	public void start() {
		new Activity().start();
	}
	
	private class Activity extends Thread {	
		public void run() {
			setChanged();
			notifyObservers("TO ZIP: " + file.getAbsolutePath());
			if(file.isDirectory()) {
				archive = file.getAbsolutePath()+".zip";
			} else if(file.isFile()) {
				archive = getFileName(file.getAbsolutePath())+".zip";
			} else {
				setChanged();
				notifyObservers("NOT directory or file: " + file.getAbsolutePath());
				return;
			}

			try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(archive)))){
				zip(file, zos, "");
				//			zip(file, zos, null);
			}catch(Exception e) {
				setChanged();
				notifyObservers("EXEPTION: " + e.getMessage());
				return;
			}
			setChanged();
			notifyObservers("ZIP-FILE: " + archive);
		}
		
		private String getFileName(String filename) {
			int index = filename.indexOf('.');
			if(index>=0)
				return filename.substring(0,index);
			else
				return filename;
		}
		
		private void zip(File file, ZipOutputStream zos, String directories) throws IOException {
			if(file.isFile()) {
				setChanged();
				notifyObservers("ZIP: " + file.getAbsolutePath());
				zos.putNextEntry(new ZipEntry(directories+file.getName()));
				try (FileInputStream fis = new FileInputStream(file)) {
					int b = fis.read();
					while(b!=-1) {
						zos.write(b);
						b = fis.read();
					}
					zos.flush();
				}
				zos.closeEntry();
			} else if(file.isDirectory()) {
				for(File f : file.listFiles()) {
					zip(f,zos,directories+file.getName()+"/");
//					zip(f,zos,(directories==null)?"":directories+file.getName()+"/");
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		ZipArchiveObservable zar = new ZipArchiveObservable("C:/Documents and Settings/Rolf/Mina dokument/Dropbox/DA343A/Kursmaterial/Material 4/F4");
		zar.addObserver(new ProgressListenerConsole2());
		zar.start();
	}
}

class ProgressListenerConsole2 implements Observer {
	public void update(Observable observable, Object obj) {
		System.out.println("Meddelande från " + observable + ": " + obj);
	}
}
