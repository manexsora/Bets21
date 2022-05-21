package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Bet;
import domain.Registered;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class RankingGUI extends JFrame {

	private JPanel contentPane;
	static Registered user;
	private DefaultTableModel tableModelRank;
	private JTable tableRank= new JTable();
	private BLFacade fatxada;
	private JFrame thisFrame;
	JLabel lblTestu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RankingGUI frame = new RankingGUI(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String[] columnNamesRank = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Username"), 
			ResourceBundle.getBundle("Etiquetas").getString("AmountBet"),
			ResourceBundle.getBundle("Etiquetas").getString("WinRate"),
	};
	private JButton btnClose;

	/**
	 * Create the frame.
	 */
	public RankingGUI(Registered us) {
		user=us;
		fatxada=AdminGUI.getBusinessLogic();
		thisFrame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane RankScrollPane = new JScrollPane();
		RankScrollPane.setBounds(63, 45, 411, 196);
		contentPane.add(RankScrollPane);
		
		JButton btnFollow = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FollowUs")); //$NON-NLS-1$ //$NON-NLS-2$
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableRank.getSelectedRow()==-1) {
					lblTestu.setText(ResourceBundle.getBundle("Etiquetas").getString("Select"));
				}else {
					int i=tableRank.getSelectedRow();
					domain.Registered u=(domain.Registered)tableModelRank.getValueAt(i,3);
					String a= fatxada.follow(user, u);
					lblTestu.setText(ResourceBundle.getBundle("Etiquetas").getString(a));	
					
					
				}
			}
		});
		btnFollow.setBounds(210, 278, 89, 23);
		contentPane.add(btnFollow);
		
		
		tableModelRank = new DefaultTableModel(null, columnNamesRank);


		tableModelRank.setDataVector(null, columnNamesRank);

		tableRank.setModel(tableModelRank);

		tableModelRank.setColumnCount(4);
		tableRank.getColumnModel().getColumn(0).setPreferredWidth(125);
		tableRank.getColumnModel().getColumn(1).setPreferredWidth(125);
		tableRank.getColumnModel().getColumn(2).setPreferredWidth(20);
		tableRank.getColumnModel().removeColumn(tableRank.getColumnModel().getColumn(3));
		
		Vector<Registered> rank = fatxada.getRank();
		
		for (Registered u:rank){
			Vector<Object> row = new Vector<Object>();
			row.add(u.getUsername());
			row.add(u.getAmountBet());
			row.add(u.getWinRate());
			row.add(u);
			tableModelRank.addRow(row);

		}
		
		RankScrollPane.setViewportView(tableRank);
		tableRank.setModel(tableModelRank);
		
		lblTestu = new JLabel();
		lblTestu.setBounds(133, 253, 278, 14);
		contentPane.add(lblTestu);
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
			}
		});
		btnClose.setBounds(10, 11, 89, 23);
		contentPane.add(btnClose);

		

	}
}
