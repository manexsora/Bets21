package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JRadioButton;

public class ApplicationMainGUI extends JFrame {

	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JPanel contentPane;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblOngiEtorri; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigXML c=ConfigXML.getInstance();
					
					System.out.println(c.getLocale());
					
					Locale.setDefault(new Locale(c.getLocale()));
					
					System.out.println("Locale: "+Locale.getDefault());
					
//					MainGUI a=new MainGUI();
//					a.setVisible(true);


					try {
						
						BLFacade appFacadeInterface;
//						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//						UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
						UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
						
						if (c.isBusinessLogicLocal()) {
							
							//In this option the DataAccess is created by FacadeImplementationWS
							//appFacadeInterface=new BLFacadeImplementation();

							//In this option, you can parameterize the DataAccess (e.g. a Mock DataAccess object)

							DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
							appFacadeInterface=new BLFacadeImplementation(da);

							
						}
						
						else { //If remote
							
							 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
							 
							//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
							URL url = new URL(serviceName);

					 
					        //1st argument refers to wsdl document above
							//2nd argument is service name, refer to wsdl document above
//					        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
					        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
					 
					        Service service = Service.create(url, qname);

					         appFacadeInterface = service.getPort(BLFacade.class);
						} 
//Azti ibili honekin
						if (c.getDataBaseOpenMode().equals("initialize")) 
							appFacadeInterface.initializeBD();
							

						AdminGUI.setBussinessLogic(appFacadeInterface);

					

						
					}catch (Exception e) {
//						a.jLabelSelectOption.setText("Error: "+e.toString());
//						a.jLabelSelectOption.setForeground(Color.RED);	
//						
//						System.out.println("Error in ApplicationLauncher: "+e.toString());
					}
					//a.pack();


				
					ApplicationMainGUI frame = new ApplicationMainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JRadioButton getradioButton1() {
		if (radioButton1 == null) {
			radioButton1 = new JRadioButton("Euskera");
			radioButton1.setSize(85, 15);
			radioButton1.setLocation(85, 277);
			radioButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent v) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(radioButton1);
		}
		return radioButton1;
	}
	private JRadioButton getradioButton2() {
		if (radioButton2 == null) {
			radioButton2 = new JRadioButton("Castellano");
			radioButton2.setSize(94, 15);
			radioButton2.setLocation(175, 277);
			radioButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(radioButton2);
		}
		return radioButton2;
	}
	private JRadioButton getradioButton3() {
		if (radioButton3 == null) {
			radioButton3 = new JRadioButton("English");
			radioButton3.setSize(200, 15);
			radioButton3.setLocation(271, 277);
			radioButton3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(radioButton3);
		}
		return radioButton3;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getradioButton1());
			panel.add(getradioButton2());
			panel.add(getradioButton3());
		}
		return panel;
	}
	/**
	 * Create the frame.
	 */
	public ApplicationMainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 336);
		contentPane =getPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("BETS21");
		lblNewLabel.setFont(new Font("Old English Text MT", Font.PLAIN, 49));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(96, 11, 264, 43);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton();
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LOGINEGIN GUI-RA ERAMAN
				JFrame a = new LoginEginGUI();
				
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(63, 113, 319, 43);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton();
		btnNewButton_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ERREGISTRATU GUI-RA ERAMAN
				JFrame a = new RegisterGUI();
				
				a.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(63, 167, 319, 43);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton();
		btnNewButton_2.setText(ResourceBundle.getBundle("Etiquetas").getString("WatchEvents"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ERREGISTRATU GABE SARTU
				UnregisteredFindQuestionsGUI a = new UnregisteredFindQuestionsGUI();
				a.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(63, 221, 319, 43);
		contentPane.add(btnNewButton_2);
		
		lblOngiEtorri = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Welcome")); //$NON-NLS-1$ //$NON-NLS-2$
		lblOngiEtorri.setHorizontalAlignment(SwingConstants.CENTER);
		lblOngiEtorri.setFont(new Font("Palace Script MT", Font.PLAIN, 52));
		lblOngiEtorri.setBounds(147, 71, 147, 31);
		contentPane.add(lblOngiEtorri);
//		JRadioButton radioButton1 = new JRadioButton("Euskera");
//		radioButton1.setBounds(83, 269, 79, 23);
//		contentPane.add(radioButton1);
//		radioButton1.setVerticalAlignment(SwingConstants.BOTTOM);
//		
//		JRadioButton radioButton2 = new JRadioButton("Castellano");
//		radioButton2.setBounds(175, 271, 84, 23);
//		contentPane.add(radioButton2);
//		
//		JRadioButton radioButton3 = new JRadioButton("English");
//		radioButton3.setBounds(276, 270, 84, 23);
//		contentPane.add(radioButton3);
	}
	private void redibujar() {
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnNewButton_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		btnNewButton_2.setText(ResourceBundle.getBundle("Etiquetas").getString("WatchEvents"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		lblOngiEtorri.setText(ResourceBundle.getBundle("Etiquetas").getString("Welcome"));
	}

}