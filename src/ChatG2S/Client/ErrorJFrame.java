package ChatG2S.Client;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ErrorJFrame extends JFrame {
	
	public ErrorJFrame(){
		this.setTitle("ChatG2S - Error");
		this.setSize(400,100);
		//Nous demandons maintenant à notre objet de se positionner au centre
	    this.setLocationRelativeTo(null);
	    ErrorJPanel errPanel = new ErrorJPanel();
	    this.setContentPane(errPanel);
	    
	    //Termine le processus lorsqu'on clique sur la croix rouge
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}
	
}
