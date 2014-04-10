package ChatG2S.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
	
	public boolean run = true;
	public ArrayList<Socket> Users = new ArrayList<Socket>();
	public ArrayList<String> Usernames = new ArrayList<String>();

	public Server(){
		try{
			ServerSocket ssock = new ServerSocket(1234);
			System.out.println("Server is listening");
			while(run){
				Socket socket = ssock.accept();
				synchronized(Users){
					Users.add(socket);
				}
				new Thread(new User(socket, run, Users, Usernames)).start();
			}
			ssock.close();
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args){
		new Server();
	}
}
