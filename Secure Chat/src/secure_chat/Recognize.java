package secure_chat;


import secure_chat.ServerThread;

public class Recognize {	//da znamo koji je korisnik prijavljen na kom threadu 
	ServerThread thread;
	int num;
	
	public Recognize(ServerThread thread, int num) {
		this.thread= thread;
		this.num=num;
	}
}
