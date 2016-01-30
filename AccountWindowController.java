package acctMgr.controller;

import acctMgr.model.*;
import acctMgr.view.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.SwingUtilities;
public class AccountWindowController extends AbstractController {
double balanceset = 300;
double balance = Account.getBalance();
double yen = balance * 94.1;
double euros = balance * .79;
static double choice = 0;
final Account account = new Account(balance);
static String Name;
final AccountView acView1 = (AccountView)getView();

	public void operation(String opt) throws FileNotFoundException  {
		final AccountWindowView NameView = (AccountWindowView)getView();
		 Name = NameView.getTitle();
		if(opt == AccountWindowView.Dollars) {
			choice = 1;
			//final Account account = new Account(balance);
			final AccountController contr = new AccountController();
			contr.getTitle();
			contr.setModel(account);
		   SwingUtilities.invokeLater(new Runnable() {
		     public void run() {
		   	  AccountView app = new AccountView(account, contr);
		   	  app.getTitle();
		    	 contr.setView(app);
		      app.setVisible(true);
		     }
		   });
			
		} else if(opt == AccountWindowView.Euros) {
			choice = 2;
			final Account account = new Account(euros);
			final AccountController contr = new AccountController();
			contr.getTitle();
			contr.setModel(account);
			double amount = Account.getBalance() * .79;
		   SwingUtilities.invokeLater(new Runnable() {
		     public void run() {
		   	  AccountView app = new AccountView(account, contr);
		    	 contr.setView(app);
		      app.setVisible(true);
		     }
		   });
			}else if(opt == AccountWindowView.Yen) {
				choice = 3;
			
				final Account account = new Account(yen);
				final AccountController contr = new AccountController();
				contr.setModel(account);
				contr.getTitle();
			   SwingUtilities.invokeLater(new Runnable() {
			     public void run() {
			    	 double amount = account.getBalance() * 94.1;
			   	  AccountView app = new AccountView(account, contr);
			   	  app.getTitle();
			    	 contr.setView(app);
			      app.setVisible(true);
			     }
			   });
		} else if (opt == AccountWindowView.Save){
			String file = "accountSave";
			PrintWriter writer;
			
				writer = new PrintWriter(file);
				final AccountWindowView acView = (AccountWindowView)getView();
				writer.println(acView.getTitle());
				writer.println("Balance "+Account.getBalance());
				writer.close();
			
		} else if (opt == AccountWindowView.Exit){
			System.exit(0);
		} else if(opt == AccountWindowView.StartDepAgent) {
			final AccountWindowView acView = (AccountWindowView)getView();
			//double amount = 3;
			double amount = acView.getAmount();
			final Agent ag = BuildAgent.createDepAgent(((Account)getModel()), amount );
			final AgentController agContr = new AgentController();
			
			agContr.setModel(ag);
			SwingUtilities.invokeLater(new Runnable() {
			      public void run() {
			    	  acView.createAgentView(ag, agContr);
			      }
			    });
		} else if(opt == AccountWindowView.StartWithdrawAgent) {
			
			final AccountWindowView acView = (AccountWindowView)getView();
			double amount = acView.getAmount();
			//double amount = 3;
			final Account accnt = (Account)getModel();
			final Agent ag = BuildAgent.createWithdrawAgent(((Account)getModel()), amount);
			final AgentController agContr = new AgentController();
			agContr.setModel(ag);
			SwingUtilities.invokeLater(new Runnable() {
			      public void run() {
			    	  AgentView app = acView.createAgentView(ag, agContr);
			    	  accnt.addModelListener(app);
			      }
			    });
			/*
		//	final Account account = new Account();
			final AgentController contr = new AgentController();
			contr.setModel(account);
		   SwingUtilities.invokeLater(new Runnable() {
		     public void run() {
		   	  AgentView app = new AgentView(account, contr);
		    	 contr.setView(app);
		      app.setVisible(true);
		     }
		   });*/
		}
		
	}
	public static double getChoice(){return choice;}
	public static String getTitle()
	{
		
		return Name;
	}
}

