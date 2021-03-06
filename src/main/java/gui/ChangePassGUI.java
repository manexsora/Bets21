package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Registered;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ChangePassGUI extends JFrame {

	private JPanel contentPane;
	private JFrame thisframe;
	private static Registered us;
	private BLFacade fatxada;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	JLabel lblTestu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassGUI frame = new ChangePassGUI(us);
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
	public ChangePassGUI(Registered pUs) {
		fatxada = AdminGUI.getBusinessLogic();
		thisframe=this;
		us = pUs;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewPass = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NewPass"));
		lblNewPass.setBounds(35, 96, 184, 14);
		contentPane.add(lblNewPass);
		
		JLabel lblConfirmPass = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ConfirmNewPass"));
		lblConfirmPass.setBounds(35, 166, 184, 14);
		contentPane.add(lblConfirmPass);
		
		JButton btnEnter = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!String.valueOf(passwordField.getPassword()).equals("") & !String.valueOf(confirmPasswordField.getPassword()).equals("")) {
					if(String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword()))) {
						fatxada.changePass(us, String.valueOf(passwordField.getPassword()));
						lblTestu.setText(ResourceBundle.getBundle("Etiquetas").getString("Changed"));
					}else {
						lblTestu.setText(ResourceBundle.getBundle("Etiquetas").getString("IncorrectPass"));
					}
				}else {
					lblTestu.setText(ResourceBundle.getBundle("Etiquetas").getString("IncorrectPass"));
				}
			}
		});
		btnEnter.setBounds(167, 207, 89, 23);
		contentPane.add(btnEnter);
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisframe.setVisible(false);
			}
		});
		btnClose.setBounds(10, 11, 89, 23);
		contentPane.add(btnClose);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(267, 93, 132, 20);
		contentPane.add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(267, 163, 132, 20);
		contentPane.add(confirmPasswordField);
		
		lblTestu = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblTestu.setBounds(167, 38, 173, 14);
		contentPane.add(lblTestu);
	}
	
}
