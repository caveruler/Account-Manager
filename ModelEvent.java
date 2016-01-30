package acctMgr.model;

public class ModelEvent {
	public enum EventKind {
		BalanceUpdate, AgentStatusUpdate, AmountTransferredUpdate, AmountBalanceUpdate, AmountOPUpdate
	}
	private EventKind kind;
	private double balance /*= Account.getBalance()*/;
	private double amount /*= Account.getBalance()*/;
	private AgentStatus agSt;
	public ModelEvent(EventKind kind, double balance, AgentStatus agSt){
		this.balance = balance;
		this.kind = kind;
		this.agSt = agSt;
	}
	public EventKind getKind(){return kind;}
	public double getBalance(){
		return balance;
	}
	public AgentStatus getAgStatus(){return agSt;}
	public double getAmount() {
		
		return amount;
	}
}
