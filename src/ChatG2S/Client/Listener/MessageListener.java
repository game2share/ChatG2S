package ChatG2S.Client.Listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ChatG2S.Client.ErrorJFrame;
import ChatG2S.Client.myJPanel;

public class MessageListener implements Runnable{
	
	private Socket socket;
	private BufferedReader in;
	private myJPanel panel;
	
	public MessageListener(myJPanel panel){
		socket = panel.getSocket();
		this.panel = panel;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				JSONObject jsonMessage = new JSONObject(in.readLine());
				if("message".equals(jsonMessage.getString("type"))){
					String str = "[" + jsonMessage.getString("datetime") + "] " + jsonMessage.getString("username") + " told : " + jsonMessage.getString("message");
					panel.getConversation().append(str + "\r\n");
				}else if("meta".equals(jsonMessage.getString("type"))){
					String str = "[" + jsonMessage.getString("datetime") + "] " + jsonMessage.getString("username") + " is " + jsonMessage.getString("message");
					panel.getConversation().append(str + "\r\n");
					panel.getMetaDataArea().append(jsonMessage.getString("username") + "\r\n");
				}else if("userlist".equals(jsonMessage.getString("type"))){
					panel.getMetaDataArea().setText("");
					JSONArray jsonUsernameList = new JSONArray(jsonMessage.getString("users"));
					for(int i=0; i < jsonUsernameList.length(); i++){
						JSONObject user = jsonUsernameList.getJSONObject(i);
						panel.getMetaDataArea().append(user.getString("username") + "\r\n");
					}
				}else if("changeUsername".equals(jsonMessage.getString("type"))){
					String str = jsonMessage.getString("prevUsername") + " is now known as " + jsonMessage.getString("username");
					panel.getConversation().append(str + "\r\n");
				}
				panel.getConversation().setCaretPosition(panel.getConversation().getDocument().getLength());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				new ErrorJFrame();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				new ErrorJFrame();
			}
		}
		
	}

}
