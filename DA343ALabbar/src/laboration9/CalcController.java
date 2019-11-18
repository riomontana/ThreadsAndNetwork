package laboration9;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CalcController {
	private CalcClient client;
	private CalcUI ui = new CalcUI(this);
	private void showCalcUI() { SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			JFrame frame = new JFrame("Client"); 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			frame.add(ui);
			frame.pack();
			frame.setVisible(true);
		} 
		});
	}
	public CalcController(CalcClient client) {
		this.client = client; client.setCalcController(this);
		showCalcUI();
	}
	public void newCalculation(String nbr1, String nbr2, String operation) {
		try { client.newCalculation(nbr1+","+nbr2+","+operation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void newResponse(final String response) { SwingUtilities.invokeLater(new Runnable() {
		public void run() { ui.setResult(response);
		} 
		});
	}
	public static void main(String[] args) {
		try {
			CalcClient clientA = new CalcClientA("127.0.0.1",3450); new CalcController(clientA);
			// CalcClient clientB = new CalcClientB("127.0.0.1",3451);
			// new CalcController(clientB);
			// CalcClient clientC = new CalcClientC("127.0.0.1",3452);
			// new CalcController(clientC);
			// CalcClient clientD = new CalcClientD("127.0.0.1",3453);
			// new CalcController(clientD);
		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
	}
}