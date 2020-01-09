package gui.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JTextArea;

import gui.client.CTSRunnable;
import gui.client.STCRunnable;

public class ChatClient {
	/**
	 * 客户端连接服务端
	 * @param host
	 * @param port
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static Socket openClient(String host,int port) throws UnknownHostException, IOException {
		Socket socket=new Socket(host,port);
		return socket;
	}
	/**
	 * 开启线程用来接收服务端下发的消息
	 * @param socket
	 * @param textArea
	 */
	public static void receiveMessage(Socket socket,JTextArea textArea) {
		Thread t=new Thread(new STCRunnable(socket,textArea));
		t.start();
	}
	/**
	 * 开启线程用来向服务端发送消息
	 * @param socket
	 * @param message
	 */
	public static void sendMessage(Socket socket,String message) {
		Thread t=new Thread(new CTSRunnable(socket,message));

		t.start();
	}
	
	/**
	 * 关闭客户端连接
	 * @param socket
	 */
	public static void closeClient(Socket socket) {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
