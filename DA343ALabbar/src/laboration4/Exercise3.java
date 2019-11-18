package laboration4;

import java.awt.Dimension;

import javax.swing.*;

public class Exercise3 extends Thread {
	
	private String[] messages;
	private JLabel lblText;
	private long pause;
	private boolean argumentsOk;
	private int index = 0;
	

	public Exercise3(String[] strings) {
		this(strings, 1000);
	}

	public Exercise3(String[] strings, long pause) {
		this.strings = strings.clone();
		this.pause = pause;	
		
	}

	public static void main(String[] args) { 
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				JLabel lblText = new JLabel();
				String[] texter = {"Det är två månader kvar på året",
						"23 * 6735 = 154905",
				"Den 28 oktober har Simone namnsdag"};
				lblText.setPreferredSize(new Dimension(400,40));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(lblText);
				frame.pack();
				frame.setVisible(true);
				Exercise3 ex3 = new Exercise3(texter, lblText, 3000);
				ex3.start(); 
				}
		}); 
	}
}
