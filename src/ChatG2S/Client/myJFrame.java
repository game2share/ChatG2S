package ChatG2S.Client;
import java.net.Socket;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class myJFrame extends JFrame{
	
	private Socket socket;
	
	public myJFrame(Socket socket){
		
		this.socket = socket;
		this.setTitle("ChatG2S");
		this.setSize(400,600);
		//Nous demandons maintenant à notre objet de se positionner au centre
	    this.setLocationRelativeTo(null);
	    myJPanel panel = new myJPanel(socket);
	    this.setContentPane(panel);
	    
	    //Termine le processus lorsqu'on clique sur la croix rouge
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}
	
	public Socket getSocket(){
		return socket;
	}
}
