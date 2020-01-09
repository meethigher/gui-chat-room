package gui.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
	/**
	 * �洢�������ߵĿͻ���
	 */
	public static ArrayList<Socket> sockets = new ArrayList<Socket>();

	/**
	 * ���������ߵĿͻ��˷����¿ͻ�������֪ͨ
	 * 
	 * @param socket
	 */
	public static void onlineInform(Socket socket) {
		try {
			BufferedWriter bw = null;
			String user = socket.getInetAddress() + ":" + socket.getPort() + "���û���������";
			for (Socket s : ChatServer.sockets) {
				bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				bw.write(user);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����̣߳�����ʵ�ֽ��ܿͻ��˷���������Ϣ��Ȼ���·����������߿ͻ��ˡ�
	 * @param socket
	 * @return
	 */
	public static void sendMessage(Socket socket) {
		new Thread(new ChatServerRunnable(socket)).start();
	}

	/**
	 * ��������
	 * 
	 * @param port
	 * @return
	 */
	public static ServerSocket openServer(int port) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return server;
	}

	/**
	 * ���з���
	 * 
	 * @param server
	 */
	public static Thread runServer(ServerSocket server) {
		Thread t=new Thread(() -> {
			while (!server.isClosed()) {
				Socket socket = null;
				try {
					socket = server.accept();
					if(socket!=null) {
						onlineInform(socket);
						sockets.add(socket);
						sendMessage(socket);
					}else {
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
					break;
				} 
			}
		});
		t.start();
		return t;
	}
}
