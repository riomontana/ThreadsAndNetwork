package laboration7;

import javax.swing.*;

public class Exercise4 extends JPanel {
	
	private JFrame frame = new JFrame();
	private JPanel pnlOp = new JPanel();
	private JLabel lblOp1 = new JLabel("Operand 1");
	private JLabel lblOp2 = new JLabel("Operand 2");
	private JTextField tfOp1 = new JTextField();
	private JTextField tfOp2 = new JTextField();
	private JRadioButton rbAdd = new JRadioButton("+");
	private JRadioButton rbSub = new JRadioButton("-");
	private JRadioButton rbMult = new JRadioButton("x");
	private JRadioButton rbDiv = new JRadioButton("/");
	private ButtonGroup btnGroup = new ButtonGroup();
	private JButton btnCalc = new JButton("BERÄKNA");
	
	public Exercise4() {
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
}
