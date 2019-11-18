package f3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class DemoDataStreams1 implements StringIO {
    private JFileChooser fileChooser = new JFileChooser();

	@Override
	public void write(String str) {
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try( DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
        			new FileOutputStream(fileChooser.getSelectedFile().getPath()))) ) {
        		dos.writeInt(str.length());
        		dos.writeBoolean(str.length()>20);
        		dos.writeUTF(str);
        		dos.flush();
        	} catch(IOException e) {
        		System.err.println(e);
        	}
        }
	}

	@Override
	public String read() {
		String str = null;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try (DataInputStream dis = new DataInputStream(
        			new BufferedInputStream(
        				new FileInputStream(fileChooser.getSelectedFile().getPath())))) {
        		int len = dis.readInt();
        		boolean longStr = dis.readBoolean();
        		String txt = dis.readUTF();
        		
        		if(longStr) {
        			str = "En lång sträng, ";
        		} else {
        			str = "En kort sträng, ";
        		}
        		str = str + len + " characters in the String-objekt\n";
        		str = str + txt;
        		
        	} catch(IOException e) {
        		System.err.println(e);
        	} 
        }
		return str;
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller(new DemoDataStreams1(),"DemoDataStreams1");
            }
        });
	}
}
