package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hamcrest.core.IsInstanceOf;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Admin;
import domain.Registered;
import domain.User;
import mail.Mail;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;


public class LoginEginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField userText;
	private JPasswordField passWordtext;
	private JFrame thisframe;
	private BLFacade fatxada;
	private Mail mail = new Mail();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginEginGUI frame = new LoginEginGUI();
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
	public LoginEginGUI() {
		thisframe= this;
		fatxada = AdminGUI.getBusinessLogic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblNewLabel.setBounds(107, 91, 76, 17);
		contentPane.add(lblNewLabel);

		userText = new JTextField();
		userText.setBounds(251, 88, 96, 20);
		contentPane.add(userText);
		userText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel_1.setBounds(107, 129, 76, 14);
		contentPane.add(lblNewLabel_1);

		passWordtext = new JPasswordField();
		passWordtext.setBounds(251, 126, 96, 20);
		contentPane.add(passWordtext);

		JLabel testuEmaitza = new JLabel("");
		testuEmaitza.setBounds(133, 218, 187, 17);
		contentPane.add(testuEmaitza);

		JButton loginButton = new JButton();
		loginButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				User a =fatxada.isLogin(userText.getText(), String.valueOf(passWordtext.getPassword()));
				if (a == null) {
					//ez erregistratua edo pasahitz okerra
					testuEmaitza.setText(ResourceBundle.getBundle("Etiquetas").getString("WrongUsr"));
					userText.setText("");
					passWordtext.setText("");
				}
				else {
					if(a instanceof Registered) { //Erabiltzaile normal
						testuEmaitza.setText(ResourceBundle.getBundle("Etiquetas").getString("Welcome"));
						RegisteredUserGUI b = new RegisteredUserGUI(a);

						thisframe.setVisible(false);
						b.setVisible(true);

					}
					if(a instanceof Admin) { //admin

						JFrame adminInt = new AdminGUI(a);

						testuEmaitza.setText(ResourceBundle.getBundle("Etiquetas").getString("Welcome"));
						adminInt.setVisible(true);
						thisframe.setVisible(false);

					}
				}
			}
		}	
			);
		loginButton.setBounds(147, 167, 167, 20);
		contentPane.add(loginButton);

		JLabel lblNewLabel_2 = new JLabel("Bets21");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblNewLabel_2.setBounds(185, 23, 70, 32);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton();
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisframe.setVisible(false);

			}
		});
		btnNewButton.setBounds(10, 0, 96, 23);
		contentPane.add(btnNewButton);
		
		JButton btnChangePass = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ChangePass")); //$NON-NLS-1$ //$NON-NLS-2$
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!userText.getText().equals("")) {
					testuEmaitza.setText(ResourceBundle.getBundle("Etiquetas").getString("SendingEmail"));
					Registered us = fatxada.getUser(userText.getText());
					String code= mail.sendEmail(us.getEmail());
					JFrame a = new VerificateGUI(us, code);
					a.setVisible(true);
				}else {
					testuEmaitza.setText(ResourceBundle.getBundle("Etiquetas").getString("WrongUsr"));
				}
			}
		});
		btnChangePass.setBounds(263, 0, 161, 23);
		contentPane.add(btnChangePass);


		}
	}

