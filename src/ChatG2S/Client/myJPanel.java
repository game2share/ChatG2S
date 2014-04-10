package ChatG2S.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ChatG2S.Client.Listener.MessageListener;

@SuppressWarnings("serial")
public class myJPanel extends JPanel implements ActionListener{
	
	private JTextArea conversation = new JTextArea();
	private JTextArea metaDataArea = new JTextArea();
	private Socket socket;
	private SendPanel sendPanel;
	private UsernamePanel usernamePanel;
	
	public myJPanel(Socket socket){
		this.socket = socket;
		sendPanel = new SendPanel(socket);
		usernamePanel = new UsernamePanel(socket);
		this.setBackground(Color.DARK_GRAY);
		BorderLayout myLayout = new BorderLayout();
		myLayout.setHgap(10);
		myLayout.setVgap(10);
		setLayout(myLayout);
		conversation.setEditable(false);
		conversation.setLineWrap(true);
		JScrollPane ascenceur = new JScrollPane(conversation);
		ascenceur.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ascenceur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		metaDataArea.setEditable(false);
		this.add(ascenceur, BorderLayout.CENTER);
		this.add(metaDataArea, BorderLayout.EAST);
		this.add(usernamePanel, BorderLayout.NORTH);
		this.add(sendPanel, BorderLayout.SOUTH);	
		
		new Thread(new MessageListener(this)).start();		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public JTextArea getConversation(){
		return conversation;
	}
	
	public void setConversation(JTextArea area){
		conversation = area;
	}
	
	public JTextArea getMetaDataArea(){
		return metaDataArea;
	}
	
	public void setMetaDataArea(JTextArea area){
		metaDataArea = area;
	}
	
	public Socket getSocket(){
		return socket;
	}
}
