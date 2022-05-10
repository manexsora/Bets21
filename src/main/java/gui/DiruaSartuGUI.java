package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import businessLogic.BLFacade;
import domain.Registered;
import domain.User;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class DiruaSartuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField amountTextField;
	private static Registered user;
	private BLFacade fatxada;
	JTextComponent lblNewLabel;
	private JTextField DepositedMoney;
	private JLabel lblNewLabel_1;
	private JFrame thisFrame;
	private JButton btnClose;
	private JLabel lblMezua;

	public static Registered getUser() {
		return user;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI frame = new DiruaSartuGUI(getUser());
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
	public DiruaSartuGUI(Registered user) {
		this.user=user;
		fatxada = AdminGUI.getBusinessLogic();
		thisFrame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		amountTextField = new JTextField();
		amountTextField.setBounds(171, 102, 86, 20);
		contentPane.add(amountTextField);
		amountTextField.setColumns(10);

		JButton btnNewButton = new JButton();
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("DepositMoney"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					float a = Float.parseFloat(amountTextField.getText());
					if(a>0) {
						float b = (float) Math.round(a*100);
						fatxada.depositMoney(getUser(), b/100);
						lblMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("Deposited"));
					}else {
						lblMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("EnteraAmount"));
					}
				}
				catch (Exception o) {
					lblMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("EnterAmount"));					
				}
				}
			});
		btnNewButton.setBounds(97, 143, 139, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Amount"));
		lblNewLabel.setBounds(65, 105, 77, 14);
		contentPane.add(lblNewLabel);

		String a = new String(""+getUser().getDirua());

		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setText(ResourceBundle.getBundle("Etiquetas").getString("CurrentMoney"));
		lblNewLabel_1.setBounds(65, 67, 108, 14);
		contentPane.add(lblNewLabel_1);

		btnClose = new JButton();
		btnClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
				RegisteredUserGUI b = new RegisteredUserGUI(user);
				b.setVisible(true);
			}
		});
		btnClose.setBounds(10, 11, 89, 23);
		contentPane.add(btnClose);

		JLabel DepositedMoneylbl = new JLabel(a);
		DepositedMoneylbl.setBounds(171, 67, 218, 14);
		contentPane.add(DepositedMoneylbl);

		lblMezua = new JLabel("");
		lblMezua.setBounds(84, 186, 183, 14);
		contentPane.add(lblMezua);

		JLabel emaLabel = new JLabel("");
		emaLabel.setBounds(107, 186, 117, 14);
		contentPane.add(emaLabel);




		}
	}
