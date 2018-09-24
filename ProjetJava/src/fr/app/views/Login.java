package fr.app.views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import fr.app.batailleNavale.domain.User;
import fr.app.batailleNavale.domain.UserType;
import fr.app.sockets.SocketClient;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Login extends JPanel {
	
	private static final String ACTION = "AUTHENTIFICATION";
	
	private JTextField textField_Login;
	private JTextField textField_password;

	/**
	 * Create the panel.
	 */
	public Login() {
		JFrame main = new JFrame("Projet ESIEA - 2018-2019 - Bataille Navale");
		main.add(this);
		main.setSize(350, 250);
		main.setLocationRelativeTo(null);
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblBattailleNavaleEsiea = new JLabel("Battaille Navale ESIEA 2018 :");
		lblBattailleNavaleEsiea.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblLogin = new JLabel("Login :");
		
		JLabel lblPassword = new JLabel("Password :");
		
		textField_Login = new JTextField();
		textField_Login.setColumns(10);
		
		textField_password = new JTextField();
		textField_password.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SocketClient.getInstance();
					SocketClient.envoyerData(SocketClient.getInstance().getSocket(), ACTION, textField_Login.getText() + "///" + textField_password.getText());
					
					String Action = SocketClient.recevoirData(SocketClient.getInstance().getSocket(), String.class);
					User u = SocketClient.recevoirData(SocketClient.getInstance().getSocket(), User.class);
					SocketClient.setConnected(u);
					
					JFrame frame = new JFrame("Projet ESIEA - 2018-2019 - Bataille Navale");
					
					if (u.getType().equals(UserType.Admin)) {
						AdminHome adminHome = new AdminHome();
						frame.add(adminHome);
						frame.setSize(adminHome.getPreferredSize().width, adminHome.getPreferredSize().height);
					} else {
						
					}
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					main.dispose();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_password)
								.addComponent(textField_Login, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(lblBattailleNavaleEsiea))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(114)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBattailleNavaleEsiea)
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogin)
						.addComponent(textField_Login, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textField_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
