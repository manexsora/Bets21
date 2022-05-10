package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Kuotak;
import domain.Question;
import exceptions.FeeAlreadyExist;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CreateFeeGUI extends JFrame {
	private BLFacade facade;
	private JPanel contentPane;
	private Question q;
	private JTextField emaitzaTextField;
	private JTextField kuotaTextField;
	private JLabel messageLabel;
	private JButton btnNewButton_1;
	private CreateFeeGUI thisframe = this;
	/**
	 * Create the frame.
	 */
	public CreateFeeGUI(Question question) {
		this.q = question;
		facade = AdminGUI.getBusinessLogic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelEmaitzaSartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Forecast")+ ":");
		labelEmaitzaSartu.setBounds(45, 54, 72, 14);
		contentPane.add(labelEmaitzaSartu);
		
		JLabel lblKuota = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fee")+":");
		lblKuota.setBounds(45, 85, 72, 14);
		contentPane.add(lblKuota);
		
		emaitzaTextField = new JTextField();
		emaitzaTextField.setBounds(145, 51, 96, 20);
		contentPane.add(emaitzaTextField);
		emaitzaTextField.setColumns(10);
		
		kuotaTextField = new JTextField();
		kuotaTextField.setColumns(10);
		kuotaTextField.setBounds(145, 82, 96, 20);
		contentPane.add(kuotaTextField);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateFee"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					float number = Float.parseFloat(kuotaTextField.getText());
					if(number<=1) throw new NumberFormatException();
					facade.createFee(q, emaitzaTextField.getText(), number);
					messageLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("EnteredFee"));
					emaitzaTextField.setText("");
					kuotaTextField.setText("");
				}
				catch (NumberFormatException ex) {
					kuotaTextField.setText("");
					messageLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("EntrCorrectFee"));
				}
				catch (FeeAlreadyExist e2) {
					messageLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("FeeExists"));
					emaitzaTextField.setText("");
					kuotaTextField.setText("");
				}
				catch (Exception e2) {
					emaitzaTextField.setText("");
					kuotaTextField.setText("");
					messageLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Error"));
				}
				
		
			}
		});
		btnNewButton.setBounds(90, 108, 105, 23);
		contentPane.add(btnNewButton);
		
		messageLabel = new JLabel("");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setBounds(10, 142, 262, 14);
		contentPane.add(messageLabel);
		
		btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisframe.setVisible(false);
				AdminFindQuestionsGUI a = new AdminFindQuestionsGUI();
//				a.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(2, 11, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
