package ChatG2S.Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import ChatG2S.Client.Settings.CheckSettings;
import ChatG2S.Client.ErrorJFrame;
import ChatG2S.Client.UsernamePanel;

public class ClicUsername implements ActionListener {

	private UsernamePanel usernamePanel;
	private Socket socket;
	private PrintWriter out;
	
	public ClicUsername(UsernamePanel panel) throws IOException{
		usernamePanel = panel;
		socket = panel.getSocket();
		out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("".equals(usernamePanel.getUsernameField().getText())){
			System.out.println("Add a username.");
		}else{
			try {
				CheckSettings.SetUsername(usernamePanel.getUsernameField().getText());
				out.println(parseMessage(usernamePanel.getUsernameField().getText()));
			} catch (JSONException e1) {
				e1.printStackTrace();
				new ErrorJFrame();
			}	
		}
		
	}
	
	private String parseMessage(String message) throws JSONException{
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("type", "changeUsername");
		jsonMessage.put("username", usernamePanel.getUsernameField().getText());
		return jsonMessage.toString();
	}

}
