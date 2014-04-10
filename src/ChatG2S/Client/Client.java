package ChatG2S.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;

import ChatG2S.Client.Settings.CheckSettings;

public class Client {
	
	public Client(){
		try {
			Socket socket = new Socket(CheckSettings.GetServer(), CheckSettings.GetPort());
			new PrintWriter(socket.getOutputStream(), true).println(CheckSettings.GetUsername());
			new myJFrame(socket);
		} catch (IOException | JSONException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ErrorJFrame();
		}
	}

	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub
		new Client();
	}

}