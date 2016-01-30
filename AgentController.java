package acctMgr.controller;

import acctMgr.model.Agent;
import acctMgr.model.Account;
import acctMgr.model.AutoDesposit;
import acctMgr.model.AutoWIthdraw;
import acctMgr.view.AgentView;

import javax.swing.SwingUtilities;
public class AgentController extends AbstractController {
	public void operation(String opt) {
		if(opt == AgentView.Pause) {
			((Agent)getModel()).onPause();
			//((AgentView)getView()).setPaused(true);
		} else if(opt == AgentView.Resume) {
			((Agent)getModel()).onResume();
			Agent ag = (Agent)getModel();
			AgentView agView = (AgentView)getView();
			if(ag instanceof AutoWIthdraw) {
				Account account = ag.getAccount();
				account.removeModelListener(agView);
			}
			ag.finish();
			agView.dispose();
			//((AgentView)getView()).setPaused(false);
		} 
	}
}
