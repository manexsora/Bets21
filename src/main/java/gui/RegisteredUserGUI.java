package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.Registered;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegisteredUserGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	private Registered user;
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton_1;
	private RegisteredUserGUI thisframe = this;
	private JButton depositMoneybtn;
	private JButton movementsBtn;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	/**
	 * This is the default constructor
	 */
	public RegisteredUserGUI(User pUser) {//b true, registered/admin, b false unregistered
		super();
		user=(Registered) pUser;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(572, 301);
		this.setContentPane(getJContentPane());
		this.setTitle("Admin");

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBoton3());
			jContentPane.add(getBtnNewButton_1());
			jContentPane.add(getDepositMoneybtn());
			jContentPane.add(getMovementsBtn());
			
			JLabel WelcomeLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Welcome")+" "+ user.getIzenabizena());
			WelcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			WelcomeLabel.setBounds(183, 60, 197, 45);
			jContentPane.add(WelcomeLabel);
			
			JLabel UsernameLabel = new JLabel(user.getUsername());
			UsernameLabel.setBounds(450, 90, 86, 19);
			jContentPane.add(UsernameLabel);
			
			JLabel CurrentMoneyLabel = new JLabel(user.getDirua() + " €");
			CurrentMoneyLabel.setBounds(460, 120, 86, 20);
			jContentPane.add(CurrentMoneyLabel);
			jContentPane.add(getBtnNewButton());
			jContentPane.add(getBtnNewButton_2());
			
			JButton btnRanking = new JButton(ResourceBundle.getBundle("Etiquetas").getString(ResourceBundle.getBundle("Etiquetas").getString("Ranking"))); //$NON-NLS-1$ //$NON-NLS-2$
			btnRanking.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new RankingGUI(user);
					a.setVisible(true);
				}
			});
			btnRanking.setBounds(10, 116, 103, 23);
			jContentPane.add(btnRanking);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.setBounds(43, 153, 471, 63);
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					FindQuestionsGUI a = new FindQuestionsGUI(user);
					a.setVisible(true);
					thisframe.setVisible(false);

					
				}
			});
		}
		return jButtonQueryQueries;
	}
	


	

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton();
			btnNewButton_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new ApplicationMainGUI();
					thisframe.setVisible(false);
				}
			});
			btnNewButton_1.setBounds(10, 11, 103, 23);
		}
		return btnNewButton_1;
	}
	
	private JButton getDepositMoneybtn() {
		if (depositMoneybtn == null) {
			depositMoneybtn = new JButton();
			depositMoneybtn.setText(ResourceBundle.getBundle("Etiquetas").getString("DepositMoney"));
			depositMoneybtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DiruaSartuGUI a = new DiruaSartuGUI(user);
					a.setVisible(true);
					thisframe.setVisible(false);
				}
			});
			depositMoneybtn.setBounds(402, 45, 146, 23);
		}
		return depositMoneybtn;
	}
	private JButton getMovementsBtn() {
		if (movementsBtn == null) {
			movementsBtn = new JButton();
			movementsBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("Movements"));
			movementsBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MovementsGUI a = new MovementsGUI(user);
					a.setVisible(true);
				}
			});
			movementsBtn.setBounds(402, 11, 146, 23);
		}
		return movementsBtn;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton();
			btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("CurrentBets"));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					thisframe.setVisible(false);
					DeleteBetsGUI d = new DeleteBetsGUI(user);
					d.setVisible(true);
				}
			});
			btnNewButton.setBounds(10, 45, 132, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Messages")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MezuakIkusiGUI b = new MezuakIkusiGUI(user);
					b.setVisible(true);
					thisframe.setVisible(false);
				}
			});
			btnNewButton_2.setBounds(10, 82, 103, 23);
		}
		return btnNewButton_2;
	}
} // @jve:decl-index=0:visual-constraint="0,0