package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domain.Movement;
import domain.Registered;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class MovementsGUI extends JFrame {

	private JPanel contentPane;
	private JFrame thisFrame;
	private static Registered user;
	private DefaultTableModel tableModelMovements;
	private JTable tableMovements= new JTable();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovementsGUI frame = new MovementsGUI(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String[] columnNamesMovements = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Type"), 
			ResourceBundle.getBundle("Etiquetas").getString("Amount"),
			ResourceBundle.getBundle("Etiquetas").getString("CurrentMoney")

	};

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public MovementsGUI(Registered user) {
		thisFrame=this;
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
			}
		});
		btnClose.setBounds(10, 11, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(78, 222, 263, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane MovementScrollPane = new JScrollPane();
		MovementScrollPane.setBounds(38, 67, 372, 158);
		contentPane.add(MovementScrollPane);
		
//		JLabel lblNewLabel_1 = new JLabel(user.getMovements().toString());
//		lblNewLabel_1.setBounds(85, 46, 253, 188);
//		contentPane.add(lblNewLabel_1);
		
		
		tableModelMovements = new DefaultTableModel(null, columnNamesMovements);
		
		
		tableModelMovements.setDataVector(null, columnNamesMovements);

		if (user.getMovements().isEmpty())
			lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoMovements"));

		for (Movement mov:user.getMovements()){
			Vector<Object> row = new Vector<Object>();

			row.add(mov.getMota());
			row.add(mov.getKop());
			row.add(mov.getDirua());
			tableModelMovements.addRow(row);	
		}
		
		MovementScrollPane.setViewportView(tableMovements);

		tableMovements.setModel(tableModelMovements);
	
		
	}
}