package com.bit2015.mysite.action.board;

import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("view".equals(actionName)){
			action = new BoardViewAction();
		} else if("modifyform".equals(actionName)){
			action = new ModifyFormAction();
		} else if("writeform".equals(actionName)){
			action = new WriteFormAction();
		} else if("add".equals(actionName)){
			action = new AddAction();
		} else if("change".equals(actionName)){
			action = new ChangeAction();
		} else if("delete".equals(actionName)){
			action = new DeleteAction();
		} else if("search".equals(actionName)){
			action = new SearchAction();
		} else {
			action = new BoardIndexAction();
		}
		
		return action;
	}

}
