package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Mezua;
import domain.Registered;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Rectangle;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class MezuakIkusiGUI extends JFrame {

	private JPanel contentPane;
	private JFrame thisFrame;
	private static Registered user;
	private BLFacade fatxada;
	private DefaultTableModel tableModelMsj;
	private JTable tableMsj= new JTable();
	private JTextPane mezuaTestua;
	private JLabel lblEmaitza;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MezuakIkusiGUI frame = new MezuakIkusiGUI(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String[] columNamesMsjs = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("From"), 
			ResourceBundle.getBundle("Etiquetas").getString("Asunto"),
	};
	/**
	 * Create the frame.
	 */
	public MezuakIkusiGUI(Registered pUser) {
		this.user= pUser;
		fatxada=AdminGUI.getBusinessLogic();
		thisFrame=this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
				RegisteredUserGUI a = new RegisteredUserGUI(user);
				a.setVisible(true);
				}
			
		});
		btnClose.setBounds(28, 11, 89, 23);
		contentPane.add(btnClose);
		
		JScrollPane scrollPaneMsjs = new JScrollPane();

		scrollPaneMsjs.setBounds(new Rectangle(40, 273, 267, 106));
		scrollPaneMsjs.setBounds(48, 57, 252, 253);
		contentPane.add(scrollPaneMsjs);
		
		tableModelMsj = new DefaultTableModel(null, columNamesMsjs);
		tableModelMsj.setDataVector(null, columNamesMsjs);
		tableMsj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableMsj.getSelectedRow();
				domain.Mezua mz=(domain.Mezua)tableModelMsj.getValueAt(i,2); 
				mezuaTestua.setText(mz.getEdukia());
			}
		});
		
		tableMsj.setModel(tableModelMsj);
		
		tableModelMsj.setColumnCount(3);
		tableMsj.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableMsj.getColumnModel().getColumn(1).setPreferredWidth(125);
		tableMsj.getColumnModel().removeColumn(tableMsj.getColumnModel().getColumn(2));
		
		scrollPaneMsjs.setViewportView(tableMsj);
		tableMsj.setModel(tableModelMsj);
		
		mezuaTestua = new JTextPane();
		mezuaTestua.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mezuaTestua.setEditable(false);
		mezuaTestua.setEnabled(false);
		mezuaTestua.setBounds(341, 57, 218, 253);
		contentPane.add(mezuaTestua);
		
		
		if(user.getJasoak().isEmpty()) {
			//TODO mezua error
		}
		else{
			for(Mezua mz:user.getJasoak()) {
				Vector<Object> row = new Vector<Object>();
				row.add(mz.getNork());
				row.add(mz.getAsuntua());
				row.add(mz);
				tableModelMsj.addRow(row);				
			}
		}
		
		JButton mezuaBerriBtn = new JButton("Mezu berria");
		mezuaBerriBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MezuaBidaliGUI a = new MezuaBidaliGUI(user,"","");
				a.setVisible(true);
			}
		});
		mezuaBerriBtn.setBounds(446, 361, 89, 23);
		contentPane.add(mezuaBerriBtn);
		
		JButton erantzunBtn = new JButton("Erantzun");
		erantzunBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableMsj.getSelectedRow();
				if(i!=-1) {
				domain.Mezua mz=(domain.Mezua)tableModelMsj.getValueAt(i,2); 
				MezuaBidaliGUI a = new MezuaBidaliGUI(user,mz.getNork().getUsername(),mz.getAsuntua());
				a.setVisible(true);
				}
				else {
					lblEmaitza.setText("Sartu mezua"); //TODO etiketa jarri
				}
			}
		});
		erantzunBtn.setBounds(274, 361, 89, 23);
		contentPane.add(erantzunBtn);
		
		lblEmaitza = new JLabel("");
		lblEmaitza.setBounds(234, 334, 209, 14);
		contentPane.add(lblEmaitza);
	}
}
