package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Kuotak;
import domain.Question;
import domain.Registered;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class BetGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JFrame thisFrame;
	private Registered user;
	private Kuotak kuota;
	private BLFacade fatxada;


	/**
	 * Create the frame.
	 */
	public BetGUI(User pUser,  Kuotak k) {

		this.user = (Registered) pUser;
		kuota=k;
		fatxada = AdminGUI.getBusinessLogic();
		thisFrame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryN")+this.kuota.getQuestion().getQuestion());
		lblNewLabel.setBounds(60, 89, 238, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MoneyAmount"));
		lblNewLabel_1.setBounds(161, 152, 110, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_7 = new JLabel(" ");
		lblNewLabel_7.setBounds(60, 236, 336, 23);
		contentPane.add(lblNewLabel_7);

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()) {
					lblNewLabel_7.setText(ResourceBundle.getBundle("Etiquetas").getString("EnterAmount"));
				}else {
					float a = Math.round(Float.parseFloat(textField.getText())*100);
					a=a/100;
					float b = user.getDirua();
					if(kuota.getQuestion().getBetMinimum()>a) {
						lblNewLabel_7.setText(ResourceBundle.getBundle("Etiquetas").getString("MinBet")+ kuota.getQuestion().getBetMinimum());
					}else
						if(b-a>=0) {
							lblNewLabel_7.setText(ResourceBundle.getBundle("Etiquetas").getString("MakeBet"));
							fatxada.makeBet(user, a,kuota);
							try {
								TimeUnit.SECONDS.sleep(2);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							thisFrame.setVisible(false);

						}else {
							lblNewLabel_7.setText(ResourceBundle.getBundle("Etiquetas").getString("NoMoney"));
						}

				}


			}
		});
		btnNewButton.setBounds(161, 202, 127, 23);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(171, 171, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel(kuota.getPronostico());
		lblNewLabel_2.setBounds(70, 114, 166, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FeeValue"));
		lblNewLabel_3.setBounds(300, 89, 96, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel((""+kuota.getValue()));
		lblNewLabel_4.setBounds(300, 117, 68, 14);
		contentPane.add(lblNewLabel_4);

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Money"));
		lblNewLabel_5.setBounds(222, 15, 76, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(user.getDirua()+"€");
		lblNewLabel_6.setBounds(300, 15, 49, 14);
		contentPane.add(lblNewLabel_6);


	}
}
