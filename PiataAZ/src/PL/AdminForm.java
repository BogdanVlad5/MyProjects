package PL;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BL.AdvertismentServices;
import BL.UserServices;
import Entities.Advertisment;
import Entities.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AdminForm extends JFrame {

	private JPanel contentPane;

	private User u;
	public AdvertismentServices as;
	public UserServices us;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	public AdminForm(User u) {
		this.u=u;
		as = new AdvertismentServices();
		us = new UserServices();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGetRaport = new JLabel("Get raport:");
		lblGetRaport.setBounds(12, 13, 74, 16);
		contentPane.add(lblGetRaport);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(12, 37, 74, 16);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(88, 34, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 66, 408, 63);
		contentPane.add(textArea);
		
		JButton btnGet = new JButton("Get");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = textField.getText();
				String rap;
				try {
					rap = as.getRaport(username);
					textArea.setText(rap);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnGet.setBounds(244, 33, 97, 25);
		contentPane.add(btnGet);
		
		JLabel lblCreateAccount = new JLabel("Create account:");
		lblCreateAccount.setBounds(12, 164, 97, 16);
		contentPane.add(lblCreateAccount);
		
		JLabel lblUsername_1 = new JLabel("username:");
		lblUsername_1.setBounds(12, 193, 74, 16);
		contentPane.add(lblUsername_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(108, 190, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(12, 225, 74, 16);
		contentPane.add(lblPassword);
		
		textField_2 = new JTextField();
		textField_2.setBounds(108, 222, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblName = new JLabel("name:");
		lblName.setBounds(12, 260, 56, 16);
		contentPane.add(lblName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(108, 257, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnCreateAccount = new JButton("Create account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = textField_1.getText();
				String password = textField_2.getText();
				String name = textField_3.getText();
				if(!username.equals("") && !password.equals("") && !name.equals(""))
				try {
					us.createAccount(username, password, name);
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});
		btnCreateAccount.setBounds(275, 206, 145, 55);
		contentPane.add(btnCreateAccount);
		
		JLabel lblDeleteAccount = new JLabel("Delete account:");
		lblDeleteAccount.setBounds(12, 298, 97, 16);
		contentPane.add(lblDeleteAccount);
		
		JLabel lblUsername_2 = new JLabel("username:");
		lblUsername_2.setBounds(12, 327, 74, 16);
		contentPane.add(lblUsername_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(108, 321, 116, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = textField_4.getText();
				try {
					us.deleteAccount(username);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(275, 320, 145, 25);
		contentPane.add(btnDelete);
		
		JLabel lblAdvID = new JLabel("Get advertisment by ID:");
		lblAdvID.setBounds(12, 135, 145, 16);
		contentPane.add(lblAdvID);
		
		textField_5 = new JTextField();
		textField_5.setBounds(194, 132, 30, 22);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("Get");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer id = Integer.parseInt(textField_5.getText());
				Advertisment adv = null;
				try {
					adv = as.getAdv(id);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(!textField_5.getText().equals("") && adv!=null){
					AdvView frame = new AdvView(adv);
					frame.setVisible(true);
					frame.setTitle(adv.getTitle());
				}
			}
		});
		btnNewButton.setBounds(244, 131, 97, 25);
		contentPane.add(btnNewButton);
		
	}
}
