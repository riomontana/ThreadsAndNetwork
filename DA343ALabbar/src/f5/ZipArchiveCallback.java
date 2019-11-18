package f5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchiveCallback extends Thread {
	private ProgressListener listener;
	private File file;
	private String archive;
	
	public ZipArchiveCallback(String fileOrDir) {
		this(new File(fileOrDir));
	}
	
	public ZipArchiveCallback(File file) {
		this.file = file;
	}
	
	public void addProgressListener(ProgressListener listener) {
		this.listener = (listener==null) ? new PL() : listener;
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
			listener.progress("To zip: " + file.getAbsolutePath());
			zos.putNextEntry(new ZipEntry(directories+file.getName()));
			try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
				int b = bis.read();
				while(b!=-1) {
					zos.write(b);
					b = bis.read();
				}
				zos.flush();
			}
			zos.closeEntry();
		} else if(file.isDirectory()) {
			for(File f : file.listFiles()) {
				zip(f,zos,directories+file.getName()+"/");
//				zip(f,zos,(directories==null)?"":directories+file.getName()+"/");
			}
		}
	}
	
	public void run() {
		listener.progress("Source: " + file.getAbsolutePath());
		if(file.isDirectory()) {
			archive = file.getAbsolutePath()+".zip";
		} else if(file.isFile()) {
			archive = getFileName(file.getAbsolutePath())+".zip";
		} else if(listener!=null){
			listener.exception("Bad name of dir or file");
			return;
		}
		
		try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(archive)))){
			zip(file, zos, "");
//			zip(file, zos, null);
		}catch(Exception e) {
			listener.exception(e.getMessage());
			return;
		}
		listener.ready(archive);
	}

	private class PL implements ProgressListener {
		public void progress(String filename) {}
		public void ready(String archive) {}
		public void exception(String message) {}		
	}
	
	public static void main(String[] args) throws IOException {
		ZipArchiveCallback zar = new ZipArchiveCallback("C:/Documents and Settings/Rolf/Mina dokument/Dropbox/DA343A/Kursmaterial/Material 4/F4");
		zar.addProgressListener(new ProgressListenerConsole());
		zar.start();
	}
}
