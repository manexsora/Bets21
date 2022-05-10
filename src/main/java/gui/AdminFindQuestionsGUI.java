package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Kuotak;
import domain.Question;
import domain.Registered;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class AdminFindQuestionsGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel( ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jButtonClose = new JButton( ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JTable tableFee= new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelFee;
	
	private JLabel lblmarked;
	private JFrame thisframe=this;
	
	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Query"),

	};
	private final JButton btnApostatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateFee")); //$NON-NLS-1$ //$NON-NLS-2$
	
	private String[] columnNamesFee = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Forecast"), 
			ResourceBundle.getBundle("Etiquetas").getString("Value"),

	};

	public AdminFindQuestionsGUI()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle( ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(138, 248, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(98, 420, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = AdminGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar est� 30 de enero y se avanza al mes siguiente, devolver�a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este c�digo se dejar� como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = AdminGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=AdminGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText( ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 273, 267, 106));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(2);
				
				if (queries.isEmpty())
					jLabelQueries.setText( ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText( ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
				try {

				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(1));
				
				tableFee.getColumnModel().getColumn(0).setPreferredWidth(150);
				tableFee.getColumnModel().getColumn(1).setPreferredWidth(93);
				tableFee.getColumnModel().removeColumn(tableFee.getColumnModel().getColumn(2));
				}
				catch(Exception exx) {
					
				}
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
	

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(268);
		


		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		
		
		tableModelFee = new DefaultTableModel(null, columnNamesFee);
		
		
		tableModelFee.setDataVector(null, columnNamesFee);
		
		JScrollPane FeeScrollPane = new JScrollPane();
		FeeScrollPane.setBounds(358, 271, 280, 108);
		getContentPane().add(FeeScrollPane);
		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableQueries.getSelectedRow();
				domain.Question q=(domain.Question)tableModelQueries.getValueAt(i,1); // obtain q object
				Vector<Kuotak> kuo=q.getFees();

				tableModelFee.setDataVector(null, columnNamesFee);
				tableModelFee.setColumnCount(3);
				
				if (kuo.isEmpty())
					jLabelQueries.setText( ResourceBundle.getBundle("Etiquetas").getString("NoFees")+": "+q.getQuestion());
				else 
					jLabelQueries.setText( ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+q.getQuestion());

				for (domain.Kuotak k:kuo){
					Vector<Object> row = new Vector<Object>();

					row.add(k.getPronostico());
					row.add(k.getValue());
					row.add(k);
					tableModelFee.addRow(row);	
				}
				try {

				tableFee.getColumnModel().getColumn(0).setPreferredWidth(150);
				tableFee.getColumnModel().getColumn(1).setPreferredWidth(93);
				tableFee.getColumnModel().removeColumn(tableFee.getColumnModel().getColumn(2));
				}
				catch(Exception exx) {
					
				}
			}
		});
		
		FeeScrollPane.setViewportView(tableFee);
		tableModelFee = new DefaultTableModel(null, columnNamesFee);

		tableFee.setModel(tableModelFee);
		tableFee.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableFee.getColumnModel().getColumn(1).setPreferredWidth(93);

		
		btnApostatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableQueries.getSelectedRow()==-1) {
					lblmarked.setText("Select a question");
				}else {
					int i=tableQueries.getSelectedRow();
					domain.Question q=(domain.Question)tableModelQueries.getValueAt(i,1); // obtain q object
					CreateFeeGUI frame = new CreateFeeGUI(q);
					//				thisframe.setVisible(false);
					frame.setVisible(true);
				}
			}
		});
		btnApostatu.setBounds(new Rectangle(98, 420, 130, 30));
		btnApostatu.setBounds(476, 420, 130, 30);
		
		getContentPane().add(btnApostatu);
		
		JButton btnEmaitza = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MarkAsResult"));
		btnEmaitza.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {
				if(tableFee.getSelectedRow()==-1) {
					lblmarked.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectFee"));
				}else {
					int i=tableFee.getSelectedRow();
					domain.Kuotak k=(domain.Kuotak)tableModelFee.getValueAt(i,2);
					if(!k.getQuestion().hasSolution()) {

						facade.markResult(k);
						lblmarked.setText(ResourceBundle.getBundle("Etiquetas").getString("NoSolution"));
					}else {
						lblmarked.setText(ResourceBundle.getBundle("Etiquetas").getString("HasSolution"));				
					}
				}
			}
		});
		btnEmaitza.setBounds(271, 420, 173, 30);
		getContentPane().add(btnEmaitza);
		
		lblmarked = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		lblmarked.setBounds(292, 395, 165, 14);
		getContentPane().add(lblmarked);
		

		
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		thisframe.setVisible(false);
	}
}