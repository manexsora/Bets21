package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MezuaBidaliGUI extends JFrame {

	private JPanel contentPane;
	private JTextField NoriTxtBox;
	private JTextField asuntoBox;
	private JTextField EdukiaBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MezuaBidaliGUI frame = new MezuaBidaliGUI();
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
	public MezuaBidaliGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NoriTxtBox = new JTextField();
		NoriTxtBox.setBounds(46, 68, 96, 20);
		contentPane.add(NoriTxtBox);
		NoriTxtBox.setColumns(10);
		
		JLabel NoriLabel = new JLabel("Nori");
		NoriLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		NoriLabel.setBounds(49, 49, 49, 14);
		contentPane.add(NoriLabel);
		
		JLabel MezuaBitaliTitle = new JLabel("Mezu bat bidali");
		MezuaBitaliTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		MezuaBitaliTitle.setBounds(179, 11, 141, 14);
		contentPane.add(MezuaBitaliTitle);
		
		JLabel AsuntoLbl = new JLabel("Asuntua");
		AsuntoLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AsuntoLbl.setBounds(49, 100, 49, 14);
		contentPane.add(AsuntoLbl);
		
		asuntoBox = new JTextField();
		asuntoBox.setColumns(10);
		asuntoBox.setBounds(46, 119, 118, 20);
		contentPane.add(asuntoBox);
		
		JLabel lblEdukia = new JLabel("Edukia");
		lblEdukia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEdukia.setBounds(49, 160, 49, 14);
		contentPane.add(lblEdukia);
		
		EdukiaBox = new JTextField();
		EdukiaBox.setColumns(10);
		EdukiaBox.setBounds(46, 179, 360, 95);
		contentPane.add(EdukiaBox);
		
		JLabel EmaitzaLabel = new JLabel("New label");
		EmaitzaLabel.setBounds(46, 286, 214, 50);
		contentPane.add(EmaitzaLabel);
		
		JButton bidaliBtn = new JButton("New button");
		bidaliBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bidaliBtn.setBounds(338, 300, 89, 23);
		contentPane.add(bidaliBtn);
	}
}
