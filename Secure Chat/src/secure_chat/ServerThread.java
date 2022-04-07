package secure_chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{

	Socket socket;
	Server server;
	int num;
	
	public ServerThread(Socket socket,Server server, int number) {
		this.server=server;
		this.socket = socket;
		this.num = number;
	}
	
	@Override
	public void run() {
		
		try {
			BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			
			out_socket.println("You've been connected to the chat. Your number is: " + num);
			
			while(true) {
				String data = in_socket.readLine(); 
				System.out.println(data);
				String[] split = data.split("1111111111"); 
				String message = split[0];
				System.out.println(message);
				String to = split[1];    
				System.out.println(to);
				int number = Integer.valueOf(to);
				for(Recognize r : server.getThreads()) {
					if(r.num== number) {
						r.thread.Send(message + "1111111111" + String.valueOf(number));
						break;
					}
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void Send(String str) {
		try {
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			out_socket.println(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
