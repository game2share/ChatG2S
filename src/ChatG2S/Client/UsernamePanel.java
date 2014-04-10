package ChatG2S.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ChatG2S.Client.Settings.CheckSettings;
import ChatG2S.Client.Controller.ClicUsername;

@SuppressWarnings("serial")
public class UsernamePanel extends JPanel implements ActionListener {
	
	private JLabel labelUsername = new JLabel("Username : ");
	private JButton changeButton = new JButton("Change");
	private JTextField usernameField = new JTextField(CheckSettings.GetUsername());
	private Socket socket;
	
	public UsernamePanel(Socket socket){
		this.socket = socket;
		this.setBackground(Color.DARK_GRAY);
		BorderLayout myLayout = new BorderLayout();
		myLayout.setHgap(10);
		myLayout.setVgap(10);
		setLayout(myLayout);
		this.add(labelUsername, BorderLayout.WEST);
		this.add(usernameField, BorderLayout.CENTER);
		this.add(changeButton, BorderLayout.EAST);
		
		ClicUsername cUsername;
		try {
			cUsername = new ClicUsername(this);
			changeButton.addActionListener(cUsername);
			usernameField.addActionListener(cUsername);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ErrorJFrame();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public JButton getChangeButton(){
		return changeButton;
	}
	
	public void setChangeButton(JButton button){
		changeButton = button;
	}
	
	public JTextField getUsernameField(){
		return usernameField;
	}
	
	public void setUsernameField(JTextField field){
		usernameField = field;
	}

}
