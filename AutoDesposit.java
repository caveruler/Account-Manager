package acctMgr.model;

import javax.swing.SwingUtilities;

public class AutoDesposit extends AbstractModel implements Runnable, Agent {
	private Object pauseLock;
	private boolean paused;
	public volatile boolean active;
	private Account account;
	private double amount;
	private double transferred;
	private String name = new String("Default");
	private AgentStatus status;
	public double balance = Account.getBalance() ;
	public double op;
	
	public AutoDesposit(Account account, double amount){
		this.account = account;
		this.amount = amount;
		this.transferred = 0;
		this.op = 0;
		this.status = AgentStatus.Running;
		this.active = true;
		this.paused = false;
		this.pauseLock = new Object();
	//	this.balance = 0;
		
	}
	
	public void run() {
		while(active) {
			synchronized (pauseLock) {
                while (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                    	System.out.println("Thread " + Thread.currentThread().getName() + " interrupted");
                    }
                }
            }
			account.deposit(amount);
			this.balance += amount ;
			this.op += 1;
			this.transferred += amount;
			final ModelEvent me = new ModelEvent(ModelEvent.EventKind.AmountTransferredUpdate, transferred, AgentStatus.NA);
			final ModelEvent me2 = new ModelEvent(ModelEvent.EventKind.AmountBalanceUpdate, balance, AgentStatus.NA);
			final ModelEvent me3 = new ModelEvent(ModelEvent.EventKind.AmountOPUpdate, op, AgentStatus.NA);
			SwingUtilities.invokeLater(
					new Runnable() {
					    public void run() {
					    
					    	notifyChanged(me);
					    	notifyChanged(me2);
					    	notifyChanged(me3);
					    }
					});
			try {
				Thread.sleep(500);
			}
			catch(InterruptedException ex){
				System.out.println("Thread " + Thread.currentThread().getName() + " interrupted");
			}
		}
	}
	public double getTransferred(){return transferred;}
	public double getAmount() { return amount;}
	public double getBalance() { return balance;}
//	public double getOperations() { return operations;}
	public void onPause() {
        synchronized (pauseLock) {
            paused = true;
            setStatus(AgentStatus.Paused);
        }
    }

    public void onResume() {
        synchronized (pauseLock) {
            paused = false;
            setStatus(AgentStatus.Running);
            pauseLock.notify();
        }
    }
    public void setStatus(AgentStatus agSt) {
    	status = agSt;
    	final ModelEvent me = new ModelEvent(ModelEvent.EventKind.AgentStatusUpdate, 0.0, agSt);
    	SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
    }
    public AgentStatus getStatus(){return status;}
    public void setName(String name) {this.name = name;}
    public String getName(){return name;}
    public Account getAccount(){return account;}
    public void finish(){
    	active = false;
    }

	
}
