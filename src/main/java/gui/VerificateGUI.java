package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Registered;
import mail.Mail;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class VerificateGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCode;
	private JLabel lblMessageBeenSent;
	static Registered us;
	static String code;
	private JFrame thisframe;
	private Mail mail = new Mail();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerificateGUI frame = new VerificateGUI(us,code);
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
	public VerificateGUI(Registered pUs, String pCode) {
		thisframe=this;
		this.us=pUs;
		this.code=code;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldCode = new JTextField();
		textFieldCode.setBounds(201, 123, 157, 20);
		contentPane.add(textFieldCode);
		textFieldCode.setColumns(10);
		
		lblMessageBeenSent = new JLabel("New label");
		lblMessageBeenSent.setBounds(59, 68, 284, 14);
		contentPane.add(lblMessageBeenSent);
		
		JLabel lblEnterCode = new JLabel("New label");
		lblEnterCode.setBounds(59, 126, 95, 14);
		contentPane.add(lblEnterCode);
		
		JButton btnEnter = new JButton("New button");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldCode.getText()!=null) {
					if(textFieldCode.getText().equals(code)) {
						JFrame a= new ChangePassGUI(us);
						thisframe.setVisible(false);
						a.setVisible(true);
					}
				}
			}
		});
		btnEnter.setBounds(162, 171, 89, 23);
		contentPane.add(btnEnter);
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisframe.setVisible(false);
			}
		});
		btnClose.setBounds(10, 11, 89, 23);
		contentPane.add(btnClose);
		
		JButton btnSendAgain = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendAgain"));
		btnSendAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				code = mail.sendEmail(us.getEmail());
			}
		});
		btnSendAgain.setBounds(335, 11, 89, 23);
		contentPane.add(btnSendAgain);
	}
}
