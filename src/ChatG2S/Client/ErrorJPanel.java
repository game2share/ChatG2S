package ChatG2S.Client;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ErrorJPanel extends JPanel {
	
	public ErrorJPanel(){
		String errorMessage = "<html>ChatG2S failed after an unexpected error.<br>You can check your ChatG2SSettings.json <br>or contact the server admin to fix this problem.</html>";
		JLabel error = new JLabel(errorMessage);
		
		this.add(error);
	}
}
