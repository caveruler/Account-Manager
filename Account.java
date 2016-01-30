package acctMgr.model;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import acctMgr.view.AccountView;
import acctMgr.controller.*;
public class Account extends AbstractModel {
	public static double balance;
	public static  JTextPane amount = AccountView.getAmountField();
	public Account(double balance){
		
		double choice = AccountWindowController.getChoice();
		if (choice == 1)
		{
			Account.balance = balance;
		} else if (choice == 2)
		{
			Account.balance = balance * .79;
		} else if ( choice == 3)
		{
			Account.balance = balance * 94.1;
		} else
		{
			Account.balance = balance;
		}
	}
	public static double getBalance(){return balance;}
	public static  double getAmount() {return Double.parseDouble(amount.getText());}
	public synchronized void deposit(double amount) {
		double oldB = balance;
		balance += amount;
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, balance, AgentStatus.NA);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
	}
	
	public synchronized void withdraw(double amount) throws OverdrawException {
		double newB = balance - amount;
		if(newB < 0.0) throw new OverdrawException(newB);
		balance = newB;
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, balance, AgentStatus.NA);
		
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
	}
	public synchronized void autoWithdraw(double amount, Agent ag) {
		try {
			System.out.println("Trying to withdraw " + amount + " from balance " + balance);
			
			//if(balance - amount < 0.0) {
				//System.out.println("Sending blocked");
				//ag.setStatus(AgentStatus.Blocked);		
			//}
			
			while(Account.balance - amount < 0.0) {
				ag.setStatus(AgentStatus.Blocked);	
				wait();
			}
			if(ag.getStatus() == AgentStatus.Paused) return;
			ag.setStatus(AgentStatus.Running);
					
			Account.balance -= amount;
			final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, Account.balance, AgentStatus.Running);
			SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		}
		catch(InterruptedException ex){
			System.out.println("Thread " + Thread.currentThread().getName() + " interrupted");
		}
		/*
		catch(InvocationTargetException ex){
			System.out.println("Thread " + Thread.currentThread().getName() + " " + ex.getMessage());
			ex.printStackTrace();
		}
		*/
	}
	
}

