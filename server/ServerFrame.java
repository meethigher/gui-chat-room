package gui.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ServerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ServerSocket server = null;
	private Thread t=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame frame = new ServerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerFrame() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ServerFrame.class.getResource("/gui/\u670D\u52A1\u7AEF.png")));
		setResizable(false);
		setTitle("\u670D\u52A1\u7AEF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 235 / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150, 235, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u7AEF\u53E3");
		label.setBounds(10, 10, 82, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(102, 7, 107, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton button = new JButton("\u5F00\u542F\u670D\u52A1");
		button.setBounds(10, 35, 199, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("\u5173\u95ED\u670D\u52A1");
		button_1.setEnabled(false);
		button_1.setBounds(10, 68, 199, 23);
		contentPane.add(button_1);
		/*
		 * 开启服务按钮事件
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int port = Integer.parseInt(textField.getText().trim());
					server = ChatServer.openServer(port);
					t=ChatServer.runServer(server);
					button.setEnabled(false);
					button_1.setEnabled(true);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "请输入正确的端口号");
				}
			}
		});
		/*
		 * 关闭服务按钮事件
		 */
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (server != null) {
						server.close();
						t.stop();
						button.setEnabled(true);
						button_1.setEnabled(false);
					}
				} catch (IOException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "服务关闭失败");
				}
			}
		});
	}

}
