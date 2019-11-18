package f5;

import java.io.IOException;

import javax.swing.JFileChooser;

public class Controller {
	public static void main(String[] args) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(fileChooser.showDialog(null,"File to zip")==JFileChooser.APPROVE_OPTION) {
			ZipArchive zip = new ZipArchive(fileChooser.getSelectedFile().getPath());
			zip.start();
		}
	}

}
