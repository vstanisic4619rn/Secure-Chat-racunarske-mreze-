package secure_chat;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public ArrayList<Recognize> threads = new ArrayList<Recognize>();	//lista korisnika
	int user_num= 1;
	
	public Server()throws Exception{
		
		ServerSocket ss = new ServerSocket(2021);
		
		while(true) {
			Socket socket  = ss.accept();
			ServerThread st = new ServerThread(socket, this,user_num);
			Thread thread = new Thread(st);
			threads.add(new Recognize(st,user_num++));
			thread.start();
		}
	}
	
	public static void main(String[] args) {
		try {
			new Server();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<Recognize> getThreads() {
		return threads;
	}
	
	
}
