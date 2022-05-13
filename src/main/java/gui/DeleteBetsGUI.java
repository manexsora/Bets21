package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Bet;
import domain.Kuotak;
import domain.Movement;
import domain.Registered;
import domain.User;

import java.awt.ScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

public class DeleteBetsGUI extends JFrame {

	private JPanel contentPane;
	private JFrame thisFrame;
	private static Registered user;
	private DefaultTableModel tableModelMovements;
	private JTable tableMovements= new JTable();
	private BLFacade fatxada;
	private JLabel lblBet;
	private JButton btnNewButton=new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteBet"));
	private JTable tableDetail= new JTable();
	private DefaultTableModel tableModelDetail;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBetsGUI frame = new DeleteBetsGUI(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String[] columnNamesMovements = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Question"), 
			ResourceBundle.getBundle("Etiquetas").getString("FeeDescription"),
			ResourceBundle.getBundle("Etiquetas").getString("FeeValue"),
			ResourceBundle.getBundle("Etiquetas").getString("BetAmount"),


	};

	private String[] columnNamesDetail = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Question"), 
			ResourceBundle.getBundle("Etiquetas").getString("Forecast")

	};
	
	/**
	 * Create the frame.
	 */
	public DeleteBetsGUI(Registered user) {
		fatxada=AdminGUI.getBusinessLogic();
		thisFrame=this;
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1042, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnClose = new JButton();
		btnClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
				RegisteredUserGUI a = new RegisteredUserGUI(user);
				a.setVisible(true);
			}
		});
		btnClose.setBounds(10, 11, 89, 23);
		contentPane.add(btnClose);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(10, 202, 302, 34);
		contentPane.add(lblNewLabel);

		JScrollPane MovementScrollPane = new JScrollPane();
		MovementScrollPane.setBounds(80, 45, 578, 268);
		contentPane.add(MovementScrollPane);
		




		//		JLabel lblNewLabel_1 = new JLabel(user.getMovements().toString());
		//		lblNewLabel_1.setBounds(85, 46, 253, 188);
		//		contentPane.add(lblNewLabel_1);


		tableModelMovements = new DefaultTableModel(null, columnNamesMovements);


		tableModelMovements.setDataVector(null, columnNamesMovements);

		tableMovements.setModel(tableModelMovements);

		tableModelMovements.setColumnCount(5);
		tableMovements.getColumnModel().getColumn(0).setPreferredWidth(125);
		tableMovements.getColumnModel().getColumn(1).setPreferredWidth(125);
		tableMovements.getColumnModel().getColumn(2).setPreferredWidth(20);
		tableMovements.getColumnModel().getColumn(3).setPreferredWidth(20);
		tableMovements.getColumnModel().removeColumn(tableMovements.getColumnModel().getColumn(4));

		if (user.getBets().isEmpty()) {

			lblNewLabel.setText( ResourceBundle.getBundle("Etiquetas").getString("NoBetsToShow"));

		}else {

			for (Bet bet:user.getBets()){
				Vector<Object> row = new Vector<Object>();
				if(bet.getKuotaList().size()==1) {
					row.add(bet.getKuotaList().get(0).getQuestion().getQuestion());
					row.add(bet.getKuotaList().get(0).getPronostico());
					row.add(bet.getKuotaList().get(0).getValue());
					row.add(bet.getApostatutakoDiruKop());
					row.add(bet);
					tableModelMovements.addRow(row);
				}else {
					row.add("Multiple questions");
					row.add("Multiple forecasts");
					row.add(bet.getKuotaTot());
					row.add(bet.getApostatutakoDiruKop());
					row.add(bet);
					tableModelMovements.addRow(row);
				}
			}
		}



		MovementScrollPane.setViewportView(tableMovements);
		tableMovements.setModel(tableModelMovements);



		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setText(ResourceBundle.getBundle("Etiquetas").getString("DeleteChosenBet"));
		lblNewLabel_1.setBounds(26, 324, 476, 26);
		contentPane.add(lblNewLabel_1);

		btnNewButton = new JButton();
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("DeleteBet"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableMovements.getSelectedRow()==-1) {
					lblBet.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectBet"));
				}else {
					int i=tableMovements.getSelectedRow();
					domain.Bet b=(domain.Bet)tableModelMovements.getValueAt(i,4);
					fatxada.deleteBet(user, b);
					lblBet.setText(ResourceBundle.getBundle("Etiquetas").getString("Deleted"));	
				}
			}
		});

		btnNewButton.setBounds(526, 326, 156, 23);

		contentPane.add(btnNewButton);
		


	

		lblBet = new JLabel();
		lblBet.setBounds(310, 249, 119, 21);
		contentPane.add(lblBet);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(713, 45, 283, 268);
		contentPane.add(scrollPane);
		

		tableMovements.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableMovements.getSelectedRow();
				domain.Bet b=(domain.Bet)tableModelMovements.getValueAt(i,4); // obtain q object
				if(b.getKuotaList().size()>1) {
					Vector<Kuotak> kuo=b.getKuotaList();

					tableModelDetail.setDataVector(null, columnNamesDetail);
					tableModelDetail.setColumnCount(3);

					//				if (kuo.isEmpty())
					//					jLabelQueries.setText( ResourceBundle.getBundle("Etiquetas").getString("NoFees")+": "+q.getQuestion());
					//				else 
					//					jLabelQueries.setText( ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+q.getQuestion());

					for (domain.Kuotak k:kuo){
						Vector<Object> row = new Vector<Object>();

						row.add(k.getQuestion().getQuestion());
						row.add(k.getPronostico());
						row.add(k);
						tableModelDetail.addRow(row);	
					}
					try {

						tableDetail.getColumnModel().getColumn(0).setPreferredWidth(150);
						tableDetail.getColumnModel().getColumn(1).setPreferredWidth(93);
						tableDetail.getColumnModel().removeColumn(tableDetail.getColumnModel().getColumn(2));
					}
					catch(Exception exx) {

					}
				}
			}
		});

		scrollPane.setViewportView(tableDetail);
		tableModelDetail = new DefaultTableModel(null, columnNamesDetail);

		tableDetail.setModel(tableModelDetail);
		
		tableDetail.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableDetail.getColumnModel().getColumn(1).setPreferredWidth(93);
		
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DeleteBetsGUI.lblNewLabel_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_2.setBounds(713, 20, 96, 14);
		contentPane.add(lblNewLabel_2);




	}
}