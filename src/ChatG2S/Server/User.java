package ChatG2S.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User implements Runnable {
	private Socket socket;
	private BufferedReader in;
	private ArrayList<Socket> Users;
	private ArrayList<String> Usernames;
	private String username;
	
	public User(Socket socket, boolean run, ArrayList<Socket> Users, ArrayList<String> Usernames) {
	  synchronized(Users){
		  this.Users = Users;
		  this.Usernames = Usernames;
	  }
	  this.socket = socket;
	  try {
		in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		SendUsernames();
		Entermessage();
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		  Users.remove(socket);
	  }
	  
	}

	@Override
	public void run() {
	  while(true){
		try {
			String str = in.readLine();
			
			if(str.isEmpty()) {System.out.println("C'est pas utile !!");}else{
				JSONObject jsonMessage = new JSONObject(str);
				if("changeUsername".equals(jsonMessage.getString("type"))){
					jsonMessage.put("prevUsername", username);
					Usernames.remove(username);
					username = jsonMessage.getString("username");
					Usernames.add(username);
					synchronized(Users){
						for(Socket sock : Users){
							new PrintWriter(sock.getOutputStream(), true).println(jsonMessage.toString());
						}
					}				
					SendUsernamesAll();
				}else{
					jsonMessage.put("username", username);
					System.out.println(jsonMessage.toString());
					synchronized(Users){
						for(Socket sock : Users){
							new PrintWriter(sock.getOutputStream(), true).println(jsonMessage.toString());
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
					
			try {	
				synchronized(Users){	
					Users.remove(socket);
					socket.close();
				}
				EndMessage();
				synchronized(Usernames){
					Usernames.remove(username);
				}
				SendUsernamesAll();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Thread terminé");
			break;
		}
	  }
	}
	
	 
	private void Entermessage() throws JSONException, IOException {
		Date date = new Date();
		username = in.readLine();
		synchronized(Usernames){
			Usernames.add(username);
		}
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("type", "meta");
		jsonMessage.put("username", username);
		jsonMessage.put("datetime", date.toString());
		jsonMessage.put("message", "connected");
		System.out.println(jsonMessage.toString());
		synchronized(Users){
			for(Socket sock : Users){
				new PrintWriter(sock.getOutputStream(), true).println(jsonMessage.toString());
			}
		}
	}
	
	private void SendUsernames() throws JSONException, IOException{
		JSONArray jsonUsernameArray = new JSONArray();
		
		synchronized(Usernames){
			for(String Username : Usernames){
				jsonUsernameArray.put(new JSONObject().put("username", Username));
			}
		}
		
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("type", "userlist");
		jsonMessage.put("users", jsonUsernameArray);
		System.out.println(jsonMessage.toString());
		new PrintWriter(socket.getOutputStream(), true).println(jsonMessage.toString());
	}
	
	private void SendUsernamesAll() throws JSONException, IOException{
		JSONArray jsonUsernameArray = new JSONArray();
		
		synchronized(Usernames){
			for(String Username : Usernames){
				jsonUsernameArray.put(new JSONObject().put("username", Username));
			}
		}
		
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("type", "userlist");
		jsonMessage.put("users", jsonUsernameArray);
		System.out.println(jsonMessage.toString());

		synchronized(Users){
			for(Socket sock : Users){
				new PrintWriter(sock.getOutputStream(), true).println(jsonMessage.toString());
			}
		}
	}
	
	private void EndMessage() throws JSONException, IOException{
		Date date = new Date();
		JSONObject jsonMessage = new JSONObject();
		jsonMessage.put("type", "meta");
		jsonMessage.put("username", username);
		jsonMessage.put("datetime", date.toString());
		jsonMessage.put("message", "disconnected");
		System.out.println(jsonMessage.toString());
		synchronized(Users){
			for(Socket sock : Users){
				new PrintWriter(sock.getOutputStream(), true).println(jsonMessage.toString());
			}
		}
	}
}
