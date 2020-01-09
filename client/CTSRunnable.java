package gui.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 用来实现客户端向服务端发送消息
 * 
 * @author kitchen
 *
 */
public class CTSRunnable implements Runnable {
	private Socket socket;
	private String message;

	public CTSRunnable(Socket socket, String message) {
		this.socket = socket;
		this.message = message;
	}

	@Override
	public void run() {

		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			if (!message.equals("")) {
				bw.write(message);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
