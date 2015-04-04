package PL;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BL.AdvertismentServices;
import Entities.Advertisment;
import Entities.User;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JScrollPane;

public class UserForm extends JFrame {

	private JPanel contentPane;

	private User u;
	public AdvertismentServices as;
	private JTextField textField;
	private BufferedImage img;
	public File file = null;
	private JTextField textField_1;
	private JTextField textField_2;
	public UserForm(User u) {
		this.u=u;
		as = new AdvertismentServices();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 428, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddAdvertisment = new JLabel("Add advertisment:");
		lblAddAdvertisment.setBounds(147, 10, 115, 16);
		contentPane.add(lblAddAdvertisment);
		
		JLabel lblTitle = new JLabel("title:");
		lblTitle.setBounds(71, 42, 56, 16);
		contentPane.add(lblTitle);
		
		JLabel lblDescription = new JLabel("description:");
		lblDescription.setBounds(71, 73, 77, 16);
		contentPane.add(lblDescription);
		
		textField = new JTextField();
		textField.setBounds(160, 39, 205, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 70, 205, 75);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel label = new JLabel("");
		label.setBounds(196, 158, 161, 26);
		contentPane.add(label);
		
		JButton btnAdd = new JButton("Add advertisment");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = textField.getText();
				String des = textArea.getText();
				if(title.equals("") || des.equals(""))label.setText("Please fill all the fields!");
					else if(file == null)label.setText("Please upload a picture!");
					else
						try {
							as.createAdvertisment(u.getUsername(), title, des, file);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
			}
		});
		btnAdd.setBounds(160, 197, 169, 25);
		contentPane.add(btnAdd);
		
		JButton btnBrowseImage = new JButton("Browse image");
		btnBrowseImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		          file = fileChooser.getSelectedFile();
		          System.out.println(file.getName());
		        }
			}
		});
		btnBrowseImage.setBounds(68, 158, 116, 25);
		contentPane.add(btnBrowseImage);
		
		JLabel lblDeleteAdvertisment = new JLabel("Delete advertisment:");
		lblDeleteAdvertisment.setBounds(147, 235, 146, 16);
		contentPane.add(lblDeleteAdvertisment);
		
		JLabel lblTitle_1 = new JLabel("title:");
		lblTitle_1.setBounds(71, 262, 56, 16);
		contentPane.add(lblTitle_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(160, 256, 205, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDeleteAdvertisment = new JButton("Delete advertisment");
		btnDeleteAdvertisment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = textField_1.getText();
				if(!title.equals(""))as.deleteAdv(title);
			}
		});
		btnDeleteAdvertisment.setBounds(160, 291, 169, 25);
		contentPane.add(btnDeleteAdvertisment);
		
		JLabel lblListAdv = new JLabel("List all your advertisments:");
		lblListAdv.setBounds(147, 328, 170, 16);
		contentPane.add(lblListAdv);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 397, 376, 95);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setEditable(false);
		
		JButton btnList = new JButton("List All");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rap;
				try {
					rap = as.getRaport(u.getUsername());
					textArea_1.setText(rap);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnList.setBounds(160, 357, 97, 25);
		contentPane.add(btnList);
		
		JLabel lblCheckTheAdvertisment = new JLabel("Check the advertisment by ID:");
		lblCheckTheAdvertisment.setBounds(23, 505, 193, 16);
		contentPane.add(lblCheckTheAdvertisment);
		
		textField_2 = new JTextField();
		textField_2.setBounds(213, 502, 56, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.parseInt(textField_2.getText());
				Advertisment adv = null;
				try {
					adv = as.getAdv(id);
				} catch (IOException e3) {
					e3.printStackTrace();
				}
				if(!textField_2.getText().equals("") && adv!=null){
					AdvView frame = new AdvView(adv);
					frame.setVisible(true);
					frame.setTitle(adv.getTitle());
				}
			}
		});
		btnCheck.setBounds(281, 501, 97, 25);
		contentPane.add(btnCheck);
		
		
	}
}
