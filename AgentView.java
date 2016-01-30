package acctMgr.view;

import acctMgr.controller.AgentController;
import acctMgr.controller.Controller;
import acctMgr.model.Agent;
import acctMgr.model.AgentStatus;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class AgentView extends JFrameView {
	public final static String Pause = "Stop Agent";
	public final static String Resume = "Dismiss";

	
	private JPanel topPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	
	private JLabel transferredLabel;
	private JLabel statusLabel;
	private JTextPane transferredField;
	private JTextPane statusField;
	private JLabel amountLabel;
	private JTextPane amountField;
	private JButton pauseButton;
	private JButton resumeButton;
	private JLabel operations;
	private JTextField operationsField;
	private JLabel opComplete;
	private JTextPane opCompField;
	double OPS = 1;
	private Handler handler = new Handler();
	
	public AgentView(Model model, Controller controller){
		super(model, controller);
		this.getContentPane().add(getContent());
		Toolkit toolkit =  Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		int x = (int) ((dim.getWidth() - this.getWidth()) * 0.5f);
	    int y = (int) ((dim.getHeight() - this.getHeight()) * 0.5f);
	    this.setLocation(x - 10, y - 10);
	    
		final Controller contr = controller;
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent evt) {
		         ((AgentController)contr).operation(Resume);
		    }
		});
		this.setTitle(((Agent)model).getName());
		pack();
	}
	private JPanel getContent() {
		if (topPanel == null) {
			topPanel = new JPanel();
			GridLayout layout = new GridLayout(2, 1);
			topPanel.setLayout(layout);
			topPanel.add(getTextFieldPanel(), null);
			topPanel.add(getButtonPanel(), null);
		}
		return topPanel;
	}
	
	private JPanel getTextFieldPanel()
	{
		if(textPanel == null){
			GridBagConstraints tl = new GridBagConstraints();
			tl.gridx = 0;
			tl.gridy = 3;
			
			GridBagConstraints tf = new GridBagConstraints();
			tf.gridx = 1;
			tf.gridy = 3;
			
			GridBagConstraints sl = new GridBagConstraints();
			sl.gridx = 0;
			sl.gridy = 2;
			
			GridBagConstraints sf = new GridBagConstraints();
			sf.gridx = 1;
			sf.gridy = 2;
			
			GridBagConstraints aml = new GridBagConstraints();
			aml.gridx = 0;
			aml.gridy = 0;
			
			GridBagConstraints amf = new GridBagConstraints();
			amf.gridx = 1;
			amf.gridy = 0;
			
			GridBagConstraints OP1 = new GridBagConstraints();
			OP1.gridx = 0;
			OP1.gridy = 1;
			
			GridBagConstraints OP2 = new GridBagConstraints();
			OP2.gridx = 1;
			OP2.gridy = 1;
			
			GridBagConstraints OP3 = new GridBagConstraints();
			OP3.gridx = 0;
			OP3.gridy = 4;
			
			GridBagConstraints OP4 = new GridBagConstraints();
			OP4.gridx = 1;
			OP4.gridy = 4;
			
			textPanel = new JPanel();
			textPanel.setLayout(new GridBagLayout());
			textPanel.add(getTransferredLabel(), tl);
			textPanel.add(getTransferredField(), tf);
			textPanel.add(getStatusLabel(), sl);
			textPanel.add(getStatusField(), sf);
			textPanel.add(getAmountLabel(), aml);
			textPanel.add(getAmountField(), amf);
			textPanel.add(getOPLabel(), OP1);
			textPanel.add(getOPField(), OP2);
			textPanel.add(getOPCompLabel(), OP3);
			textPanel.add(getOPCompField(), OP4);
		}
		return textPanel;
	}
	
	private JPanel getButtonPanel()
	{
		if(buttonPanel == null){
			GridBagConstraints pauseButtonCtr = new GridBagConstraints();
			pauseButtonCtr.gridx = 0;
			pauseButtonCtr.gridy = 0;
			
			GridBagConstraints resButtonCtr = new GridBagConstraints();
			resButtonCtr.gridx = 1;
			resButtonCtr.gridy = 0;
			
			GridBagConstraints dissButtonCtr = new GridBagConstraints();
			dissButtonCtr.gridx = 2;
			dissButtonCtr.gridy = 0;
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.add(getPauseButton(), pauseButtonCtr);
			buttonPanel.add(getResumeButton(), resButtonCtr);
			
		}
		
		return buttonPanel;
	}
	
	private JButton getPauseButton(){
		if(pauseButton == null){
			pauseButton = new JButton(Pause);
			//pauseButton.setEnabled(false);
			pauseButton.addActionListener(handler);
		}
		return pauseButton;
	}
	private JButton getResumeButton(){
		if(resumeButton == null){
			resumeButton = new JButton(Resume);
			resumeButton.setEnabled(false);
			resumeButton.addActionListener(handler);
		}
		return resumeButton;
	}
	
	private JLabel getTransferredLabel(){
		if(transferredLabel == null){
			transferredLabel = new JLabel();
			transferredLabel.setText("Transferred:");
			transferredLabel.setPreferredSize(new Dimension(200, 20));
		}
		return transferredLabel;
	}
	
	private JTextPane getTransferredField(){
		if(transferredField == null){
			transferredField = new JTextPane();
			transferredField.setText(Double.toString(((Agent)getModel()).getTransferred()));
			transferredField.setSize(400, 25);
			transferredField.setEditable(false);
		}
		return transferredField;
	}
	private JLabel getStatusLabel(){
		if(statusLabel == null){
			statusLabel = new JLabel();
			statusLabel.setText("Status:");
			statusLabel.setPreferredSize(new Dimension(200, 20));
		}
		return statusLabel;
	}
	
	private JTextPane getStatusField(){
		if(statusField == null){
			statusField = new JTextPane();
			statusField.setText(AgentStatus.Running.toString());
			statusField.setSize(200, 25);
			statusField.setEditable(false);
		}
		return statusField;
	}
	private JLabel getAmountLabel(){
		if(amountLabel == null){
			amountLabel = new JLabel();
			amountLabel.setText("Amount:");
			amountLabel.setPreferredSize(new Dimension(200, 20));
		}
		return amountLabel;
	}
	
	private JTextPane getAmountField(){
		if(amountField == null){
			amountField = new JTextPane();
			//amountField.setText(Double.toString(5));
			amountField.setText(Double.toString(((Agent)getModel()).getBalance()));
			amountField.setSize(200, 25);
			amountField.setEditable(false);
		}
		return amountField;
	}
	private JTextField getOPField(){
		if(operationsField == null){
			operationsField = new JTextField();
			operationsField.setText(Double.toString(OPS/60));
			operationsField.setSize(200, 25);
			operationsField.setEditable(false);
		}
		return operationsField;
	}
	private JLabel getOPLabel(){
		if(operations == null){
			operations = new JLabel();
			operations.setText("Operations per Sec:");
			operations.setPreferredSize(new Dimension(200, 20));
		}
		return operations;
	}
	private JTextPane getOPCompField(){
		if(opCompField == null){
			opCompField = new JTextPane();
			opCompField.setText(Double.toString(OPS));
			opCompField.setSize(200, 25);
			opCompField.setEditable(false);
		}
		return opCompField;
	}
	private JLabel getOPCompLabel(){
		if(opComplete == null){
			opComplete = new JLabel();
			opComplete.setText("Operations completed:");
			opComplete.setPreferredSize(new Dimension(200, 20));
		}
		return opComplete;
	}
	public void setPaused(boolean paused){
		resumeButton.setEnabled(paused);
		pauseButton.setEnabled(!paused);
	}
	
	public void modelChanged(ModelEvent me){
		
		System.out.println("AgentView.modelChanged invoked " + me.getAgStatus().toString());
		ModelEvent.EventKind kind = me.getKind();
		
		if(kind == ModelEvent.EventKind.AmountTransferredUpdate) {
			System.out.println("Balance field to " + me.getBalance());
			transferredField.setText(Double.toString(me.getBalance()));
			
		}
		
		else if(kind == ModelEvent.EventKind.AgentStatusUpdate) {
			AgentStatus agSt = me.getAgStatus();
			System.out.println("Status to " + agSt.toString());
			if(agSt == AgentStatus.Paused) setPaused(true);
			else setPaused(false);
			statusField.setText(agSt.toString());
		}
		if(kind == ModelEvent.EventKind.AmountBalanceUpdate) {
			System.out.println("Balance field to " + me.getBalance());
			amountField.setText(Double.toString(me.getBalance()));
			
		}
		while(kind == ModelEvent.EventKind.AmountTransferredUpdate)
		{ 
		OPS  += 1;
		System.out.println(OPS);
		break;
		}
		if(kind == ModelEvent.EventKind.AmountOPUpdate) {
			System.out.println("Balance field to " + me.getBalance());
			opCompField.setText(Double.toString(OPS));
			operationsField.setText(Double.toString(OPS/60));
			
		}
	}
	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			((AgentController)getController()).operation(evt.getActionCommand());
		}
	}
	
	
}
