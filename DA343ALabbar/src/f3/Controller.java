package f3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Controller {
	private StringIO inOut;
    private JFrame frame = new JFrame();
    private Memo memo = new Memo(this);

    public Controller(StringIO inOut, String title) {
    	this.inOut = inOut;
    	JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(memo);
        frame.pack();
        frame.setVisible(true);
    }

	public void clear() {
		memo.clear();
	}

    public void save() {
    	inOut.write(memo.getText());
    }

    public void load() {
    	memo.setText(inOut.read());
    }
}
