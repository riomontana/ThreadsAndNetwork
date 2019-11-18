package laboration7;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;

public class Exercise1 {

	private JFrame frame = new JFrame();
	private JLabel lblIpAdress = new JLabel("IP= ");
	private JLabel lblName = new JLabel("Name= ");
	private JLabel lblHostName = new JLabel("Host name:");
	private JTextField tfHostName = new JTextField(); 
	private JLabel lblHostIp = new JLabel("Host ip: ");
	private JButton btnGetIp = new JButton("GET IP");

	public Exercise1() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3,1));
		frame.add(lblIpAdress);
		frame.add(lblName);
		frame.add(lblHostName);
		frame.add(tfHostName);
		frame.add(lblHostIp);
		frame.add(btnGetIp);
		btnGetIp.addActionListener(new IpListener());
		frame.setBounds(400, 400, 400, 150);
		frame.setVisible(true);

	}

	public void ipAdress() { 
		try {
			InetAddress myAddress = InetAddress.getLocalHost();
			InetAddress hostAddress = InetAddress.getByName(tfHostName.getText());
			lblIpAdress.setText("IP= " + myAddress.getHostAddress());
			lblName.setText("Name= " + myAddress.getHostName());
			lblHostIp.setText("Host ip: " + hostAddress.getHostAddress());
		}
		catch(UnknownHostException ex) {
		}
	}
	
	private class IpListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnGetIp) {
				ipAdress();
			}
		}		
	}
	
	public static void main(String[] args) {
		Exercise1 e1 = new Exercise1();
	}
}
