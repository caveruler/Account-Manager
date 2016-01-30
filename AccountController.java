package acctMgr.controller;

import acctMgr.model.Account;
import acctMgr.model.Agent;
import acctMgr.model.BuildAgent;
import acctMgr.model.OverdrawException;
import acctMgr.model.AutoWIthdraw;
import acctMgr.view.AccountView;
import acctMgr.view.AccountWindowView;
import acctMgr.view.AgentView;

import javax.swing.SwingUtilities;

public class AccountController extends AbstractController {
	static String Name;
	
	 
	public String getTitle()
	{
		Name = AccountWindowController.getTitle();
		return Name;
	}
	public void operation(String opt) {
		if(opt == AccountView.Deposit) {
			double amount = ((AccountView)getView()).getAmount();
			((Account)getModel()).deposit(amount);
		} else if(opt == AccountView.Withdraw) {
			double amount = ((AccountView)getView()).getAmount();
			try {
				((Account)getModel()).withdraw(amount);
			}
			catch(OverdrawException ex) {
				final String msg = ex.getMessage();
				SwingUtilities.invokeLater(new Runnable() {
				      public void run() {
				    	  ((AccountView)getView()).showError(msg);
				      }
				    });
			}
		}else if(opt == AccountView.Dismiss) {
			final AccountView acView = (AccountView)getView();
			acView.dispose();
		
		}
	}
}
