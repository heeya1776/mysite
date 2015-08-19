package com.bit2015.mysite.action.guestbook;

import com.bit2015.web.action.Action;
import com.bit2015.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if( "add".equals( actionName ) ) {
			action = new AddAction();
		} else if( "delete".equals( actionName ) ) {
			action = new DeleteAction();
		} else if( "deleteform".equals( actionName ) ) {
			action = new DeleformAction();
		} else {
			action = new IndexAction();
		}
		
		return action;
	}
}
