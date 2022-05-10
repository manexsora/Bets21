package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Registered;
import domain.User;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField password2;
	private JTextField erabiltzaile;
	private JTextField izenabizena;
	private JTextField email;
	private JPasswordField password1;
	private BLFacade fatxada;


	RegisterGUI thisframe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {

		thisframe = this;
		fatxada = AdminGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		password2 = new JPasswordField();
		password2.setBounds(196, 156, 168, 20);
		contentPane.add(password2);
		
		JLabel testua = new JLabel("");
		testua.setBounds(108, 244, 256, 25);
		contentPane.add(testua);
		
		erabiltzaile = new JTextField();
		erabiltzaile.setBounds(196, 91, 168, 20);
		contentPane.add(erabiltzaile);
		erabiltzaile.setColumns(10);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblNewLabel.setBounds(37, 88, 111, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fatxada.isRegister(erabiltzaile.getText())) {
					testua.setText("Username is already used");
					erabiltzaile.setText("");
				}
				else {
					if(String.valueOf(password1.getPassword()).equals(String.valueOf(password2.getPassword()))) {
						Registered o = new Registered(erabiltzaile.getText(), email.getText(),izenabizena.getText(), String.valueOf(password1.getPassword()));

						fatxada.register(o);
						testua.setText(ResourceBundle.getBundle("Etiquetas").getString("Registered"));
					}
					else {
						testua.setText("Passwords dont match");
						password1.setText("");
						password2.setText("");
					}
				}
			}
		});
		btnNewButton.setBounds(134, 206, 140, 26);
		contentPane.add(btnNewButton);
		
		JLabel lblPasahitza = new JLabel();
		lblPasahitza.setText(ResourceBundle.getBundle("Etiquetas").getString("RepeatPassword"));
		lblPasahitza.setBounds(37, 153, 111, 26);
		contentPane.add(lblPasahitza);
		
		izenabizena = new JTextField();
		izenabizena.setColumns(10);
		izenabizena.setBounds(196, 29, 168, 20);
		contentPane.add(izenabizena);
		
		JLabel lblIzenabizenak = new JLabel();
		lblIzenabizenak.setText(ResourceBundle.getBundle("Etiquetas").getString("NameSurname"));
		lblIzenabizenak.setBounds(37, 26, 111, 26);
		contentPane.add(lblIzenabizenak);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(196, 60, 168, 20);
		contentPane.add(email);
		
		JLabel lblEmaila = new JLabel();
		lblEmaila.setText(ResourceBundle.getBundle("Etiquetas").getString("Email"));
		lblEmaila.setBounds(37, 56, 111, 26);
		contentPane.add(lblEmaila);
		
		JLabel lblPasahitza_1 = new JLabel();
		lblPasahitza_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPasahitza_1.setBounds(37, 119, 111, 26);
		contentPane.add(lblPasahitza_1);
		
		password1 = new JPasswordField();
		password1.setBounds(196, 122, 168, 20);
		contentPane.add(password1);

		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ApplicationMainGUI();
				thisframe.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(0, 0, 103, 23);
		contentPane.add(btnNewButton_1);
		

	}
}