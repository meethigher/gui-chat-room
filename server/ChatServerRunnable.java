package gui.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * ����ʵ�ַ������ͻ��˷�����Ϣ
 * 
 * @author kitchen
 *
 */
public class ChatServerRunnable implements Runnable {
	private Socket socket;

	public ChatServerRunnable(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			String user = socket.getInetAddress() + ":" + socket.getPort() + "���û�";
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = null;
			String message = null;
			String exit = null;
			ArrayList<Socket> sockets = ChatServer.sockets;
			while (true) {
				try {
					message = br.readLine();
					socket.sendUrgentData(0);
				} catch (SocketException e) {
					exit = "�˳�����";
					socket.close();
					sockets.remove(socket);
				}
				for (Socket s : sockets) {
					bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
					if (exit != null) {
						bw.write(user + exit);
					} else {
						bw.write(user + "��" + message);
					}
					bw.newLine();
					bw.flush();
				}
				if (exit != null) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
