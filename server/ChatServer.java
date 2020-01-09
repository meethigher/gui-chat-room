package gui.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
	/**
	 * 存储所有在线的客户端
	 */
	public static ArrayList<Socket> sockets = new ArrayList<Socket>();

	/**
	 * 给所有在线的客户端发送新客户端上线通知
	 * 
	 * @param socket
	 */
	public static void onlineInform(Socket socket) {
		try {
			BufferedWriter bw = null;
			String user = socket.getInetAddress() + ":" + socket.getPort() + "的用户进入聊天";
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
	 * 开启线程，用来实现接受客户端发过来的消息，然后下发给所有在线客户端。
	 * @param socket
	 * @return
	 */
	public static void sendMessage(Socket socket) {
		new Thread(new ChatServerRunnable(socket)).start();
	}

	/**
	 * 开启服务
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
	 * 运行服务
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
