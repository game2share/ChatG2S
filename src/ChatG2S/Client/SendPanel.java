package ChatG2S.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ChatG2S.Client.Controller.ClicSend;

@SuppressWarnings("serial")
public class SendPanel extends JPanel implements ActionListener{
	
	private JButton sendButton = new JButton("Send");
	private JTextField talk = new JTextField();
	private Socket socket;
	
	public SendPanel(Socket socket){
		this.socket = socket;
		this.setBackground(Color.DARK_GRAY);
		BorderLayout myLayout = new BorderLayout();
		myLayout.setHgap(10);
		myLayout.setVgap(10);
		setLayout(myLayout);
		this.add(talk, BorderLayout.CENTER);
		this.add(sendButton, BorderLayout.EAST);
		
		ClicSend cSend;
		try {
			cSend = new ClicSend(this);
			sendButton.addActionListener(cSend);
			talk.addActionListener(cSend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ErrorJFrame();
		}
	}
	
	
	public JButton getSendButton(){
		return sendButton;
	}
	
	public void setSendButton(JButton button){
		sendButton = button;
	}
	
	public JTextField getTalk(){
		return talk;
	}
	
	public void setTalk(JTextField field){
		talk = field;
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
