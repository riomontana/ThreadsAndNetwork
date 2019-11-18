package laboration9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

public class CalcUI extends JPanel implements ActionListener {
private JButton btnCalculate = new JButton("Beräkna");
private JLabel lblTitle = new JLabel("Inmatning av matematiska uttryck");
private JTextArea taResult = new JTextArea("");
private JLabel lblOp1 = new JLabel("Operand 1:");
private JLabel lblOp2 = new JLabel("Operand 2:");
private JTextField tfNbr1 = new JTextField();
private JTextField tfNbr2 = new JTextField();
private JRadioButton rbAdd = new JRadioButton("+");
private JRadioButton rbSub = new JRadioButton("-");
private JRadioButton rbMul = new JRadioButton("*");
private JRadioButton rbDiv = new JRadioButton("/");
private ButtonGroup group = new ButtonGroup();
private CalcController controller;

/** Creates new form UserInput */

public CalcUI(CalcController controller) {
this.controller = controller; setLayout(new BorderLayout());
lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
taResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));
taResult.setPreferredSize(new Dimension(400,40));
btnCalculate.addActionListener(this); 
add(lblTitle,BorderLayout.NORTH);
add(operationPanel(), BorderLayout.EAST); 
add(centerPanel(), BorderLayout.CENTER);
add(taResult, BorderLayout.SOUTH);
}

private JPanel centerPanel() {
JPanel panel = new JPanel(new BorderLayout()); panel.add(operandPanel(),BorderLayout.CENTER); panel.add(btnCalculate,BorderLayout.SOUTH); return panel;
}
private JPanel operandPanel() {
JPanel panel = new JPanel(new GridLayout(2,2)); panel.setBorder(BorderFactory.createTitledBorder("Operander")); tfNbr1.setHorizontalAlignment(JTextField.RIGHT); tfNbr1.setText("0"); tfNbr2.setHorizontalAlignment(JTextField.RIGHT); tfNbr2.setText("0");
panel.add(lblOp1);
panel.add(tfNbr1);
panel.add(lblOp2);
panel.add(tfNbr2);
return panel;
}
private JPanel operationPanel() {
JPanel panel = new JPanel(new GridLayout(4,1)); panel.setPreferredSize(new Dimension(60,50)); panel.setBorder(javax.swing.BorderFactory.createTitledBorder("")); rbAdd.setSelected(true);
group.add(rbAdd);
group.add(rbSub);
group.add(rbMul);
group.add(rbDiv);
panel.add(rbAdd);
panel.add(rbSub);
panel.add(rbMul);
panel.add(rbDiv);
return panel;
}
public void actionPerformed(ActionEvent e) {
String operation;
if (rbAdd.isSelected()) {
operation = "+";
} else if (rbSub.isSelected()) {operation = "-";
} else if (rbMul.isSelected()) {
operation = "*"; } else {
operation = "/"; }
2016/2017
controller.newCalculation(tfNbr1.getText(), tfNbr2.getText(), operation); }
public void setResult(String result) { taResult.setText(result);
} }
public class CalcController {
private CalcClient client;
private CalcUI ui = new CalcUI(this);
private void showCalcUI() { SwingUtilities.invokeLater(new Runnable() {
public void run() {
JFrame frame = new JFrame("Client"); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.add(ui);
frame.pack();
frame.setVisible(true);
} });
}
public CalcController(CalcClient client) { this.client = client; client.setCalcController(this); showCalcUI();
}
public void newCalculation(String nbr1, String nbr2, String operation) {
try { client.newCalculation(nbr1+","+nbr2+","+operation);
} catch (IOException e) {
// TODO Auto-generated catch block e.printStackTrace();
} }
public void newResponse(final String response) { SwingUtilities.invokeLater(new Runnable() {
public void run() { ui.setResult(response);
} });
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