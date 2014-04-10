package ChatG2S.Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import ChatG2S.Client.ErrorJFrame;
import ChatG2S.Client.SendPanel;

public class ClicSend implements ActionListener {
	private SendPanel sendPanel;
	private Socket socket;
	private PrintWriter out;
	
	public ClicSend(SendPanel sendPanel) throws IOException{
		this.sendPanel = sendPanel;
		this.socket = sendPanel.getSocket();
		out = new PrintWriter(socket.getOutputStream(), true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("".equals(sendPanel.getTalk().getText())){
			
		}else{
			try {
				out.println(parseMessage(sendPanel.getTalk().getText()));
			} catch (JSONException e1) {
				e1.printStackTrace();
				new ErrorJFrame();
			}
			sendPanel.getTalk().setText("");	
		}
	}
	
	private String parseMessage(String message) throws JSONException{
		Date date = new Date();
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("type", "message");
		jsonMessage.put("datetime", date.toString());
		jsonMessage.put("message", message);
		return jsonMessage.toString();
	}

}
