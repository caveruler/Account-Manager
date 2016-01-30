package acctMgr.view;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import acctMgr.controller.*;
import acctMgr.model.*;


import javax.swing.JOptionPane;

import java.awt.BorderLayout;
//import view.AccountFrame;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
public class AccountWindowView extends JFrameView{
	
		public final static String Save = "Save";
		public final static String Exit = "Exit";
		public final static String Dollars = "Edit Account In Dollars";
		public final static String Euros = " Edit Account In Euros";
		public final static String Yen = "Edit Account In Yen";
		public final static String StartDepAgent = "StartDepAgent";
		public final static String StartWithdrawAgent = "StartWithdrawAgent";
		private JPanel topPanel;
		private JPanel textPanel;
		private JPanel buttonPanel;
		//public JTextPane amountField;
		private JButton dollarButton;
		private JButton euroButton;
		private JButton yenButton;
		private JButton saveButton;
		private JButton ExitButton;
		private JButton startDepAgentButton;
		private JButton startWithdrawAgentButton;
		private Handler handler = new Handler();
		private List<AgentController> agentContrs = new ArrayList<AgentController>(10);
	//	private static JComboBox comboBox;
	//	private static JTextField textField;
		static String labels[] = { "Enter Account Name/ID" };
		Object args[];
	    static String title =  labels[0] ;
	   // JFrame frame = new JFrame(title);
	    static JComboBox comboBox1 = new JComboBox(labels);
	    //Object JComboBox.getSelectedItem();
	    //String varName = (String)comboBox1.getSelectedItem();
	    
	   // comboBox1.setMaximumRowCount(5);
		public AccountWindowView(Model model, Controller controller) {
			super(model, controller);
			this.getContentPane().add(getContent());
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension dim = toolkit.getScreenSize();
			int x = (int) ((dim.getWidth() - this.getWidth()) * 0.5f);
		    int y = (int) ((dim.getHeight() - this.getHeight()) * 0.5f);
		    this.setLocation(x, y);
		    
		   // String[] options = { "Option1", "Option2", "Option3", "Option4", "Option15" };
	        //comboBox = new JComboBox(options);
	       // comboBox.addActionListener(new ActionListener() {

	         //   @Override
	        //    public void actionPerformed(ActionEvent e) {
	                // Do something when you select a value

	         //   }
	       // });
		    addWindowListener(new java.awt.event.WindowAdapter() {
			    public void windowClosing(java.awt.event.WindowEvent evt) {
			    	for(AgentController agContr : agentContrs) agContr.operation(AgentView.Resume);
			    	BuildAgent.finishThreads();
			        dispose();
			        System.exit(0);
			    }
			});
			pack();
			 
		}
		
		private JPanel getContent() {
			if (topPanel == null) {
				topPanel = new JPanel();
				GridLayout layout = new GridLayout(2, 2);
				topPanel.setLayout(layout);
				//topPanel.setPreferredSize(new Dimension(300, 100));
				GridBagConstraints ps = new GridBagConstraints();
				ps.gridx = 0;
				ps.gridy = 0;
				ps.fill = GridBagConstraints.HORIZONTAL;
				
				GridBagConstraints bs = new GridBagConstraints();
				bs.gridx = 0;
				bs.gridy = 1;
				//topPanel.add(getTextFieldPanel(), null);
				topPanel.add(getButtonPanel(), null);
				this.getContentPane().add(comboBox1, BorderLayout.NORTH);
			    comboBox1.setEditable(true);
				
			}
			return topPanel;
		}
		private JPanel getTextFieldPanel()
		{
			if(textPanel == null){
				GridBagConstraints tl = new GridBagConstraints();
				tl.gridx = 0;
				tl.gridy = 0;
				
				GridBagConstraints tf = new GridBagConstraints();
				tf.gridx = 1;
				tf.gridy = 0;
				
				GridBagConstraints sl = new GridBagConstraints();
				sl.gridx = 0;
				sl.gridy = 1;
				
				GridBagConstraints sf = new GridBagConstraints();
				sf.gridx = 1;
				sf.gridy = 1;
				
				textPanel = new JPanel();
				textPanel.setLayout(new GridBagLayout());
				//textPanel.setPreferredSize(new Dimension(250, 150));
				//textPanel.add(getTransferredLabel(), tl);
				 // Textfield
		        JLabel labelTextField = new JLabel("Bank account number");
		       // textField1 = new JTextField();
		        JLabel labelCombo = new JLabel("Bank Code");
		        // Add labels
		      //  textField.add(labelCombo,sl);
		     //   textField.add(labelTextField,sf);

		        // Add fields
		     //   textField.add(comboBox,tl);
		       // textField.add(textField1);
			}
			return textPanel;
		}
		
