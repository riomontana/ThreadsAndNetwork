package laboration8;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class QuizController {

	private QuizUI ui = new QuizUI(this);
	private QuizClient client;

	public QuizController() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(ui);
				frame.pack();
				frame.setVisible(true);
			}
		});
		client = new QuizClient(this);
	}


	// metoder  som QuizClient anropar

	public void newQuestion(String txt, int nbr) {
		ui.setText("Fråga: " + nbr + "\n" + txt);
		ui.enableAnswer();
	}

	public void newResponse(String txt, int nbr) {
		ui.appendText("\nResultat fråga " + nbr + ": " + txt);
		ui.enableQuestion();
	}

	public void connected(String txt) {
		ui.appendText("\n" + txt);
		ui.enableQuestion();
		ui.connected();
	}

	public void disconnected(String txt) {
		ui.setText(txt);
		disconnect();
	}


	// metoder som QuizUI anropar

	public void connect(String ip, String port) {
		try {
			client.connect(ip, Integer.parseInt(port));
			ui.setText("ansluter till: ip: " + ip + ", port= " + port + "\n");
		}	
		catch(Exception ex) {
			ui.setText(ex.toString());
		}
	}

	public void disconnect() {
		try {
			ui.disconnected();
			client.disconnect();
		}
		catch(IOException e2) { 
		}
	}

	public void question(String nbrStr) throws IOException {
		int nbr; 
		try {
			nbr = Integer.parseInt(nbrStr);
			client.question(nbr);
			ui.waitingForResponse();
		}
		catch(NumberFormatException e1) {
			ui.setText("Inmatning ska vara heltal (fråga nr): " + nbrStr);
		}
	}
	
	public void answer(String nbrStr, String answerStr) {
		try {
			int nbr = Integer.parseInt(nbrStr);
			int answer = Integer.parseInt(answerStr);
			client.answer(nbr, answer);
			ui.waitingForResponse();
		}
		catch(NumberFormatException e) {
			ui.appendText("\nText ska vara heltal, Fråga: " + nbrStr + ", Årtal: "
					+ answerStr);
		}
		catch(IOException e) {
			ui.setText(e.toString());
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				QuizController prog = new QuizController();
			}
		});
	}


}
