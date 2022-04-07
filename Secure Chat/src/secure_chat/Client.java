package secure_chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

public class Client {

	private String key = "EncryptionKey";

	public Client() throws Exception {

		DESE encryptor = new DESE(key);
		Socket socket = new Socket("localhost", 2021);
		BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		Gui gui = new Gui();
		gui.chat.append("Server message:   " + in_socket.readLine());
		gui.send.addActionListener(e -> {
			if (!gui.message.getText().isEmpty() && !gui.to.getText().isEmpty()) {
				String messageSent = gui.message.getText();
				String encrypted = encryptor.encrypt(messageSent);
				gui.chat.append("\nYou sent message " + gui.to.getText() + " :" + gui.message.getText());
				out_socket.println(encrypted + "1111111111" + gui.to.getText());
				gui.message.setText(null);
				gui.to.setText(null);
			}
		});
		gui.setVisible(true);

		while (true) {
			String data = in_socket.readLine();
			String[] split = data.split("1111111111");
			String message = split[0];
			String to = split[1];
			String decrypted = encryptor.decrypt(message);

			gui.chat.append("\n" + to + ": " + decrypted);

			if (decrypted.equalsIgnoreCase("QUIT")) {
				socket.close();
				in_socket.close();
				out_socket.close();
				gui.setVisible(false);
			}

		}
	}

	public static void main(String[] args) {

		try {
			new Client();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
