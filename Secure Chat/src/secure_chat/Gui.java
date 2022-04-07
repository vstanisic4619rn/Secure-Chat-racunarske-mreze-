package secure_chat;




import java.awt.Color;

import javax.swing.*;

public class Gui extends JFrame{		//graficko okruzenje za aplikaciju 
	
	JTextArea chat = new JTextArea();
	JTextField message = new JTextField();
	JButton send = new JButton("Send");
	JLabel towho = new JLabel("To:");
	JLabel from_message= new JLabel("Messege:");
	JTextField to = new JTextField();
	
	public Gui() {
		this.setTitle("Secure Chat--> Encrypted");
		this.setSize(600,600);
		this.setLayout(null);
		chat.setEditable(false);
		send.setBounds(300,480,100,40);
		
		chat.setBounds(20,50,500,300);
		message.setBounds(20,400,500,60);
		to.setBounds(70,480,100,50);
		towho.setBounds(20,490,60,60);
		
		this.add(send);
		this.add(chat);
		this.add(from_message);
		this.add(message);
		this.add(to);
		this.add(towho);
		
		this.getContentPane().setBackground(Color.CYAN);
		send.setBackground(Color.ORANGE);
		send.setForeground(Color.GREEN);
	}
}
