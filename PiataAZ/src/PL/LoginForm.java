package PL;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import BL.AdvertismentServices;
import BL.UserServices;
import Entities.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.security.NoSuchAlgorithmException;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public UserServices us;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		us = new UserServices();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log In:");
		lblLogIn.setBounds(167, 13, 56, 16);
		contentPane.add(lblLogIn);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(99, 58, 77, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(99, 96, 77, 16);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(188, 55, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 93, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel resultL = new JLabel("");
		resultL.setBounds(216, 142, 186, 16);
		contentPane.add(resultL);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = textField.getText();
				String pass = textField_1.getText();
				User u;
				try {
					u = us.login(user, pass);
					if(u==null)resultL.setText("Log In failed!");
					else {
						if(u.getRole().equals("admin")){
							AdminForm frame = new AdminForm(u);
							frame.setVisible(true);
							frame.setTitle("Welcome "+ user+"!");
							textField.setText("");
							textField_1.setText("");
						}else{
							UserForm frame = new UserForm(u);
							frame.setVisible(true);
							frame.setTitle("Welcome "+ user+"!");
							textField.setText("");
							textField_1.setText("");
						}
						System.out.println(u.getName()+" are rolul de: "+u.getRole());
						}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		});
		btnLogIn.setBounds(99, 138, 97, 25);
		contentPane.add(btnLogIn);
		
		JButton btnAddAdvertisment = new JButton("Add Advertisment");
		btnAddAdvertisment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientForm frame = new ClientForm();
				frame.setVisible(true);
				frame.setTitle("Add your advertisment");
			}
		});
		btnAddAdvertisment.setBounds(99, 215, 145, 25);
		contentPane.add(btnAddAdvertisment);
		
		JLabel lblForClients = new JLabel("For clients:");
		lblForClients.setBounds(139, 186, 75, 16);
		contentPane.add(lblForClients);
		
		
		
		
	}
}
