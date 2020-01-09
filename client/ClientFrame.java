package gui.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import gui.client.CheckIP;

public class ClientFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Socket socket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame frame = new ClientFrame();
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
	public ClientFrame() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ClientFrame.class.getResource("/gui/\u5BA2\u6237\u7AEF.png")));
		setTitle("\u5BA2\u6237\u7AEF");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 300,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u804A\u5929\u8BB0\u5F55");
		lblNewLabel.setBounds(10, 0, 241, 21);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 334, 331);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JPanel panel = new JPanel();
		panel.setBounds(354, 20, 220, 331);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblip = new JLabel("\u670D\u52A1\u5668ip");
		lblip.setBounds(10, 10, 71, 15);
		panel.add(lblip);

		textField = new JTextField();
		textField.setBounds(83, 7, 127, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("\u7AEF\u53E3");
		label.setBounds(10, 51, 71, 15);
		panel.add(label);

		textField_1 = new JTextField();
		textField_1.setBounds(83, 48, 127, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("\u53D1\u9001\u6D88\u606F");
		btnNewButton.setEnabled(false);

		btnNewButton.setBounds(10, 297, 200, 23);
		panel.add(btnNewButton);

		JButton button = new JButton("\u4E0A\u7EBF");
		button.setBounds(10, 88, 93, 23);
		panel.add(button);

		JButton button_1 = new JButton("\u4E0B\u7EBF");

		button_1.setEnabled(false);
		button_1.setBounds(117, 88, 93, 23);
		panel.add(button_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 121, 200, 168);
		panel.add(scrollPane_1);

		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JButton button_2 = new JButton("\u6E05\u7A7A\u8BB0\u5F55");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		button_2.setBounds(251, 0, 93, 21);
		contentPane.add(button_2);

		/*
		 * 上线按钮事件
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String host = textField.getText();
					if (CheckIP.isIP(host)) {
						int port = Integer.parseInt(textField_1.getText());
						socket = ChatClient.openClient(host, port);
						// 客户端接收消息
						ChatClient.receiveMessage(socket, textArea);
						button.setEnabled(false);
						button_1.setEnabled(true);
						btnNewButton.setEnabled(true);
					} else
						JOptionPane.showMessageDialog(null, "请输入正确的ip地址");

				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(null, "请确定是否输入了正确的ip地址和端口");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "连接服务端失败");
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "请输入正确的端口号");
				} catch (HostFormatException e3) {
					JOptionPane.showMessageDialog(null, "请输入正确的ip地址");
				}

			}
		});
		/*
		 * 下线按钮事件
		 */
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);//此处本来是想通过关闭socket，而不关闭应用程序，但是存在bug尚未解决。所以改成了这个
			}
		});
		/*
		 * 发送消息按钮事件
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = textArea_1.getText().trim();
				ChatClient.sendMessage(socket, message);
				textArea_1.setText("");
			}
		});
	}

}
