package ChatG2S.Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Settings implements Serializable {
	private static String server;
	private static int port;
	private static String username;
	
	private Settings(){
		server = "188.165.231.116";
		port = 1234;
		username = "Unknown";
	}
	
	public static class CheckSettings{
		
		@SuppressWarnings("finally")
		public static Settings CheckFile(){
			File settingsFile = new File("./ChatG2S_Settings.json");
			if(settingsFile.exists()){
				Settings settings = new Settings();
				
			    try {
			      BufferedReader br = new BufferedReader(new FileReader(settingsFile));
			      JSONObject jsonSettings = new JSONObject(br.readLine());
			      br.close();
			      Settings.username = jsonSettings.getString("username");
			      Settings.port = jsonSettings.getInt("port");
			      Settings.server = jsonSettings.getString("server");
			    }catch (IOException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					new ErrorJFrame();
				}
				
				return settings;
			}else{
				try {				
					
				    FileOutputStream oos;
				    try {
				      oos = new FileOutputStream(settingsFile);
				        	
				      Settings settings = new Settings();
				      oos.write(settings.toString().getBytes("UTF-8"));
				      oos.close();
					  return settings;
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						new ErrorJFrame();
					}
				} finally{
					Settings settings = new Settings();
				    return settings;
				}
			}
		}
		
		public static String GetServer() throws JSONException{
			CheckFile();
			return server;
		}
		
		public static int GetPort() throws JSONException{
			CheckFile();
			return port;
		}
		
		public static String GetUsername(){
			CheckFile();
			return username;
		}
		
		public static void SetUsername(String newusername){
			File settingsFile = new File("./ChatG2S_Settings.json");
			
				try {				
					Settings settings = CheckFile();
				    FileOutputStream oos;
				    try {
				      oos = new FileOutputStream(settingsFile);				        	
				      
				      settings.setUsername(newusername);
				      oos.write(settings.toString().getBytes("UTF-8"));
				      oos.close();
					  
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						new ErrorJFrame();
					}
				} finally{
				}
			}
			
		
	}
	
	public String toString(){
		JSONObject settingsJson = new JSONObject();		
		try {
			settingsJson.put("username", username);
			settingsJson.put("server", server);
			settingsJson.put("port", port);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ErrorJFrame();
		}
		return settingsJson.toString();
	}
	
	private void setUsername(String username){
		Settings.username = username;
	}
}
