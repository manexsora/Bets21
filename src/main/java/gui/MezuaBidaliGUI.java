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
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class MezuaBidaliGUI extends JFrame {

	private JPanel contentPane;
	private JTextField NoriTxtBox;
	private JTextField asuntoBox;
	JTextPane EdukiaBox;

	private static Registered user;
	private BLFacade fatxada;
	JFrame thisframe;
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
					MezuaBidaliGUI frame = new MezuaBidaliGUI(getUser(),"","  ");
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
	public MezuaBidaliGUI(Registered us,String s,String as) {
		MezuaBidaliGUI.user=us;
		fatxada = AdminGUI.getBusinessLogic();
		thisframe=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NoriTxtBox = new JTextField(s);
		NoriTxtBox.setBounds(46, 68, 96, 20);
		contentPane.add(NoriTxtBox);
		NoriTxtBox.setColumns(10);
		
		JLabel NoriLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Nori"));
		NoriLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		NoriLabel.setBounds(49, 49, 93, 14);
		contentPane.add(NoriLabel);
		
		JLabel MezuaBitaliTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SendAMessage"));
		MezuaBitaliTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		MezuaBitaliTitle.setBounds(174, 11, 187, 35);
		contentPane.add(MezuaBitaliTitle);
		
		JLabel AsuntoLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Asunto"));
		AsuntoLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AsuntoLbl.setBounds(49, 100, 93, 14);
		contentPane.add(AsuntoLbl);
		String tema;
		if(as.equals("")) { //Erantzuna edo ez
			tema = as;
		}
		else {
			tema = "Re: " + as;
		}
		asuntoBox = new JTextField(tema);
		asuntoBox.setColumns(10);
		asuntoBox.setBounds(46, 119, 118, 20);
		contentPane.add(asuntoBox);
		
		JLabel lblEdukia = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Contenido"));
		lblEdukia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEdukia.setBounds(49, 160, 93, 14);
		contentPane.add(lblEdukia);
		
		JLabel EmaitzaLabel = new JLabel("");
		EmaitzaLabel.setBounds(46, 286, 214, 50);
		contentPane.add(EmaitzaLabel);
		
		JButton bidaliBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMessage"));
		bidaliBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nori= NoriTxtBox.getText();
				String nork = user.getUsername();
				String asun = asuntoBox.getText();
				String eduk = EdukiaBox.getText();
				Boolean a =fatxada.mezuaBidali(nori, nork, asun, eduk);
				if(a == false) {
					EmaitzaLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("MsjError"));
				}
				else {
					EmaitzaLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("MsjEnviado"));
				}
			}
		});
		bidaliBtn.setBounds(338, 300, 89, 23);
		contentPane.add(bidaliBtn);
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisframe.setVisible(false);
			}
		});
		btnClose.setBounds(25, 11, 89, 23);
		contentPane.add(btnClose);
		
		EdukiaBox = new JTextPane();
		EdukiaBox.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		EdukiaBox.setBounds(46, 188, 367, 101);
		contentPane.add(EdukiaBox);
	}
}
