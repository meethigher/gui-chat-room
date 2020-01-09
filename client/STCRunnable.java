package gui.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 用来实现服务端向客户端下发消息
 * @author kitchen
 *
 */
public class STCRunnable implements Runnable {
	private Socket socket;
	private JTextArea textArea;
	public STCRunnable(Socket socket,JTextArea textArea) {
		this.socket=socket;
		this.textArea=textArea;
	}
	@Override
	public void run() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message=null;
			boolean isOnline=true;
			while(isOnline) {
				try {
					message=br.readLine();
					textArea.append(message);
					textArea.append(System.getProperty("line.separator"));
				} catch (SocketException e) {
					socket.close();
					isOnline=false;
					break;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