		private JButton getdollarButton(){
			if(dollarButton == null)
			{
				dollarButton = new JButton(Dollars);
				dollarButton.addActionListener(handler);
			}
			return dollarButton;
		}
		private JButton geteuroButton(){
			if(euroButton == null)
			{
				euroButton = new JButton(Euros);
				euroButton.addActionListener(handler);
			}
			return euroButton;
		}
		private JButton getYenButton(){
			if(yenButton == null)
			{
				yenButton = new JButton(Yen);
				yenButton.addActionListener(handler);
			}
			return yenButton;
		}
		private JButton getSaveButton(){
			if(saveButton == null)
			{
				saveButton = new JButton(Save);
				saveButton.addActionListener(handler);
			}
			return saveButton;
		}
		private JButton getExitButton(){
			if(ExitButton == null)
			{
				ExitButton = new JButton(Exit);
				ExitButton.addActionListener(handler);
			}
			return ExitButton;
		}
		private JButton getDepAgentButton(){
			if(startDepAgentButton == null){
				startDepAgentButton = new JButton(StartDepAgent);
				startDepAgentButton.addActionListener(handler);
			}
			return startDepAgentButton;
		}
		private JButton getWithdrawAgentButton(){
			if(startWithdrawAgentButton == null){
				startWithdrawAgentButton = new JButton(StartWithdrawAgent);
				startWithdrawAgentButton.addActionListener(handler);
			}
			return startWithdrawAgentButton;
		}
		private JPanel getButtonPanel()
		{
			if(buttonPanel == null){
				GridBagConstraints DollarButtonCtr = new GridBagConstraints();
				DollarButtonCtr.gridx = 2;
				DollarButtonCtr.gridy = 4;
				
				GridBagConstraints EuroButtonCtr = new GridBagConstraints();
				EuroButtonCtr.gridx = 3;
				EuroButtonCtr.gridy = 4;
				
				GridBagConstraints YenButtonCtr = new GridBagConstraints();
				YenButtonCtr.gridx = 4;
				YenButtonCtr.gridy = 4;
		
				GridBagConstraints SaveButtonCtr = new GridBagConstraints();
				SaveButtonCtr.gridx = 2;
				SaveButtonCtr.gridy = 2;
				
				GridBagConstraints ExitButtonCtr = new GridBagConstraints();
				ExitButtonCtr.gridx = 4;
				ExitButtonCtr.gridy = 2;
				
				GridBagConstraints depAgButtonCtr = new GridBagConstraints();
				depAgButtonCtr.gridx = 2;
				depAgButtonCtr.gridy = 6;
				
				GridBagConstraints wAgButtonCtr = new GridBagConstraints();
				wAgButtonCtr.gridx = 4;
				wAgButtonCtr.gridy = 6;
				
				buttonPanel = new JPanel();
				buttonPanel.setLayout(new GridBagLayout());
				buttonPanel.add(getdollarButton(), DollarButtonCtr);
				buttonPanel.add(geteuroButton(), EuroButtonCtr);
				buttonPanel.add(getYenButton(), YenButtonCtr);
				buttonPanel.add(getSaveButton(), SaveButtonCtr);
				buttonPanel.add(getExitButton(), ExitButtonCtr);
				buttonPanel.add(getDepAgentButton(), depAgButtonCtr);
				buttonPanel.add(getWithdrawAgentButton(), wAgButtonCtr);
			}
			return buttonPanel;
		}
		
		private class Handler implements ActionListener {
			public void actionPerformed(ActionEvent evt) {
			
					try {
						((AccountWindowController)getController()).operation(evt.getActionCommand());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
			}
		}
		public void showError(String msg) {
			JOptionPane.showMessageDialog(this, msg);
		}
		
		
		
		public AgentView createAgentView1(Agent ag, AgentController agContr){
			AgentView app = new AgentView(ag, agContr);
	  	  	agContr.setView(app);
	  	  	agentContrs.add(agContr);
	  	  	app.setVisible(true);
	  	  	return app;
		}
	
		public AgentView createAgentView(Agent ag, AgentController agContr) {
			AgentView app = new AgentView(ag, agContr);
	  	  	agContr.setView(app);
	  	  	agentContrs.add(agContr);
	  	  	app.setVisible(true);
	  	  	return app;
			
		}
		public String getTitle() {
			String Name = comboBox1.getSelectedItem().toString();
			System.out.println(Name);
			return Name;
			}
		public double getAmount() {
			double amount = Account.getAmount();
			//double amount = 3;
			// no checking for parsing errors
			//amount = Double.parseDouble(amountField.getText());
			return amount;
		}

		@Override
		public void modelChanged(ModelEvent me) {
			// TODO Auto-generated method stub
			
		}
		}
		
//}
