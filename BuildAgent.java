package acctMgr.model;

import java.util.List;

import acctMgr.controller.AccountWindowController;
import acctMgr.view.AccountWindowView;

import java.util.ArrayList;
public class BuildAgent {
	
	private static List<Thread> agentThreads = new ArrayList<Thread>(10);
	public static Agent createDepAgent(Account account, double amount) {
		AutoDesposit depAg = new AutoDesposit(account, amount);
		Thread depAgT = new Thread(depAg);
		String name = "Edit Account In " + AccountWindowController.getTitle();
		depAg.setName(name);
		depAgT.setName(name);
		agentThreads.add(depAgT);
		depAgT.start();
		return depAg;
	}
	public static Agent createWithdrawAgent(Account account, double amount) {
		AutoWIthdraw wAg = new AutoWIthdraw(account, amount);
		Thread wAgT = new Thread(wAg);
		String name = "Edit Account In " + AccountWindowController.getTitle();
		wAg.setName(name);
		wAgT.setName(name);
		agentThreads.add(wAgT);
		wAgT.start();
		return wAg;
	}
	public static void finishThreads(){
		for(Thread t: agentThreads) {
				System.out.println("Finishing thread " + Thread.currentThread().getName());
				t.interrupt();
			}
	}
}
