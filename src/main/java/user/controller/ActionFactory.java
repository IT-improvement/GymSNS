package user.controller;

import user.controller.action.LoginAction;
import user.controller.action.TestAction;
import user.controller.action.UserCreateAction;

public class ActionFactory {

	private ActionFactory() {
		
	}
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("create")) 
			action = new UserCreateAction();
		else if(command.equals("test"))
			action = new TestAction();
		
		return action;
	}
}
