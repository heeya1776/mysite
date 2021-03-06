package com.bit2015.mysite.action.user;

import com.bit2015.mysite.action.index.IndexAction;
import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		Action action = null;
		
		if("joinform".equals(actionName)){
			action = new JoinFormAction();
		} else if("loginform".equals(actionName)){
			action = new LoginFormAction();
		} else if("join".equals(actionName)){
			action = new JoinAction();
		} else if("joinsuccess".equals(actionName)){
			action = new JoinSuccessAction();
		} else if("modifyform".equals(actionName)){
			action = new ModifyFormAction();
		} else if("loginform".equals(actionName)){
			action = new LoginFormAction();
		} else if("login".equals(actionName)){
			action = new LoginAction();
		} else if("logout".equals(actionName)){
			action = new LogoutAction();
		} else if("checkemail".equals(actionName)){
			action = new CheckEmailAction();
		} else {
			action = new IndexAction();
		}
		return action;
	}

}
